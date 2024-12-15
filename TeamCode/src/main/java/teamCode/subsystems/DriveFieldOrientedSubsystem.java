package teamCode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class DriveFieldOrientedSubsystem extends SubsystemBase
{
    public MecanumDrive m_drive;
    private DcMotor m_fLMotor;
    private DcMotor m_fRMotor;
    private DcMotor m_bLMotor;
    private DcMotor m_bRMotor;
    private int m_fLPos;
    private int m_fRPos;
    private int m_bLPos;
    private int m_bRPos;
    private Orientation m_lastRecordedAngle;
    private double m_currentAngle;
    private MecanumDrive m_robot;



    BNO055IMU m_imu;



    public DriveFieldOrientedSubsystem(MecanumDrive drive)  //Constructor
    {
        //m_robot.inti(hardwareMap);

        this.m_drive = drive;
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        m_imu = hardwareMap.get(BNO055IMU.class, "m_imu");
        m_imu.initialize(parameters);
        this.m_lastRecordedAngle = new Orientation();
        this.m_robot = drive;
        this.m_currentAngle = 0.0;

    }

    public void headingDrive(double leftX, double leftY, double rightX, double imu)
    {

    }

    public void driveRobot(int fL, int fR, int bL, int bR)
    {

    }

    public void resetAngle()
    {
        m_lastRecordedAngle = m_imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, BNO055IMU.AngleUnit.DEGREES.toAngleUnit());
        m_currentAngle = 0;
    }

    public double getAngle()
    {
        Orientation orientation = m_imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = orientation.firstAngle - m_lastRecordedAngle.firstAngle;

        if (deltaAngle > 180)
        {
            deltaAngle -= 360;
        } else if (deltaAngle <= -180)
        {
            deltaAngle += 360;
        }

        m_currentAngle += deltaAngle;
        m_lastRecordedAngle = orientation;
        return m_currentAngle;
    }

    public void turn(double degrees)
    {
       resetAngle();

        double error = degrees;

        while (Math.abs(error) > 2)
        {
            double motorPower= (error < 0 ? -0.3 : 0.3);
            error = degrees - getAngle();
            telemetry.addData("error", error);
            telemetry.update();
        }
        m_drive.stop();
    }

    private void setAllPower(int i)
    {
    }

    public void turnTo(double degrees)
    {
        Orientation orientation = m_imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double error = degrees - orientation.firstAngle;

        if(error > 180)
        {
            error -= 360;
        }
        else if (error < -180)
        {
            error += 360;
        }

        turn(error);


    }

}
/* Robot Test Run
waitForStart();
turn(90);
sleep(3000);
turnTo(-90);
 */

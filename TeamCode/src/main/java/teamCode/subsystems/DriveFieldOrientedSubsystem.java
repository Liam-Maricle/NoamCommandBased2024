package teamCode.subsystems;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;

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



    IMU m_imu;
    IMU.Parameters m_imuParam;
//    Orientation m_imu;



    public DriveFieldOrientedSubsystem(MecanumDrive drive, IMU imu)  //Constructor
    {
        //m_robot.inti(hardwareMap);

        this.m_drive = drive;
//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
//        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
//        parameters.loggingEnabled = true;
//        parameters.loggingTag = "IMU";
//        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

//        m_imu = imu;hardwareMap.get(BNO055IMU.class, "m_imu");
//        this.m_imuParam = new IMU.Parameters();
//        m_imu.initialize()
        this.m_imu = imu;
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
        m_lastRecordedAngle = m_imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES); // .getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, BNO055IMU.AngleUnit.DEGREES.toAngleUnit());
        m_currentAngle = 0;
    }

    public double getAngle()
    {
        Orientation orientation = this.m_imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

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

    public void turn(double degrees, double desiredAngle)
    {
        resetAngle();

        double error = degrees;

        while (Math.abs(error) > 6)
        {
            double motorPower = (error < 0 ? -0.3 : 0.3);
            error = degrees - getAngle();
            this.m_robot.driveWithMotorPowers(motorPower, -motorPower, motorPower, -motorPower);
//            telemetry.addData("error", error);
//            telemetry.update();
            System.out.println("Desired Angle: " + desiredAngle);
            System.out.println("Error: " + error);
            System.out.println("Current Angle: " + this.m_imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        }
        m_drive.stop();
    }

    private void setAllPower(int i)
    {
    }

    public void turnTo(double rightX, double rightY)
    {
        Orientation orientation = m_imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double desiredAngle = Math.atan2(rightY, rightX) * (180 / Math.PI);
//        angle = atan2(y, x) * (180 / pi)

        double error = desiredAngle - orientation.firstAngle;

        if(error > 180)
        {
            error -= 360;
        }
        else if (error < -180)
        {
            error += 360;
        }

//        turn(error, desiredAngle);
//        turn(error, desiredAngle);

//        System.out.println(error);
//        System.out.println(desiredAngle);
//        System.out.println("Right X: " +
//        rightX + "Right Y: " + rightY);
        turn(error + 90, desiredAngle);
        System.out.println(error + 90);
    }


}

package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.function.DoubleSupplier;

import teamCode.subsystems.DriveSubsystem;

public class DriveFieldOrientedCommand extends CommandBase
{
    public DriveSubsystem m_driveSubsystem;

    public DoubleSupplier m_leftX;
    public DoubleSupplier m_leftY;
    public DoubleSupplier m_rightX;
    public DoubleSupplier m_rightY;

    public MecanumDrive m_drive;

    private Orientation m_lastRecordedAngle;
    private double m_currentAngle;
    private double error;


    IMU m_imu;

    public DriveFieldOrientedCommand(DriveSubsystem driveSubsystem, DoubleSupplier leftX, DoubleSupplier leftY, DoubleSupplier rightX, DoubleSupplier rightY, IMU imu, MecanumDrive drive)  //Constructor
    {
        this.m_driveSubsystem = driveSubsystem;
        addRequirements(m_driveSubsystem);

        this.m_leftX = leftX;
        this.m_leftY = leftY;
        this.m_rightX = rightX;
        this.m_rightY = rightY;

        this.m_imu = imu;
        this.m_lastRecordedAngle = new Orientation();
        this.m_currentAngle = 0.0;
        this.m_drive = drive;

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
        }
        else if (deltaAngle <= -180)
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

        error = degrees;
    }

    public void turnTo(double rightX, double rightY)
    {
        Orientation orientation = m_imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double desiredAngle;
        if (rightX > 0.5 || rightX < -0.5 || rightY > 0.5 || rightY < -0.5)
        {
            desiredAngle = Math.atan2(rightX, rightY * -1) * -1 * (180 / Math.PI);
        }
        else
        {
            desiredAngle = orientation.firstAngle;
        }

        error = desiredAngle - orientation.firstAngle;

        if(error > 180)
        {
            error -= 360;
        }
        else if (error < -180)
        {
            error += 360;
        }

        turn(error, desiredAngle);
    }

    @Override
    public void initialize()
    {
    }

    @Override
    public void execute()
    {
        this.m_drive.driveFieldCentric


                (
                        this.m_leftX.getAsDouble() * this.m_leftX.getAsDouble() * this.m_leftX.getAsDouble() * -1,
                        this.m_leftY.getAsDouble() * this.m_leftY.getAsDouble() * this.m_leftY.getAsDouble() * -1,
                        test(),
                        this.m_imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES)
                );
//        this.m_driveSubsystem.headingDrive(this.m_leftX.getAsDouble(), this.m_leftY.getAsDouble(), this.m_rightX.getAsDouble(), this.m_rightY.getAsDouble());

    }
    public double test()
    {
        turnTo(this.m_rightX.getAsDouble(), this.m_rightY.getAsDouble());
        System.out.println("Running!");

        if (Math.abs(error) > 6)
        {
            double motorPower = 0.5;
            error = error - getAngle();
//            this.m_robot.driveWithMotorPowers(motorPower, -motorPower, motorPower, -motorPower);
//            this.m_drive.driveWithMotorPowers(motorPower, -motorPower, motorPower, -motorPower);
            return motorPower * error / 100 + (0.1 * (error / Math.abs(error)));
        }
        else
        {
            return 0.0;
        }
    }
}

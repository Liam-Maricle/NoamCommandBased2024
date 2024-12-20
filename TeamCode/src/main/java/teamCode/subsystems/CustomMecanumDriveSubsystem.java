package teamCode.subsystems;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.geometry.Vector2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class CustomMecanumDriveSubsystem extends MecanumDrive
{
    public CustomMecanumDriveSubsystem(Motor frontLeft, Motor frontRight, Motor backLeft, Motor backRight)
    {
        super(frontLeft, frontRight, backLeft, backRight);
    }

    public void customFieldCentric(double strafeSpeed, double forwardSpeed, double turnSpeed, double gyroAngle)
    {
        super.driveFieldCentric(strafeSpeed, forwardSpeed, turnSpeed, gyroAngle );
//        strafeSpeed = clipRange(strafeSpeed);
//        forwardSpeed = clipRange(forwardSpeed);
//
//        Vector2d input = new Vector2d(strafeSpeed, forwardSpeed);
//        input = input.rotateBy(-gyroAngle);
//
//        double theta = input.angle();
//
//        double[] wheelSpeeds = new double[4];
//        wheelSpeeds[MotorType.kFrontLeft.value] = Math.sin(theta + Math.PI / 4);
//        wheelSpeeds[MotorType.kFrontRight.value] = Math.sin(theta - Math.PI / 4);
//        wheelSpeeds[MotorType.kBackLeft.value] = Math.sin(theta - Math.PI / 4);
//        wheelSpeeds[MotorType.kBackRight.value] = Math.sin(theta + Math.PI / 4);
//
//        normalize(wheelSpeeds, input.magnitude());
//
//        normalize(wheelSpeeds);
//
//        driveWithMotorPowers(
//                wheelSpeeds[MotorType.kFrontLeft.value],
//                wheelSpeeds[MotorType.kFrontRight.value],
//                wheelSpeeds[MotorType.kBackLeft.value],
//                wheelSpeeds[MotorType.kBackRight.value]
//        );
    }

    @Override
    public void driveWithMotorPowers(double frontLeftSpeed, double frontRightSpeed,
                                     double backLeftSpeed, double backRightSpeed)
    {
        super.driveWithMotorPowers(frontLeftSpeed, frontRightSpeed, backLeftSpeed, backRightSpeed);
    }
}

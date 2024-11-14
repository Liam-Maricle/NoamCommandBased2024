package teamCode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.ftccommon.internal.manualcontrol.parameters.ImuParameters;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import teamCode.commands.DriveFieldOrientedCommand;
import teamCode.commands.PivotIntakeCommand;
import teamCode.commands.ResetGyroCommand;
import teamCode.commands.SpinIntakeCommand;
import teamCode.subsystems.DriveSubsystem;
import teamCode.subsystems.GyroSubsystem;
import teamCode.subsystems.IntakePivotSubsystem;
import teamCode.subsystems.IntakeWheelSubsystem;


@TeleOp(name = "Sample TeleOp")
public class RobotContainer extends CommandOpMode
{
   private DriveSubsystem m_driveSubsystem;
   private IntakeWheelSubsystem m_intakeWheelSubsystem;
   private IntakePivotSubsystem m_intakePivotSubsystem;
   private GyroSubsystem m_gyroSubsystem;
   private DriveFieldOrientedCommand m_driveFieldOrientedCommand;
   private PivotIntakeCommand m_pivotIntakeCommand;
   private SpinIntakeCommand m_spinIntakeCommand;
   private ResetGyroCommand m_resetGyroCommand;
   private MecanumDrive m_drive;
   private CRServo m_intakeWheelServo;
   private GamepadEx m_driver1;
   private GamepadEx m_driver2;
   private Button m_rightBumper;
   private IMU m_imu;
   private IMU.Parameters m_imuParameters;
   private Button m_gyroResetButton;

    @Override
    public void initialize()
    {
        this.m_imu = hardwareMap.get(IMU.class, "imu");
        this.m_imuParameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT
        ));

        this.m_imu.initialize(this.m_imuParameters);

        this.m_intakeWheelServo = new CRServo(hardwareMap, "intakeWheelServo");
//        m_intakeWheelSubsystem = new IntakeWheelSubsystem(hardwareMap, "intakeWheelServo");
        this.m_intakeWheelSubsystem = new IntakeWheelSubsystem(this.m_intakeWheelServo);
        this.m_intakePivotSubsystem = new IntakePivotSubsystem(hardwareMap, "intakePivotServo");
        this.m_gyroSubsystem = new GyroSubsystem(this.m_imu);

        this.m_pivotIntakeCommand = new PivotIntakeCommand(this.m_intakePivotSubsystem);
        this.m_resetGyroCommand = new ResetGyroCommand(this.m_gyroSubsystem);
        this.m_drive = new MecanumDrive
        (
            new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_312),
            new Motor(hardwareMap, "frontRight", Motor.GoBILDA.RPM_312),
            new Motor(hardwareMap, "backLeft", Motor.GoBILDA.RPM_312),
            new Motor(hardwareMap, "backRight", Motor.GoBILDA.RPM_312)
        );
        this.m_driveSubsystem = new DriveSubsystem(this.m_drive);


        this.m_driver1 = new GamepadEx(gamepad1);
        this.m_driver2 = new GamepadEx(gamepad2);
        this.m_gyroResetButton = (new GamepadButton(this.m_driver1, GamepadKeys.Button.START))
                .whenPressed(this.m_resetGyroCommand);

 //       this.m_driveFieldOrientedCommand = new DriveFieldOrientedCommand(this.m_driveSubsystem, () -> this.m_driver1.getLeftX(),
        //       () -> this.m_driver1.getLeftY(), () -> this.m_driver1)

        this.m_driveFieldOrientedCommand = new DriveFieldOrientedCommand(this.m_driveSubsystem, () -> this.m_driver1.getLeftX(),
                () -> this.m_driver1.getLeftY(), () -> this.m_driver1.getRightX(),  () -> this.m_imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));

        this.m_rightBumper = (new GamepadButton(this.m_driver2, GamepadKeys.Button.RIGHT_BUMPER))
                .whenPressed(this.m_pivotIntakeCommand);

        this.m_spinIntakeCommand = new SpinIntakeCommand(this.m_intakeWheelSubsystem, () -> this.m_driver2.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER),
                () -> this.m_driver2.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER));



        System.out.println("Running!");

        register(m_driveSubsystem);
        register(m_intakeWheelSubsystem);

        m_driveSubsystem.setDefaultCommand(m_driveFieldOrientedCommand);
        m_intakeWheelSubsystem.setDefaultCommand(m_spinIntakeCommand);



    }
}

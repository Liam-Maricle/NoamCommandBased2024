package teamCode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import teamCode.commands.ArmFudgeFactorDownCommand;
import teamCode.commands.ArmFudgeFactorUpCommand;
import teamCode.commands.DriveFieldOrientedCommand;
import teamCode.commands.SlideArmCommand;
import teamCode.commands.LiftArmCommand;
import teamCode.commands.ArmPositionHomeCommand;
import teamCode.commands.ArmPositionAscentCommand;
import teamCode.commands.ArmPositionCloseSampleCommand;
import teamCode.commands.ArmPositionFarSampleCommand;
import teamCode.commands.ArmPositionHighBasketCommand;
import teamCode.commands.ArmPositionHighChamberCommand;
import teamCode.commands.ArmPositionHomeCommand;
import teamCode.commands.ArmPositionLowBasketCommand;
import teamCode.commands.ArmPositionLowChamberCommand;
import teamCode.commands.IntakePivotCommand;
import teamCode.commands.IntakeWheelCommand;
import teamCode.commands.ResetGyroCommand;

import teamCode.subsystems.DriveFieldOrientedSubsystem;
import teamCode.subsystems.SlideArmSubsystem;
import teamCode.subsystems.LiftArmSubsystem;
import teamCode.subsystems.IntakePivotSubsystem;
import teamCode.subsystems.IntakeWheelSubsystem;
import teamCode.subsystems.GyroSubsystem;

@TeleOp(name = "Sting-Ray")
public class RobotContainer extends CommandOpMode
{
   /* Drivetrain */
   private MecanumDrive m_drive;


   /* IMU */
   private IMU m_imu;
   private IMU.Parameters m_imuParameters;


    /* Gamepad */
   private GamepadEx m_driver1;
   private GamepadEx m_driver2;

   private Button m_leftBumper;
   private Button m_rightBumper;

   private Button m_a;
   private Button m_b;
   private Button m_x;
   private Button m_y;
   private Button m_dpadTop;
   private Button m_dpadBottom;
   private Button m_dpadLeft;
   private Button m_dpadRight;
   private Button m_gyroResetButton;


   /* Motors */
   private DcMotor m_slideArmMotor;
   private DcMotor m_liftArmMotor;
   private CRServo m_intakeWheelServo;


    /* Subsystems */
   private DriveFieldOrientedSubsystem m_driveFieldOrientedSubsystem;
   private SlideArmSubsystem m_slideArmSubsystem;
   private LiftArmSubsystem m_liftArmSubsystem;
   private IntakePivotSubsystem m_intakePivotSubsystem;
   private IntakeWheelSubsystem m_intakeWheelSubsystem;
   private GyroSubsystem m_gyroSubsystem;


   /* Commands */
   private DriveFieldOrientedCommand m_driveFieldOrientedCommand;
   private ArmFudgeFactorUpCommand m_armFudgeFactorUpCommand;
   private ArmFudgeFactorDownCommand m_armFudgeFactorDownCommand;
   private ArmPositionAscentCommand m_armPositionAscentCommand;
   private ArmPositionCloseSampleCommand m_armPositionCloseSampleCommand;
   private ArmPositionFarSampleCommand m_armPositionFarSampleCommand;
   private ArmPositionHighBasketCommand m_armPositionHighBasketCommand;
   private ArmPositionHighChamberCommand m_armPositionHighChamberCommand;
   private ArmPositionLowBasketCommand m_armPositionLowBasketCommand;
   private ArmPositionLowChamberCommand m_armPositionLowChamberCommand;
   private ArmPositionHomeCommand m_armPositionHomeCommand;
   private IntakePivotCommand m_intakePivotCommand;
   private IntakeWheelCommand m_intakeWheelCommand;
   private ResetGyroCommand m_resetGyroCommand;


   /* PID */
    private PIDController m_pIDController;


    @Override
    public void initialize()
    {

        /* Drivetrain */

        this.m_drive = new MecanumDrive
                (
                        new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_312),
                        new Motor(hardwareMap, "frontRight", Motor.GoBILDA.RPM_312),
                        new Motor(hardwareMap, "backLeft", Motor.GoBILDA.RPM_312),
                        new Motor(hardwareMap, "backRight", Motor.GoBILDA.RPM_312)
                );


        /* IMU */

        this.m_imu = hardwareMap.get(IMU.class, "imu");
        this.m_imuParameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT
        ));

        this.m_imu.initialize(this.m_imuParameters);


        /* Gamepad */

        this.m_driver1 = new GamepadEx(gamepad1);
        this.m_driver2 = new GamepadEx(gamepad2);


        /* Motors */

        this.m_slideArmMotor = hardwareMap.get (DcMotor.class, ("slideArmMotor"));
        this.m_liftArmMotor = hardwareMap.get(DcMotor.class, ("liftArmMotor"));
        this.m_intakeWheelServo = new CRServo(hardwareMap, "intakeWheelServo");

        /* PID */

        this.m_pIDController = new PIDController(0, 0, 0);
        this.m_pIDController.setPID(0.0, 0.0, 0.0);


        /* Subsystems */

        this.m_driveFieldOrientedSubsystem = new DriveFieldOrientedSubsystem(this.m_drive);
        this.m_slideArmSubsystem = new SlideArmSubsystem(this.m_slideArmMotor);
        this.m_liftArmSubsystem = new LiftArmSubsystem(this.m_liftArmMotor)/*() -> this.m_pIDController.calculate(this.m_liftArmMotor.getCurrentPosition()))*/;
        this.m_intakePivotSubsystem = new IntakePivotSubsystem(hardwareMap, "intakePivotServo");
        this.m_intakeWheelSubsystem = new IntakeWheelSubsystem(this.m_intakeWheelServo);
        this.m_gyroSubsystem = new GyroSubsystem(this.m_imu);

        register(this.m_driveFieldOrientedSubsystem);
        register(this.m_intakeWheelSubsystem);


        /* Default Commands */

        this.m_driveFieldOrientedCommand = new DriveFieldOrientedCommand(this.m_driveFieldOrientedSubsystem, () -> this.m_driver1.getLeftX(),
                () -> this.m_driver1.getLeftY(), () -> this.m_driver1.getRightX(),  () -> this.m_imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        this.m_driveFieldOrientedSubsystem.setDefaultCommand(this.m_driveFieldOrientedCommand);

        this.m_intakeWheelCommand = new IntakeWheelCommand(this.m_intakeWheelSubsystem, () -> this.m_driver2.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER),
                () -> this.m_driver2.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER));
        this.m_intakeWheelSubsystem.setDefaultCommand(this.m_intakeWheelCommand);


        /* Event Commands */

        this.m_armFudgeFactorUpCommand = new ArmFudgeFactorUpCommand(m_liftArmSubsystem);
        this.m_dpadRight = (new GamepadButton(this.m_driver2, GamepadKeys.Button.DPAD_RIGHT))
                .whenPressed(this.m_armFudgeFactorUpCommand);

        this.m_armFudgeFactorDownCommand = new ArmFudgeFactorDownCommand(m_liftArmSubsystem);
        this.m_dpadLeft = (new GamepadButton(this.m_driver2, GamepadKeys.Button.DPAD_LEFT))
                .whenPressed(this.m_armFudgeFactorDownCommand);

        this.m_armPositionAscentCommand = new ArmPositionAscentCommand(m_liftArmSubsystem, m_slideArmSubsystem);
        this.m_leftBumper = (new GamepadButton(this.m_driver1, GamepadKeys.Button.LEFT_BUMPER))
                .whenPressed(this.m_armPositionAscentCommand);

        this.m_armPositionCloseSampleCommand = new ArmPositionCloseSampleCommand(m_liftArmSubsystem, m_slideArmSubsystem);
        this.m_x = (new GamepadButton(this.m_driver2, GamepadKeys.Button.X))
                .whenPressed(this.m_armPositionCloseSampleCommand);

        this.m_armPositionFarSampleCommand = new ArmPositionFarSampleCommand(m_liftArmSubsystem, m_slideArmSubsystem);
        this.m_b = (new GamepadButton(this.m_driver2, GamepadKeys.Button.B))
                .whenPressed(this.m_armPositionFarSampleCommand);

        this.m_armPositionHighBasketCommand = new ArmPositionHighBasketCommand(m_liftArmSubsystem, m_slideArmSubsystem);
        this.m_y = (new GamepadButton(this.m_driver2, GamepadKeys.Button.Y))
                .whenPressed(this.m_armPositionHighBasketCommand);

        this.m_armPositionHighChamberCommand = new ArmPositionHighChamberCommand(m_liftArmSubsystem, m_slideArmSubsystem);
        this.m_dpadTop = (new GamepadButton(this.m_driver2, GamepadKeys.Button.DPAD_UP))
                .whenPressed(this.m_armPositionHighChamberCommand);

        this.m_armPositionHomeCommand = new ArmPositionHomeCommand(m_liftArmSubsystem, m_slideArmSubsystem);
        this.m_leftBumper = (new GamepadButton(this.m_driver2, GamepadKeys.Button.LEFT_BUMPER))
                .whenPressed(this.m_armPositionHomeCommand);

        this.m_armPositionLowBasketCommand = new ArmPositionLowBasketCommand(m_liftArmSubsystem, m_slideArmSubsystem);
        this.m_a = (new GamepadButton(this.m_driver2, GamepadKeys.Button.A))
                .whenPressed(this.m_armPositionLowBasketCommand);

        this.m_armPositionLowChamberCommand = new ArmPositionLowChamberCommand(m_liftArmSubsystem, m_slideArmSubsystem);
        this.m_dpadBottom = (new GamepadButton(this.m_driver2, GamepadKeys.Button.DPAD_DOWN))
                .whenPressed(this.m_armPositionLowChamberCommand);

        this.m_intakePivotCommand = new IntakePivotCommand(this.m_intakePivotSubsystem);
        this.m_rightBumper = (new GamepadButton(this.m_driver2, GamepadKeys.Button.RIGHT_BUMPER))
                .whenPressed(this.m_intakePivotCommand);

        this.m_resetGyroCommand = new ResetGyroCommand(this.m_gyroSubsystem);
        this.m_gyroResetButton = (new GamepadButton(this.m_driver1, GamepadKeys.Button.START))
                .whenPressed(this.m_resetGyroCommand);
        }
}

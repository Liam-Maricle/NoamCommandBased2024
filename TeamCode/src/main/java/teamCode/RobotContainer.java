package teamCode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import teamCode.commands.ArmPositionAscentCommand;
import teamCode.commands.ArmPositionCloseSampleCommand;
import teamCode.commands.ArmPositionFarSampleCommand;
import teamCode.commands.ArmPositionHighBasketCommand;
import teamCode.commands.ArmPositionHighChamberCommand;
import teamCode.commands.ArmPositionLowBasketCommand;
import teamCode.commands.ArmPositionLowChamberCommand;
import teamCode.commands.DriveFieldOrientedCommand;
import teamCode.commands.ExtendArmCommand;
import teamCode.commands.LiftArmCommand;
import teamCode.commands.PivotIntakeCommand;
import teamCode.commands.ResetGyroCommand;
import teamCode.commands.SpinIntakeCommand;
import teamCode.subsystems.DriveSubsystem;
import teamCode.subsystems.ExtendArmSubsystem;
import teamCode.subsystems.GyroSubsystem;
import teamCode.subsystems.IntakePivotSubsystem;
import teamCode.subsystems.IntakeWheelSubsystem;
import teamCode.subsystems.LiftArmSubsystem;


@TeleOp(name = "Sting-Ray")
public class RobotContainer extends CommandOpMode
{
   private DriveSubsystem m_driveSubsystem;
   private ExtendArmSubsystem m_extendArmSubsystem;
   private LiftArmSubsystem m_liftArmSubsystem;
   private IntakeWheelSubsystem m_intakeWheelSubsystem;
   private IntakePivotSubsystem m_intakePivotSubsystem;
   private GyroSubsystem m_gyroSubsystem;

   private DriveFieldOrientedCommand m_driveFieldOrientedCommand;
   private ExtendArmCommand m_extendArmCommand;
   private LiftArmCommand m_liftArmCommand;
   private PivotIntakeCommand m_pivotIntakeCommand;
   private SpinIntakeCommand m_spinIntakeCommand;
   private ResetGyroCommand m_resetGyroCommand;
   private ArmPositionAscentCommand m_armPositionAscentCommand;
   private ArmPositionCloseSampleCommand m_armPositionCloseSampleCommand;
   private ArmPositionFarSampleCommand m_armPositionFarSampleCommand;
   private ArmPositionHighBasketCommand m_armPositionHighBasketCommand;
   private ArmPositionHighChamberCommand m_armPositionHighChamberCommand;
   private ArmPositionLowBasketCommand m_armPositionLowBasketCommand;
   private ArmPositionLowChamberCommand m_armPositionLowChamberCommand;


   private MecanumDrive m_drive;

   private Motor m_extendArmMotor;
   private Motor m_liftArmMotor;
   private CRServo m_intakeWheelServo;

   private GamepadEx m_driver1;
   private GamepadEx m_driver2;
   private Button m_rightBumper;
   private Button m_y;
   private Button m_a;
   private Button m_x;
   private Button m_b;
   private Button m_dpadTop;
   private Button m_dpadBottom;
   private Button m_leftBumper;

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

        this.m_extendArmMotor = new Motor(hardwareMap, "extendArmMotor", Motor.GoBILDA.RPM_312);
        this.m_liftArmMotor = new Motor(hardwareMap, "liftArmMotor", Motor.GoBILDA.RPM_117);

        this.m_extendArmSubsystem = new ExtendArmSubsystem(this.m_extendArmMotor);
        this.m_liftArmSubsystem = new LiftArmSubsystem(this.m_liftArmMotor);

        this.m_driver1 = new GamepadEx(gamepad1);
        this.m_driver2 = new GamepadEx(gamepad2);
        this.m_gyroResetButton = (new GamepadButton(this.m_driver1, GamepadKeys.Button.START))
                .whenPressed(this.m_resetGyroCommand);

 //       this.m_driveFieldOrientedCommand = new DriveFieldOrientedCommand(this.m_driveSubsystem, () -> this.m_driver1.getLeftX(),
        //       () -> this.m_driver1.getLeftY(), () -> this.m_driver1)

        this.m_driveFieldOrientedCommand = new DriveFieldOrientedCommand(this.m_driveSubsystem, () -> this.m_driver1.getLeftX(),
                () -> this.m_driver1.getLeftY(), () -> this.m_driver1.getRightX(),  () -> this.m_imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));

        this.m_extendArmCommand = new ExtendArmCommand(this.m_extendArmSubsystem, () -> this.m_driver2.getLeftY());
        this.m_liftArmCommand = new LiftArmCommand(this.m_liftArmSubsystem, () -> this.m_driver2.getRightY());

        this.m_armPositionAscentCommand = new ArmPositionAscentCommand(this.m_armPositionAscentCommand, () -> this.m_driver2.getGamepadButton(GamepadKeys.Button.Y));

        this.m_rightBumper = (new GamepadButton(this.m_driver2, GamepadKeys.Button.RIGHT_BUMPER))
                .whenPressed(this.m_pivotIntakeCommand);

        this.m_spinIntakeCommand = new SpinIntakeCommand(this.m_intakeWheelSubsystem, () -> this.m_driver2.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER),
                () -> this.m_driver2.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER));

        this.m_leftBumper = (new GamepadButton(this.m_driver2, GamepadKeys.Button.LEFT_BUMPER))
                .whenPressed(this.m_armPositionAscentCommand);

        this.m_x = (new GamepadButton(this.m_driver2, GamepadKeys.Button.X))
                .whenPressed(this.m_armPositionCloseSampleCommand);

        this.m_b = (new GamepadButton(this.m_driver2, GamepadKeys.Button.B))
                .whenPressed(this.m_armPositionFarSampleCommand);

        this.m_y = (new GamepadButton(this.m_driver2, GamepadKeys.Button.Y))
                .whenPressed(this.m_armPositionHighBasketCommand);

        this.m_dpadTop = (new GamepadButton(this.m_driver2, GamepadKeys.Button.DPAD_UP))
                .whenPressed(this.m_armPositionHighChamberCommand);

        this.m_a = (new GamepadButton(this.m_driver2, GamepadKeys.Button.A))
                .whenPressed(this.m_armPositionLowBasketCommand);

        this.m_dpadBottom = (new GamepadButton(this.m_driver2, GamepadKeys.Button.DPAD_DOWN))
                .whenPressed(this.m_armPositionLowChamberCommand);


        System.out.println("Running!");

        register(this.m_driveSubsystem);
        register(this.m_extendArmSubsystem);
        register(this.m_liftArmSubsystem);
        register(this.m_intakeWheelSubsystem);


        this.m_driveSubsystem.setDefaultCommand(this.m_driveFieldOrientedCommand);
//
        this.m_extendArmSubsystem.setDefaultCommand(this.m_extendArmCommand);
        this.m_liftArmSubsystem.setDefaultCommand(this.m_liftArmCommand);

        this.m_intakeWheelSubsystem.setDefaultCommand(this.m_spinIntakeCommand);

//        while(1 == 1)
//        {
//            telemetry.addData("Lift Arm", this.m_liftArmMotor.getCurrentPosition());
//            telemetry.addData("Extend Arm", this.m_extendArmMotor.getCurrentPosition());
//            telemetry.update();
//        }
    }
}

package teamCode;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.Servo;

import teamCode.autoCommands.AutoDriveCommand;
import teamCode.autoSubsystems.AutoMotorSubsystem;
import teamCode.commands.ExtendArmCommand;
import teamCode.commands.ExtendArmPositionCommand;
import teamCode.commands.LiftArmCommand;
import teamCode.commands.PivotIntakeCommand;
import teamCode.commands.SpinIntakeCommand;
import teamCode.subsystems.ExtendArmSubsystem;
import teamCode.subsystems.IntakePivotSubsystem;
import teamCode.subsystems.IntakeWheelSubsystem;
import teamCode.subsystems.LiftArmSubsystem;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Sting-Ray Auto")
public class Autonomous extends CommandOpMode
{
    private Motor m_fLMotor;
    private Motor m_fRMotor;
    private Motor m_bLMotor;
    private Motor m_bRMotor;
    private Motor m_liftArmMotor;
    private Motor m_extendArmMotor;
    private Servo m_intakePivotServo;
    private CRServo m_intakeWheelServo;
    private IntakePivotSubsystem m_intakePivotSubsystem;

    @Override
    public void initialize()
    {
        this.m_fLMotor = new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_312);
        this.m_fRMotor = new Motor(hardwareMap, "frontRight", Motor.GoBILDA.RPM_312);
        this.m_bLMotor = new Motor(hardwareMap, "backLeft", Motor.GoBILDA.RPM_312);
        this.m_bRMotor = new Motor(hardwareMap, "backRight", Motor.GoBILDA.RPM_312);

        this.m_extendArmMotor = new Motor(hardwareMap, "extendArmMotor", Motor.GoBILDA.RPM_312);
        this.m_liftArmMotor = new Motor(hardwareMap, "liftArmMotor", Motor.GoBILDA.RPM_117);

        this.m_intakeWheelServo = new CRServo(hardwareMap, "intakeWheelServo");
        this.m_intakePivotSubsystem = new IntakePivotSubsystem(hardwareMap, "intakePivotServo");
    }
    @Override
    public void run()
    {
        telemetry.addData("Front Left", this.m_fLMotor.getCurrentPosition());
        telemetry.addData("Front Right", this.m_fRMotor.getCurrentPosition());
        driveRobot(-500, 500, -500, 500);
    }

    public void driveRobot(int fL, int fR, int bL, int bR)
    {
        new ParallelCommandGroup
                (
                        new AutoDriveCommand(new AutoMotorSubsystem(this.m_fLMotor), fL),
                        new AutoDriveCommand(new AutoMotorSubsystem(this.m_fRMotor), fR),
                        new AutoDriveCommand(new AutoMotorSubsystem(this.m_bLMotor), bL),
                        new AutoDriveCommand(new AutoMotorSubsystem(this.m_bRMotor), bR)
                );
    }

    /*public void  liftExtendArm (int lift, int extend)
    {
        new ParallelCommandGroup
                (
                        new LiftArmPositionCommand(new LiftArmSubsystem(this.m_liftArmMotor), lift),
                        new ExtendArmPositionCommand(new ExtendArmSubsystem(this.m_extendArmMotor), extend)
                );
    }

    public void pivotIntake (String pivot)
    {
        new ParallelCommandGroup
                (
                        new PivotIntakeCommand(new IntakePivotSubsystem(hardwareMap, pivot))
                );
    }

    public void spinIntake (int spin)
    {
        new ParallelCommandGroup
                (
//                        new SpinIntakeCommand(new IntakeWheelSubsystem(this.m_intakeWheelServo), spin)
                );
    }*/
}
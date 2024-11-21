package teamCode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import teamCode.subsystems.IntakePivotSubsystem;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Sting-Ray Auto")

public class Autonomous extends CommandOpMode
{
    private DcMotor m_fLMotor;
    private DcMotor m_fRMotor;
    private DcMotor m_bLMotor;
    private DcMotor m_bRMotor;
    private DcMotor m_liftArmMotor;
    private DcMotor m_extendArmMotor;
    private Servo m_intakePivotServo;
    private CRServo m_intakeWheelServo;
    private IntakePivotSubsystem m_intakePivotSubsystem;
    private int m_fLPos;
    private int m_fRPos;
    private int m_bLPos;
    private int m_bRPos;

    @Override

    public void initialize()
    {
        this.m_fLMotor = hardwareMap.get(DcMotor.class, "frontLeft");
        this.m_fRMotor = hardwareMap.get(DcMotor.class, "frontRight");
        this.m_bLMotor = hardwareMap.get(DcMotor.class, "backLeft");
        this.m_bRMotor = hardwareMap.get(DcMotor.class, "backRight");

        this.m_fLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.m_fRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.m_bLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.m_bRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        this.m_fLMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.m_fRMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.m_bLMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.m_bRMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.m_fLMotor.setDirection(DcMotor.Direction.REVERSE);
        this.m_bLMotor.setDirection(DcMotor.Direction.REVERSE);

        this.m_fLMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.m_fRMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.m_bLMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.m_bRMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        /*  this.m_extendArmMotor = new Motor(hardwareMap, "extendArmMotor", Motor.GoBILDA.RPM_312);
        this.m_liftArmMotor = new Motor(hardwareMap, "liftArmMotor", Motor.GoBILDA.RPM_117);*/

        this.m_intakeWheelServo = new CRServo(hardwareMap, "intakeWheelServo");
        this.m_intakePivotSubsystem = new IntakePivotSubsystem(hardwareMap, "intakePivotServo");

        m_fLPos = 0;
        m_fRPos = 0;
        m_bLPos = 0;
        m_bRPos = 0;
        waitForStart();


    }

    @Override
    public void run()
    {
        driveRobot(1025, -1025, 1025, -1025);
        stop();
    }

    public void driveRobot(int fL, int fR, int bL, int bR)
    {
        m_fLPos += fL;
        m_fRPos += fR;
        m_bLPos += bL;
        m_bRPos += bR;

        m_fLMotor.setTargetPosition(m_fLPos);
        m_fRMotor.setTargetPosition(m_fRPos);
        m_bLMotor.setTargetPosition(m_bLPos);
        m_bRMotor.setTargetPosition(m_bRPos);

        this.m_fLMotor.setPower(0.5);
        this.m_fRMotor.setPower(0.5);
        this.m_bLMotor.setPower(0.5);
        this.m_bRMotor.setPower(0.5);

        this.m_fLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.m_fRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.m_bLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.m_bRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        sleep(4000);
        this.m_fLMotor.setPower(0);
        this.m_fRMotor.setPower(0);
        this.m_bLMotor.setPower(0);
        this.m_bRMotor.setPower(0);

       /* new ParallelCommandGroup
                (
                      //  new AutoDriveCommand(new AutoMotorSubsystem(this.m_fLMotor), fL),
                      //  new AutoDriveCommand(new AutoMotorSubsystem(this.m_fRMotor), fR),
                      //  new AutoDriveCommand(new AutoMotorSubsystem(this.m_bLMotor), bL),
                      //  new AutoDriveCommand(new AutoMotorSubsystem(this.m_bRMotor), bR)
                );
    }

    public void  liftExtendArm (int lift, int extend)
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
                        new SpinIntakeCommand(new IntakeWheelSubsystem(this.m_intakeWheelServo), spin)
                );*/


    }
}
package teamCode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import teamCode.autoSubsystems.AutoDriveSubsystem;
import teamCode.subsystems.IntakePivotSubsystem;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Sting-Ray Auto")

public class Autonomous extends CommandOpMode
{
    private DcMotor m_fLMotor;
    private DcMotor m_fRMotor;
    private DcMotor m_bLMotor;
    private DcMotor m_bRMotor;
    private DcMotor m_liftArmMotor;
    private DcMotor m_slideArmMotor;
    private Servo m_intakePivotServo;
    private CRServo m_intakeWheelServo;
    private IntakePivotSubsystem m_intakePivotSubsystem;
    private AutoDriveSubsystem m_autoDriveSubsystem;
    private int m_fLPos;
    private int m_fRPos;
    private int m_bLPos;
    private int m_bRPos;
    private int driveRobot;

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

        /*  this.m_slideArmMotor = new Motor(hardwareMap, "slideArmMotor", Motor.GoBILDA.RPM_312);
        this.m_liftArmMotor = new Motor(hardwareMap, "liftArmMotor", Motor.GoBILDA.RPM_117);*/

        this.m_intakeWheelServo = new CRServo(hardwareMap, "intakeWheelServo");
        this.m_intakePivotSubsystem = new IntakePivotSubsystem(hardwareMap, "intakePivotServo");
        this.m_autoDriveSubsystem = new AutoDriveSubsystem();
        waitForStart();
    }

    @Override
    public void run()
    {
        this.m_autoDriveSubsystem.driveRobot(1025, -1025, 1025, -1025);
        stop();
    }
}
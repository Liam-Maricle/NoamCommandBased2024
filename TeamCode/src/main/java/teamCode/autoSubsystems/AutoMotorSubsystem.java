package teamCode.autoSubsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import teamCode.subsystems.IntakePivotSubsystem;

public class AutoMotorSubsystem extends SubsystemBase
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

    public AutoMotorSubsystem (DcMotor dcMotor)
    {
//        this.m_driveMotor = dcMotor;
    }

    public void runMotor(int pos)
    {
       // this.m_driveMotor.setRunMode(Motor.RunMode.PositionControl);
     //   this.m_driveMotor.set(0.5);
    }
}








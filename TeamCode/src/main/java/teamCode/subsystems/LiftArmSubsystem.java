package teamCode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;

public class LiftArmSubsystem extends SubsystemBase
{
    private final DcMotor m_liftArmMotor;
    public int m_lArmPos;
    public LiftArmSubsystem(DcMotor liftArmMotor)
    {
        this.m_liftArmMotor = liftArmMotor;
        this.m_liftArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // this.m_liftArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.m_liftArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m_lArmPos = 0;
    }

    public void liftArm(int lift)
    {
        m_liftArmMotor.setTargetPosition(lift);
        this.m_liftArmMotor.setPower(0.5);
        this.m_liftArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void fudgeFactor(int fudge)
    {
        this.m_liftArmMotor.setTargetPosition(this.m_liftArmMotor.getCurrentPosition() + fudge);
        this.m_liftArmMotor.setPower(0.5);
        this.m_liftArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}

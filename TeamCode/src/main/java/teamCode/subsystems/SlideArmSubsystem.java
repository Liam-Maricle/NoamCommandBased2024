package teamCode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;

public class SlideArmSubsystem extends SubsystemBase
{
    private final DcMotor m_slideArmMotor;
    public int m_sArmPos;
    public SlideArmSubsystem(DcMotor slideArmMotor)
    {
        this.m_slideArmMotor = slideArmMotor;
        this.m_slideArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.m_slideArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.m_slideArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m_sArmPos = 0;
    }

    public void slideArm(double power)
    {
        this.m_slideArmMotor.setPower(power);
    }

     public void slideArm(int slide)
    {
        m_slideArmMotor.setTargetPosition(slide);
        this.m_slideArmMotor.setPower(0.5);
    }
}

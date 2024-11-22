package teamCode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;

public class ExtendArmSubsystem extends SubsystemBase
{
    private final DcMotor m_extendArmMotor;
    public int m_eArmPos;
    public ExtendArmSubsystem(DcMotor extendArmMotor)
    {
        this.m_extendArmMotor = extendArmMotor;
        this.m_extendArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.m_extendArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.m_extendArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m_eArmPos = 0;
    }

    public void extendArm(double power)
    {
        this.m_extendArmMotor.setPower(power);
    }

     public void extendArm(int extend)
    {
        m_extendArmMotor.setTargetPosition(0);
        this.m_extendArmMotor.setPower(0.5);
    }
}

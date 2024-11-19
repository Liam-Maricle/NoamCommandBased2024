package teamCode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;

public class ExtendArmSubsystem extends SubsystemBase
{
    private final Motor m_extendArmMotor;

    public ExtendArmSubsystem(Motor extendArmMotor)
    {
        this.m_extendArmMotor = extendArmMotor;
//        this.m_extendArmMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
    }

    public void extendArm(double power)
    {
        this.m_extendArmMotor.set(power);
    }

    public void extendArm(int extend)
    {
        this.m_extendArmMotor.setTargetPosition(extend);
    }
}

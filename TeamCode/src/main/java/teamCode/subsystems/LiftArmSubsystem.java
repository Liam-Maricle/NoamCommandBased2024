package teamCode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;

public class LiftArmSubsystem extends SubsystemBase
{
    private final Motor m_liftArmMotor;

    public LiftArmSubsystem(Motor liftArmMotor)
    {
        this.m_liftArmMotor = liftArmMotor;
        this.m_liftArmMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    // Write methods below.
    public void liftArm(double power)
    {
        this.m_liftArmMotor.set(power);
    }

    public void liftArm(int lift)
    {
        this.m_liftArmMotor.setRunMode(Motor.RunMode.PositionControl);
        this.m_liftArmMotor.setTargetPosition(lift);
        this.m_liftArmMotor.set(0.5);
    }
}
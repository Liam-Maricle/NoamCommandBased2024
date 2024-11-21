package teamCode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class ExtendArmSubsystem extends SubsystemBase
{
    private final MotorEx m_extendArmMotor;

    public ExtendArmSubsystem(MotorEx extendArmMotor)
    {
        this.m_extendArmMotor = extendArmMotor;
        this.m_extendArmMotor.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void extendArm(double power)
    {
//        this.m_extendArmMotor.setRunMode(Motor.RunMode.PositionControl);
        this.m_extendArmMotor.set(power);
    }

    public void extendArm(int extend)
    {
        m_extendArmMotor.setRunMode(MotorEx.RunMode.PositionControl);

    }
}

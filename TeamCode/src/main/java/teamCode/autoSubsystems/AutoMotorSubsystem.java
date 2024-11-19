package teamCode.autoSubsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class AutoMotorSubsystem extends SubsystemBase
{
    public Motor m_driveMotor;

    public AutoMotorSubsystem(Motor motor)
    {
        this.m_driveMotor = motor;
    }

    public void runMotor(int pos)
    {
        this.m_driveMotor.setTargetPosition(pos);
    }
}

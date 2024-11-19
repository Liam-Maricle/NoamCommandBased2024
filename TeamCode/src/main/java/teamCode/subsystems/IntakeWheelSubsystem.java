package teamCode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeWheelSubsystem extends SubsystemBase
{
    private final CRServo m_intakeWheelServo;

    public IntakeWheelSubsystem(CRServo wheel) //HardwareMap hMap, String name
    {
        this.m_intakeWheelServo = wheel; //this.m_intakeWheelServo = hMap.get(CRServo.class, name);
        this.m_intakeWheelServo.setRunMode(Motor.RunMode.VelocityControl);
    }

    // Spins the intake wheel forwards, or in reverse.
    public void spinIntake(double speed)
    {
        this.m_intakeWheelServo.set(speed);
    }
    public void spinIntake(int spin)
    {
        this.m_intakeWheelServo.setTargetPosition(spin);
    }
}

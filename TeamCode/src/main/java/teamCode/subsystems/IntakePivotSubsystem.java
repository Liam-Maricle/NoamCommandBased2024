package teamCode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakePivotSubsystem extends SubsystemBase
{
    private final Servo m_intakePivotServo;

    public IntakePivotSubsystem(HardwareMap hMap, String name )
    {
        this.m_intakePivotServo = hMap.get(Servo.class, name);      //this.m_intakePivotServo = hMap.get(CRServo.class, name);
    }

    // Spins the intake wheel forwards, or in reverse.
    public void pivotIntake(double pos)
    {
        this.m_intakePivotServo.setPosition(pos);
    }
}

package teamCode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakePivotSubsystem extends SubsystemBase
{
    private Servo intakePivot;

    public IntakePivotSubsystem()
    {
        intakePivot = hardwareMap.get(Servo.class, "intakePivotServo");
    }

    public void intakePivotMove(double pos)
    {
        intakePivot.setPosition(pos);
    }
}

package teamCode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeWheelSubsystem extends SubsystemBase
{
    private CRServo intakeWheel;

    public IntakeWheelSubsystem()
    {
        intakeWheel = hardwareMap.get(CRServo.class, "intakeWheelServo");
    }

    public void moveIntake(double pos)
    {
        intakeWheel.setPower(pos);
    }
}

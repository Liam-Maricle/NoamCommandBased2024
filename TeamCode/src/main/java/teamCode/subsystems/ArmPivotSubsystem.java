package teamCode.subsystems;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmPivotSubsystem extends SubsystemBase
{

    /**
     ** Creates a new ExampleSubsystem.
     */
    private DcMotor armExtend;
    private DcMotor armPivot;

    public ArmPivotSubsystem()
    {
        armPivot = hardwareMap.get(DcMotor.class, "armPivotMotor");
    }

    public void armExtendMove()
    {
         armExtend.setPower(gamepad2.left_stick_y);
    }
    public void armPivotMove()
    {
        armPivot.setPower(gamepad2.right_stick_y);
    }
    public void armExtendSetPoint(int pos)
    {
        armExtend.setTargetPosition(pos);
    }
    public void armPivotSetPoint(int pos)
    {
        armExtend.setTargetPosition(pos);
    }
}

package teamCode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;

import teamCode.subsystems.ArmExtendSubsystem;

public class ArmExtendSubsystem extends SubsystemBase
{
    private DcMotor armExtend;
    int extensionSpeed = 0;
    double extensionLimit = 0.0;

    public ArmExtendSubsystem ()
    {
        armExtend = hardwareMap.get(DcMotor.class, "armExtendMotor");
    }

}

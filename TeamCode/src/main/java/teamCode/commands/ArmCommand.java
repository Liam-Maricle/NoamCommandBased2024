package teamCode.commands;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.subsystems.ArmExtendSubsystem;
import teamCode.subsystems.ArmPivotSubsystem;

public class ArmCommand extends CommandBase
{
    // The subsystem the command runs on
    private final ArmExtendSubsystem m_armExtendSubsystem;
    private final ArmPivotSubsystem m_armPivotSubsystem;

    public ArmCommand(ArmExtendSubsystem extendSubsystem, ArmPivotSubsystem pivotSubsystem)
    {
        m_armExtendSubsystem = extendSubsystem;
        addRequirements(m_armExtendSubsystem);

        m_armPivotSubsystem = pivotSubsystem;
        addRequirements(m_armPivotSubsystem);
    }

    @Override
    public void initialize()
    {
    }

    @Override
    public void execute()
    {
        if (gamepad2.x)
        {
            ArmExtendSubsystem
        }
    }

    public void end()
    {

    }

    @Override
    public boolean isFinished()
    {
        return true;
    }

}

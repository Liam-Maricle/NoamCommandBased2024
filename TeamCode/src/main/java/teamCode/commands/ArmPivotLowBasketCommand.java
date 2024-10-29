package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.subsystems.ArmExtendSubsystem;
import teamCode.subsystems.ArmPivotSubsystem;

public class ArmPivotLowBasketCommand extends CommandBase
{   private final ArmExtendSubsystem m_armExtendSubsystem;
    private final ArmPivotSubsystem m_armPivotSubsystem;

    public ArmPivotLowBasketCommand(ArmExtendSubsystem extendSubsystem, ArmPivotSubsystem pivotSubsystem)
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
        m_armPivotSubsystem.armPivotSetPoint(0);
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }

}
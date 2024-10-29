package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.subsystems.IntakePivotSubsystem;

public class IntakePivotCommand extends CommandBase
{
    // The subsystem the command runs on
    private final IntakePivotSubsystem m_intakePivotSubsystem;
    private int pivotPosition;

    public IntakePivotCommand(IntakePivotSubsystem intakePivotSubsystem)
    {
        m_intakePivotSubsystem = intakePivotSubsystem;
        addRequirements(m_intakePivotSubsystem);

        pivotPosition = 0;
    }

    @Override
    public void initialize()
    {
    }

    @Override
    public void execute()
    {
        if (pivotPosition == 0)
        {
            m_intakePivotSubsystem.intakePivotMove(1);
            pivotPosition = 1;
        }
        else if (pivotPosition == 1)
        {
            m_intakePivotSubsystem.intakePivotMove(0);
            pivotPosition = 0;
        }
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }

}




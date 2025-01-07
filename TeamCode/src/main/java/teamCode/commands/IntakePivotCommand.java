package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.subsystems.IntakePivotSubsystem;

public class IntakePivotCommand extends CommandBase
{
    private static final double m_pickUpPos = 0.5;
    private static final double m_scorePos = 0.75;
    private final IntakePivotSubsystem m_intakePivotSubsystem;
    private int m_position;
    private static final int m_pickUp = 1;
    private static final int m_score = 0;

    public IntakePivotCommand(IntakePivotSubsystem pivotSubsystem)
    {
        this.m_intakePivotSubsystem = pivotSubsystem;
        m_position = m_score;
        addRequirements(this.m_intakePivotSubsystem);
    }

    @Override
    public void initialize()
    {
    }
    @Override
    public void execute()
    {
        if (m_position == m_score)
        {
            this.m_intakePivotSubsystem.pivotIntake(m_scorePos);
            m_position = m_pickUp;
        }
        else if (m_position == m_pickUp)
        {
            this.m_intakePivotSubsystem.pivotIntake(m_pickUpPos);
            m_position = m_score;
        }

    }

    @Override
    public void end(boolean interrupted)
    {
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }
}
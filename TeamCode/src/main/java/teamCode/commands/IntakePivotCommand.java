package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.subsystems.IntakePivotSubsystem;

public class IntakePivotCommand extends CommandBase
{
    private final IntakePivotSubsystem m_intakePivotSubsystem;
    //    private final IntakePivotSubsystem m_intakePivotSubsystem;
    private int position;

    public IntakePivotCommand(IntakePivotSubsystem pivotSubsystem)
    {
        this.m_intakePivotSubsystem = pivotSubsystem;
        position = 0;
        addRequirements(this.m_intakePivotSubsystem);
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        if (position == 0)
        {
            this.m_intakePivotSubsystem.pivotIntake(1.1);
            position = 1;
        }
        else if (position == 1)
        {
            this.m_intakePivotSubsystem.pivotIntake(0.55);
            position = 0;
        }

    }

    @Override
    public void end(boolean inturrupted)
    {

    }

    @Override
    public boolean isFinished()
    {
        return true;
    }
}
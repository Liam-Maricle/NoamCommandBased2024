package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.subsystems.IntakePivotSubsystem;
import teamCode.subsystems.IntakeWheelSubsystem;

public class IntakeWheelCommand extends CommandBase
{
    // The subsystem the command runs on
    private final IntakeWheelSubsystem m_intakeWheelSubsystem;

    public IntakeWheelCommand(IntakeWheelSubsystem intakeWheelSubsystem)
    {
        m_intakeWheelSubsystem = intakeWheelSubsystem;
        addRequirements(m_intakeWheelSubsystem);
    }

    @Override
    public void initialize()
    {
    }

    @Override
    public void execute()
    {
        m_intakeWheelSubsystem.intakeWheelMove(0.25);
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }

}

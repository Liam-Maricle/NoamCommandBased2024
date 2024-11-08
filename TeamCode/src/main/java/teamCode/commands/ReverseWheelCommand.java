package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.subsystems.IntakeWheelSubsystem;

public class ReverseWheelCommand extends CommandBase
{
    // The subsystem the command runs on
    private final IntakeWheelSubsystem m_intakeWheelSubsystem;

    public ReverseWheelCommand(IntakeWheelSubsystem intakeWheelSubsystem)
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
        m_intakeWheelSubsystem.moveIntake(-0.25);
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }

}

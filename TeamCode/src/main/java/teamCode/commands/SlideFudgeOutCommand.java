package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.Constants;
import teamCode.subsystems.SlideArmSubsystem;

public class SlideFudgeOutCommand extends CommandBase
{
    private SlideArmSubsystem m_slideArmSubsystem;

    public int m_slide;

    public SlideFudgeOutCommand(SlideArmSubsystem slideArmSubsystem)
    {
        this.m_slideArmSubsystem = slideArmSubsystem;

        addRequirements(m_slideArmSubsystem);

        this.m_slide = Constants.SlideArmConstants.kSlideFudgeOut;
    }

    @Override
    public void initialize()
    {
    }

    @Override
    public void execute()
    {
        if (!m_slideArmSubsystem.atTarget(2025))
        {
            this.m_slideArmSubsystem.slideFudgeFactor(m_slide);
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

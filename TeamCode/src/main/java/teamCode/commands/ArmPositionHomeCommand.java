package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.Constants;
import teamCode.subsystems.LiftArmSubsystem;
import teamCode.subsystems.SlideArmSubsystem;

public class ArmPositionHomeCommand extends CommandBase
{
    private LiftArmSubsystem m_liftArmSubsystem;
    private SlideArmSubsystem m_slideArmSubsystem;

    public int m_lift;
    public int m_slide;

    public ArmPositionHomeCommand (LiftArmSubsystem liftArmSubsystem,
                                  SlideArmSubsystem slideArmSubsystem)
    {
        this.m_slideArmSubsystem = slideArmSubsystem;
        this.m_liftArmSubsystem = liftArmSubsystem;

        addRequirements(m_liftArmSubsystem, m_slideArmSubsystem);

        this.m_slide = Constants.SlideArmConstants.kSlideArmHome;
        this.m_lift = Constants.LiftArmConstants.kLiftArmHome;
    }

    @Override
    public void initialize()
    {
    }

    @Override
    public void execute()
    {
        this.m_slideArmSubsystem.slideArm(m_slide);
        if(this.m_slideArmSubsystem.atTarget(this.m_slide))
        {
            this.m_liftArmSubsystem.liftArm(m_lift);
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

package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.Constants;
import teamCode.subsystems.SlideArmSubsystem;
import teamCode.subsystems.LiftArmSubsystem;

public class ArmPositionCloseSampleCommand extends CommandBase
{
    private LiftArmSubsystem m_liftArmSubsystem;
    private SlideArmSubsystem m_slideArmSubsystem;

    public int m_lift;
    public int m_slide;

    public ArmPositionCloseSampleCommand(LiftArmSubsystem liftArmSubsystem,
                                    SlideArmSubsystem slideArmSubsystem)
    {
        this.m_liftArmSubsystem = liftArmSubsystem;
        this.m_slideArmSubsystem = slideArmSubsystem;

        addRequirements(m_liftArmSubsystem, m_slideArmSubsystem);

        this.m_lift = Constants.LiftArmConstants.kLiftArmCloseSample;
        this.m_slide = Constants.SlideArmConstants.kSlideArmCloseSample;
    }

    @Override
    public void initialize()
    {
    }

    @Override
    public void execute()
    {
        this.m_liftArmSubsystem.liftArm(m_lift);
//        if(this.m_liftArmSubsystem.atTarget((int)(this.m_lift * 0.75)))
//        {
            this.m_slideArmSubsystem.slideArm(m_slide);
       // }
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

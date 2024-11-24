package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

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

        this.m_lift = -10;
        this.m_slide = -590;
    }

    @Override
    public void initialize()
    {
    }

    @Override
    public void execute()
    {
        this.m_liftArmSubsystem.liftArm(m_lift);
        this.m_slideArmSubsystem.slideArm(m_slide);
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

package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.Constants;
import teamCode.Logic;
import teamCode.subsystems.SlideArmSubsystem;
import teamCode.subsystems.LiftArmSubsystem;

public class ArmPositionLowChamberCommand extends CommandBase
{
    private LiftArmSubsystem m_liftArmSubsystem;
    private SlideArmSubsystem m_slideArmSubsystem;

    public int m_lift;
    public int m_slide;

    public ArmPositionLowChamberCommand(LiftArmSubsystem liftArmSubsystem,
                                        SlideArmSubsystem slideArmSubsystem)
    {
        this.m_liftArmSubsystem = liftArmSubsystem;
        this.m_slideArmSubsystem = slideArmSubsystem;

        addRequirements(m_liftArmSubsystem, m_slideArmSubsystem);

        this.m_lift = Constants.LiftArmConstants.kLiftArmLowChamber;
        this.m_slide = Constants.SlideArmConstants.kSlideArmLowChamber;
    }

    @Override
    public void initialize()
    {
    }

    @Override
    public void execute()
    {
        this.m_liftArmSubsystem.liftArm(m_lift);
        Logic.WaitClass.wait(() -> this.m_liftArmSubsystem.atTarget(this.m_lift-200));
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

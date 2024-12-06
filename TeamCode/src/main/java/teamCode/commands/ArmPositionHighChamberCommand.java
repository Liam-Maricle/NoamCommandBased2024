package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.Logic;
import teamCode.subsystems.SlideArmSubsystem;
import teamCode.subsystems.LiftArmSubsystem;

public class ArmPositionHighChamberCommand extends CommandBase
{
    private LiftArmSubsystem m_liftArmSubsystem;
    private SlideArmSubsystem m_slideArmSubsystem;

    public int m_lift;
    public int m_slide;

    public ArmPositionHighChamberCommand(LiftArmSubsystem liftArmSubsystem,
                                         SlideArmSubsystem slideArmSubsystem)
    {
        this.m_liftArmSubsystem = liftArmSubsystem;
        this.m_slideArmSubsystem = slideArmSubsystem;

        addRequirements(m_liftArmSubsystem, m_slideArmSubsystem);

        this.m_lift = 1330;
        this.m_slide = -470;
    }

    @Override
    public void initialize()
    {
    }

    @Override
    public void execute()
    {
        this.m_liftArmSubsystem.liftArm(m_lift);
        Logic.WaitClass.wait(() -> this.m_liftArmSubsystem.atTarget(this.m_lift-500));
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

package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.WaitCommand;

import teamCode.Constants;
import teamCode.Logic;
import teamCode.subsystems.LiftArmSubsystem;
import teamCode.subsystems.SlideArmSubsystem;

public class ArmPositionHighBasketCommand extends CommandBase
{
    private LiftArmSubsystem m_liftArmSubsystem;
    private SlideArmSubsystem m_slideArmSubsystem;

    public int m_lift;
    public int m_slide;

    public ArmPositionHighBasketCommand(LiftArmSubsystem liftArmSubsystem,
                                        SlideArmSubsystem slideArmSubsystem)
    {
        this.m_liftArmSubsystem = liftArmSubsystem;
        this.m_slideArmSubsystem = slideArmSubsystem;

        addRequirements(m_liftArmSubsystem, m_slideArmSubsystem);

        this.m_lift = Constants.LiftArmConstants.kLiftArmHighBasket;
        this.m_slide = Constants.SlideArmConstants.kSlideArmHighBasket;
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
//        return Logic.OpModeType.opMode.equals("Sting-Ray Auto")
//                && this.m_liftArmSubsystem.atTarget(this.m_lift)
//                && this.m_slideArmSubsystem.atTarget(this.m_slide);
        return true;
    }
}

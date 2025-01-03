package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.Constants;
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
        this.m_liftArmSubsystem.liftArm(2380);
//        if(this.m_liftArmSubsystem.atTarget((int)(this.m_lift * 0.75)))
//        {
          //  this.m_slideArmSubsystem.slideArm(m_slide);
//        }
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

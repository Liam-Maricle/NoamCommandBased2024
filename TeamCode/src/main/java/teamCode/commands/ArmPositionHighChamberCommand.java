package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.Constants;
import teamCode.subsystems.IntakePivotSubsystem;
import teamCode.subsystems.SlideArmSubsystem;
import teamCode.subsystems.LiftArmSubsystem;

public class ArmPositionHighChamberCommand extends CommandBase
{
    private LiftArmSubsystem m_liftArmSubsystem;
    private SlideArmSubsystem m_slideArmSubsystem;
    private IntakePivotSubsystem m_intakePivotSubsystem;

    public int m_lift;
    public int m_slide;
    public double m_score;

    public ArmPositionHighChamberCommand(LiftArmSubsystem liftArmSubsystem,
                                         SlideArmSubsystem slideArmSubsystem, IntakePivotCommand intakePivotCommand)
    {
        this.m_liftArmSubsystem = liftArmSubsystem;
        this.m_slideArmSubsystem = slideArmSubsystem;

        addRequirements(m_liftArmSubsystem, m_slideArmSubsystem);

    }

    @Override
    public void initialize()
    {
    }

    @Override
    public void execute()
    {
        this.m_liftArmSubsystem.liftArm(Constants.LiftArmConstants.kLiftArmHighChamber);
        if(this.m_liftArmSubsystem.atTarget((int)(Constants.LiftArmConstants.kLiftArmHighChamber * 0.75)))
        {
            this.m_slideArmSubsystem.slideArm(Constants.SlideArmConstants.kSlideArmHighChamber);
            this.m_intakePivotSubsystem.pivotIntake(Constants.PivotIntakeConstants.kIntakePivotScore);//Pivot intake

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

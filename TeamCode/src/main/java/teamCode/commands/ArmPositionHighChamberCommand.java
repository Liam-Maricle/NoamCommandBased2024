package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.subsystems.ExtendArmSubsystem;
import teamCode.subsystems.LiftArmSubsystem;

public class ArmPositionHighChamberCommand extends CommandBase
{
    private LiftArmSubsystem m_liftArmSubsystem;
    private ExtendArmSubsystem m_extendArmSubsystem;

    public int m_lift;
    public int m_extend;

    public ArmPositionHighChamberCommand(LiftArmSubsystem liftArmSubsystem, int lift,
                                         ExtendArmSubsystem extendArmSubsystem, int extend)
    {
        this.m_liftArmSubsystem = liftArmSubsystem;
        this.m_extendArmSubsystem = extendArmSubsystem;

        addRequirements(m_liftArmSubsystem, m_extendArmSubsystem);

        this.m_lift = lift;
        this.m_extend = extend;
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        this.m_liftArmSubsystem.liftArm(m_lift);
        this.m_extendArmSubsystem.extendArm(m_extend);
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

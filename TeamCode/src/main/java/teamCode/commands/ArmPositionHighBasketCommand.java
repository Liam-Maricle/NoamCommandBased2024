package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.subsystems.ExtendArmSubsystem;
import teamCode.subsystems.LiftArmSubsystem;

public class ArmPositionHighBasketCommand extends CommandBase
{
    private LiftArmSubsystem m_liftArmSubsystem;
    private ExtendArmSubsystem m_extendArmSubsystem;

    public int m_lift;
    public int m_extend;

    public ArmPositionHighBasketCommand(LiftArmSubsystem liftArmSubsystem, int lift,
                                        ExtendArmSubsystem extendArmSubsystem, int extend)
    {
        this.m_liftArmSubsystem = liftArmSubsystem;
        this.m_extendArmSubsystem = extendArmSubsystem;

        addRequirements(m_liftArmSubsystem);
        addRequirements(m_extendArmSubsystem);

        this.m_lift = lift;
        this.m_extend = extend;
        lift = 1917;
        extend = -2363;
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
}

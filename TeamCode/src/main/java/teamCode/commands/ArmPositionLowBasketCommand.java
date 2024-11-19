package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.subsystems.ExtendArmSubsystem;
import teamCode.subsystems.LiftArmSubsystem;

public class ArmPositionLowBasketCommand extends CommandBase
{
    private LiftArmSubsystem m_liftArmSubsystem;
    private ExtendArmSubsystem m_extendArmSubsystem;

    public int m_lift;
    public int m_extend;

    public ArmPositionLowBasketCommand(LiftArmSubsystem liftArmSubsystem, int lift,
                                       ExtendArmSubsystem extendArmSubsystem, int extend)
    {
        this.m_liftArmSubsystem = liftArmSubsystem;
        this.m_liftArmSubsystem = liftArmSubsystem;
        this.m_extendArmSubsystem = extendArmSubsystem;

        addRequirements(m_liftArmSubsystem);
        addRequirements(m_extendArmSubsystem);

        this.m_lift = lift;
        this.m_extend = extend;
        lift = 1395;
        extend = -1047;
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

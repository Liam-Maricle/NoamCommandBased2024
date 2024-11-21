package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import java.util.function.DoubleSupplier;

import teamCode.subsystems.LiftArmSubsystem;

public class LiftArmCommand extends CommandBase
{
    private LiftArmSubsystem m_liftArmSubsystem;
    public DoubleSupplier m_rightY;
    public boolean m_x;
    public LiftArmCommand(LiftArmSubsystem liftArmSubsystem, DoubleSupplier rightY)
    {
        this.m_liftArmSubsystem = liftArmSubsystem;
        addRequirements(m_liftArmSubsystem);

        this.m_rightY = rightY;
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        this.m_liftArmSubsystem.liftArm(m_rightY.getAsDouble() * 0.5);
    }
}

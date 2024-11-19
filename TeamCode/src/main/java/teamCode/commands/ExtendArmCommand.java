package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import java.util.function.DoubleSupplier;

import teamCode.subsystems.ExtendArmSubsystem;

public class ExtendArmCommand extends CommandBase
{
    private ExtendArmSubsystem m_extendArmSubsystem;
    public DoubleSupplier m_leftY;

    public ExtendArmCommand(ExtendArmSubsystem extendArmSubsystem, DoubleSupplier leftY)
    {
        this.m_extendArmSubsystem = extendArmSubsystem;
        addRequirements(m_extendArmSubsystem);

        this.m_leftY = leftY;
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        this.m_extendArmSubsystem.extendArm(m_leftY.getAsDouble() * 0.5);
    }
}

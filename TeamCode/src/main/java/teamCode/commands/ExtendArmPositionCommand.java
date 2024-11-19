package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import java.util.function.DoubleSupplier;

import teamCode.subsystems.ExtendArmSubsystem;

public class ExtendArmPositionCommand extends CommandBase
{
    private ExtendArmSubsystem m_extendArmSubsystem;
    public int m_extend;

    public ExtendArmPositionCommand(ExtendArmSubsystem extendArmSubsystem, int extend)
    {
        this.m_extendArmSubsystem = extendArmSubsystem;
        addRequirements(m_extendArmSubsystem);

        this.m_extend = extend;
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        this.m_extendArmSubsystem.extendArm(m_extend);
    }
}

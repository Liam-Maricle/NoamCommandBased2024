package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import java.util.function.DoubleSupplier;

import teamCode.subsystems.SlideArmSubsystem;

public class SlideArmCommand extends CommandBase
{
    private SlideArmSubsystem m_slideArmSubsystem;
    public DoubleSupplier m_leftY;

    public SlideArmCommand(SlideArmSubsystem slideArmSubsystem, DoubleSupplier leftY)
    {
        this.m_slideArmSubsystem = slideArmSubsystem;
        addRequirements(m_slideArmSubsystem);

        this.m_leftY = leftY;
    }

    @Override
    public void initialize()
    {
    }

    @Override
    public void execute()
    {
        this.m_slideArmSubsystem.slideArm(m_leftY.getAsDouble() * 0.5);
    }
}

package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import java.util.function.DoubleSupplier;

import teamCode.subsystems.IntakeWheelSubsystem;

public class SpinIntakeCommand extends CommandBase
{
    private final IntakeWheelSubsystem m_intakeWheelSubsystem;
    private DoubleSupplier m_rightTriggerValue;
    private DoubleSupplier m_leftTriggerValue;

    public SpinIntakeCommand(IntakeWheelSubsystem wheel, DoubleSupplier rightTrigger, DoubleSupplier leftTrigger)
    {
        this.m_intakeWheelSubsystem = wheel;
        this.m_rightTriggerValue = rightTrigger;
        this.m_leftTriggerValue = leftTrigger;
        addRequirements(this.m_intakeWheelSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute()
    {
        this.m_intakeWheelSubsystem.spinIntake(
                this.m_rightTriggerValue.getAsDouble() * -1 - this.m_leftTriggerValue.getAsDouble() * -1 );
    }

//    @Override
//    public boolean isFinished()
//    {
//        return true;
//    }
}

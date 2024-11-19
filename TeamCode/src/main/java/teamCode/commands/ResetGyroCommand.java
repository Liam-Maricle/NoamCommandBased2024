package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.IMU;

import java.util.function.DoubleSupplier;

import teamCode.subsystems.DriveSubsystem;
import teamCode.subsystems.GyroSubsystem;

public class ResetGyroCommand extends CommandBase
{
    public GyroSubsystem m_gyroSubsystem;

    public ResetGyroCommand(GyroSubsystem gyroSubsystem)
    {
        this.m_gyroSubsystem = gyroSubsystem;
        addRequirements(this.m_gyroSubsystem);
    }

    @Override
    public void initialize()
    {
        m_gyroSubsystem.resetGyro();
    }

    @Override
    public void execute()
    {
    }

    @Override
    public void end(boolean interrupted)
    {
    }
}

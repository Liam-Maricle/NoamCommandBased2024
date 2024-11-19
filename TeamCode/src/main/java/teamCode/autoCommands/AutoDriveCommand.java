package teamCode.autoCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamCode.autoSubsystems.AutoMotorSubsystem;

public class AutoDriveCommand extends CommandBase
{
    public AutoMotorSubsystem m_autoMotorSubsystem;
    public int m_position;
    public AutoDriveCommand(AutoMotorSubsystem autoDriveSubsystem, int pos)
    {
        this.m_autoMotorSubsystem = autoDriveSubsystem;
        m_position = pos;
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        this.m_autoMotorSubsystem.runMotor(m_position);
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

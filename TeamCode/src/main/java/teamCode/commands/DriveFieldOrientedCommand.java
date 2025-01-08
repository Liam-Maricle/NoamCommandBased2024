package teamCode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import java.util.function.DoubleSupplier;
import teamCode.subsystems.DriveFieldOrientedSubsystem;

public class DriveFieldOrientedCommand extends CommandBase
{
    public DriveFieldOrientedSubsystem m_driveFieldOrientedSubsystem;
    public DoubleSupplier m_leftX;
    public DoubleSupplier m_leftY;
    public DoubleSupplier m_rightX;
    public DoubleSupplier m_imuValue;

    public DriveFieldOrientedCommand(DriveFieldOrientedSubsystem driveSubsystem, DoubleSupplier leftX, DoubleSupplier leftY, DoubleSupplier rightX, DoubleSupplier imuValue)
    {
       this.m_driveFieldOrientedSubsystem = driveSubsystem;
       addRequirements(m_driveFieldOrientedSubsystem);

       this.m_leftX = leftX;
       this.m_leftY = leftY;
       this.m_rightX = rightX;
       this.m_imuValue = imuValue;
    }

    @Override
    public void initialize()
    {
    }

    @Override
    public void execute()
    {
       this.m_driveFieldOrientedSubsystem.headingDrive
               (m_leftX.getAsDouble(), m_leftY.getAsDouble(), m_rightX.getAsDouble(),
                       m_imuValue.getAsDouble());
    }
}

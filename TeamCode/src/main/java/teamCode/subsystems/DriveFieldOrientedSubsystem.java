package teamCode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;

public class DriveFieldOrientedSubsystem extends SubsystemBase
{
    public MecanumDrive m_drive;
    public DriveFieldOrientedSubsystem(MecanumDrive drive)
    {
        this.m_drive = drive;
    }

    public void headingDrive(double leftX, double leftY, double rightX, double imu)
    {
        m_drive.driveFieldCentric
        (
            leftX * leftX * leftX * -1,
            leftY * leftY * leftY * -1,
            rightX * -1,
            imu
        );
    }
}
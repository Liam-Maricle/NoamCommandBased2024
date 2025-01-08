package teamCode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.qualcomm.robotcore.hardware.DcMotor;

public class DriveFieldOrientedSubsystem extends SubsystemBase
{
    public MecanumDrive m_drive;
    private DcMotor m_fLMotor;
    private DcMotor m_fRMotor;
    private DcMotor m_bLMotor;
    private DcMotor m_bRMotor;
    private int m_fLPos;
    private int m_fRPos;
    private int m_bLPos;
    private int m_bRPos;
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
    public void driveRobot(int fL, int fR, int bL, int bR)
    {
        m_fLPos += fL;
        m_fRPos += fR;
        m_bLPos += bL;
        m_bRPos += bR;

        m_fLMotor.setTargetPosition(m_fLPos);
        m_fRMotor.setTargetPosition(m_fRPos);
        m_bLMotor.setTargetPosition(m_bLPos);
        m_bRMotor.setTargetPosition(m_bRPos);

        this.m_fLMotor.setPower(0.5);
        this.m_fRMotor.setPower(0.5);
        this.m_bLMotor.setPower(0.5);
        this.m_bRMotor.setPower(0.5);

//        this.m_fLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        this.m_fRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        this.m_bLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        this.m_bRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
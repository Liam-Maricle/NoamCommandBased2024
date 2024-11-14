package teamCode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.HardwareMap;

public class GyroSubsystem extends SubsystemBase
{
    public final IMU m_imu;

    public GyroSubsystem(IMU imu)
    {
       this.m_imu = imu;
    }

    public void resetGyro()
    {
        this.m_imu.resetYaw();
    }
}

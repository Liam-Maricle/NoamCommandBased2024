package teamCode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.checkerframework.checker.lock.qual.Holding;

import java.util.concurrent.locks.Lock;

public class LiftArmSubsystem extends SubsystemBase
{
    private final DcMotor m_liftArmMotor;
    public int m_lArmPos;
    public LiftArmSubsystem(DcMotor liftArmMotor)
    {
        this.m_liftArmMotor = liftArmMotor;
        this.m_liftArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.m_liftArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.m_liftArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m_lArmPos = 0;
    }

    public void liftArm(double power)
    {
        this.m_liftArmMotor.setPower(power);
    }

    public void liftArm(int lift)
    {
          m_liftArmMotor.setTargetPosition(lift);
          System.out.println(this.m_liftArmMotor.getTargetPosition()); // 830
          this.m_liftArmMotor.setPower(0.5);
    }
}

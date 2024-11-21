package teamCode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;

public class LiftArmSubsystem extends SubsystemBase
{
    private final MotorEx m_liftArmMotor;
    private final PIDController m_pidController;

    public LiftArmSubsystem(MotorEx liftArmMotor)
    {
        this.m_liftArmMotor = liftArmMotor;
        this.m_liftArmMotor.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        this.m_pidController = new PIDController(0, 0, 0);
    }

    // Write methods below.
    public void liftArm(double power)
    {
        this.m_liftArmMotor.set(power);
    }

    public void liftArm(int lift)
    {
//        this.m_liftArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        this.m_liftArmMotor.setTargetPosition(lift);
//        this.m_liftArmMotor.setPower(0.5);

        while(this.m_liftArmMotor.getCurrentPosition() != lift)
        {
            this.m_liftArmMotor.setRunMode(Motor.RunMode.PositionControl);
            this.m_liftArmMotor.setTargetPosition(lift);
            this.m_liftArmMotor.set(0);
        }
    }
}
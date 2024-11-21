package teamCode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import java.util.function.DoubleSupplier;


public class LiftArmSubsystem extends SubsystemBase
{
    private final MotorEx m_liftArmMotor;

//    private final PIDController m_controller;

//    public static double p = 0, i = 0, d = 0;
//    public static double f = 0;
//
    private final double m_ticksInDegrees;
    private DoubleSupplier m_pid;
    private int m_feedFoward;

    public LiftArmSubsystem(MotorEx liftArmMotor, DoubleSupplier pid)
    {
        this.m_liftArmMotor = liftArmMotor;
        this.m_liftArmMotor.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        this.m_pid = pid;
        this.m_feedFoward = 0;
//        this.m_controller = new PIDController(p, i, d);
        this.m_ticksInDegrees = 1425.1 / 360;
    }

    // Write methods below.
    public void liftArm(double power)
    {
        this.m_liftArmMotor.set(power);
    }

    public void liftArm(int target)
    {
//        m_controller.setPID(p, i, d);
//        int armPos = this.m_liftArmMotor.getCurrentPosition();
//        double pid = this.m_controller.calculate(armPos, target);
//        double ff = Math.cos(Math.toRadians(target / this.m_ticksInDegrees)) * f;
////        double ff = Math.sin(Math.toRadians(armPos / m_ticksInDegrees + zeroOffset)) *  f;

        double power = this.m_pid.getAsDouble() + target / this.m_ticksInDegrees * this.m_feedFoward;

        this.m_liftArmMotor.set(power);
        System.out.println(power);
    }
}

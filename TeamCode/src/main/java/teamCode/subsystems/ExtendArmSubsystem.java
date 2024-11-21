package teamCode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;

public class ExtendArmSubsystem extends SubsystemBase
{
    private final MotorEx m_extendArmMotor;

    public ExtendArmSubsystem(MotorEx extendArmMotor)
    {
        this.m_extendArmMotor = extendArmMotor;
        this.m_extendArmMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    public void extendArm(double power)
    {
//        this.m_extendArmMotor.setRunMode(Motor.RunMode.PositionControl);
        this.m_extendArmMotor.set(power);
    }

    public void extendArm(int extend)
    {
       // this.m_extendArmMotor.setTargetPosition(extend);
      //  this.m_extendArmMotor.setRunMode(Motor.RunMode.PositionControl);
      //  this.m_extendArmMotor.set(0.5);

        // set the run mode
        m_extendArmMotor.setRunMode(Motor.RunMode.PositionControl);

// set and get the position coefficient
        m_extendArmMotor.setPositionCoefficient(0.05);
        double kP = m_extendArmMotor.getPositionCoefficient();

// set the target position
        m_extendArmMotor.setTargetPosition(0);      // an integer representing
        // desired tick count

        m_extendArmMotor.set(0);

// set the tolerance
        m_extendArmMotor.setPositionTolerance(13.6);   // allowed maximum error

// perform the control loop
        while (!m_extendArmMotor.atTargetPosition()) {
            m_extendArmMotor.set(0.75);
        }
        m_extendArmMotor.stopMotor(); // stop the motor

        /* ALTERNATIVE TARGET DISTANCE */

// configure a distance per pulse,
// which is the distance traveled in a single tick
// dpp = distance traveled in one rotation / CPR
        m_extendArmMotor.setDistancePerPulse(0.015);

// set the target
        m_extendArmMotor.setTargetDistance(18.0);

// this must be called in a control loop
        m_extendArmMotor.set(0.5); // mode must be PositionControl
    }
}

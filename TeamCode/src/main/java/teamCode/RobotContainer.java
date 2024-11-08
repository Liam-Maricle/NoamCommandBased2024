package teamCode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import teamCode.commands.IntakePivotCommand;
import teamCode.commands.IntakeWheelCommand;
import teamCode.commands.ReverseWheelCommand;
import teamCode.subsystems.ArmExtendSubsystem;
import teamCode.subsystems.ArmPivotSubsystem;
import teamCode.subsystems.IntakePivotSubsystem;
import teamCode.subsystems.IntakeWheelSubsystem;

@TeleOp(name= "Sting-Ray TeleOp")
public class RobotContainer extends CommandOpMode
{
    private IntakePivotSubsystem m_intakePivotSubsystem;
    private IntakeWheelSubsystem m_intakeWheelSubsystem;
    private GamepadEx driver1 = new GamepadEx(gamepad1);
    private GamepadEx driver2 = new GamepadEx(gamepad2);
    private ArmExtendSubsystem m_extendSubsystem;
    private ArmPivotSubsystem m_pivotSubsystem;

    @Override
    public void initialize()
    {
        m_intakeWheelSubsystem = new IntakeWheelSubsystem();
        m_intakePivotSubsystem = new IntakePivotSubsystem();

        m_extendSubsystem = new ArmExtendSubsystem();

        m_pivotSubsystem = new ArmPivotSubsystem();
        m_intakeWheelSubsystem = new IntakeWheelSubsystem();
        m_intakePivotSubsystem = new IntakePivotSubsystem();

        m_extendSubsystem = new ArmExtendSubsystem();

        m_pivotSubsystem = new ArmPivotSubsystem();

        GamepadButton intakeWheelButton = new GamepadButton(driver2, GamepadKeys.Button.A); // Change to right triggers
        GamepadButton reverseWheelButton = new GamepadButton(driver2, GamepadKeys.Button.B); // change to left trigger

        GamepadButton intakePivotButton = new GamepadButton(driver2, GamepadKeys.Button.RIGHT_BUMPER);

//      GamepadButton ExtendButton = new GamepadButton(driver2, GamepadKeys.Button.B);
        GamepadButton PivotButtonHB = new GamepadButton(driver2, GamepadKeys.Button.DPAD_UP);
        GamepadButton PivotButtonHC = new GamepadButton(driver2, GamepadKeys.Button.DPAD_RIGHT);
        GamepadButton PivotButtonLB = new GamepadButton(driver2, GamepadKeys.Button.DPAD_DOWN);
        GamepadButton PivotButtonLC = new GamepadButton(driver2, GamepadKeys.Button.DPAD_LEFT);



        intakeWheelButton.whileHeld(new IntakeWheelCommand(m_intakeWheelSubsystem));
        reverseWheelButton.whileHeld(new ReverseWheelCommand(m_intakeWheelSubsystem));

        intakePivotButton.whenPressed(new IntakePivotCommand(m_intakePivotSubsystem));


        //        ExtendButton.whenPressed(new ArmExtendSubsystem(m_extendSubsystem));
//        PivotButtonHB.whenPressed(new ArmPivotHighBasketCommand(m_pivotSubsystem));
//        PivotButtonHC.whenPressed(new ArmPivotHighBarCommand(m_pivotSubsystem));
//        PivotButtonLB.whenPressed(new ArmPivotLowBasketCommand(m_pivotSubsystem));
//        PivotButtonLC.whenPressed(new ArmPivotLowBarCommand(m_pivotSubsystem));

    }

}

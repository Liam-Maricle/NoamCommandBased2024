package teamCode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;


import teamCode.commands.ArmPivotHighBarCommand;
import teamCode.commands.ArmPivotHighBasketCommand;
import teamCode.commands.ArmPivotLowBarCommand;
import teamCode.commands.ArmPivotLowBasketCommand;
import teamCode.commands.IntakePivotCommand;
import teamCode.commands.IntakeWheelCommand;
import teamCode.commands.ReverseWheelCommand;
import teamCode.subsystems.ArmExtendSubsystem;
import teamCode.subsystems.ArmPivotSubsystem;
import teamCode.subsystems.IntakePivotSubsystem;
import teamCode.subsystems.IntakeWheelSubsystem;

public class RobotContainer
{
    private final IntakePivotSubsystem m_intakePivotSubsystem;
    private final IntakeWheelSubsystem m_intakeWheelSubsystem;
    private final GamepadEx driver1 = new GamepadEx(gamepad1);
    private final GamepadEx driver2 = new GamepadEx(gamepad2);
    private final ArmExtendSubsystem m_extendSubsystem;
    private final ArmPivotSubsystem m_pivotSubsystem;

    public RobotContainer(IntakeWheelSubsystem intakeWheelSubsystem)
    {
        m_intakeWheelSubsystem = intakeWheelSubsystem;
        m_intakePivotSubsystem = new IntakePivotSubsystem();

        m_extendSubsystem = new ArmExtendSubsystem();

        m_pivotSubsystem = new ArmPivotSubsystem();
        configureButtonBindings();
    }

    public void configureButtonBindings()
    {
        GamepadButton intakeWheelButton = new GamepadButton(driver2, GamepadKeys.Button.A); // Change to right triggers
        GamepadButton reverseWheelButton = new GamepadButton(driver2, GamepadKeys.Button.B); // change to left trigger

        GamepadButton intakePivotButton = new GamepadButton(driver2, GamepadKeys.Button.RIGHT_BUMPER);

//        GamepadButton ExtendButton = new GamepadButton(driver2, GamepadKeys.Button.B);
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

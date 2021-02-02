package ca.webber.ftc.ftcliboverhaul.teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import ca.webber.ftc.ftcliboverhaul.commands.DriveCommand;
import ca.webber.ftc.ftcliboverhaul.commands.ShooterCommand;
import ca.webber.ftc.ftcliboverhaul.subsystems.DriveSystem;
import ca.webber.ftc.ftcliboverhaul.subsystems.ShooterSystem;

@TeleOp(name = "CommandBasedTeleop")
public class Teleop extends CommandOpMode {
    private Motor fL, fR, bL, bR;
    private Motor leftShooter, rightShooter;
    private DriveSystem driveSystem;
    private DriveCommand driveCommand;
    private ShooterSystem shooterSystem;
    private ShooterCommand shooterCommand;

    private GamepadEx driver_gamepad;

    @Override
    public void initialize() {
        fL = new Motor(hardwareMap, "fL");
        fR = new Motor(hardwareMap, "fR");
        bL = new Motor(hardwareMap, "bL");
        bR = new Motor(hardwareMap, "bR");
        leftShooter = new Motor(hardwareMap, "leftShooter");
        rightShooter = new Motor(hardwareMap, "rightShooter");

        driver_gamepad = new GamepadEx(gamepad1);

        driveSystem = new DriveSystem(fL, fR, bL, bR);
        driveCommand = new DriveCommand(driveSystem, driver_gamepad::getLeftX, driver_gamepad::getLeftY, driver_gamepad::getRightX);

        register(driveSystem);
        driveSystem.setDefaultCommand(driveCommand);

//        GamepadKeys.Trigger trig = driver_gamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);
//
//        shooterSystem = new ShooterSystem(leftShooter, rightShooter);
//        shooterCommand = new ShooterCommand(shooterSystem, driver_gamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)::getValue);
    }
}

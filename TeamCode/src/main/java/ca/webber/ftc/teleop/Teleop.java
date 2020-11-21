package ca.webber.ftc.teleop;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import ca.webber.ftc.robot.Omnibot;

@TeleOp(name = "Teleop", group = "Iterative Opmodes")
public class Teleop extends OpMode {
    private Omnibot omnibot;
    private GamepadEx gamepad1, gamepad2;

    @Override
    public void init() {
        this.gamepad1 = new GamepadEx(super.gamepad1);
        this.gamepad2 = new GamepadEx(super.gamepad2);
        omnibot = new Omnibot(this);
    }

    @Override
    public void loop() {
        omnibot.drive(gamepad1.getLeftX(), gamepad1.getLeftY(), gamepad1.getRightX());
    }
}

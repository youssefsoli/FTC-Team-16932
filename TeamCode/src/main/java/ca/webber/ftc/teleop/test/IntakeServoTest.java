package ca.webber.ftc.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Debug: Intake Servo Control", group = "Debug")
public class IntakeServoTest extends OpMode {

    private Servo servo = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        servo = hardwareMap.get(Servo.class, "servo");
        servo.setDirection(Servo.Direction.FORWARD);

    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            servo.setPosition(1);
        } else {
            servo.setPosition(0);
        }
    }

    @Override
    public void stop() {
    }
}

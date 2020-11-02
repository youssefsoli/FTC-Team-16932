package ca.webber.ftc.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class DiskGathererUpper extends OpMode {

    private Servo servoturn = null;
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        servoturn = hardwareMap.get(Servo.class, "servoturn");
        servoturn.setDirection(Servo.Direction.FORWARD);

    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        boolean servoTurnOn = gamepad1.start;
        if (servoTurnOn) {
            servoturn.setPower(10);
        }
        else {

        }
    }

    @Override
    public void stop() {
    }
}

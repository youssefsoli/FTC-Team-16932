package ca.webber.ftc.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Debug: Controller Speed Motor Test", group = "Debug")
public class ControllerMotorSpeedTest extends OpMode {
    private DcMotorEx corehex = null;
    private DcMotorEx hdhex = null;
    private double hdPower = 0;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        corehex = hardwareMap.get(DcMotorEx.class, "corehex");
        hdhex = hardwareMap.get(DcMotorEx.class, "hdhex");
        corehex.setDirection(DcMotor.Direction.FORWARD);
        hdhex.setDirection(DcMotor.Direction.FORWARD);
        corehex.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hdhex.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        double corePower = gamepad1.left_stick_y;
        hdPower = 0.75;
        corehex.setPower(corePower);
        hdhex.setPower(hdPower);
        telemetry.addData("Motors", "corehex (%.2f) hdhex (%.2f)", corePower, hdPower);
    }

    @Override
    public void stop() {
    }

}

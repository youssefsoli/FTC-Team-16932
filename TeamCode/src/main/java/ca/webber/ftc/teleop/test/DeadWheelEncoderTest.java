package ca.webber.ftc.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Debug: Dead Wheel Encoder Test", group = "Debug")
public class DeadWheelEncoderTest extends OpMode {
    private DcMotorEx encoder = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        encoder = hardwareMap.get(DcMotorEx.class, "corehex");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        telemetry.addData("Encoders", "encoder (%d)", encoder.getCurrentPosition());
    }

    @Override
    public void stop() {
    }

}

package ca.webber.ftc.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Debug: Controller Motor Test", group = "Debug")
public class ControllerMotorTest extends OpMode {
    private DcMotorEx corehex = null;
    private DcMotorEx hdhex1 = null;
    private DcMotorEx hdhex2 = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        corehex = hardwareMap.get(DcMotorEx.class, "corehex");
        hdhex1 = hardwareMap.get(DcMotorEx.class, "hdhex1");
        hdhex2 = hardwareMap.get(DcMotorEx.class, "hdhex2");
        corehex.setDirection(DcMotor.Direction.FORWARD);
        hdhex1.setDirection(DcMotor.Direction.FORWARD);
        hdhex2.setDirection(DcMotor.Direction.REVERSE);
        corehex.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hdhex1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hdhex2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

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

        double hdPower = gamepad1.right_trigger - gamepad1.left_trigger;
        corehex.setPower(corePower);
        hdhex1.setPower(hdPower);
        hdhex2.setPower(hdPower);
        telemetry.addData("Motors", "corehex (%.2f) hdhex (%.2f)", corePower, hdPower);
        telemetry.addData("Encoder Speeds", "hdhex1 %d hdhex2 %d", hdhex1.getCurrentPosition(), hdhex2.getCurrentPosition());
    }

    @Override
    public void stop() {
    }

}

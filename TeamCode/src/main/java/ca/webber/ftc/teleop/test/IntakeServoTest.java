package ca.webber.ftc.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;


@TeleOp(name = "Debug: Intake Servo Control", group = "Debug")
public class IntakeServoTest extends OpMode {

    private CRServo servoturn = null;
    private DcMotorEx intaker = null;
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        servoturn = hardwareMap.get(CRServo.class, "servoturn");
        intaker = hardwareMap.get(DcMotorEx.class, "intaker");
        servoturn.setDirection(CRServo.Direction.FORWARD);
        intaker.setDirection(DcMotorEx.Direction.FORWARD);

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
            servoturn.setPower(20);
            intaker.setPower(20);
        }
        else {
            servoturn.setPower(0);
            intaker.setPower(0);
        }
    }

    @Override
    public void stop() {
    }
}

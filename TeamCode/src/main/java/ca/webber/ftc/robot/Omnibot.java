package ca.webber.ftc.robot;

import com.arcrobotics.ftclib.drivebase.HDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Omnibot {
    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;
    private final GamepadEx gamepad1;
    private final GamepadEx gamepad2;
    private final Motor fL;
    private final Motor fR;
    private final Motor bL;
    private final Motor bR;
    private final HDrive drive;
    private final RevIMU imu;

    public Omnibot(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;
        this.gamepad1 = new GamepadEx(opMode.gamepad1);
        this.gamepad2 = new GamepadEx(opMode.gamepad2);

        imu = new RevIMU(hardwareMap);

        fL = new Motor(hardwareMap, "fL");
        fR = new Motor(hardwareMap, "fR");
        bL = new Motor(hardwareMap, "bL");
        bR = new Motor(hardwareMap, "fR");
        drive = new HDrive(fL, fR, bL, bR);
    }

    public void drive(double strafe, double forward, double turn) {
        drive.driveFieldCentric(strafe, forward, turn, imu.getAbsoluteHeading());
    }
    public void shoot() {

    }
}

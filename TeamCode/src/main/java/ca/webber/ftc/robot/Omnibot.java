package ca.webber.ftc.robot;

import com.arcrobotics.ftclib.drivebase.HDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import ca.webber.ftc.subsystems.Shooter;

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
    private final Shooter shooter;
    private final Motor conveyor, intake;
    private final Servo wobbleGrab, wobbleLift;

    public Omnibot(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;
        this.gamepad1 = new GamepadEx(opMode.gamepad1);
        this.gamepad2 = new GamepadEx(opMode.gamepad2);

        imu = new RevIMU(hardwareMap);

        fL = new Motor(hardwareMap, "fL");
        fR = new Motor(hardwareMap, "fR");
        bL = new Motor(hardwareMap, "bL");
        bR = new Motor(hardwareMap, "bR");
        drive = new HDrive(fL, fR, bL, bR);

        conveyor = new Motor(hardwareMap, "conveyor");
        intake = new Motor(hardwareMap, "intake");

        shooter = new Shooter(new Motor(hardwareMap, "leftShooter"), new Motor(hardwareMap, "rightShooter"), true);

        wobbleGrab = hardwareMap.get(Servo.class, "wobbleGrab");
        wobbleLift = hardwareMap.get(Servo.class, "wobbleLift");
    }

    public void drive(double strafe, double forward, double turn) {
        drive.driveFieldCentric(strafe, forward, turn, imu.getAbsoluteHeading());
    }

    public void shoot() {
    }

    public Shooter getShooter() {
        return shooter;
    }

    public Motor getConveyor() {
        return conveyor;
    }

    public Motor getIntake() {
        return intake;
    }

    public Servo getWobbleGrab() {
        return wobbleGrab;
    }

    public Servo getWobbleLift() {
        return wobbleLift;
    }
}

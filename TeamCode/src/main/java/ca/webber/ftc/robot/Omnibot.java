package ca.webber.ftc.robot;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.drivebase.HDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import ca.webber.ftc.robot.roadrunner.StandardTrackingWheelLocalizer;
import ca.webber.ftc.subsystems.Shooter;

public class Omnibot {
    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;
    private final GamepadEx gamepad1;
    private final GamepadEx gamepad2;
    private final HDrive drive;
    private final Shooter shooter;
    private final Motor conveyor, intake;
    private final Servo wobbleGrab, wobbleLift;
    //private final CRServo dropperServo;
    private final Motor fL, fR, bL, bR;
    private StandardTrackingWheelLocalizer odometry;

    public Omnibot(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;
        this.gamepad1 = new GamepadEx(opMode.gamepad1);
        this.gamepad2 = new GamepadEx(opMode.gamepad2);

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

        for (LynxModule module : hardwareMap.getAll(LynxModule.class)) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        //dropperServo = hardwareMap.get(CRServo.class, "dropperServo");

        // Initialize localizer
        odometry = new StandardTrackingWheelLocalizer(hardwareMap);
    }

    public Omnibot(OpMode opMode, boolean x) {
        this.hardwareMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;
        this.gamepad1 = new GamepadEx(opMode.gamepad1);
        this.gamepad2 = new GamepadEx(opMode.gamepad2);

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

        //dropperServo = hardwareMap.get(CRServo.class, "dropperServo");
    }

    public void drive(double strafe, double forward, double turn, double heading) {
        drive.driveFieldCentric(strafe, forward, turn, heading);
    }

    public StandardTrackingWheelLocalizer getOdometry() {
        return odometry;
    }

    public Pose2d getPose() {
        return odometry.getPoseEstimate();
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

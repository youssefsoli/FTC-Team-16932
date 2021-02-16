package ca.webber.ftc.robot;

import com.arcrobotics.ftclib.drivebase.HDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
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
    public static final double TRACKWIDTH = 27.42;
    public static final double CENTER_WHEEL_OFFSET = -TRACKWIDTH / 2.0;
    private final HDrive drive;
    private final Shooter shooter;
    private final Motor conveyor, intake;
    private final Servo wobbleGrab, wobbleLift;
    public static final double WHEEL_DIAMETER = 6;
    public static final double TICKS_PER_REV = 8192;
    public static final double DISTANCE_PER_PULSE = Math.PI * WHEEL_DIAMETER / TICKS_PER_REV;
    private final Motor fL, fR, bL, bR;
    private final MotorEx leftEncoder, rightEncoder, perpEncoder;
    private final HolonomicOdometry odometry;

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


        // Initialize localizer
        leftEncoder = new MotorEx(hardwareMap, "fR");
        rightEncoder = new MotorEx(hardwareMap, "bR");
        perpEncoder = new MotorEx(hardwareMap, "bL");

        leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        perpEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);

        rightEncoder.encoder.setDirection(Motor.Direction.REVERSE);
        perpEncoder.encoder.setDirection(Motor.Direction.REVERSE);

        odometry = new HolonomicOdometry(
                leftEncoder::getDistance,
                rightEncoder::getDistance,
                perpEncoder::getDistance,
                TRACKWIDTH,
                CENTER_WHEEL_OFFSET
        );
        odometry.updatePose(new Pose2d(0, 0, new Rotation2d()));
    }

    public void drive(double strafe, double forward, double turn, double heading) {
        drive.driveFieldCentric(strafe, forward, turn, heading);
    }

    public void shoot() {
    }

    public HolonomicOdometry getOdometry() {
        return odometry;
    }

    public Pose2d getPose() {
        return odometry.getPose();
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

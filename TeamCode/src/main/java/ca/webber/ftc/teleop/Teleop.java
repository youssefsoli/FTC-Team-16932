package ca.webber.ftc.teleop;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import ca.webber.ftc.robot.Omnibot;

@TeleOp(name = "Teleop", group = "Iterative Opmodes")
public class Teleop extends OpMode {
    private Omnibot omnibot;
    private GamepadEx gamepad1, gamepad2;

    // odometry stuff
    private MotorEx leftEncoder, rightEncoder, perpEncoder;
    private HolonomicOdometry odometry;

    public static final double TRACKWIDTH = 14.31;
    public static final double CENTER_WHEEL_OFFSET = 0.477;
    public static final double WHEEL_DIAMETER = 2.0;
    public static final double TICKS_PER_REV = 8192;
    public static final double DISTANCE_PER_PULSE = Math.PI * WHEEL_DIAMETER / TICKS_PER_REV;

    @Override
    public void init() {
        this.gamepad1 = new GamepadEx(super.gamepad1);
        this.gamepad2 = new GamepadEx(super.gamepad2);
        omnibot = new Omnibot(this);
        leftEncoder = new MotorEx(hardwareMap, "lEnc");
        rightEncoder = new MotorEx(hardwareMap, "rEnc");
        perpEncoder = new MotorEx(hardwareMap, "perpEnc");

        leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        perpEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);

        odometry = new HolonomicOdometry(
                leftEncoder::getDistance,
                rightEncoder::getDistance,
                perpEncoder::getDistance,
                TRACKWIDTH,
                CENTER_WHEEL_OFFSET
        );
        odometry.updatePose(new Pose2d(0, 0, new Rotation2d()));

    }

    @Override
    public void loop() {
        omnibot.drive(gamepad1.getLeftX(), gamepad1.getLeftY(), gamepad1.getRightX());
        odometry.updatePose();
        telemetry.addData("Robot Position at Init: ", odometry.getPose());
        telemetry.update();
    }
}
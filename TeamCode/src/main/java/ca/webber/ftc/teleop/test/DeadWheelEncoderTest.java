package ca.webber.ftc.teleop.test;

import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import ca.webber.ftc.robot.Omnibot;

@TeleOp(name = "Debug: Dead Wheel Encoder Test", group = "Debug")
public class DeadWheelEncoderTest extends OpMode {
    public static final double WHEEL_DIAMETER = 1.9685;
    public static final double TICKS_PER_REV = 32767;
    public static final double DISTANCE_PER_PULSE = Math.PI * WHEEL_DIAMETER / TICKS_PER_REV;
    private MotorEx leftEncoder, rightEncoder, perpEncoder = null;
    private HolonomicOdometry odometry;
    private Omnibot omnibot;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        leftEncoder = new MotorEx(hardwareMap, "fR");
        rightEncoder = new MotorEx(hardwareMap, "bR");
        perpEncoder = new MotorEx(hardwareMap, "bL");
        leftEncoder.encoder.setDirection(Motor.Direction.REVERSE);
        omnibot = new Omnibot(this);
        odometry = new HolonomicOdometry(
                leftEncoder::getDistance,
                rightEncoder::getDistance,
                perpEncoder::getDistance,
                10.625,
                6
        );

        leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        perpEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);

        odometry.updatePose(new Pose2d(0, 0, new Rotation2d()));
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        telemetry.addData("Encoders", "encoder1 (%d)", leftEncoder.getCurrentPosition());
        telemetry.addData("Encoders", "encoder2 (%d)", rightEncoder.getCurrentPosition());
        telemetry.addData("Encoders", "encoder3 (%d)", perpEncoder.getCurrentPosition());

        odometry.updatePose();
        telemetry.addData("Robot Position now: ", odometry.getPose());
        if (odometry.getPose().getRotation().getDegrees() < 40)
            omnibot.drive(0, 0, -0.2, 0);
        else if (odometry.getPose().getRotation().getDegrees() > 100)
            omnibot.drive(0, 0, 0.2, 0);
        telemetry.update();
    }

    @Override
    public void stop() {
    }

}

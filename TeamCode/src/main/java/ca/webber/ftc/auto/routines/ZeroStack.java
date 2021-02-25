package ca.webber.ftc.auto.routines;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import ca.webber.ftc.robot.Omnibot;
import ca.webber.ftc.robot.roadrunner.MecanumDrive;

public class ZeroStack extends UGRoutine {

    public ZeroStack(MecanumDrive drive, Omnibot robot, LinearOpMode opMode) {
        super(drive, robot, opMode);
    }

    @Override
    public void run() {
        omnibot.getIntake().setRunMode(Motor.RunMode.RawPower);
        omnibot.getWobbleGrab().setPosition(1);

        // Drop wobble at zone C
        drive.followTrajectory(drive.trajectoryBuilder(drive.getPoseEstimate(), Math.toRadians(-10))
                .lineToConstantHeading(new Vector2d(44, -60))
                .addTemporalMarker(7, () -> {
                    omnibot.getWobbleLift().setPosition(0);
                    omnibot.getWobbleGrab().setPosition(0);
                })
                .build());

        // Start up the shooter
        omnibot.getShooter().setVelocity(1);

        // Move to the shooting location
        drive.followTrajectory(drive.trajectoryBuilder(drive.getPoseEstimate(), Math.toRadians(160))
                .splineToConstantHeading(new Vector2d(-5, -12), Math.toRadians(150))
                // Shoot the rings
                .addTemporalMarker(8, () -> {
                    omnibot.getIntake().set(1);
                    opMode.sleep(500);
                    omnibot.getIntake().set(0);
                    drive.turn(Math.toRadians(6));
                    opMode.sleep(2000);
                    omnibot.getIntake().set(1);
                    opMode.sleep(500);
                    omnibot.getIntake().set(0);
                    drive.turn(Math.toRadians(4.5));
                    opMode.sleep(2000);
                    omnibot.getIntake().set(1);
                    opMode.sleep(500);
                    omnibot.getIntake().set(0);
                })
                .build());

        // Stop the shooter
        omnibot.getShooter().setVelocity(0);

        // Move towards the second wobble goal
        drive.followTrajectory(drive.trajectoryBuilder(drive.getPoseEstimate(), Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-48, 0, Math.toRadians(-90)), Math.toRadians(180))
                .build());

        // Move forward and grab the wobble goal
        drive.followTrajectory(drive.trajectoryBuilder(drive.getPoseEstimate(), Math.toRadians(90))
                .forward(21)
                .addTemporalMarker(2, () -> {
                    omnibot.getWobbleGrab().setPosition(1);
                    opMode.sleep(1000);
                    omnibot.getWobbleLift().setPosition(1);
                })
                .build());
        opMode.sleep(2000);
    }
}

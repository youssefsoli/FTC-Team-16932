package ca.webber.ftc.auto.routines;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import ca.webber.ftc.robot.Omnibot;
import ca.webber.ftc.robot.roadrunner.MecanumDrive;

public class OneStack extends UGRoutine {

    public OneStack(MecanumDrive drive, Omnibot robot, LinearOpMode opMode) {
        super(drive, robot, opMode);
    }

    @Override
    public void run() {
        omnibot.getIntake().setRunMode(Motor.RunMode.RawPower);
        omnibot.getWobbleGrab().setPosition(1);

        // Drop wobble at zone C
        drive.followTrajectory(drive.trajectoryBuilder(drive.getPoseEstimate(), Math.toRadians(-10))
                .addDisplacementMarker(120, () -> {
                    omnibot.getWobbleLift().setPosition(0);
                    omnibot.getWobbleGrab().setPosition(0);
                })
                .splineToConstantHeading(new Vector2d(44, -60), 0)
                .build());

        // Start up the shooter
        omnibot.getShooter().setVelocity(0.55);

        // Move to the shooting location
        drive.followTrajectory(drive.trajectoryBuilder(drive.getPoseEstimate(), Math.toRadians(160))
                .splineToConstantHeading(new Vector2d(-4, -14), Math.toRadians(150))
                // Shoot the rings
                .addDisplacementMarker(() -> {
                    omnibot.getIntake().set(1);
                    opMode.sleep(500);
                    omnibot.getIntake().set(0);
                    drive.turn(Math.toRadians(6));
                    opMode.sleep(1000);
                    omnibot.getIntake().set(1);
                    opMode.sleep(500);
                    omnibot.getIntake().set(0);
                    drive.turn(Math.toRadians(7));
                    opMode.sleep(1000);
                    omnibot.getShooter().setVelocity(0.5);
                    omnibot.getIntake().set(1);
                    opMode.sleep(500);
                    omnibot.getIntake().set(0);
                })
                .build());

        // Stop the shooter
        omnibot.getShooter().setVelocity(0);

        // Move towards the second wobble goal
        drive.followTrajectory(drive.trajectoryBuilder(drive.getPoseEstimate(), Math.toRadians(-180))
                .splineToSplineHeading(new Pose2d(-43, -20, Math.toRadians(-110)), Math.toRadians(-120))
                .addDisplacementMarker(() -> {
                    omnibot.getWobbleGrab().setPosition(1);
                    opMode.sleep(1000);
                    omnibot.getWobbleLift().setPosition(1);
                })
                .build());

        // Drop wobble at zone C
        drive.followTrajectory(drive.trajectoryBuilder(drive.getPoseEstimate(), 0)
                .splineToLinearHeading(new Pose2d(44, -60, 0), Math.toRadians(-40))
                .addDisplacementMarker(() -> {
                    omnibot.getWobbleLift().setPosition(0);
                    omnibot.getWobbleGrab().setPosition(0);
                })
                .build());

        drive.followTrajectory(drive.trajectoryBuilder(drive.getPoseEstimate(), Math.toRadians(90))
                .back(30)
                .build());
    }
}

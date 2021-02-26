package ca.webber.ftc.auto.routines;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import ca.webber.ftc.robot.Omnibot;
import ca.webber.ftc.robot.roadrunner.MecanumDrive;

@Autonomous(name = "Old Four Stack")
public class OldFourStack extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Omnibot omnibot = new Omnibot(this, false);
        MecanumDrive drive = new MecanumDrive(hardwareMap);

        omnibot.getIntake().setRunMode(Motor.RunMode.RawPower);
        omnibot.getWobbleGrab().setPosition(1);

        Pose2d startPose = new Pose2d(-60, -48, 0);

        drive.setPoseEstimate(startPose);

        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectory(drive.trajectoryBuilder(startPose, Math.toRadians(-10))
                .splineToConstantHeading(new Vector2d(44, -60), 0)
                .addTemporalMarker(7, () -> {
                    omnibot.getWobbleLift().setPosition(0);
                    omnibot.getWobbleGrab().setPosition(0);
                })
                .build());

        omnibot.getShooter().setVelocity(1);
        drive.followTrajectory(drive.trajectoryBuilder(new Pose2d(drive.getPoseEstimate().getX(),
                drive.getPoseEstimate().getY(), 0), Math.toRadians(160))
                .splineToConstantHeading(new Vector2d(-5, -12), Math.toRadians(150))
                .addTemporalMarker(8, () -> {
                    omnibot.getIntake().set(1);
                    sleep(500);
                    omnibot.getIntake().set(0);
                    drive.turn(Math.toRadians(6));
                    sleep(2000);
                    omnibot.getIntake().set(1);
                    sleep(500);
                    omnibot.getIntake().set(0);
                    drive.turn(Math.toRadians(4.5));
                    sleep(2000);
                    omnibot.getIntake().set(1);
                    sleep(500);
                    omnibot.getIntake().set(0);
                })
                .build());
        omnibot.getShooter().setVelocity(0);

        drive.followTrajectory(drive.trajectoryBuilder(new Pose2d(drive.getPoseEstimate().getX(),
                drive.getPoseEstimate().getY(), 0), Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-48, 0, Math.toRadians(-90)), Math.toRadians(180))
                .build());

        drive.followTrajectory(drive.trajectoryBuilder(new Pose2d(drive.getPoseEstimate().getX(),
                drive.getPoseEstimate().getY(), drive.getPoseEstimate().getHeading()), Math.toRadians(90))
                .forward(23)
                .addTemporalMarker(2, () -> {
                    omnibot.getWobbleGrab().setPosition(1);
                    sleep(1000);
                    omnibot.getWobbleLift().setPosition(1);
                })
                .build());
        sleep(2000);
    }
}
package ca.webber.ftc.auto.routines;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import ca.webber.ftc.robot.Omnibot;
import ca.webber.ftc.robot.roadrunner.SampleMecanumDrive;

@Disabled
@Autonomous(name = "One Stack")
public class OneStack extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Omnibot omnibot = new Omnibot(this);

        omnibot.getIntake().setRunMode(Motor.RunMode.RawPower);
        omnibot.getWobbleGrab().setPosition(0);
        omnibot.getWobbleLift().setPosition(0);

        Pose2d startPose = new Pose2d(-60, -36, 0);

        drive.setPoseEstimate(startPose);

        waitForStart();

        if (isStopRequested()) return;

        omnibot.getShooter().setVelocity(1);

        Trajectory traj = drive.trajectoryBuilder(startPose)
                .splineToConstantHeading(new Vector2d(-9, -36), 0)
                .addTemporalMarker(5, () -> {
                    omnibot.getIntake().set(1);
                    sleep(700);
                    omnibot.getIntake().set(0);
                    sleep(2000);
                    omnibot.getIntake().set(1);
                    sleep(1000);
                })
                .build();
        drive.followTrajectory(traj);


    }
}

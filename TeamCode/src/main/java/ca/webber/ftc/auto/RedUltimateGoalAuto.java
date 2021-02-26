package ca.webber.ftc.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.vision.UGContourRingPipeline.Height;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import ca.webber.ftc.auto.routines.FourStack;
import ca.webber.ftc.auto.routines.OneStack;
import ca.webber.ftc.auto.routines.UGRoutine;
import ca.webber.ftc.auto.routines.ZeroStack;
import ca.webber.ftc.robot.Omnibot;
import ca.webber.ftc.robot.roadrunner.MecanumDrive;
import ca.webber.ftc.subsystems.PoseStorage;
import ca.webber.ftc.subsystems.Vision;

@Autonomous(name = "Red UG Auto")
public class RedUltimateGoalAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Omnibot omnibot = new Omnibot(this, false);
        MecanumDrive drive = new MecanumDrive(hardwareMap);
        Vision vision = new Vision(hardwareMap);

        omnibot.getIntake().setRunMode(Motor.RunMode.RawPower);
        omnibot.getWobbleGrab().setPosition(1);

        // Set initial position
        drive.setPoseEstimate(new Pose2d(-60, -48, 0));

        while (!isStarted()) {
            telemetry.addData("Vision ready!", vision.getHeight());
            telemetry.addData("Pose: ", drive.getPoseEstimate());
            telemetry.update();
        }

        waitForStart();

        if (isStopRequested()) return;

        Height height = vision.getHeight();
        vision.stop();

        UGRoutine routine = null;

        telemetry.addData("Pose: ", drive.getPoseEstimate());
        telemetry.update();

        switch (height) {
            case ZERO:
                routine = new ZeroStack(drive, omnibot, this);
                break;
            case ONE:
                routine = new OneStack(drive, omnibot, this);
                break;
            case FOUR:
                routine = new FourStack(drive, omnibot, this);
                break;
            default:
                telemetry.addLine("Unknown Height!");
                telemetry.update();
        }

        routine.run();
        PoseStorage.currentPose = drive.getPoseEstimate();
    }
}

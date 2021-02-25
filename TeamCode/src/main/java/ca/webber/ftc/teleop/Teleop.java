package ca.webber.ftc.teleop;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import ca.webber.ftc.robot.Omnibot;
import ca.webber.ftc.robot.roadrunner.StandardTrackingWheelLocalizer;
import ca.webber.ftc.subsystems.PoseStorage;

@TeleOp(name = "Teleop", group = "Iterative Opmodes")
public class Teleop extends OpMode {
    private Omnibot omnibot;
    private GamepadEx gamepad1, gamepad2;
    private StandardTrackingWheelLocalizer odometry;
    private double conveyorSpeed = 0, intakeSpeed = 0;

    @Override
    public void init() {
        this.gamepad1 = new GamepadEx(super.gamepad1);
        this.gamepad2 = new GamepadEx(super.gamepad2);
        omnibot = new Omnibot(this);

        odometry = omnibot.getOdometry();
        odometry.setPoseEstimate(PoseStorage.currentPose);

        telemetry.addData("Robot Position at Init: ", odometry.getPoseEstimate());

        omnibot.getConveyor().setRunMode(Motor.RunMode.RawPower);
        omnibot.getIntake().setRunMode(Motor.RunMode.RawPower);
    }

    @Override
    public void loop() {
        odometry.update();
        telemetry.addData("Robot Position now: ", odometry.getPoseEstimate());

        double speedMultiplier = 3 * gamepad1.gamepad.right_trigger / 4;
        if (gamepad1.gamepad.right_bumper)
            speedMultiplier = 1;
        omnibot.drive(
                Range.clip(Math.pow(gamepad1.getLeftX(), 3), -speedMultiplier, speedMultiplier),
                Range.clip(Math.pow(gamepad1.getLeftY(), 3), -speedMultiplier, speedMultiplier),
                Range.clip(Math.pow(-gamepad1.getRightX(), 3), -0.7, 0.7),
                odometry.getPoseEstimate().getHeading());

        if (gamepad2.gamepad.right_bumper)
            intakeSpeed = -1;
        else if (gamepad2.gamepad.left_bumper)
            intakeSpeed = 1;
        else
            intakeSpeed = 0;

        conveyorSpeed = gamepad2.gamepad.left_trigger;
        omnibot.getShooter().setVelocity(gamepad2.gamepad.right_trigger);
        telemetry.addData("Shooter power: ", gamepad2.gamepad.right_trigger);
        omnibot.getConveyor().set(conveyorSpeed);
        omnibot.getIntake().set(intakeSpeed);

        omnibot.getWobbleGrab().setPosition(gamepad2.gamepad.a ? 1 : 0);
        omnibot.getWobbleLift().setPosition(gamepad2.gamepad.b ? 1 : 0);

        telemetry.update();
    }
}
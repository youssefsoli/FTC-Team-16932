package ca.webber.ftc.teleop;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import ca.webber.ftc.robot.Omnibot;

@TeleOp(name = "Teleop", group = "Iterative Opmodes")
public class Teleop extends OpMode {
    private Omnibot omnibot;
    private GamepadEx gamepad1, gamepad2;
    private HolonomicOdometry odometry;

    // odometry stuff
    private GamepadButton aButton, bButton;
    private double conveyorSpeed = 0, intakeSpeed = 0;

    @Override
    public void init() {
        this.gamepad1 = new GamepadEx(super.gamepad1);
        this.gamepad2 = new GamepadEx(super.gamepad2);
        omnibot = new Omnibot(this);

        odometry = omnibot.getOdometry();
        odometry.updatePose(new Pose2d(0, 0, new Rotation2d()));
        telemetry.addData("Robot Position at Init: ", odometry.getPose());

        aButton = new GamepadButton(gamepad1, GamepadKeys.Button.A);
        bButton = new GamepadButton(gamepad1, GamepadKeys.Button.B);
        omnibot.getConveyor().setRunMode(Motor.RunMode.RawPower);
        omnibot.getIntake().setRunMode(Motor.RunMode.RawPower);

        aButton
                .whenPressed(new InstantCommand(() -> conveyorSpeed = 1))
                .whenReleased(new InstantCommand(() -> conveyorSpeed = 0));

        bButton
                .whenPressed(new InstantCommand(() -> intakeSpeed = 1))
                .whenReleased(new InstantCommand(() -> intakeSpeed = 0));
    }

    @Override
    public void loop() {
        odometry.updatePose();
        telemetry.addData("Robot Position now: ", odometry.getPose());
        omnibot.drive(
                Range.clip(Math.pow(gamepad1.getLeftX(), 3), -0.5, 0.5),
                Range.clip(Math.pow(gamepad1.getLeftY(), 3), -0.5, 0.5),
                Range.clip(Math.pow(-gamepad1.getRightX(), 3), -0.5, 0.5),
                odometry.getPose().getRotation().getDegrees());
        if (gamepad1.gamepad.right_bumper)
            intakeSpeed = -1;
        else if (gamepad1.gamepad.left_bumper)
            intakeSpeed = 1;
        else
            intakeSpeed = 0;

        conveyorSpeed = gamepad1.gamepad.left_trigger;
        omnibot.getShooter().setVelocity(gamepad1.gamepad.right_trigger);
        telemetry.addData("Shooter power: ", gamepad1.gamepad.right_trigger);
        omnibot.getConveyor().set(conveyorSpeed);
        omnibot.getIntake().set(intakeSpeed);
        //odometry.updatePose();

        omnibot.getWobbleGrab().setPosition(gamepad1.gamepad.a ? 1 : 0);
//        if(gamepad1.gamepad.a)
//            omnibot.getWobbleGrab().setPosition(1);
//        else
//            omnibot.getWobbleGrab().setPosition(0);

        omnibot.getWobbleLift().setPosition(gamepad1.gamepad.b ? 1 : 0);
//        if(gamepad1.gamepad.b)
//            omnibot.getWobbleLift().setPosition(1);
//        else
//            omnibot.getWobbleLift().setPosition(0);

        telemetry.update();
    }
}
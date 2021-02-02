package ca.webber.ftc.ftcliboverhaul.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class ShooterSystem extends SubsystemBase {
    private final Motor leftShooter, rightShooter;

    public ShooterSystem(Motor leftShooter, Motor rightShooter) {
        this.leftShooter = leftShooter;
        this.rightShooter = rightShooter;

        leftShooter.setRunMode(Motor.RunMode.RawPower);
        rightShooter.setRunMode(Motor.RunMode.RawPower);

        leftShooter.motor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightShooter.motor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void setVelocity(double velocity) {
        leftShooter.set(velocity);
        rightShooter.set(velocity);
    }
}

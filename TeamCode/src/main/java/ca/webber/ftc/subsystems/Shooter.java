package ca.webber.ftc.subsystems;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Shooter {

    private final Motor leftShooter;
    private final Motor rightShooter;

    public Shooter(Motor leftShooter, Motor rightShooter) {
        this.leftShooter = leftShooter;
        this.rightShooter = rightShooter;

        leftShooter.setRunMode(Motor.RunMode.VelocityControl);
        rightShooter.setRunMode(Motor.RunMode.VelocityControl);
        leftShooter.motor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightShooter.motor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void setVelocity(double velocity) {
        leftShooter.set(velocity);
        rightShooter.set(velocity);
    }
}

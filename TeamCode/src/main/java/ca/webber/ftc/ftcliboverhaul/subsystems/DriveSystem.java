package ca.webber.ftc.ftcliboverhaul.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.HDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class DriveSystem extends SubsystemBase {
    private final HDrive drive;
    private final Motor fL, fR, bL, bR;

    public DriveSystem(Motor fL, Motor fR, Motor bL, Motor bR) {
        this.fL = fL;
        this.fR = fR;
        this.bL = bL;
        this.bR = bR;

        drive = new HDrive(fL, fR, bL, bR);
    }

    public void drive(double strafe, double forward, double turn) {
        drive.driveRobotCentric(strafe, forward, turn);
    }
}

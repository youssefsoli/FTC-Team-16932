package ca.webber.ftc.auto.routines;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import ca.webber.ftc.robot.Omnibot;
import ca.webber.ftc.robot.roadrunner.MecanumDrive;

public abstract class UGRoutine {
    protected final MecanumDrive drive;
    protected final Omnibot omnibot;
    protected final LinearOpMode opMode;

    public UGRoutine(MecanumDrive drive, Omnibot omnibot, LinearOpMode opMode) {
        this.drive = drive;
        this.omnibot = omnibot;
        this.opMode = opMode;
    }

    public abstract void run();
}

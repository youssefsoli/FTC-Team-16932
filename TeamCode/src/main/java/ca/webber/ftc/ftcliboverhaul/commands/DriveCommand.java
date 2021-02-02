package ca.webber.ftc.ftcliboverhaul.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import java.util.function.DoubleSupplier;

import ca.webber.ftc.ftcliboverhaul.subsystems.DriveSystem;

public class DriveCommand extends CommandBase {
    private final DriveSystem driveSystem;
    private final DoubleSupplier strafe, forward, turn;

    public DriveCommand(DriveSystem driveSystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn) {
        this.driveSystem = driveSystem;
        this.strafe = strafe;
        this.forward = forward;
        this.turn = turn;

        addRequirements(driveSystem);
    }

    @Override
    public void execute() {
        driveSystem.drive(strafe.getAsDouble(), forward.getAsDouble(), turn.getAsDouble());
    }
}

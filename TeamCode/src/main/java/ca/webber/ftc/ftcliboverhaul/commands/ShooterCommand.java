package ca.webber.ftc.ftcliboverhaul.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import java.util.function.DoubleSupplier;

import ca.webber.ftc.ftcliboverhaul.subsystems.ShooterSystem;

public class ShooterCommand extends CommandBase {
    private final ShooterSystem shooterSystem;
    private final DoubleSupplier speed;

    public ShooterCommand(ShooterSystem shooterSystem, DoubleSupplier speed) {
        this.shooterSystem = shooterSystem;
        this.speed = speed;

        addRequirements(shooterSystem);
    }

    @Override
    public void execute() {
        shooterSystem.setVelocity(speed.getAsDouble());
    }
}

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;
import frc.robot.subsystems.ClawGripSubsystem;

public class ClawGripOut extends CommandBase {

    private final ClawGripSubsystem m_clawGripSubsystem;
 
    public ClawGripOut(ClawGripSubsystem subsystem) {

        m_clawGripSubsystem = subsystem;
        addRequirements(m_clawGripSubsystem);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_clawGripSubsystem.OpenGrip();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }

}

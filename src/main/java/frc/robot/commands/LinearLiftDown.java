package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LinearLiftSubsystem;

public class LinearLiftDown extends CommandBase {

    private final LinearLiftSubsystem m_linearLiftSubsystem;

    public LinearLiftDown(LinearLiftSubsystem subsystem) {

        m_linearLiftSubsystem = subsystem;
        addRequirements(m_linearLiftSubsystem);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (!m_linearLiftSubsystem.ReadGroundSwitch()) {
            m_linearLiftSubsystem.SetLinearLiftMotors(-0.55);
        } else {
            m_linearLiftSubsystem.SetLinearLiftMotors(0);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_linearLiftSubsystem.SetLinearLiftMotors(0);
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

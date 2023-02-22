package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LinearLiftSubsystem;

public class LinearLiftToPosition extends CommandBase {

    private final LinearLiftSubsystem m_linearLiftSubsystem;

    double liftPosition;
    double liftSpeed = 0.5;
    double movementThreshold = 10;

    public LinearLiftToPosition(LinearLiftSubsystem subsystem, double liftPosition) {

        m_linearLiftSubsystem = subsystem;
        addRequirements(m_linearLiftSubsystem);

        this.liftPosition = liftPosition;

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (m_linearLiftSubsystem.GetPosition() < liftPosition) {
            m_linearLiftSubsystem.SetLinearLiftMotors(liftSpeed);
        } else if (m_linearLiftSubsystem.GetPosition() > liftPosition) {
            m_linearLiftSubsystem.SetLinearLiftMotors(-liftSpeed);
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
        return ((m_linearLiftSubsystem.GetPosition() < liftPosition + movementThreshold) && (m_linearLiftSubsystem.GetPosition() > liftPosition - movementThreshold));
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }

}

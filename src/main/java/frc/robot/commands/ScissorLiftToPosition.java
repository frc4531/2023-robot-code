package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ScissorLiftSubsystem;

public class ScissorLiftToPosition extends CommandBase {

    private final ScissorLiftSubsystem m_scissorLiftSubsystem;

    double scissorPosition;
    double scissorSpeed = 0.5;
    double movementThreshold = 10;

    public ScissorLiftToPosition(ScissorLiftSubsystem subsystem, double scissorPosition) {

        m_scissorLiftSubsystem = subsystem;
        addRequirements(m_scissorLiftSubsystem);

        this.scissorPosition = scissorPosition;

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (m_scissorLiftSubsystem.GetPostion() < scissorPosition) {
            m_scissorLiftSubsystem.SetScissorLiftMotors(scissorSpeed);
        } else if (m_scissorLiftSubsystem.GetPostion() > scissorPosition) {
            m_scissorLiftSubsystem.SetScissorLiftMotors(-scissorSpeed);
        } else {
            m_scissorLiftSubsystem.SetScissorLiftMotors(0);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_scissorLiftSubsystem.SetScissorLiftMotors(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return ((m_scissorLiftSubsystem.GetPostion() < scissorPosition + movementThreshold) && (m_scissorLiftSubsystem.GetPostion() > scissorPosition - movementThreshold));
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }

}

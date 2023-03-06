package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LinearLiftSubsystem;

public class LinearLiftToPosition extends CommandBase {

    private final LinearLiftSubsystem m_linearLiftSubsystem;

    int targetPosition;
    double liftSpeed = 0.65;
    double movementThreshold = 55;
    boolean IsCentering;

    public LinearLiftToPosition(LinearLiftSubsystem subsystem, int pos) {

        m_linearLiftSubsystem = subsystem;
        addRequirements(m_linearLiftSubsystem);

        this.targetPosition = pos;

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        if(targetPosition == m_linearLiftSubsystem.GetStage()) {
            IsCentering = true;
        } else {
            IsCentering = false;
        }

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (IsCentering) { // If centering the lift on the current stage previously set
            if (m_linearLiftSubsystem.GetPosition() < 0) { // If the lift is lower than it is supposed to be, move up
                m_linearLiftSubsystem.SetLinearLiftMotors(liftSpeed*0.5);
            } else if (m_linearLiftSubsystem.GetPosition() > 0) { // If the lift is higher that it is supposed to be, move down
                m_linearLiftSubsystem.SetLinearLiftMotors(-liftSpeed*0.5);
            }
        } else { // If moving the lift from one stage to another, different stage
            if (targetPosition > m_linearLiftSubsystem.GetStage()) { // Going up!
                m_linearLiftSubsystem.SetLinearLiftMotors(liftSpeed);
            } else if(targetPosition < m_linearLiftSubsystem.GetStage()) { // Going down!
                m_linearLiftSubsystem.SetLinearLiftMotors(-liftSpeed);
            }   
        }
    }


    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_linearLiftSubsystem.SetLinearLiftMotors(0);
        
        if (!interrupted) {
            m_linearLiftSubsystem.ResetEncoder();
        }
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
       if (IsCentering) {
        // If centering the lift on current stage, finish the command when the lift is within margin of error of the zero point
        return ((m_linearLiftSubsystem.GetPosition() >= -movementThreshold) && (m_linearLiftSubsystem.GetPosition() <= movementThreshold));
       } else {
        // End the command when the subsystem says we're at the target position (limit switch is triggered)
         return m_linearLiftSubsystem.GetStage() == targetPosition;
       }
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }

}

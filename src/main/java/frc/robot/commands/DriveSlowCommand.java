
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.RobotContainer;

public class DriveSlowCommand extends CommandBase {

  private final DriveSubsystem m_driveSubsystem;

  public DriveSlowCommand(DriveSubsystem subsystem) {
    
    m_driveSubsystem = subsystem;
    addRequirements(m_driveSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveSubsystem.shiftToMecanum();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveSubsystem.SlowMecanumDriveRobot(RobotContainer.getInstance().getDriveStick().getY(), -RobotContainer.getInstance().getDriveStick().getX(), -RobotContainer.getInstance().getDriveStick().getZ());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

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

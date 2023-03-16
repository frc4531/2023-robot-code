// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveToPosWithPID extends PIDCommand {
  private final DriveSubsystem m_driveSubsystem;

  /** Creates a new DriveToPosWithPID. */
  public DriveToPosWithPID(DriveSubsystem subsystem, double TargetPoint) {
    super(
        // The controller that the command will use
        new PIDController(0.0005, 0, 0),
        // This should return the measurement
        () -> subsystem.GetPosition(),
        // This should return the setpoint (can also be a constant)
        () -> TargetPoint,
        // This uses the output
        output -> {
          subsystem.TankDriveRobot(-output, 0);
          SmartDashboard.putNumber("Test PID Output", output);
          // Use the output here
        },
        subsystem
        );
        m_driveSubsystem = subsystem;
        addRequirements(m_driveSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    getController().setTolerance(5);
    getController().setIntegratorRange(-0.5, 0.5);
  }

  @Override
  public void initialize() {
    m_controller.reset();
    m_driveSubsystem.ResetEncoder();
    m_driveSubsystem.shiftToTank();
  }

  @Override
  public void end(boolean interrupted) {
    m_useOutput.accept(0);
    m_driveSubsystem.shiftToMecanum();
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return getController().atSetpoint();
    return false;
  }
}

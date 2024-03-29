package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;

public class DriveShift extends CommandBase {

    private final DriveSubsystem m_driveSubsystem;

    public DriveShift(DriveSubsystem subsystem) {

        m_driveSubsystem = subsystem;
        addRequirements(m_driveSubsystem);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_driveSubsystem.shiftToTank();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_driveSubsystem.TankDriveRobot(RobotContainer.getInstance().getDriveStick().getY(), RobotContainer.getInstance().getDriveStick().getZ());
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

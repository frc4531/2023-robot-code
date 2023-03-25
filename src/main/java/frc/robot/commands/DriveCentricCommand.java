package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCentricCommand extends CommandBase {

    private final DriveSubsystem m_driveSubsystem;

    public DriveCentricCommand(DriveSubsystem subsystem) {

        m_driveSubsystem = subsystem;
        addRequirements(m_driveSubsystem);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_driveSubsystem.shiftToMecanum();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
            double forward = RobotContainer.getInstance().getDriveStick().getY();
            double strafe = -RobotContainer.getInstance().getDriveStick().getX();
            double rot = -RobotContainer.getInstance().getDriveStick().getZ()*0.4;
            double GyroDegrees = m_driveSubsystem.ReadHeading();
            double GyroRadians = GyroDegrees * (Math.PI / 180);

            double temp = (forward * Math.cos(GyroRadians)) + (strafe * Math.sin(GyroRadians));
            strafe = (-forward * Math.sin(GyroRadians) + (strafe * Math.cos(GyroRadians)));
            forward = temp;

            m_driveSubsystem.MecanumDriveRobot(forward, strafe, rot);
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

package frc.robot.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveTurnRight extends CommandBase {

    private final DriveSubsystem m_driveSubsystem;

    double DriveSpeed;

    double InitialAngle;
    double CurrentAngle;
    double GoalAngle;
    double Offset = 0;

    public DriveTurnRight(DriveSubsystem subsystem, double goalAngle) {

        m_driveSubsystem = subsystem;
        addRequirements(m_driveSubsystem);

        this.GoalAngle = goalAngle;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {            
    DriveSpeed = Preferences.getDouble("Gyro-Turn-Speed", 0.1);
    InitialAngle = m_driveSubsystem.ReadHeading();

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        CurrentAngle = m_driveSubsystem.ReadHeading();
        SmartDashboard.putNumber("Gyro-Angle", CurrentAngle);

        m_driveSubsystem.TankDriveRobot(0, DriveSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_driveSubsystem.TankDriveRobot(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return CurrentAngle <= (InitialAngle + GoalAngle);
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }

}

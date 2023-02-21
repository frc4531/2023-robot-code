package frc.robot.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveToPosition extends CommandBase {

    private final DriveSubsystem m_driveSubsystem;

    double BaseSpeed; //Defines our starting, slower speed
    double MaxSpeed; //Defines our maximum speed
    double Distance; //Determines the distance we need to travel (encoder ticks)
    double CurrentSpeed; //Continuously stores current speed

    public DriveToPosition(DriveSubsystem subsystem, double distance) {

        m_driveSubsystem = subsystem;
        addRequirements(m_driveSubsystem);

        this.Distance = distance;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_driveSubsystem.ResetGyro(); //Reset gyro to zero degrees
        m_driveSubsystem.ResetEncoder(); //Start by reseting encoder to zero

        BaseSpeed = Preferences.getDouble("StraightGyro-BaseSpeed", 0.1); //Set our base speed on Robot Preferences
        MaxSpeed = Preferences.getDouble("StraightGyro-MaxSpeed", 0.1); //Set our max speed on Robot Preferences

        CurrentSpeed = BaseSpeed; //sets our current speed to the base (slowest) speed
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Double Heading = m_driveSubsystem.ReadHeading(); //Grab our current angle from gyro
        SmartDashboard.putNumber("Gyro-Angle", Heading); //Post current angle to SmartDashBoard for debug

        if (CurrentSpeed <= MaxSpeed){//if we haven't hit max
            CurrentSpeed += 0.005; //accelerate
        } 

        if(Distance - 10 <= m_driveSubsystem.GetPosition() && CurrentSpeed >= BaseSpeed){ //if we are about to hit our target distance
            CurrentSpeed = (CurrentSpeed - 0.02); //decelerate
        }

        SmartDashboard.putNumber("StraightGyro-CurrentSpeed", CurrentSpeed);

        Double left = (CurrentSpeed + (0.025 * Heading)); //slightly adjust left side based on current angle
        Double right = (CurrentSpeed + (0.025 * Heading)); //slightly adjust right side based on current angle

        m_driveSubsystem.DifferentialDriveRobot(left, right);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_driveSubsystem.DifferentialDriveRobot(0, 0); //brake when we hit our final destination
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return m_driveSubsystem.GetPosition() < Distance; //finish command if we have reached our final destination
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }

}

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {

    public boolean IsLinedUp;

    NetworkTableEntry xEntry;
    NetworkTableEntry yEntry;
    NetworkTableEntry aEntry;
    NetworkTableEntry vEntry;

    public double visX;
    public double visY;
    public double visA;
    public double visV;

    public VisionSubsystem() {
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable table = inst.getTable("Limelight");

        xEntry = table.getEntry("tx");
        yEntry = table.getEntry("ty");
        aEntry = table.getEntry("ta");
        vEntry = table.getEntry("tv");

        IsLinedUp = false;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        visX = xEntry.getDouble(0.0);
        visY = yEntry.getDouble(0.0);
        visA = aEntry.getDouble(0.0);
        visV = vEntry.getDouble(0.0);

        SmartDashboard.putNumber("Limelight X Value", visX);
        SmartDashboard.putNumber("Limelight Y Value", visY);
        SmartDashboard.putNumber("Limelight A Value", visA);
        SmartDashboard.putNumber("Limelight V Value", visV);

        SmartDashboard.putBoolean("Lined Up?", IsLinedUp);
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}


package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class LinearLiftSubsystem extends SubsystemBase {

    private WPI_TalonFX linearPulleyMotor;
    private DigitalInput GroundSwitch;
    private DigitalInput LowSwitch;
    private DigitalInput MediumSwitch;
    private DigitalInput HighSwitch;

    private int CurrentPosition;

    public LinearLiftSubsystem() {
        linearPulleyMotor = new WPI_TalonFX(9);
        linearPulleyMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        GroundSwitch = new DigitalInput(12);
        LowSwitch = new DigitalInput(15);
        MediumSwitch = new DigitalInput(16);
        HighSwitch = new DigitalInput(17);


    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putBoolean("Ground Switch Triggered", ReadGroundSwitch());
        SmartDashboard.putBoolean("Low Switch Triggered", ReadLowSwitch());
        SmartDashboard.putBoolean("Medium Switch Triggered", ReadMediumSwitch());
        SmartDashboard.putBoolean("High Switch Triggered", ReadHighSwitch());
        SmartDashboard.putNumber("LiftStageNumber", CurrentPosition);

        if (ReadGroundSwitch()) {
            CurrentPosition = 0;
        } else if (ReadLowSwitch()) {
            CurrentPosition = 1;
        } else if (ReadMediumSwitch()) {
            CurrentPosition = 2;
        } else if (ReadHighSwitch()) {
            CurrentPosition = 3;
        }

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public void SetLinearLiftMotors(double Speed) {
        linearPulleyMotor.set(Speed);
    }

    public boolean LinearLiftMotorsTowardsPosition(int pos) {
        if (GetPosition() < pos) {
            SetLinearLiftMotors(0.5);
            return false;
        } else if (GetPosition() > pos) {
            SetLinearLiftMotors(-0.5);
            return false;
        } else {
            SetLinearLiftMotors(0);
            return true;
        }
    }

    public double GetPosition() {
        return linearPulleyMotor.getSelectedSensorPosition();
    }
    
    public int GetStage() {
        return CurrentPosition;
    }

    public void ResetEncoder() {
        linearPulleyMotor.setSelectedSensorPosition(0);
    }

    public boolean ReadGroundSwitch() {
        return !GroundSwitch.get();
    }

    public boolean ReadLowSwitch() {
        return !LowSwitch.get();
    }

    public boolean ReadMediumSwitch() {
        return !MediumSwitch.get();
    }

    public boolean ReadHighSwitch() {
        return !HighSwitch.get();
    }

    public int GetCurrentPosition() {
        return CurrentPosition;

    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}


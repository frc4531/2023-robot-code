package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
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
        GroundSwitch = new DigitalInput(4);
        LowSwitch = new DigitalInput(5);
        MediumSwitch = new DigitalInput(6);
        HighSwitch = new DigitalInput(7);


    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        if (GroundSwitch.get()) {
            CurrentPosition = 0;
        } else if (LowSwitch.get()) {
            CurrentPosition = 1;
        } else if (MediumSwitch.get()) {
            CurrentPosition = 2;
        } else if (HighSwitch.get()) {
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
        return GroundSwitch.get();
    }

    public boolean ReadLowSwitch() {
        return LowSwitch.get();
    }

    public boolean ReadMediumSwitch() {
        return MediumSwitch.get();
    }

    public boolean ReadHighSwitch() {
        return HighSwitch.get();
    }

    public int GetCurrentPosition() {
        return CurrentPosition;

    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}


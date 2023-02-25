package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class LinearLiftSubsystem extends SubsystemBase {

    private WPI_TalonFX linearPulleyMotor;
    private DigitalInput GroundSwitch;
    private DigitalInput LowSwitch;
    private DigitalInput MediumSwitch;
    private DigitalInput HighSwitch;

    public LinearLiftSubsystem() {
        linearPulleyMotor = new WPI_TalonFX(9);
        GroundSwitch = new DigitalInput(4);
        LowSwitch = new DigitalInput(5);
        MediumSwitch = new DigitalInput(6);
        HighSwitch = new DigitalInput(7);

 
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public void SetLinearLiftMotors(double Speed) {
        linearPulleyMotor.set(Speed);
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

    public int GetPosition() {
        return 0;

    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}


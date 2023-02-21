package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class LinearLiftSubsystem extends SubsystemBase {

    private WPI_TalonFX linearPulleyMotor;

    public LinearLiftSubsystem() {
        linearPulleyMotor = new WPI_TalonFX(9);
 
 


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

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}


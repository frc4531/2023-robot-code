package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ScissorLiftSubsystem extends SubsystemBase {

    private CANSparkMax scissorLiftMotor;

    public ScissorLiftSubsystem() {
        scissorLiftMotor = new CANSparkMax(1,MotorType.kBrushless);
        scissorLiftMotor.setInverted(false);


    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public void SetScissorLiftMotors(double Speed) {
        scissorLiftMotor.set(Speed);
    }

    public int GetPostion() {
        return 0;

    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}


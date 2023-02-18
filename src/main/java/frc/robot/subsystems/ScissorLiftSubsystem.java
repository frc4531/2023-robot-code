package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
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

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}


package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ClawSpinSubsystem extends SubsystemBase {

    private CANSparkMax clawMotorLeft;
    private CANSparkMax clawMotorRight;

 
    public ClawSpinSubsystem() {
        clawMotorLeft = new CANSparkMax(2,MotorType.kBrushless);
        clawMotorLeft.setInverted(false);

        clawMotorRight = new CANSparkMax(3,MotorType.kBrushless);
        clawMotorRight.setInverted(true);


    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}


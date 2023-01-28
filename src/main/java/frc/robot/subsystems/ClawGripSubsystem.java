package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class ClawGripSubsystem extends SubsystemBase {

    private DoubleSolenoid clawGripSolenoid;


    public ClawGripSubsystem() {
        clawGripSolenoid = new DoubleSolenoid(1, PneumaticsModuleType.REVPH, 2, 3);
        addChild("ClawGripSolenoid", clawGripSolenoid);
 


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


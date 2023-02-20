package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class ClawGripSubsystem extends SubsystemBase {

    private DoubleSolenoid clawGripSolenoid;


    public ClawGripSubsystem() {
        clawGripSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);
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

    public void CloseGrip() {
        clawGripSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void OpenGrip() {
        clawGripSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}


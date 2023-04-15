package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class CompressorSubsystem extends SubsystemBase {

    private Compressor compressor;

    public CompressorSubsystem() {
        compressor = new Compressor(PneumaticsModuleType.REVPH);
        compressor.enableAnalog(75, 115);
        addChild("Compressor",compressor);
 


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


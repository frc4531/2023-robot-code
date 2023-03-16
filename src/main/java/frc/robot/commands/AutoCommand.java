package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class AutoCommand extends SequentialCommandGroup {

    public AutoCommand(DriveSubsystem Drive){

        addCommands(

            //new DriveToPosition(Drive, 115000) //9 rotations
            
            new DriveToTargetPitch(Drive, 8)

            // Add Commands here:
            // Also add parallel commands using the
            //
            // addCommands(
            //      new command1(argsN, subsystem),
            //      Commands.parallel(
            //          new command2(argsN, subsystem),
            //          new command3(argsN, subsystem)
            //      )    
            //  );

        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}

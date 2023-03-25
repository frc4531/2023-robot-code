package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class AutoMidExitAndBalanceCommand extends SequentialCommandGroup {

    public AutoMidExitAndBalanceCommand(DriveSubsystem Drive){

        addCommands(

            //new DriveToPosition(Drive, 115000) //9 rotations
            
            Commands.deadline(new WaitCommand(1), new DriveBackToTargetPitch(Drive, 45)),
            new DriveToTargetPitch(Drive, 8),
            new WaitCommand(1.5),
            new DriveBackToTargetPitch(Drive, 11),
            new BalanceWithPIDAndGyro(Drive, 0)

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

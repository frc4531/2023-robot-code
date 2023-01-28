// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: RobotContainer.

package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private static RobotContainer m_robotContainer = new RobotContainer();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
// The robot's subsystems
    public final CompressorSubsystem m_compressorSubsystem = new CompressorSubsystem();
    public final VisionSubsystem m_visionSubsystem = new VisionSubsystem();
    public final ClawSpinSubsystem m_clawSpinSubsystem = new ClawSpinSubsystem();
    public final ClawGripSubsystem m_clawGripSubsystem = new ClawGripSubsystem();
    public final ScissorLiftSubsystem m_scissorLiftSubsystem = new ScissorLiftSubsystem();
    public final LinearLiftSubsystem m_linearLiftSubsystem = new LinearLiftSubsystem();
    public final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

// Joysticks
private final Joystick nesStick = new Joystick(1);
private final Joystick driveStick = new Joystick(0);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  private RobotContainer() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Smartdashboard Subsystems


    // SmartDashboard Buttons

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND
    m_driveSubsystem.setDefaultCommand(new DriveCommand( m_driveSubsystem ));
    m_compressorSubsystem.setDefaultCommand(new CompressorCompress( m_compressorSubsystem ));


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

    // Configure autonomous sendable chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    //m_chooser.setDefaultOption("$command.getName()", new ${name.replace(' ', '')}( m_${name.substring(0,1).toLowerCase()}${name.substring(1).replace(' ', '')} ));

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    //SmartDashboard.putData("Auto Mode", m_chooser);
  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
// Create some buttons
final JoystickButton driveShiftButton = new JoystickButton(driveStick, 1);        
driveShiftButton.toggleOnTrue(new DriveShift( m_driveSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton linearLiftUpButton = new JoystickButton(nesStick, 1);        
linearLiftUpButton.whileTrue(new LinearLiftUp( m_linearLiftSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton linearLiftDownButton = new JoystickButton(nesStick, 2);        
linearLiftDownButton.whileTrue(new LinearLiftDown( m_linearLiftSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton scissorLiftInButton = new JoystickButton(nesStick, 3);        
scissorLiftInButton.whileTrue(new ScissorLiftIn( m_scissorLiftSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton scissorLiftOutButton = new JoystickButton(nesStick, 4);        
scissorLiftOutButton.whileTrue(new ScissorLiftOut( m_scissorLiftSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton clawGripInButton = new JoystickButton(nesStick, 5);        
clawGripInButton.onTrue(new ClawGripIn( m_clawGripSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton clawGripOutButton = new JoystickButton(nesStick, 6);        
clawGripOutButton.onTrue(new ClawGripOut( m_clawGripSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton clawSpinDownButton = new JoystickButton(nesStick, 7);        
clawSpinDownButton.whileTrue(new ClawSpinDown( m_clawSpinSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton clawSpinUpButton = new JoystickButton(nesStick, 8);        
clawSpinUpButton.whileTrue(new ClawSpinUp( m_clawSpinSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
  }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public Joystick getDriveStick() {
        return driveStick;
    }

public Joystick getNesStick() {
        return nesStick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }
  

}


package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private static RobotContainer m_robotContainer = new RobotContainer();

  // The robot's subsystems
  public final CompressorSubsystem m_compressorSubsystem = new CompressorSubsystem();
  public final VisionSubsystem m_visionSubsystem = new VisionSubsystem();
  public final ClawGripSubsystem m_clawGripSubsystem = new ClawGripSubsystem();
  public final ScissorLiftSubsystem m_scissorLiftSubsystem = new ScissorLiftSubsystem();
  public final LinearLiftSubsystem m_linearLiftSubsystem = new LinearLiftSubsystem();
  public final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  // Joysticks
  private final Joystick nesStick = new Joystick(1);
  private final Joystick driveStick = new Joystick(0);
  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  private RobotContainer() {
    // Smartdashboard Subsystems


    // SmartDashboard Buttons

    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    m_driveSubsystem.setDefaultCommand(new DriveCommand( m_driveSubsystem ));

    // Configure autonomous sendable chooser

    m_chooser.setDefaultOption("Middle Exit and Balance", new AutoMidExitAndBalanceCommand(m_driveSubsystem));
    m_chooser.addOption("Sides Exit", new AutoSidesCommand(m_driveSubsystem));
    m_chooser.addOption("Middle Balance Only", new AutoMidBalanceOnlyCommand(m_driveSubsystem));
    m_chooser.addOption("Score Only", new AutoScoreOnly(m_driveSubsystem));

    SmartDashboard.putData("Auto Commandsss", m_chooser);
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
    // Create some buttons
    final JoystickButton driveShiftButton = new JoystickButton(driveStick, 8);        
    driveShiftButton.toggleOnTrue(new DriveShift( m_driveSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton driveCentricButton = new JoystickButton(driveStick, 7);        
    driveCentricButton.toggleOnTrue(new DriveCentricCommand( m_driveSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton SlowMecanumDriveButton = new JoystickButton(driveStick, 1);        
    SlowMecanumDriveButton.whileTrue(new DriveSlowCommand( m_driveSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton PIDTestButton = new JoystickButton(driveStick, 5);        
    PIDTestButton.whileTrue(new DriveToPosWithPID( m_driveSubsystem, 0 ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton BalanceWithPIDAndGyroButton = new JoystickButton(driveStick, 10);        
    BalanceWithPIDAndGyroButton.whileTrue(new BalanceWithPIDAndGyro( m_driveSubsystem, 0 ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
    final JoystickButton linearLiftUpButton = new JoystickButton(nesStick, 7);        
    linearLiftUpButton.whileTrue(new LinearLiftUp( m_linearLiftSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
    final JoystickButton linearLiftDownButton = new JoystickButton(nesStick, 8);        
    linearLiftDownButton.whileTrue(new LinearLiftDown( m_linearLiftSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                       
    final JoystickButton ResetGyroButton = new JoystickButton(nesStick, 11);        
    ResetGyroButton.onTrue(new DriveResetGyro( m_driveSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton scissorLiftInButton = new JoystickButton(nesStick, 12);        
    scissorLiftInButton.whileTrue(new ScissorLiftIn( m_scissorLiftSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
    final JoystickButton scissorLiftOutButton = new JoystickButton(nesStick, 13);        
    scissorLiftOutButton.whileTrue(new ScissorLiftOut( m_scissorLiftSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
    final JoystickButton clawGripInButton = new JoystickButton(nesStick, 9);        
    clawGripInButton.onTrue(new ClawGripIn( m_clawGripSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
    final JoystickButton clawGripOutButton = new JoystickButton(nesStick, 10);        
    clawGripOutButton.onTrue(new ClawGripOut( m_clawGripSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton LinearLiftGroundStageButton = new JoystickButton(nesStick, 2);        
    //LinearLiftGroundStageButton.onTrue(new LinearLiftToPosition( m_linearLiftSubsystem, 0 ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton LinearLiftLowStageButton = new JoystickButton(nesStick, 1);        
    LinearLiftLowStageButton.onTrue(new LinearLiftToPosition( m_linearLiftSubsystem, 1 ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton LinearLiftMediumStageButton = new JoystickButton(nesStick, 3);        
    LinearLiftMediumStageButton.onTrue(new LinearLiftToPosition( m_linearLiftSubsystem, 2 ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton LinearLiftHighStageButton = new JoystickButton(nesStick, 5);        
    LinearLiftHighStageButton.onTrue(new LinearLiftToPosition( m_linearLiftSubsystem, 3 ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
  }

  public Joystick getDriveStick() {
    return driveStick;
    }

  public Joystick getNesStick() {
    return nesStick;
    }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    //return new AutoCommand(m_driveSubsystem);
    return m_chooser.getSelected();
  }
  

}


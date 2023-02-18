package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class DriveSubsystem extends SubsystemBase {

    private DoubleSolenoid shiftSolenoid;
    private WPI_TalonFX frontLeft;
    private WPI_TalonFX frontRight;
    private WPI_TalonFX backLeft;
    private WPI_TalonFX backRight;
    private MecanumDrive mecanumDriveTrain;
    private DifferentialDrive TankDriveTrain;
    private MotorControllerGroup LeftSide;
    private MotorControllerGroup RightSide;
    private AHRS DriveGyro;
    private Encoder DriveEncoder;


    public DriveSubsystem() {
        DriveGyro = new AHRS(SerialPort.Port.kUSB);

        DriveEncoder = new Encoder(0, 1, false, EncodingType.k4X);

        shiftSolenoid = new DoubleSolenoid(1, PneumaticsModuleType.REVPH, 0, 1);
        addChild("ShiftSolenoid", shiftSolenoid);
    

        frontLeft = new WPI_TalonFX(6);

        frontRight = new WPI_TalonFX(7);
    
        backLeft = new WPI_TalonFX(8);
    
        backRight = new WPI_TalonFX(9);
    
    

        mecanumDriveTrain = new MecanumDrive(frontLeft, backLeft,frontRight, backRight);
        addChild("MecanumDriveTrain",mecanumDriveTrain);
        mecanumDriveTrain.setSafetyEnabled(true);
        mecanumDriveTrain.setExpiration(0.1);
        mecanumDriveTrain.setMaxOutput(0.9);

        LeftSide = new MotorControllerGroup(frontLeft, backLeft);
        RightSide = new MotorControllerGroup(frontRight, backRight);

        TankDriveTrain = new DifferentialDrive(LeftSide, RightSide);
        addChild("TankDriveTrain", TankDriveTrain);
        TankDriveTrain.setSafetyEnabled(true);
        TankDriveTrain.setExpiration(0.1);
        TankDriveTrain.setMaxOutput(0.9);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public double GetPosition() {
        return DriveEncoder.getDistance();
    }

    public void ResetEncoder() {
        DriveEncoder.reset();
    }

    public double ReadHeading() {
        return DriveGyro.getYaw();
    }

    public void ResetGyro() {
        DriveGyro.zeroYaw();
    }

    public void MecanumDriveRobot(double Forward, double Strafe, double Turn) {
        mecanumDriveTrain.driveCartesian(Forward, Strafe, Turn);
    }

    public void TankDriveRobot(double Forward, double Turn) {
        TankDriveTrain.arcadeDrive(Forward, Turn);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}


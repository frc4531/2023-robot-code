package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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

        DriveEncoder = new Encoder(12, 13, false, EncodingType.k4X);

        shiftSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 2, 3);
        addChild("ShiftSolenoid", shiftSolenoid);
    

        frontLeft = new WPI_TalonFX(5);
        frontLeft.setInverted(true);

        frontRight = new WPI_TalonFX(6);
    
        backLeft = new WPI_TalonFX(7);
        backLeft.setInverted(true);
    
        backRight = new WPI_TalonFX(8);
    
    

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
        SmartDashboard.putNumber("DriveEncoder", GetPosition());
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

    public void SlowMecanumDriveRobot(double Forward, double Strafe, double Turn) {
        mecanumDriveTrain.driveCartesian(Forward*0.2, Strafe*0.2, Turn*0.2);
    }

    public void TankDriveRobot(double Forward, double Turn) {
        TankDriveTrain.arcadeDrive(Forward, Turn);
    }

    public void DifferentialDriveRobot(double Left, double Right){
        TankDriveTrain.tankDrive(Left, Right);
    }

    public void shiftToTank() {
        shiftSolenoid.set(Value.kReverse);
    }

    public void shiftToMecanum() {
        shiftSolenoid.set(Value.kForward);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}


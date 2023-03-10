package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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

        DriveEncoder = new Encoder(12, 13, true, EncodingType.k4X);

        shiftSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 2, 3);
        addChild("ShiftSolenoid", shiftSolenoid);
    

        frontLeft = new WPI_TalonFX(5);
        frontLeft.setInverted(true);

        frontRight = new WPI_TalonFX(6);
    
        backLeft = new WPI_TalonFX(7);
        backLeft.setInverted(true);
        backLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

    
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
        SmartDashboard.putNumber("Gyro Heading", ReadHeading());
        SmartDashboard.putNumber("Gyro Pitch", ReadPitch());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public double GetPosition() {
        //return DriveEncoder.getDistance();
        return -(backLeft.getSelectedSensorPosition());

    }

    public void ResetEncoder() {
        //DriveEncoder.reset();
        backLeft.setSelectedSensorPosition(0);

    }

    public double ReadHeading() {
        return DriveGyro.getYaw();
    }

    public double ReadPitch() {
        return DriveGyro.getPitch();
    }

    public void ResetGyro() {
        DriveGyro.zeroYaw();
    }

    public void MecanumDriveRobot(double Forward, double Strafe, double Turn) {
        mecanumDriveTrain.driveCartesian(Forward, Strafe, Turn);
    }

    public void SlowMecanumDriveRobot(double Forward, double Strafe, double Turn) {
        mecanumDriveTrain.driveCartesian(Forward*0.4, Strafe*0.4, Turn*0.3);
    }

    public void TankDriveRobot(double Forward, double Turn) {
        TankDriveTrain.arcadeDrive(Forward, Turn);
    }

    public void DifferentialDriveRobot(double Left, double Right){
        TankDriveTrain.tankDrive(Left, Right);
    }

    public void shiftToTank() {
        shiftSolenoid.set(Value.kReverse);
        frontLeft.setNeutralMode(NeutralMode.Brake);
        frontRight.setNeutralMode(NeutralMode.Brake);
        backLeft.setNeutralMode(NeutralMode.Brake);
        backRight.setNeutralMode(NeutralMode.Brake);
    }

    public void shiftToMecanum() {
        shiftSolenoid.set(Value.kForward);
        frontLeft.setNeutralMode(NeutralMode.Coast);
        frontRight.setNeutralMode(NeutralMode.Coast);
        backLeft.setNeutralMode(NeutralMode.Coast);
        backRight.setNeutralMode(NeutralMode.Coast);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}


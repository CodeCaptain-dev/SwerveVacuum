package frc.robot;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.lib.Conversions;
import frc.robot.Constants.SwerveConstants;

public class SwerveModule {
    public int moduleNumber;
    private final DutyCycleOut driveDutyCycle;
    private final ProfiledPIDController turningPidController;
    private final SimpleMotorFeedforward driveFeedforward;

    //module constant stuffs:
    private Rotation2d angleOffset;
    private TalonFX angleMotor;
    private TalonFX driveMotor;
    private CANcoder angleEncoder;
    
    public SwerveModule(int moduleNumber) {
        SwerveModuleConstants moduleConstants = SwerveConstants.moduleConstants[moduleNumber]; 

        this.moduleNumber = moduleNumber;
        this.angleOffset = moduleConstants.angleOffset;
        
        angleEncoder = new CANcoder(moduleConstants.canCoderID);
        angleEncoder.getConfigurator().apply(SwerveConstants.ctreConfigs.swerveCANcoderConfig);

        angleMotor = new TalonFX(moduleConstants.angleMotorID);
        angleMotor.getConfigurator().apply(SwerveConstants.ctreConfigs.swerveAngleFXConfig);
        
        angleMotor = new TalonFX(moduleConstants.driveMotorID);
        angleMotor.getConfigurator().apply(SwerveConstants.ctreConfigs.swerveDriveFXConfig);

        driveDutyCycle = new DutyCycleOut(0);

        turningPidController = new ProfiledPIDController(
            SwerveConstants.ctreConfigs.swerveAngleFXConfig.Slot0.kP, 
            SwerveConstants.ctreConfigs.swerveAngleFXConfig.Slot0.kI, 
            SwerveConstants.ctreConfigs.swerveAngleFXConfig.Slot0.kD, 
            new TrapezoidProfile.Constraints(SwerveConstants.maxAngularSpeed, SwerveConstants.maxAngularAccel)
        );

        turningPidController.enableContinuousInput(-Math.PI, Math.PI);
        driveFeedforward = new SimpleMotorFeedforward(
            SwerveConstants.driveKS,
            SwerveConstants.driveKV,
            SwerveConstants.driveKA
        );

    }

    public void setDesiredState(SwerveModuleState desiredState, boolean isOpenLoop) {

        desiredState.optimize(getState().angle);

        desiredState.cosineScale(getState().angle);

        angleMotor.setVoltage(turningPidController.calculate(
            Rotation2d.fromRotations(angleMotor.getPosition().getValueAsDouble()).getRadians(),desiredState.angle.getRadians()
        ) * SwerveConstants.maxAngularSpeed);

        if(isOpenLoop) {
            driveDutyCycle.Output = desiredState.speedMetersPerSecond * SwerveConstants.maxDriveSpeed;
            driveMotor.setControl(driveDutyCycle);
        } else {
            driveMotor.setVoltage(
                turningPidController.calculate(
                    driveMotor.getVelocity().getValueAsDouble(),
                    desiredState.speedMetersPerSecond
                ) + driveFeedforward.calculate(desiredState.speedMetersPerSecond)
            );
        }
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(
            Conversions.RPSToMPS(driveMotor.getVelocity().getValueAsDouble(), Units.inchesToMeters(4) * Math.PI),
            Rotation2d.fromRotations(angleMotor.getPosition().getValueAsDouble())
        );
    }

    public Rotation2d getCANcoder() {
        return Rotation2d.fromRotations(angleEncoder.getAbsolutePosition().getValueAsDouble());
    }

    public void resetToAbsolute(){
        double absolutePosition = getCANcoder().getRotations() - angleOffset.getRotations();
        angleMotor.setPosition(absolutePosition);
    }

    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(
            Conversions.rotationsToMeters(driveMotor.getPosition().getValueAsDouble(), Units.inchesToMeters(4) * Math.PI),
            Rotation2d.fromRotations(angleMotor.getPosition().getValueAsDouble())
        );
    }

    public TalonFX getDriveMotor() {
        return driveMotor;
    }

    public void setDriveVoltage(double voltage) {
        driveDutyCycle.Output = voltage;
        driveMotor.setControl(driveDutyCycle);
    }
}

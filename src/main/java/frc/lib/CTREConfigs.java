package frc.lib;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public final class CTREConfigs {
    public TalonFXConfiguration swerveAngleFXConfig = new TalonFXConfiguration();
    public TalonFXConfiguration swerveDriveFXConfig = new TalonFXConfiguration();
    public CANcoderConfiguration swerveCANcoderConfig = new CANcoderConfiguration();

    public static final COTSTalonFXSwerveConstants chosenModule =  
        COTSTalonFXSwerveConstants.SDS.MK4i.KrakenX60(COTSTalonFXSwerveConstants.SDS.MK4i.driveRatios.L2);

    public CTREConfigs(){
        /** Swerve CANCoder Configuration */
        swerveCANcoderConfig.MagnetSensor.SensorDirection = chosenModule.cancoderInvert;

        /** Swerve Angle Motor Configurations */
        /* Motor Inverts and Neutral Mode */
        swerveAngleFXConfig.MotorOutput.Inverted = chosenModule.angleMotorInvert;
        swerveAngleFXConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;

        /* Gear Ratio and Wrapping Config */
        swerveAngleFXConfig.Feedback.SensorToMechanismRatio = chosenModule.angleGearRatio;
        swerveAngleFXConfig.ClosedLoopGeneral.ContinuousWrap = true;
        
        /* Current Limiting */
        swerveAngleFXConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
        swerveAngleFXConfig.CurrentLimits.SupplyCurrentLimit = 25;
        swerveAngleFXConfig.CurrentLimits.SupplyCurrentLowerLimit = 40;
        swerveAngleFXConfig.CurrentLimits.SupplyCurrentLowerTime = 0.1;

        /* PID Config */
        swerveAngleFXConfig.Slot0.kP = 2.50;
        swerveAngleFXConfig.Slot0.kI = 0.00;
        swerveAngleFXConfig.Slot0.kD = 0.00;

        /** Swerve Drive Motor Configuration */
        /* Motor Inverts and Neutral Mode */
        swerveDriveFXConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        swerveDriveFXConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        /* Gear Ratio Config */
        swerveDriveFXConfig.Feedback.SensorToMechanismRatio = chosenModule.driveGearRatio;

        /* Current Limiting */
        swerveDriveFXConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
        swerveDriveFXConfig.CurrentLimits.SupplyCurrentLimit = 35;
        swerveDriveFXConfig.CurrentLimits.SupplyCurrentLowerLimit = 60;
        swerveDriveFXConfig.CurrentLimits.SupplyCurrentLowerTime = 0.1;

        /* PID Config */
        swerveDriveFXConfig.Slot0.kP = 0.12;
        swerveDriveFXConfig.Slot0.kI = 0.0;
        swerveDriveFXConfig.Slot0.kD = 0.0;

        /* Open and Closed Loop Ramping */
        swerveDriveFXConfig.OpenLoopRamps.DutyCycleOpenLoopRampPeriod = 0.25;
        swerveDriveFXConfig.OpenLoopRamps.VoltageOpenLoopRampPeriod = 0.25;

        swerveDriveFXConfig.ClosedLoopRamps.DutyCycleClosedLoopRampPeriod = 0.0;
        swerveDriveFXConfig.ClosedLoopRamps.VoltageClosedLoopRampPeriod = 0.0;
    }
}
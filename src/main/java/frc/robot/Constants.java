package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.XboxController;
import frc.lib.CTREConfigs;

/** Add your docs here. */
public class Constants {

    // CTRE
    public static CTREConfigs ctreConfigs = new CTREConfigs();

    //drive motors
    public static final class SwerveConstants {

        public static final double translationSlew = 0.5;
        public static final double rotationSlew = 0.5;

        public static final SwerveModuleConstants[] moduleConstants = new SwerveModuleConstants[]{
            //Motor 0 - Tank Front Left
            new SwerveModuleConstants(
                2,
                1,
                1,
                Rotation2d.fromRotations(0)
            ),

            //Motor 1 - Tank Front Right
            new SwerveModuleConstants(
                4,
                3,
                2,
                Rotation2d.fromRotations(0)
            ),

            //Motor 2 - Tank Back Left
            new SwerveModuleConstants(
                8,
                7,
                4,
                Rotation2d.fromRotations(0)
            ),

            //Motor 3 - Tank Back Right
            new SwerveModuleConstants(
                6,
                5,
                3,
                Rotation2d.fromRotations(0)
            ),

            //Motor 4 - Intake Front Left
            new SwerveModuleConstants(
                10,
                9,
                5,
                Rotation2d.fromRotations(0)
            ),

            //Motor 5 - Intake Front Right
            new SwerveModuleConstants(
                12,
                11,
                6,
                Rotation2d.fromRotations(0)
            ),

            //Motor 6 - Intake Back Left
            new SwerveModuleConstants(
                16,
                15,
                8,
                Rotation2d.fromRotations(0)
            ),

            //Motor 7 - Intake Back Right
            new SwerveModuleConstants(
                14,
                13,
                7,
                Rotation2d.fromRotations(0)
            )
        };

        // CTRE
        public static CTREConfigs ctreConfigs = new CTREConfigs();

        // max turn speed
        public static final double maxAngularSpeed = 2 * Math.PI;
        //max acceleration speed
        public static final double maxAngularAccel = 4 * Math.PI;

        //angle profiles
        public static final double angleKP = 0.0;
        public static final double angleKI = 0.0;
        public static final double angleKD = 0.0;

        //drive profiles
        public static final double driveKS = 0.0;
        public static final double driveKV = 0.0;
        public static final double driveKA = 0.0;

        public static final double maxDriveSpeed = 0.5;

        public static final double wheelDiameter = Units.inchesToMeters(4);
        public static final double wheelCircumference = wheelDiameter * Math.PI;
        public static final double trackWidth = Units.inchesToMeters(24.5);
        public static final double wheelBase = Units.inchesToMeters(24.5);

        public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
            new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0)
        );
            
    }

    public final class JoystickConstants {
        public static final double translationDeadband = 0.05;
        public static final double angularDeadband = 0.05;

        public static final class DriverBinds {
            // Axes
            public static final int tXAxis = XboxController.Axis.kLeftY.value;
            public static final int tYAxis = XboxController.Axis.kLeftX.value;
            public static final int rotAxis = XboxController.Axis.kRightX.value;

            // Swerve binds
            public static final int fieldRelativeButton = XboxController.Button.kLeftBumper.value;

            // Subsystem binds
            public static final int intakeButton = XboxController.Button.kA.value;
            public static final int launcherButton = XboxController.Button.kB.value;
        }
    }
    
}

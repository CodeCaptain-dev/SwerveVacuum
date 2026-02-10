//basically the intake is a separate part

package frc.robot.subsystems;

import com.ctre.phoenix6.swerve.SwerveModule;

public class IntakeSwerveDrive {
    private final SwerveModule[] modules;

    @SuppressWarnings("rawtypes")
    public IntakeSwerveDrive() {
        modules = new SwerveModule[]{
            new SwerveModule(4);
            new SwerveModule(5);
            new SwerveModule(6);
            new SwerveModule(7);
        };
    }
}
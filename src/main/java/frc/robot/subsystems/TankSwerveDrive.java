//the main body of the vacuum
package frc.robot.subsystems;
import com.ctre.phoenix6.swerve.SwerveModule;

import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class TankSwerveDrive extends SubsystemBase{
    private final SwerveModule[] modules;

    public TankSwerveDrive() {
        modules = new SwerveModule[]{
            new SwerveModule(0);
            new SwerveModule(1);
            new SwerveModule(2);
            new SwerveModule(3);
        };
        
    }


public SwerveModule[] getModules() {
    return modules;
}
}

package org.discobots.recyclerush.subsystems;

import org.discobots.recyclerush.HW;
import org.discobots.recyclerush.commands.TestArcadeDrive;
import org.discobots.recyclerush.commands.TestTankDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TestSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Victor[] pwm = new Victor[7];
	//RobotDrive drive;
	
	public TestSubsystem() {
		for (int i = 0; i < 7; i++) {
			pwm[i] = new Victor(i);
		}
		//0,1 right 2,3 left
		//drive = new RobotDrive(pwm[0], pwm[2], pwm[3], pwm[1]);
		//drive.setSafetyEnabled(false);
	}
	
	public void tankDrive(double leftVal, double rightVal) {
		//drive.tankDrive(leftVal, rightVal);
	} 
	
	public void arcadeDrive(double moveVal, double rotateVal) {
		//drive.arcadeDrive(moveVal, rotateVal);
	}
	
	public void setMotor(int index, double val) {
		pwm[index].set(val);
	}

    public void initDefaultCommand() {
        this.setDefaultCommand(new TestArcadeDrive());
    }
}


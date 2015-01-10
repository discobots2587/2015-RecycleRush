package org.discobots.recyclerush.subsystems;

import org.discobots.recyclerush.HW;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DrivetrainSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Victor pwm0;
	Victor pwm1;
	Victor pwm2;
	Victor pwm3;
	Victor pwm4;
	Victor pwm5;
	Victor pwm6;
	RobotDrive drive;
	
	public DrivetrainSubsystem() {
		pwm0 = new Victor(HW.motorPwm0);
		pwm1 = new Victor(HW.motorPwm1);
		pwm2 = new Victor(HW.motorPwm2);
		pwm3 = new Victor(HW.motorPwm3);
		pwm4 = new Victor(HW.motorPwm4);
		pwm5 = new Victor(HW.motorPwm5);
		pwm6 = new Victor(HW.motorPwm6);
		//0,1 right 2,3 left
		drive = new RobotDrive(pwm2, pwm3, pwm0, pwm1);
	}
	
	public void tankDrive(double leftVal, double rightVal) {
		drive.tankDrive(leftVal, rightVal);
	}
	
	public void arcadeDrive(double moveVal, double rotateVal) {
		drive.arcadeDrive(moveVal, rotateVal);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


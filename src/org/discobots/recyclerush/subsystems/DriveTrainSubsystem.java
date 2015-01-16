package org.discobots.recyclerush.subsystems;

import org.discobots.recyclerush.HW;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {
    
	TalonSRX frontRight = new TalonSRX(HW.frontRightTalon);
	TalonSRX frontLeft = new TalonSRX(HW.frontLeftTalon);
	TalonSRX backRight = new TalonSRX(HW.backRightTalon);
	TalonSRX backLeft = new TalonSRX(HW.backLeftTalon);
	TalonSRX centerDropDown = new TalonSRX(HW.centerDropDownTalon);
	
	RobotDrive robotDrive = new RobotDrive(HW.frontLeftTalon,HW.backLeftTalon,HW.frontRightTalon,HW.backRightTalon);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void tankDrive(double leftStick, double rightStick)
    {
    	robotDrive.tankDrive(leftStick, rightStick);
    }
    
    public void arcacdeDrive(double y, double x)
    {
    	robotDrive.arcadeDrive(y, x);
    }
    
    public void holonomicDrive(double y, double x, double r)
    {
    	robotDrive.arcadeDrive(y, r);
    	centerDropDown.set(x);
    }

}


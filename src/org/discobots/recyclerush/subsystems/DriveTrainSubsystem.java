package org.discobots.recyclerush.subsystems;

import org.discobots.recyclerush.HW;
import org.discobots.recyclerush.commands.drive.CycleDriveCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
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

	Encoder encoderForward;
	Encoder encoderSideway;
	
	Gyro gyroscope;
	
	RobotDrive robotDrive = new RobotDrive(HW.frontLeftTalon, HW.backLeftTalon,
			HW.frontRightTalon, HW.backRightTalon);

	public void tankDrive(double leftStick, double rightStick) {
		robotDrive.tankDrive(leftStick, rightStick);
	}

	public void arcadeDrive(double y, double x) {
		robotDrive.arcadeDrive(y, x);
	}

	public void holonomicDrive(double y, double x, double r) { // h-drive holonomic
		robotDrive.arcadeDrive(y, r);
		centerDropDown.set(x);
	}
	
	public double[] getMotorOutput() {
		return new double[]{frontRight.get(), frontLeft.get(), backRight.get(), backLeft.get(), centerDropDown.get()};
	}

	public void initDefaultCommand() {
		setDefaultCommand(new CycleDriveCommand());
	}
}

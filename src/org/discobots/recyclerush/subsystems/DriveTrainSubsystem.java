package org.discobots.recyclerush.subsystems;

import org.discobots.recyclerush.HW;
import org.discobots.recyclerush.commands.drive.CycleDriveCommand;
import org.discobots.recyclerush.commands.drive.StickDriveCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrainSubsystem extends Subsystem {

	public enum Motor {
		FRONTLEFT, BACKLEFT, FRONTRIGHT, BACKRIGHT, CENTERDROPDOWN;
	}

	CANTalon frontLeft, backLeft, frontRight, backRight, centerDropDown;
	// switch to TalonSRX class if we use pwm instead.
	// with can the following values are available:
	// out curr, out volt, in volt, setpoint, temp, 

	RobotDrive robotDrive;

	Encoder encoderForward;
	Encoder encoderSideway;

	Gyro gyroscope;

	public DriveTrainSubsystem() {
		frontLeft = new CANTalon(HW.motorFrontLeft);
		backLeft = new CANTalon(HW.motorBackLeft);
		frontRight = new CANTalon(HW.motorFrontRight);
		backRight = new CANTalon(HW.motorBackRight);
		centerDropDown = new CANTalon(HW.motorCenterDropDown);

		robotDrive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
	}

	public void tankDrive(double leftStick, double rightStick) {
		robotDrive.tankDrive(leftStick, rightStick);
	}

	public void arcadeDrive(double y, double x) {
		robotDrive.arcadeDrive(y, x);
	}

	public void holonomicDrive(double y, double x, double r) { // h-drive
		robotDrive.arcadeDrive(y, r);
		centerDropDown.set(x);
	}

	public double getMotorSetpoint(Motor motor) {
		if (motor == Motor.BACKLEFT) {
			return this.backLeft.getSetpoint();
		} else if (motor == Motor.BACKRIGHT) {
			return this.backRight.getSetpoint();
		} else if (motor == Motor.FRONTLEFT) {
			return this.frontLeft.getSetpoint();
		} else if (motor == Motor.FRONTRIGHT) {
			return this.frontRight.getSetpoint();
		} else if (motor == Motor.CENTERDROPDOWN) {
			return this.centerDropDown.getSetpoint();
		} else {
			return -9001;
		}
	}

	public double getMotorCurrent(Motor motor) {
		if (motor == Motor.BACKLEFT) {
			return this.backLeft.getOutputCurrent();
		} else if (motor == Motor.BACKRIGHT) {
			return this.backRight.getOutputCurrent();
		} else if (motor == Motor.FRONTLEFT) {
			return this.frontLeft.getOutputCurrent();
		} else if (motor == Motor.FRONTRIGHT) {
			return this.frontRight.getOutputCurrent();
		} else if (motor == Motor.CENTERDROPDOWN) {
			return this.centerDropDown.getOutputCurrent();
		} else {
			return -9001;
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new StickDriveCommand());
		CycleDriveCommand.setModeCounter(CycleDriveCommand.MODE_STICK);
	}
}

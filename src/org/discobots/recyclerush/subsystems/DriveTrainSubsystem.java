package org.discobots.recyclerush.subsystems;

import org.discobots.recyclerush.HW;
import org.discobots.recyclerush.commands.drive.CycleDriveCommand;
import org.discobots.recyclerush.commands.drive.StickDriveCommand;
import org.discobots.recyclerush.commands.drive.TankDriveCommand;
import org.discobots.recyclerush.utils.Lidar;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
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

	DoubleSolenoid centerDropSolenoid;

	Encoder encoderForward; // sensors
	Encoder encoderSideway;

	Gyro gyroscope;

	Lidar lidar;
	
	static final double CONSTANT_RAMP_LIMIT = 0.1; // ramping stuff
	boolean allowRamped = true;
	private double prevLeft  = 0, prevRight = 0;
	private double prevY = 0, prevX = 0, prevR;

	public DriveTrainSubsystem() {
		frontLeft = new CANTalon(HW.motorFrontLeft);
		backLeft = new CANTalon(HW.motorBackLeft);
		frontRight = new CANTalon(HW.motorFrontRight);
		backRight = new CANTalon(HW.motorBackRight);
		centerDropDown = new CANTalon(HW.motorCenterDropDown);

		encoderForward = new Encoder(HW.encoderForwardA, HW.encoderForwardB,
				false, EncodingType.k4X);
		encoderSideway = new Encoder(HW.encoderSidewayA, HW.encoderSidewayB,
				false, EncodingType.k4X);
		resetEncoderForward();
		resetEncoderSideway();

		centerDropSolenoid = new DoubleSolenoid(HW.dsolCenterDropdownA,
				HW.dsolCenterDropdownB);

		gyroscope = new Gyro(HW.gyroscope);

		lidar = new Lidar(HW.i2cLidarAddress);
		lidar.start();

		robotDrive = new RobotDrive(frontLeft, backLeft, frontRight, backRight) {
			
		};
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
		robotDrive.setSafetyEnabled(false);
	}

	
	
	public void setRamped(boolean a) {
		this.allowRamped = a;
	}
	
	public void tankDriveRamp(double leftStick, double rightStick) {
		if (!allowRamped) {
			tankDriveUnramped(leftStick, rightStick);
			return;
		}
		
		double left = leftStick, right = -rightStick;

		if (left - prevLeft > CONSTANT_RAMP_LIMIT) {
			left = prevLeft + CONSTANT_RAMP_LIMIT;
		} else if (prevLeft - left > CONSTANT_RAMP_LIMIT) {
			left = prevLeft - CONSTANT_RAMP_LIMIT;
		}
		
		if (right - prevRight > CONSTANT_RAMP_LIMIT) {
			right = prevRight + CONSTANT_RAMP_LIMIT;
		} else if (prevRight - right > CONSTANT_RAMP_LIMIT) {
			right = prevRight - CONSTANT_RAMP_LIMIT;
		}

		robotDrive.tankDrive(leftStick, -rightStick);
	}

	public void arcadeDriveRamp(double iy, double ix) {
		if (!allowRamped) {
			arcadeDriveUnramped(iy, ix);
			return;
		}
		double ox = ix, oy = -iy;
		
		if (oy - prevY > CONSTANT_RAMP_LIMIT) {
			oy = prevY + CONSTANT_RAMP_LIMIT;
		} else if (prevY - oy > CONSTANT_RAMP_LIMIT) {
			oy = prevY - CONSTANT_RAMP_LIMIT;
		}
		
		if (ox - prevX > CONSTANT_RAMP_LIMIT) {
			ox = prevX + CONSTANT_RAMP_LIMIT;
		} else if (prevX - ox > CONSTANT_RAMP_LIMIT) {
			ox = prevX - CONSTANT_RAMP_LIMIT;
		}
		
		robotDrive.arcadeDrive(ox, oy); 
		// robotdrive is dumb arcadeDrive so params are switched
	}

	public void holonomicDriveRamp(double y, double x, double r) { // h-drive
		if (!allowRamped) {
			holonomicDriveUnramped(y, x, r);
			return;
		}
		
		double ox = x, oy = -y, or = r;
		
		if (ox - prevX > CONSTANT_RAMP_LIMIT) {
			ox = prevX + CONSTANT_RAMP_LIMIT;
		} else if (prevX - ox > CONSTANT_RAMP_LIMIT) {
			ox = prevX - CONSTANT_RAMP_LIMIT;
		}
		if (oy - prevY > CONSTANT_RAMP_LIMIT) {
			oy = prevY + CONSTANT_RAMP_LIMIT;
		} else if (prevY - oy > CONSTANT_RAMP_LIMIT) {
			oy = prevY - CONSTANT_RAMP_LIMIT;
		}
		if (or - prevR > CONSTANT_RAMP_LIMIT) {
			or = prevR + CONSTANT_RAMP_LIMIT;
		} else if (prevR - or > CONSTANT_RAMP_LIMIT) {
			or = prevR - CONSTANT_RAMP_LIMIT;
		}
		
		robotDrive.arcadeDrive(r, -y); 
		// robotdrive is dumb arcadeDrive so params are switched
		centerDropDown.set(x);
	}

	public void tankDriveUnramped(double leftStick, double rightStick) {
		robotDrive.tankDrive(leftStick, -rightStick);
	}

	public void arcadeDriveUnramped(double y, double x) {
		robotDrive.arcadeDrive(x, -y); 
		// robotdrive is dumb arcadeDrive so params are switched
	}

	public void holonomicDriveUnramped(double y, double x, double r) { // h-drive
		robotDrive.arcadeDrive(r, -y); 
		// robotdrive is dumb arcadeDrive so params are switched
		centerDropDown.set(x);
	}

	public double getMotorSetpoint(Motor motor) {
		if (motor == Motor.BACKLEFT) {
			return this.backLeft.get();
		} else if (motor == Motor.BACKRIGHT) {
			return this.backRight.get();
		} else if (motor == Motor.FRONTLEFT) {
			return this.frontLeft.get();
		} else if (motor == Motor.FRONTRIGHT) {
			return this.frontRight.get();
		} else if (motor == Motor.CENTERDROPDOWN) {
			return this.centerDropDown.get();
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

	public void resetEncoderForward() {
		encoderForward.reset();
	}

	public void resetEncoderSideway() {
		encoderSideway.reset();
	}

	public double getEncoderForwardDistance() {
		double encoderDistancePerCount = HW.encoderForwardConstant
				/ HW.encoderCountsPerRevolution;
		double output = encoderForward.getRaw() * encoderDistancePerCount;
		return output;
	}

	public double getEncoderSidewayDistance() {
		double encoderDistancePerCount = HW.encoderSidewaysConstant
				/ HW.encoderCountsPerRevolution;
		double output = encoderSideway.getRaw() * encoderDistancePerCount;
		return output;
	}

	public double getLidarDistanceCm() {
		return lidar.getDistanceCm();
	}

	public double getGyroscopeAngle() {
		return gyroscope.getAngle();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TankDriveCommand());
	}
}

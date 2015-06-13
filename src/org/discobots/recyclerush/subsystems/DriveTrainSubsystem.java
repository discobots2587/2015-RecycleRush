package org.discobots.recyclerush.subsystems;

import org.discobots.recyclerush.HW;
import org.discobots.recyclerush.Robot;
import org.discobots.recyclerush.commands.drive.HolonomicDriveCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrainSubsystem extends Subsystem {

	public enum Motor {
		FRONTLEFT, BACKLEFT, FRONTRIGHT, BACKRIGHT;
	}

	CANTalon backLeft, frontRight, frontLeft, backRight;
	// switch to TalonSRX class if we use pwm instead.
	// with can the following values are available:
	// out curr, out volt, in volt, setpoint, temp,

	RobotDrive robotDrive;

	Gyro gyroscope;
	boolean speedUp = false;
	static final double CONSTANT_RAMP_LIMIT = 0.05; // ramping
	// 0.05 = 4/10 seconds to full, 0.1 = 2/10 seconds to full
	boolean allowRamped = true;
	private double prevLeft = 0, prevRight = 0;
	private double prevY = 0, prevX = 0, prevR;
	private DigitalInput limitBackA, limitBackB;

	double speedScaling = 1.0;

	public DriveTrainSubsystem() {
		backLeft = new CANTalon(HW.motorBackLeft);
		frontLeft = new CANTalon(HW.motorFrontLeft);
		frontRight = new CANTalon(HW.motorFrontRight);
		backRight = new CANTalon(HW.motorBackRight);

		//limitBackA = new DigitalInput(HW.switchBackLimitA);
		//limitBackB = new DigitalInput(HW.switchBackLimitB);

		gyroscope = new Gyro(HW.aGyroscope);

		robotDrive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
		robotDrive.setSafetyEnabled(false);
	}
	
	public boolean isAtBack()
	{
		return limitBackA.get() || limitBackB.get();
	}

	public void setRamped(boolean a) {
		this.allowRamped = a;
	}

	public boolean getRamped() {
		return this.allowRamped;
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

		prevLeft = left;
		prevRight = right;

		robotDrive.tankDrive(left * speedScaling, right * speedScaling);
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

		prevX = ox;
		prevY = oy;
		robotDrive.arcadeDrive(ox * speedScaling, oy * speedScaling);
		// robotdrive is dumb arcadeDrive so params are switched
	}

	public void holonomicDriveRamp(double y, double x, double r) { // h-drive
		if (!allowRamped) {
			holonomicDriveUnramped(-y, x, r);
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

		prevX = ox;
		prevY = oy;
		prevR = or;
		if (speedUp == true)
			robotDrive.mecanumDrive_Cartesian(ox * speedScaling, oy * speedScaling, or * speedScaling, 0);
		else 
			robotDrive.mecanumDrive_Cartesian(ox * speedScaling *3/4, oy * speedScaling *3/4, or * speedScaling/2.5, 0);
	}

	public void tankDriveUnramped(double leftStick, double rightStick) {
		prevLeft = 0;
		prevRight = 0;
		prevX = 0;
		prevY = 0;
		prevR = 0;
		robotDrive.tankDrive(leftStick * speedScaling, -rightStick
				* speedScaling);
	}

	public void arcadeDriveUnramped(double y, double x) {
		prevLeft = 0;
		prevRight = 0;
		prevX = 0;
		prevY = 0;
		prevR = 0;
		robotDrive.arcadeDrive(x * speedScaling, -y * speedScaling);
		// robotdrive is dumb arcadeDrive so params are switched
	}

	public void holonomicDriveUnramped(double y, double x, double r) { // h-drive
		robotDrive.mecanumDrive_Cartesian(x * speedScaling, y * speedScaling, r * speedScaling, 0);
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
		} else {
			return -9001;
		}
	}

	public double getMotorCurrent(Motor motor) {
		if (motor == Motor.BACKLEFT) {
			return backLeft.getOutputCurrent();
		} else if (motor == Motor.BACKRIGHT) {
			return backRight.getOutputCurrent();
		} else if (motor == Motor.FRONTLEFT) {
			return frontLeft.getOutputCurrent();
		} else if (motor == Motor.FRONTRIGHT) {
			return frontRight.getOutputCurrent();
		} else {
			return -9001;
		}
	}

	public double getAngle() {
		return gyroscope.getAngle();
	}

	public void resetAngle() {
		gyroscope.reset();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new HolonomicDriveCommand());
	}

	public void setSpeedScaling(double x) {
		speedScaling = x;
	}
	public boolean speedBoostTest()
	{
		return speedUp;
	}
	public void setSpeedUp(boolean newSpd)
	{
		speedUp = newSpd;
	}
}

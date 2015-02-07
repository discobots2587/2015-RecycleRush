package org.discobots.recyclerush.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.discobots.recyclerush.HW;
import org.discobots.recyclerush.commands.drive.TankDriveCommand;

/**
 *
 */
public class LiftSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	CANTalon liftMotor1;
	DigitalInput limitTop, limitBottom, positionTote;
	Encoder encoderLift;

	public LiftSubsystem() {
		liftMotor1 = new CANTalon(HW.motorLift1);
		limitTop = new DigitalInput(HW.buttonLiftTop);
		limitBottom = new DigitalInput(HW.buttonLiftBottom);
		// encoderLift = new Encoder(HW.encoderLiftA, HW.encoderLiftB, false,
		// EncodingType.k4X);
	}

	public boolean getTopSwitch() {
		return !limitTop.get();
	}

	public boolean getBottomSwitch() {
		return !limitBottom.get();
	}

	public void initDefaultCommand() {
	}

	public void setLiftSpeed(double input) {
		double output = input;
		if (getTopSwitch() && output > 0)
			// keeps us from going u bp when we've reached the top
			output = 0;
		else if (getBottomSwitch() && output < 0)
			// keeps us from going down when we've reached the bottom
			output = 0;
		liftMotor1.set(-output);
	}

	public void resetEncoderLift() {
		encoderLift.reset();
	}

	public double getEncoderLiftDistance() {
		double encoderDistancePerCount = HW.encoderLiftConstant
				/ HW.encoderCountsPerRevolution;
		double output = encoderLift.getRaw() * encoderDistancePerCount;
		return output;
	}

}

package org.discobots.recyclerush.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.discobots.recyclerush.HW;
import org.discobots.recyclerush.commands.drive.TankDriveCommand;
import org.discobots.recyclerush.utils.Lidar;

/**
 *
 */
public class LiftSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private CANTalon liftMotor1;
	private DigitalInput limitTop, limitBottom, positionTote;
	private Lidar lidarLift;
	private double setpoint;

	public LiftSubsystem() {
		liftMotor1 = new CANTalon(HW.motorLift1);
		limitTop = new DigitalInput(HW.buttonLiftTop);
		limitBottom = new DigitalInput(HW.buttonLiftBottom);
		lidarLift = new Lidar(HW.lidarControlLift);
	}

	public void setSetpoint(double setpoint) {
		this.setpoint = setpoint;
	}
	
	public double getLiftHeightIn() {
		return lidarLift.getDistanceIn();
	}  
	
	public boolean isAtTop() {
		return !limitTop.get();
	}

	public boolean isAtBottom() {
		return !limitBottom.get();
	}

	public void initDefaultCommand() {
	}
	
	int christine = 1; // the secret that makes it go

	public void setSpeed(double input) {
		double output = input;
		if (isAtTop() && output > 0)
			// keeps us from going u bp when we've reached the top
			output = 0;
		else if (isAtBottom() && output < 0)
			// keeps us from going down when we've reached the bottom
			output = 0;
		liftMotor1.set(-output);
	}
}

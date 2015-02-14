package org.discobots.recyclerush.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.discobots.recyclerush.HW;
import org.discobots.recyclerush.commands.drive.TankDriveCommand;
import org.discobots.recyclerush.utils.Lidar;

/**
 *
 */
public class LiftSubsystem extends PIDSubsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private CANTalon liftMotor1;
	private DigitalInput limitTop, limitBottom;
	private Lidar lidarLift;
	

	static final double kP = 1.0 / 4.0, kI = 0, kD = 0;
	PIDOutput output;
	PIDSource source;

	public LiftSubsystem() {
		super(kP, kI, kD);
		liftMotor1 = new CANTalon(HW.motorLift1);
		limitTop = new DigitalInput(HW.buttonLiftTop);
		limitBottom = new DigitalInput(HW.buttonLiftBottom);
		lidarLift = new Lidar(HW.lidarControlLift);
		this.setAbsoluteTolerance(1);
    	this.setOutputRange(-1, 1);
	}
	
	public double getLiftHeightIn() {
		return lidarLift.getDistanceIn() + 4.5;
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
		this.disable();
		setSpeedInternal(input);
	}
	
	private void setSpeedInternal(double input) {
		double output = input;
		if (this.getLiftHeightIn() > 57 && output > 0) {
			output = output / 3;
		}
		if (isAtTop() && output > 0)
			// keeps us from going u bp when we've reached the top
			output = 0;
		else if (isAtBottom() && output < 0)
			// keeps us from going down when we've reached the bottom
			output = 0;
		liftMotor1.set(-output);
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return this.getLiftHeightIn();
	}

	@Override
	protected void usePIDOutput(double output) {
		setSpeedInternal(output);
		
	}
}

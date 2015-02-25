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
import org.discobots.recyclerush.commands.lift.VariableLiftCommand;
import org.discobots.recyclerush.utils.Lidar;

/**
 *
 */
public class LiftSubsystem extends PIDSubsystem {
	/* == == == MECHANICAL NOTE == == ==
	 * Plastic Hub on lift shaft is broken but works.
	 */
	
	private CANTalon liftMotor1;
	private DigitalInput limitTop, limitBottom;
	private Lidar lidarLift;
	public static final double kMaxHeight = 62;
	public static final double kHeightSlow = 59;
	public static final double kMinHeight = 5;

	public static final double kP = 1.0 / 4.0, kI = 0, kD = 0;
	PIDOutput output;
	PIDSource source;
	
	SpeedMonitor speedControlThread;
	private double setpointSpeed;

	public LiftSubsystem() {
		super(kP, kI, kD);
		liftMotor1 = new CANTalon(HW.motorLift1);
		limitTop = new DigitalInput(HW.buttonLiftTop);
		limitBottom = new DigitalInput(HW.buttonLiftBottom);
		lidarLift = new Lidar(HW.lidarControlLift);

		setpointSpeed = 0;
		speedControlThread = new SpeedMonitor();
		speedControlThread.setName("D.SpeedControl");
		speedControlThread.start();
		
		this.setAbsoluteTolerance(1);
    	this.setOutputRange(-1, 1);
	}
	
	public double getLiftHeightInches() {
		return lidarLift.getDistanceIn() + 4.5;
	}  
	
	
	public boolean isAtTop() {
		return !limitTop.get() /*|| getLiftHeightInches() > LiftSubsystem.kMaxHeight*/;
	}

	public boolean isAtBottom() {
		return !limitBottom.get() /*|| getLiftHeightInches() < LiftSubsystem.kMinHeight*/;
	}

	public void initDefaultCommand() {
		//setDefaultCommand(new VariableLiftCommand());
	}
	
	int christine = 1; // the secret that makes it go

	public void setSpeed(double input) {
		this.disable();
		this.setpointSpeed = input;
		//setSpeedInternal(input);
	}
	
	
	private void setSpeedInternal(double input) {
		double output = input;
		/*if (this.getLiftHeightInches() > kHeightSlow && output > 0) {
			output = output / 3;
		}*/
		/*if (this.getLiftHeightInches() < kMinHeight + 3 && output > 0) {
			output = output / 3;
		}*/
		if (isAtTop() && output > 0)
			// keeps us from going up when we've reached the top
			output = 0;
		else if (isAtBottom() && output < 0)
			// keeps us from going down when we've reached the bottom
			output = 0;
		liftMotor1.set(-output);
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return this.getLiftHeightInches();
	}

	@Override
	protected void usePIDOutput(double output) {
		this.setpointSpeed = output;
		//setSpeedInternal(output);
	}
	
	class SpeedMonitor extends Thread {
		public SpeedMonitor() {
			
		}
		public void run() {
			while (true) {
				setSpeedInternal(setpointSpeed);
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

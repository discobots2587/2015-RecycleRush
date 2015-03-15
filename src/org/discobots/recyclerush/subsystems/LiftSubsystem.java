package org.discobots.recyclerush.subsystems;

import org.discobots.recyclerush.HW;
import org.discobots.recyclerush.utils.Lidar;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/** LiftSubsystem
 * This class has a thread so that we can actively update the speed of the 
 * lift motor based on the height. Otherwise speed will only be set when
 * the button is pressed and not while the lift is moving. PID Controller
 * does work with the lidar.
 * 
 */
public class LiftSubsystem extends PIDSubsystem {
	
	private CANTalon liftMotorLeft, liftMotorRight;
	private DigitalInput limitTop, limitBottom;
	private Lidar lidarLift;
	public static final double kMaxHeight = 60;
	public static final double kHeightSlow = 56;
	public static final double kMinHeight = 10;

	public static final double kP = 1.0 / 4.0, kI = 0, kD = 0;
	PIDOutput output;
	PIDSource source;
	
	SpeedMonitor speedControlThread;
	private double setpointSpeed;
	
	private boolean useLidar = true;

	public LiftSubsystem() {
		super(kP, kI, kD);
		liftMotorLeft = new CANTalon(HW.motorLiftLeft);
		liftMotorRight = new CANTalon(HW.motorLiftRight);
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
	
	public static final boolean kMotorLiftLeft = false;
	public static final boolean kMotorLiftRight = true;
	public double getCurrent(boolean motor) {
		if (kMotorLiftLeft) {
			return liftMotorLeft.getOutputCurrent();
		} else {
			return liftMotorRight.getOutputCurrent();
		}
	}
	
	public double getLiftHeightInches() {
		return lidarLift.getDistanceIn() + 4.5;
	}  
	
	
	public boolean isAtTop() {
		if (useLidar) {
			return !limitTop.get() || getLiftHeightInches() > LiftSubsystem.kMaxHeight;
		} else {
			return !limitTop.get();
		}
	}

	public boolean isAtBottom() {
		if (useLidar) {
			return !limitTop.get() || getLiftHeightInches() > LiftSubsystem.kMaxHeight;
		} else {
			return !limitBottom.get();
		}
	}

	public void initDefaultCommand() {
	}
	
	int christine = 1; // the secret that makes it go

	public void setSpeed(double input) {
		this.disable();
		this.setpointSpeed = input;
	}

	public void shutdownLidar() {
		this.useLidar = false;
		this.disable();
	}
	
	private void setSpeedInternal(double input) {
		double output = input;
		if (useLidar && this.getLiftHeightInches() > kHeightSlow && output > 0) {
			output = output / 3;
		}
		if (isAtTop() && output > 0)
			// keeps us from going up when we've reached the top
			output = 0;
		else if (isAtBottom() && output < 0)
			// keeps us from going down when we've reached the bottom
			output = 0;
		liftMotorLeft.set(output);
		liftMotorRight.set(output);
	}

	@Override
	protected double returnPIDInput() {
		return this.getLiftHeightInches();
	}

	@Override
	protected void usePIDOutput(double output) {
		if (useLidar) {
			this.setpointSpeed = output;
		} else {
			this.setpointSpeed = 0;
		}
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
					e.printStackTrace();
				}
			}
		}
	}
}

package org.discobots.recyclerush.commands.auton;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import org.discobots.recyclerush.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
*
*/
public class MoveForwardHoloCommand extends Command {
	
	long time;
	long duration;
	double speed;
	PIDController controller;
	PIDSource controllerSource;
	PIDOutput controllerOutput;
	double controllerSourceVal;
	double controllerOutputVal;

	public MoveForwardHoloCommand(long time, double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrainSub);
		duration = time;
		this.speed = speed;
		controllerSource = new PIDSource() {
			public double pidGet() {
				return controllerSourceVal;
			}
		};
		controllerOutput = new PIDOutput() {
			public void pidWrite(double output) {
				controllerOutputVal = output;
			}
		};
		controller = new PIDController(1.0 / 30.0, 0, 0, controllerSource,
				controllerOutput);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		time = System.currentTimeMillis();
		controller.setSetpoint(Robot.driveTrainSub.getAngle());
		controller.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		this.controllerSourceVal = Robot.driveTrainSub.getAngle();
		Robot.driveTrainSub.holonomicDriveRamp(-speed, 0, controllerOutputVal);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return System.currentTimeMillis() - time >= duration;
	}

	// Called once after isFinished returns true
	protected void end() {
		controller.disable();
		Robot.driveTrainSub.holonomicDriveUnramped(0, 0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
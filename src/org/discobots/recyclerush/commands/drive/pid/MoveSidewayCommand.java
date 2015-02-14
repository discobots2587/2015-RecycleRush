package org.discobots.recyclerush.commands.drive.pid;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveSidewayCommand extends Command {

	double kP, kI, kD;
	double distance;
	double sourceVal;
	double outputVal;
	double y, x;
	PIDSource pidSource;
	PIDOutput pidOutput;
	PIDController pidController;

	public MoveSidewayCommand(double distance) {
		pidSource = new PIDSource() {

			@Override
			public double pidGet() {
				// TODO Auto-generated method stub
				return sourceVal;
			}
		};

		pidOutput = new PIDOutput() {

			@Override
			public void pidWrite(double output) {
				// TODO Auto-generated method stub
				outputVal = output;
			}

		};
		
		pidController = new PIDController(kP, kI, kD, pidSource, pidOutput);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrainSub);
		this.distance = distance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		pidController.setSetpoint(distance);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		sourceVal = Robot.driveTrainSub.getSidewayDistance();
		Robot.driveTrainSub.holonomicDriveRamp(0, outputVal, 0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return pidController.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrainSub.arcadeDriveRamp(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}

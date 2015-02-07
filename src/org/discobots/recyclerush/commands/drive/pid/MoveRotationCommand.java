package org.discobots.recyclerush.commands.drive.pid;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveRotationCommand extends Command {

	double kP, kI, kD;
	double angle;
	double sourceVal;
	double outputVal;
	double y, x;
	PIDSource pidSource;
	PIDOutput pidOutput;
	PIDController pidController;

	public MoveRotationCommand(double angle) {
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
		this.angle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		pidController.setSetpoint(angle);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		sourceVal = Robot.driveTrainSub.getGyroscopeAngle();
		Robot.driveTrainSub.arcadeDriveRamp(0, outputVal);
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

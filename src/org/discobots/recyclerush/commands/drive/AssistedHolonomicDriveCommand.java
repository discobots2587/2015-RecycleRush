package org.discobots.recyclerush.commands.drive;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AssistedHolonomicDriveCommand extends Command {
	
	PIDController controller;
	PIDSource controllerSource;
	PIDOutput controllerOutput;
	double controllerSourceVal;
	double controllerOutputVal;
	
    public AssistedHolonomicDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrainSub);
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
    	controller = new PIDController(1.0/30.0, 0, 0, controllerSource, controllerOutput);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	controller.setSetpoint(Robot.driveTrainSub.getAngle());
    	controller.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double y = Robot.oi.getRawAnalogStickALY();
    	double x = Robot.oi.getRawAnalogStickALX();
    	double r = Robot.oi.getRawAnalogStickARX();
    	
    	if (Math.abs(r) > 0.1) { // we're rotating
        	controller.setSetpoint(Robot.driveTrainSub.getAngle());
    		this.controllerSourceVal = Robot.driveTrainSub.getAngle();
        	Robot.driveTrainSub.holonomicDriveRamp(y, x, r*Math.abs(r));
    	} else { // we're strafing or standing still
    		this.controllerSourceVal = Robot.driveTrainSub.getAngle();
        	Robot.driveTrainSub.holonomicDriveRamp(y, x, this.controllerOutputVal);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;

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

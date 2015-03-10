package org.discobots.recyclerush.utils;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetPlowCommand extends Command {
	double plowSpeed;
	
    public SetPlowCommand(double plowSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.plowSub);
    	this.plowSpeed = plowSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.plowSub.setSpeedLeft(plowSpeed);
    	Robot.plowSub.setSpeedRight(plowSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

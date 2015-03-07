package org.discobots.recyclerush.commands.lift;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseLiftCommand extends Command {

	double liftSpd;
	long time;
	long duration;
	
    public RaiseLiftCommand(double liftSpd, long duration) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.liftSub);
    	this.liftSpd=liftSpd;
    	this.duration=duration;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = System.currentTimeMillis();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.liftSub.setSpeed(liftSpd);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.currentTimeMillis() - time >= duration || Robot.liftSub.isAtTop() || Robot.liftSub.isAtBottom();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.liftSub.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

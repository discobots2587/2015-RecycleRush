package org.discobots.recyclerush.commands.intake;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleIntakeSolenoids extends Command {
int closed;
    public ToggleIntakeSolenoids(int closed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeSub);
    	this.closed=closed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(closed==1)
    		Robot.intakeSub.setIntakeDoubleSol(true);
    	else
    		Robot.intakeSub.setIntakeDoubleSol(false);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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

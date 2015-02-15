package org.discobots.recyclerush.commands.wings;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetWingCommand extends Command {
	boolean pos;
    public SetWingCommand(boolean pos) {
        requires(Robot.wingSub);
        this.pos = pos;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.wingSub.set(pos);
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
    }
}

package org.discobots.recyclerush.commands.claw;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetClawCommand extends Command {
	boolean clawSPD;

	public SetClawCommand(boolean clawSPD) {
		requires(Robot.clawSub);
		this.clawSPD = clawSPD;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.clawSub.setClaw(clawSPD);
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

package org.discobots.recyclerush.commands.claw;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetClawCommand extends Command {
	boolean intakeSPD;

	public SetClawCommand(boolean intakeSPD) {
		requires(Robot.clawSub);
		this.intakeSPD = intakeSPD;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.clawSub.setIntake(intakeSPD);
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

package org.discobots.recyclerush.commands.drive;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CycleDriveCommand extends Command {
	
	public CycleDriveCommand() {
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Command driveCmd = Robot.driveTrainSub.getCurrentCommand();
		if (driveCmd instanceof ArcadeDriveCommand) {
			new TankDriveCommand().start();
		} else if (driveCmd instanceof ArcadeDriveCommand) {
			new StickDriveCommand().start();
		} else if (driveCmd instanceof StickDriveCommand) {
			new HolonomicDriveCommand().start();
		} else {
			new ArcadeDriveCommand().start();
		}
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

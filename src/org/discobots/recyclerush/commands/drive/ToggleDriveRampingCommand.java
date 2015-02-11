 package org.discobots.recyclerush.commands.drive;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleDriveRampingCommand extends Command {

	public ToggleDriveRampingCommand() {
	}
	
	@Override
	protected void initialize() {
		Robot.driveTrainSub.setRamped(!Robot.driveTrainSub.getRamped());
	}

	@Override
	protected void execute() {

	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {

	}

}

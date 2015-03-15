package org.discobots.recyclerush.commands;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleCompressor extends Command {

	@Override
	protected void initialize() {
		Robot.electricalSub.setCompressor(!Robot.electricalSub.getCompressorState());
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

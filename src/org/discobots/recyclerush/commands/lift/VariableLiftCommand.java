package org.discobots.recyclerush.commands.lift;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class VariableLiftCommand extends Command {

	public VariableLiftCommand() {
		requires(Robot.liftSub);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		Robot.liftSub.setSpeed(Robot.oi.getRawAnalogStickBLY()/2);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		Robot.liftSub.setSpeed(0);

	}

	@Override
	protected void interrupted() {
		end();
		// TODO Auto-generated method stub

	}

}
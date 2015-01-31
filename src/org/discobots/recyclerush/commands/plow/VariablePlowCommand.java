package org.discobots.recyclerush.commands.plow;

import org.discobots.recyclerush.Robot;
import org.discobots.recyclerush.subsystems.PlowSubsystem.Motor;

import edu.wpi.first.wpilibj.command.Command;

public class VariablePlowCommand extends Command {

	public VariablePlowCommand() {
		requires(Robot.plowSub);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		Robot.plowSub.setSpeed(Robot.oi.getRawAnalogStickBLY(), Motor.LEFT);
		Robot.plowSub.setSpeed(Robot.oi.getRawAnalogStickBRY(), Motor.RIGHT);

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		Robot.plowSub.setSpeed(0, Motor.LEFT);
		Robot.plowSub.setSpeed(0, Motor.RIGHT);

	}

	@Override
	protected void interrupted() {
		end();
		// TODO Auto-generated method stub

	}

}

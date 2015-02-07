package org.discobots.recyclerush.commands.lift;

import org.discobots.recyclerush.Robot;
import org.discobots.recyclerush.subsystems.PlowSubsystem.Motor;

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
		//Robot.liftSub.setLiftSpeed(-Robot.oi.getRawAnalogStickBLY());
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

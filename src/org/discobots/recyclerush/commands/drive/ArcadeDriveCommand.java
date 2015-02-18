package org.discobots.recyclerush.commands.drive;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDriveCommand extends Command {

	public ArcadeDriveCommand() {
		requires(Robot.driveTrainSub);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	public static final double kScaleLeftStick = 7.0/8.0;
	public static final double kScaleRightStick = 1.0;
	
	protected void execute() {
		double move = Robot.oi.getRawAnalogStickALY() * kScaleLeftStick
				+ Robot.oi.getRawAnalogStickARY() * kScaleRightStick;
		double turn = Robot.oi.getRawAnalogStickALX() * kScaleLeftStick
				+ Robot.oi.getRawAnalogStickARX() * kScaleRightStick;
		Robot.driveTrainSub.arcadeDriveRamp(move, turn);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrainSub.arcadeDriveUnramped(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}

package org.discobots.recyclerush.commands.drive;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToggleDriveTrainSpeedConstant extends Command {

	private static double constant = 5.0 / 7.0;

	public ToggleDriveTrainSpeedConstant() {
	}

	protected void initialize() {
		if (constant == 0.7) {
			Robot.driveTrainSub.setSpeedScaling(1.0);
			constant = 1.0;
		} else {
			Robot.driveTrainSub.setSpeedScaling(0.7);
			constant = 0.7;
		}
		SmartDashboard.putNumber("Speed Scaling", constant);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
		end();
	}
}

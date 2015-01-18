package org.discobots.recyclerush.commands.drive;

import edu.wpi.first.wpilibj.command.Command;

public class CycleDriveCommand extends Command {

	public static final int MODE_ARCADE = 0;
	public static final int MODE_STICK = 1;
	public static final int MODE_HOLONOMIC = 2;
	public static final int MODE_TANK = 3;

	int mode = 0;

	public CycleDriveCommand() {
		if (mode < 3)
			mode++;
		else
			mode = 0;
	}

	public CycleDriveCommand(int mode) {
		this.mode = mode;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		switch (mode) {
		case MODE_ARCADE:
			new ArcadeDriveCommand().start();
			break;
		case MODE_STICK:
			new StickDriveCommand().start();
			break;
		case MODE_HOLONOMIC:
			new HolonomicDriveCommand().start();
			break;
		case MODE_TANK:
			new TankDriveCommand().start();
			break;
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

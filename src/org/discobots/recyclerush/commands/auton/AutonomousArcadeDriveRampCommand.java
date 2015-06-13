package org.discobots.recyclerush.commands.auton;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousArcadeDriveRampCommand extends Command {

	private double y;
	private double x;
	private int time;
	private long endTime;
	
	public AutonomousArcadeDriveRampCommand(double y, double x, int time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.driveTrainSub);
		this.y = y;
		this.x = x;
		this.time = time;
		
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrainSub.arcadeDriveRamp(y, x);
    	endTime = System.currentTimeMillis() + time;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrainSub.arcadeDriveRamp(y, x);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return endTime <= System.currentTimeMillis();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrainSub.arcadeDriveRamp(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

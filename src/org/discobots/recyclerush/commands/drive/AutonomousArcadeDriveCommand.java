package org.discobots.recyclerush.commands.drive;

import org.discobots.recyclerush.Robot;
import org.discobots.recyclerush.subsystems.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousArcadeDriveCommand extends Command {
	
	private double y;
	private double x;
	private int time;
	private long startTime;

    public AutonomousArcadeDriveCommand(double y, double x, int time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrainSub);
    	this.y = y;
    	this.x = x;
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrainSub.arcadeDriveUnramped(0, 0);
    	startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrainSub.arcadeDriveUnramped(y, x);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.currentTimeMillis() - startTime > time;
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

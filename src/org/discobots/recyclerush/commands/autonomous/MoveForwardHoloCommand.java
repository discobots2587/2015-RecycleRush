package org.discobots.recyclerush.commands.autonomous;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveForwardHoloCommand extends Command {

	long time;
	long duration;
	double speed;
	
    public MoveForwardHoloCommand(long time, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrainSub);
    	duration = time;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrainSub.holonomicDriveRamp(-speed, 0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return System.currentTimeMillis()-time>=duration;

    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrainSub.holonomicDriveRamp(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

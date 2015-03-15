package org.discobots.recyclerush.commands.drive;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToggleDriveTrainSpeedConstant extends Command {

	private static double constant = 5.0/7.0;
	
    public ToggleDriveTrainSpeedConstant() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
  
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(constant==5.0/7.0)
    	{
    		Robot.driveTrainSub.setSpeedScaling(1.0); 
    		constant = 1.0;
    	}	
    	else
    	{
    		Robot.driveTrainSub.setSpeedScaling(5.0/7.0);
    		constant = 5.0/7.0;
    	}
    	SmartDashboard.putNumber("Speed Scaling", constant);
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
    	end();
    }
}
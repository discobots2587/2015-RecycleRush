package org.discobots.recyclerush.commands;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CheckWingStateCommand extends Command {

    public CheckWingStateCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.wingSub);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.wingSub.get()==1){
    		Robot.wingSub.set(-1);
    		try{
    			Thread.sleep(1000);
    		}
    		catch(InterruptedException ex)
    		{
    			ex.printStackTrace();
    		}
    	}
    		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

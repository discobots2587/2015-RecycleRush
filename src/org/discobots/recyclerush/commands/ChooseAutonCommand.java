package org.discobots.recyclerush.commands;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ChooseAutonCommand extends Command {
int auton;
    public ChooseAutonCommand(int auton) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.auton=auton;
    	Robot.auton = this.auton;
    	SmartDashboard.putNumber("Setting Auton To:", this.auton);
    }



	// Called just before this Command runs the first time
    protected void initialize() {
    	Robot.auton = this.auton;

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	end();
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

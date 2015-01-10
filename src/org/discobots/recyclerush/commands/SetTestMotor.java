package org.discobots.recyclerush.commands;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetTestMotor extends Command {
	int a;
	double b;
    public SetTestMotor(int a, double b) {
    	this.a = a;
    	this.b = b;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrainSubsystem.setMotor(a, b);
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
    	end();
    }
}

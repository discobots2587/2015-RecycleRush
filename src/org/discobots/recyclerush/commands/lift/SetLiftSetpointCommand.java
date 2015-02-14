package org.discobots.recyclerush.commands.lift;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetLiftSetpointCommand extends Command {
	double setpoint;
    public SetLiftSetpointCommand(double setpoint) {
    	this.setpoint = setpoint;
    	requires(Robot.liftSub);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.liftSub.enable();
    	Robot.liftSub.setSetpoint(setpoint);
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

package org.discobots.recyclerush.commands.intake;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetActiveIntakeCommand extends Command {
double direction;
    public SetActiveIntakeCommand(double direction) {
        this.direction=direction;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intakeSub.setActiveIntake(direction);
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

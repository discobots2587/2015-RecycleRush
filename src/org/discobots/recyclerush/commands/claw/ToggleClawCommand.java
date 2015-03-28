package org.discobots.recyclerush.commands.claw;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleClawCommand extends Command {

    public ToggleClawCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.clawSub);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.clawSub.setIntake(!Robot.clawSub.getIntakeValue());
    	Robot.electricalSub.setCompressor(true);
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

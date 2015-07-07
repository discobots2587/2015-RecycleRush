package org.discobots.recyclerush.commands.drive;

import org.discobots.recyclerush.OI.Hand;
import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HolonomicDriveCommand extends Command {

    public HolonomicDriveCommand() {
        requires(Robot.driveTrainSub);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double y = Robot.oi.getRawAnalogStickALY();
    	double x = Robot.oi.getRawAnalogStickALX();
    	double r = Robot.oi.getRawAnalogStickARX();
    	
    	if (x<=-0.1)
    	Robot.oi.setRumble(Hand.LEFT, -x);
    	else
    		Robot.oi.setRumble(Hand.LEFT, 0);
    	if (x>=0.1)
    		Robot.oi.setRumble(Hand.RIGHT, x);
    	else
    		Robot.oi.setRumble(Hand.RIGHT,0);
    	//if (Math.abs(y) < 0.1)
    	//	y = 0;
    	//if (Math.abs(x) < 0.1)
    	//	x = 0;
    	//if (Math.abs(r) < 0.1)
    	//	r = 0;
    	
    	Robot.driveTrainSub.holonomicDriveRamp(y, x, r);
    	//System.out.println(x + " " + y + " " + r);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrainSub.holonomicDriveUnramped(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

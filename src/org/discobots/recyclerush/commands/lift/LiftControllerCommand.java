package org.discobots.recyclerush.commands.lift;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LiftControllerCommand extends Command {
	
	PIDController pidcont;
	double kP = 1.0 / 6.0, kI = 0, kD = 0;
	PIDOutput output;
	PIDSource source;
	double outVal = 0, inVal = 0;
	
    public LiftControllerCommand(double setpoint) {
    	requires(Robot.liftSub);
    	output = new PIDOutput() {

			@Override
			public void pidWrite(double output) {
				outVal = output;
				
			}
    		            
    	};
    	source = new PIDSource() {

			@Override
			public double pidGet() {
				// TODO Auto-generated method stub
				return inVal;
			}
    		
    	};
    	
    	pidcont = new PIDController(kP, kI, kD, source, output);
    	pidcont.setSetpoint(setpoint);
    	pidcont.setAbsoluteTolerance(1);
    	pidcont.setOutputRange(-1, 1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	pidcont.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	this.inVal = Robot.liftSub.getLiftHeightIn();
    	SmartDashboard.putNumber("TMP Lift Out", outVal);
    	Robot.liftSub.setSpeed(outVal);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pidcont.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	pidcont.disable();
    	Robot.liftSub.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

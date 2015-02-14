package org.discobots.recyclerush.commands.drive;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CheesyArcadeDrive extends Command {
	
	private double move;
    private double turn;
    private double driveLeft = 0, driveRight = 0;
    private double a = 0.25;
    private double b = 1;
    private double oldTurn = 0.0;
    double negInertiaAccumulator = 0.0;
    private boolean isQuickTurn = true;
    private double quickStopAccumulator;

    public CheesyArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrainSub);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	move = turn = 0;
        //calculateInput();

        double negInertia = turn - oldTurn;
        oldTurn = turn;

        //SINE SCALING OMITTED
        double sensitivity = 0.85;
        // Negative inertia!

        double negInertiaScalar = 5.0;

        if (turn * negInertia > 0) {//same direction: accelerating
            negInertiaScalar = 2.5;
        } else {//decelerating
            if (Math.abs(turn) > 0.65) {
                negInertiaScalar = 5.0;
            } else {
                negInertiaScalar = 3.0;
            }
        }
        double negInertiaPower = negInertia * negInertiaScalar;
        negInertiaAccumulator += negInertiaPower;

        turn = turn + negInertiaAccumulator;
        if (negInertiaAccumulator > 1) {
            negInertiaAccumulator -= 1;
        } else if (negInertiaAccumulator < -1) {
            negInertiaAccumulator += 1;
        } else {
            negInertiaAccumulator = 0;
        }

        // Quickturn!
        if (isQuickTurn) {
            if (Math.abs(move) < 0.2) {
                double alpha = 0.1;
                quickStopAccumulator = (1 - alpha) * quickStopAccumulator + alpha
                        * limit(turn, 1.0) * 5;
            }
            sensitivity = 1.0;
        }
        if (negInertia < -0.15) {//request decel a lot
            turn -= quickStopAccumulator;
            if (quickStopAccumulator > 1) {
                quickStopAccumulator -= 1;
            } else if (quickStopAccumulator < -1) {
                quickStopAccumulator += 1;
            } else {
                quickStopAccumulator = 0.0;
            }
        }

        //The important part
        if (move >= 0 && turn >= 0) {//Q1
            driveRight = turn * b + move * (1 - turn * b);
            driveLeft = -1 * turn * b + move * ((1 - turn * (a + 1)) + turn * b);
        } else if (move >= 0 && turn < 0) {//Q2
            driveRight = turn * b + move * (1 + turn * (1 + a) - turn * b);
            driveLeft = -1 * turn * b + move * (1 + turn * b);
        } else if (move < 0 && turn >= 0) {//Q4
            driveRight = turn * b - move * (turn * (a + 1) - 1 - turn * b);
            driveLeft = -1 * turn * b - move * (-1 + turn * b);
        } else if (move < 0 && turn < 0) {//Q3
            driveRight = turn * b - move * (-1 - turn * b);
            driveLeft = -1 * turn * b - move * (-1 - turn * (a + 1) + turn * b);
        }

        Robot.driveTrainSub.arcadeDriveUnramped(-driveLeft, -driveRight);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrainSub.arcadeDriveUnramped(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    public static double limit(double v, double limit) {
        return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
    }
}

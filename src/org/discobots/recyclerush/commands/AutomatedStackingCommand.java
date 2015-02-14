package org.discobots.recyclerush.commands;

import org.discobots.recyclerush.HW;
import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutomatedStackingCommand extends Command {

	public AutomatedStackingCommand(int target) {
		requires(Robot.liftSub);
		requires(Robot.intakeSub);
		this.target = target;
	}
	
	boolean initialLiftPositionBelowOpeningPosition = false;

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run

	int counter = 0;
	int target = 0;
	
	int state = 0;
	final static int state_upReadyForNext = 0;
	final static int state_movingDownForDrop = 1;
	final static int state_moveForwardForNext = 2;
	final static int state_midReleasingHeld = 3;
	final static int state_movingDownForNext = 4;
	final static int state_downEatingNext = 5;
	final static int state_movingUpForHold = 6;
	final static int state_upWaitingForNext = 7;
	long wait = 0; 
	static final int wait_halfSecond = 25;
	static final int wait_tenthSecond = 5;

	protected void execute() {
		SmartDashboard.putNumber("Automated Stacking State ", state);
		if (wait > 0) {
			wait--;
			return;
		}
		
		switch (state) {
		case state_upReadyForNext:
			state = state_movingDownForDrop;
			Robot.liftSub.setSetpoint(HW.liftPosToteOneRaise - 6);
			Robot.liftSub.enable();
			break;
		case state_movingDownForDrop:
			if (Robot.liftSub.onTarget()) {
				state = state_moveForwardForNext;
				Robot.liftSub.disable();
			}
			break;
		case state_moveForwardForNext:
			Robot.driveTrainSub.tankDriveUnramped(0.5, 0.5);
			wait = wait_tenthSecond;
			state = state_midReleasingHeld;
			break;
		case state_midReleasingHeld:
			Robot.driveTrainSub.tankDriveUnramped(0.0, 0.0);
			Robot.intakeSub.setIntake(false);
			wait = wait_tenthSecond;
			state = state_movingDownForNext;
			break;
		case state_movingDownForNext:
			Robot.liftSub.setSetpoint(0);
			Robot.liftSub.enable();
			if (Robot.liftSub.isAtBottom()) {
				state = state_downEatingNext;
				Robot.liftSub.disable();
			}
			break;
		case state_downEatingNext:
			Robot.intakeSub.setIntake(true);
			wait = wait_tenthSecond * 5;
			counter++;
			state = state_movingUpForHold;
			break;
		case state_movingUpForHold:
			Robot.liftSub.setSetpoint(HW.liftPosToteOneRaise + 8);
			Robot.liftSub.enable();
			if (Robot.liftSub.onTarget()) {
				Robot.liftSub.disable();
				wait = wait_halfSecond * 4;
				state = state_upReadyForNext;
			}
			break;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return counter >= target;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.liftSub.disable();
		Robot.liftSub.setSpeed(0);
		Robot.driveTrainSub.tankDriveUnramped(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}

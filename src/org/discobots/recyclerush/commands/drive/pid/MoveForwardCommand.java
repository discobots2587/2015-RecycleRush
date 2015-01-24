package org.discobots.recyclerush.commands.drive.pid;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveForwardCommand extends Command {

	double distance;
	double y, x;
	double kPR, kIR, kDR, kPL, kIL, kDL, kPA, kIA, kDA; // R right, L left, A
														// angle
	double sourceValR, sourceValL, sourceValA;
	double outputValR, outputValL, outputValA;
	PIDSource pidSourceR, pidSourceL, pidSourceA;
	PIDOutput pidOutputR, pidOutputL, pidOutputA;
	PIDController pidControllerR, pidControllerL, pidControllerA;

	public MoveForwardCommand(double distance) {
		pidSourceR = new PIDSource() {

			@Override
			public double pidGet() {
				// TODO Auto-generated method stub
				return sourceValR;
			}
		};

		pidSourceL = new PIDSource() {

			@Override
			public double pidGet() {
				// TODO Auto-generated method stub
				return sourceValL;
			}
		};
		
		pidSourceA = new PIDSource() {

			@Override
			public double pidGet() {
				// TODO Auto-generated method stub
				return sourceValA;
			}
		};

		pidOutputR = new PIDOutput() {

			@Override
			public void pidWrite(double output) {
				// TODO Auto-generated method stub
				outputValR = output;
			}

		};

		pidOutputL = new PIDOutput() {

			@Override
			public void pidWrite(double output) {
				// TODO Auto-generated method stub
				outputValL = output;
			}

		};
		
		pidOutputA = new PIDOutput() {

			@Override
			public void pidWrite(double output) {
				// TODO Auto-generated method stub
				outputValA = output;
			}

		};

		pidControllerR = new PIDController(kPR, kIR, kDR, pidSourceR, pidOutputR);
		pidControllerL = new PIDController(kPL, kIL, kDL, pidSourceL, pidOutputL);
		pidControllerA = new PIDController(kPA, kIA, kDA, pidSourceA, pidOutputA);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrainSub);
		this.distance = distance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		pidControllerR.setSetpoint(distance);
		pidControllerL.setSetpoint(distance);
		pidControllerA.setSetpoint(Robot.driveTrainSub.getGyroscopeAngle());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		sourceValR = Robot.driveTrainSub.getEncoderForwardRDistance();
		sourceValL = Robot.driveTrainSub.getEncoderForwardLDistance();
		sourceValA = Robot.driveTrainSub.getGyroscopeAngle();
		Robot.driveTrainSub.tankDrive(outputValL - outputValA, outputValR + outputValA);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return pidControllerR.onTarget() && pidControllerL.onTarget() && pidControllerA.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrainSub.tankDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}

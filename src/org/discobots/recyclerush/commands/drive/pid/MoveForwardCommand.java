package org.discobots.recyclerush.commands.drive.pid;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveForwardCommand extends Command {

	double distance;
	double y, x;
	static final double kPF = 1, 
			kIF = 0, 
			kDF = 0, 
			kPA = 0.5, 
			kIA = 0, 
			kDA = 0; // F forward, A angle
	double sourceValF, sourceValA;
	double outputValF, outputValA;
	PIDSource pidSourceF, pidSourceA;
	PIDOutput pidOutputF, pidOutputA;
	PIDController pidControllerF, pidControllerA;

	public MoveForwardCommand(double distance) {

		requires(Robot.driveTrainSub);

		pidSourceF = new PIDSource() {

			@Override
			public double pidGet() {
				// TODO Auto-generated method stub
				return sourceValF;
			}
		};

		pidSourceA = new PIDSource() {

			@Override
			public double pidGet() {
				// TODO Auto-generated method stub
				return sourceValA;
			}
		};

		pidOutputF = new PIDOutput() {

			@Override
			public void pidWrite(double output) {
				// TODO Auto-generated method stub
				outputValF = output;
			}

		};

		pidOutputA = new PIDOutput() {

			@Override
			public void pidWrite(double output) {
				// TODO Auto-generated method stub
				outputValA = output;
			}

		};

		pidControllerF = new PIDController(kPF, kIF, kDF, pidSourceF,
				pidOutputF);
		pidControllerF.setOutputRange(-1, 1);
		pidControllerA = new PIDController(kPA, kIA, kDA, pidSourceA,
				pidOutputA);
		pidControllerA.setOutputRange(-1, 1);

		this.distance = distance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		pidControllerF.setSetpoint(distance);
		pidControllerA.setSetpoint(Robot.driveTrainSub.getAngle());
		Robot.driveTrainSub.resetAngle();
		Robot.driveTrainSub.resetForwardDistance();
		pidControllerF.setAbsoluteTolerance(1);
		pidControllerA.setAbsoluteTolerance(1);
		pidControllerF.enable();
		pidControllerA.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		sourceValF = Robot.driveTrainSub.getForwardDistance();
		sourceValA = Robot.driveTrainSub.getAngle();
		SmartDashboard.putNumber("TMP MoveFoward Output Foward", outputValF);
		SmartDashboard.putNumber("TMP MoveFoward Output Rotational", outputValA);
		Robot.driveTrainSub.arcadeDriveUnramped(outputValF, outputValA);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		System.out.println(outputValF + " " + outputValA + " " + (pidControllerF.onTarget() && pidControllerA.onTarget()));
		return pidControllerF.onTarget() && pidControllerA.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrainSub.tankDriveRamp(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}

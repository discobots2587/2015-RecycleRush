package org.discobots.recyclerush;

import org.discobots.recyclerush.subsystems.DriveTrainSubsystem.Motor;
import org.discobots.recyclerush.subsystems.LiftSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard {
	
	private static int driveCounter = 0;

	public static void init() {
	}

	public static void update() {
		driveCounter++;
		if (driveCounter == Integer.MAX_VALUE) {
			driveCounter = 0;
		}

		if (driveCounter % 5 == 0) { // 100ms
			SmartDashboard.putNumber("Robot Loop Execution Time",
					Robot.loopExecutionTime);

			// SmartDashboard.putNumber("Battery Voltage", DriverStation
			// .getInstance().getBatteryVoltage());

		//	SmartDashboard.putNumber("Motor FrontLeft Current",
		//			Robot.driveTrainSub.getMotorCurrent(Motor.FRONTLEFT));
		//	SmartDashboard.putNumber("Motor BackLeft Current",
			//		Robot.driveTrainSub.getMotorCurrent(Motor.BACKLEFT));
		//	SmartDashboard.putNumber("Motor FrontRight Current",
			//		Robot.driveTrainSub.getMotorCurrent(Motor.FRONTRIGHT));
		//	SmartDashboard.putNumber("Motor BackRight Current",
			//		Robot.driveTrainSub.getMotorCurrent(Motor.BACKRIGHT));
		//	SmartDashboard.putNumber("Motor Lift Left Current",
	//				Robot.liftSub.getCurrent(LiftSubsystem.kMotorLiftLeft));
		//	SmartDashboard.putNumber("Motor Lift Right Current",
		//			Robot.liftSub.getCurrent(LiftSubsystem.kMotorLiftRight));
		} else if (driveCounter % 5 == 1) {
			
			SmartDashboard.putNumber("AUTON MODE (debug):", Robot.auton);
			SmartDashboard.putNumber("Time", Robot.totalTime);
			
			SmartDashboard.putNumber("Lift Lidar Distance (in)",
					Robot.liftSub.getLiftHeightInches());
			SmartDashboard.putBoolean("Lift Top", Robot.liftSub.isAtTop());
			SmartDashboard
					.putBoolean("Lift Bottom", Robot.liftSub.isAtBottom());
				SmartDashboard.putBoolean("is Lidar Running?", Robot.liftSub.isLidarRunning());
				SmartDashboard.putNumber("Raw Imput Lift SPD", Robot.liftSub.getRawLiftSpeed());
			SmartDashboard.putNumber("Gyroscope",
					Robot.driveTrainSub.getAngle());
			SmartDashboard.putBoolean("Pressure Full",
					Robot.electricalSub.getPressureSwitchState());
			SmartDashboard.putNumber("Pressure",
					Robot.electricalSub.getPressure());
			SmartDashboard.putNumber("Wings", Robot.wingSub.get());
			SmartDashboard.putData(Robot.driveTrainSub);
			SmartDashboard.putData(Robot.liftSub);
			SmartDashboard.putNumber("Wings Down", Robot.wingSub.get());

		}

	}


}

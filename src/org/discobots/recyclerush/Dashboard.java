package org.discobots.recyclerush;

import org.discobots.recyclerush.subsystems.DriveTrainSubsystem.Motor;
import org.discobots.recyclerush.subsystems.LiftSubsystem;

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

			//SmartDashboard.putNumber("Motor FrontLeft Current",
				//	Robot.driveTrainSub.getMotorCurrent(Motor.FRONTLEFT));
			// SmartDashboard.putNumber("Motor BackLeft Current",
			//		Robot.driveTrainSub.getMotorCurrent(Motor.BACKLEFT));
		//	SmartDashboard.putNumber("Motor FrontRight Current",
			//		Robot.driveTrainSub.getMotorCurrent(Motor.FRONTRIGHT));
		//	SmartDashboard.putNumber("Motor Back Right Current",
		//			Robot.driveTrainSub.getMotorCurrent(Motor.BACKRIGHT));
		//	SmartDashboard.putNumber("Motor Lift Left Current",
		//			Robot.liftSub.getCurrent(LiftSubsystem.kMotorLiftLeft));
		//	SmartDashboard.putNumber("Motor Lift Right Current",
			//		Robot.liftSub.getCurrent(LiftSubsystem.kMotorLiftRight));
		} else if (driveCounter % 5 == 1) {
			SmartDashboard.putNumber("Lift Lidar Distance (in)",
					Robot.liftSub.getLiftHeightInches());
			SmartDashboard.putNumber("Gyroscope",
					Robot.driveTrainSub.getAngle());
			SmartDashboard.putBoolean("Pressure Full",
					Robot.electricalSub.getPressureSwitchState());
			SmartDashboard.putNumber("Pressure",
					Robot.electricalSub.getPressure());
			SmartDashboard.putBoolean("Wings", Robot.wingSub.get());

			SmartDashboard.putBoolean("Lift Top", Robot.liftSub.isAtTop());
			SmartDashboard.putBoolean("Lift Bottom", Robot.liftSub.isAtBottom());

		//	SmartDashboard.putData(Robot.driveTrainSub);
		//	SmartDashboard.putData(Robot.liftSub);
	}
	}
	

}

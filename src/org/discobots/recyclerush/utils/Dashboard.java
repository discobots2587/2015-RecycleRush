package org.discobots.recyclerush.utils;

import org.discobots.recyclerush.Robot;
import org.discobots.recyclerush.subsystems.DriveTrainSubsystem.Motor;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard {
	
	
	private static NetworkTable debug;
	public static void init() {
		debug = NetworkTable.getTable("Debug");
	}
	
	public static void updateDriver() {
		SmartDashboard.putNumber("Robot Loop Execution Time", Robot.loopExecutionTime);

		SmartDashboard.putNumber("Battery Voltage", DriverStation.getInstance().getBatteryVoltage());

		SmartDashboard.putNumber("Motor FrontLeft Current", Robot.driveTrainSub.getMotorCurrent(Motor.FRONTLEFT));
		SmartDashboard.putNumber("Motor BackLeft Current", Robot.driveTrainSub.getMotorCurrent(Motor.BACKLEFT));
		SmartDashboard.putNumber("Motor FrontRight Current", Robot.driveTrainSub.getMotorCurrent(Motor.FRONTRIGHT));
		SmartDashboard.putNumber("Motor BackRight Current", Robot.driveTrainSub.getMotorCurrent(Motor.BACKRIGHT));
		SmartDashboard.putNumber("Motor DropDown Current", Robot.driveTrainSub.getMotorCurrent(Motor.CENTERDROPDOWN));
		
		SmartDashboard.putNumber("Motor FrontLeft Setpoint", Robot.driveTrainSub.getMotorSetpoint(Motor.FRONTLEFT));
		SmartDashboard.putNumber("Motor BackLeft Setpoint", Robot.driveTrainSub.getMotorSetpoint(Motor.BACKLEFT));
		SmartDashboard.putNumber("Motor FrontRight Setpoint", Robot.driveTrainSub.getMotorSetpoint(Motor.FRONTRIGHT));
		SmartDashboard.putNumber("Motor BackRight Setpoint", Robot.driveTrainSub.getMotorSetpoint(Motor.BACKRIGHT));
		SmartDashboard.putNumber("Motor DropDown Setpoint", Robot.driveTrainSub.getMotorSetpoint(Motor.CENTERDROPDOWN));
		
		SmartDashboard.putData(Robot.driveTrainSub);
	
	}
	
	
	public static void updateDebug() {
		//for (int index = 0; index < 16; index++) {
		//	debug.putNumber("PDP Current, Module " + index, Robot.electricalSub.getCurrentFromPDPChannel(index));
		//}
		
		debug.putNumber("PDP Total Current", Robot.electricalSub.getPDPTotalCurrent());
		debug.putNumber("PDP Temperature", Robot.electricalSub.getPDPTemperature());
		debug.putNumber("PDP Voltage", Robot.electricalSub.getPDPVoltage());
		
		debug.putBoolean("Pressure Switch State", Robot.electricalSub.getPressureSwitchState());
		debug.putBoolean("Compressor State", Robot.electricalSub.getCompressorState());
		debug.putBoolean("Compressor Control Loop State", Robot.electricalSub.getCompressorControlLoopState());
		debug.putNumber("Compressor Current", Robot.electricalSub.getCompressorCurrent());

		debug.putNumber("Motor FrontLeft Current", Robot.driveTrainSub.getMotorCurrent(Motor.FRONTLEFT));
		debug.putNumber("Motor BackLeft Current", Robot.driveTrainSub.getMotorCurrent(Motor.BACKLEFT));
		debug.putNumber("Motor FrontRight Current", Robot.driveTrainSub.getMotorCurrent(Motor.FRONTRIGHT));
		debug.putNumber("Motor BackRight Current", Robot.driveTrainSub.getMotorCurrent(Motor.BACKRIGHT));
		
		debug.putNumber("Motor FrontLeft Setpoint", Robot.driveTrainSub.getMotorSetpoint(Motor.FRONTLEFT));
		debug.putNumber("Motor BackLeft Setpoint", Robot.driveTrainSub.getMotorSetpoint(Motor.BACKLEFT));
		debug.putNumber("Motor FrontRight Setpoint", Robot.driveTrainSub.getMotorSetpoint(Motor.FRONTRIGHT));
		debug.putNumber("Motor BackRight Setpoint", Robot.driveTrainSub.getMotorSetpoint(Motor.BACKRIGHT));
		
		int robotMode = 0;
		if (RobotState.isAutonomous())
			robotMode = 1;
		else if (RobotState.isOperatorControl())
			robotMode = 2;
		else if (RobotState.isTest())
			robotMode = 3;
		debug.putNumber("Robot Mode", robotMode);
		
		int robotState = 0;
		if (RobotState.isDisabled())
			robotState = -1;
		else if (RobotState.isEnabled())
			robotState = 1;
		debug.putNumber("Robot State", robotState);

		debug.putNumber("Robot Match Time", DriverStation.getInstance().getMatchTime());
		debug.putNumber("Robot Battery Voltage", DriverStation.getInstance().getBatteryVoltage());
		debug.putNumber("Robot Loop Execution Time", Robot.loopExecutionTime);
	}

}

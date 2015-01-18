package org.discobots.recyclerush.utils;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard {
	
	public static void init() {
		
	}
	
	public static void updateDriver() {
		SmartDashboard.putString("Data Mode", "driver");
		
	}
	
	public static void updateDebug() {
		SmartDashboard.putString("Data Mode", "debug");
		
		for (int index = 0; index < 16; index++) {
			SmartDashboard.putNumber("PDP Current, Module " + index, Robot.powerInfoSub.getCurrentFromChannel(index));
		}
		SmartDashboard.putNumber("PDP Current, Total", Robot.powerInfoSub.getTotalCurrent());
		SmartDashboard.putNumber("PDP Temperature", Robot.powerInfoSub.getTemperature());
		SmartDashboard.putNumber("PDP Voltage", Robot.powerInfoSub.getVoltage());
		
		
		int robotMode = 0;
		if (RobotState.isAutonomous())
			robotMode = 1;
		else if (RobotState.isOperatorControl())
			robotMode = 2;
		else if (RobotState.isTest())
			robotMode = 3;
		SmartDashboard.putNumber("Robot Mode", robotMode);
		
		int robotState = 0;
		if (RobotState.isDisabled())
			robotState = -1;
		else if (RobotState.isEnabled())
			robotState = 1;
		SmartDashboard.putNumber("Robot State", robotState);
		
		SmartDashboard.putNumber("Robot Match Time", DriverStation.getInstance().getMatchTime());
		SmartDashboard.putNumber("Robot Battery Voltage", DriverStation.getInstance().getBatteryVoltage());
	}

}

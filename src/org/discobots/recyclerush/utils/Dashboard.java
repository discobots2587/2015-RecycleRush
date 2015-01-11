package org.discobots.recyclerush.utils;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard {
	
	public static void init() {
		
	}
	
	public static void update() {
		for (int index = 0; index < 16; index++) {
			SmartDashboard.putNumber("PDP Current, Module " + index, Robot.powerInfoSub.getCurrentFromChannel(index));
		}
		SmartDashboard.putNumber("PDP Current, Total", Robot.powerInfoSub.getTotalCurrent());
		SmartDashboard.putNumber("PDP Temperature", Robot.powerInfoSub.getTemperature());
		
		
		String robotMode = "Unknown";
		if (RobotState.isAutonomous())
			robotMode = "Autonomous";
		else if (RobotState.isOperatorControl())
			robotMode = "Teleop";
		else if (RobotState.isTest())
			robotMode = "Test";
		SmartDashboard.putString("Robot Mode", robotMode);
		
		String robotState = "Unknown";
		if (RobotState.isDisabled())
			robotState = "Disabled";
		else if (RobotState.isEnabled())
			robotState = "Enabled";
		SmartDashboard.putString("Robot State", robotState);
	}

}

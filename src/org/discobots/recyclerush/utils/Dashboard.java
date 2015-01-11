package org.discobots.recyclerush.utils;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard {
	
	public static void init() {
		
	}
	
	public static void update() {
		for (int index = 0; index < 16; index++) {
			SmartDashboard.putNumber("PDP Current Module " + index, Robot.powerInfoSub.getCurrentFromChannel(index));
		}
		SmartDashboard.putNumber("PDP Current Total", Robot.powerInfoSub.getTotalCurrent());
		SmartDashboard.putNumber("PDP Temperature", Robot.powerInfoSub.getTemperature());
		SmartDashboard.putNumber("PDP Voltage", Robot.powerInfoSub.getVoltage());
	}

}

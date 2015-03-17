package org.discobots.recyclerush.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;
import org.discobots.recyclerush.HW;

/**
 *
 */
public class PlowSubsystem extends Subsystem {
	
	CANTalon left, right;

	public PlowSubsystem () {
		left = new CANTalon(HW.motorPlowLeft);
		right = new CANTalon(HW.motorPlowRight);
	}

	public void initDefaultCommand() {
	}

	public void setSpeedLeft(double input) {
		left.set(input);
	}

	public void setSpeedRight(double input) {
		right.set(input);
	}
}

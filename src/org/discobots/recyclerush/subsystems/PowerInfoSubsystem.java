package org.discobots.recyclerush.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PowerInfoSubsystem extends Subsystem {

	PowerDistributionPanel pdp;

	public PowerInfoSubsystem() {
		pdp = new PowerDistributionPanel();
	}

	public double getVoltage() {
		return pdp.getVoltage();
	}

	public double getCurrentFromChannel(int channel) {
		return pdp.getCurrent(channel);
	}

	public double getTotalCurrent() {
		double totalCurrent = 0;
		for (int i = 0; i < 16; i++) {
			totalCurrent += pdp.getCurrent(i);
		}
		return totalCurrent;
	}

	public double getTemperature() {
		return pdp.getTemperature();
	}

	public void initDefaultCommand() {
	}
}

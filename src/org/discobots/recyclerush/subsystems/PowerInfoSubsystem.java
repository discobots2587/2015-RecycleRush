package org.discobots.recyclerush.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PowerInfoSubsystem extends Subsystem {

	PowerDistributionPanel pdp;
	double curr;

	public PowerInfoSubsystem() {
		pdp = new PowerDistributionPanel();
	}

	public double getVoltage() {
		return pdp.getVoltage();
	}
	
	public double getCurrentFromChannel(int channel) {
		double chCurr = pdp.getCurrent(channel);
		if (channel == 0) {
			curr = chCurr;
		} else {
			curr += chCurr;
		}
		return chCurr;
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

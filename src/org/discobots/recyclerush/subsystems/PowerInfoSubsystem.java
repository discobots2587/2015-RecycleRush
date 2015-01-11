
package org.discobots.recyclerush.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PowerInfoSubsystem extends Subsystem {
    
	PowerDistributionPanel pdp;
	
	public PowerInfoSubsystem() {
		pdp = new PowerDistributionPanel();
	}
	
	public double getVoltage() {
		return pdp.getVoltage();
	}
	double curr;
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
		return curr;
	}
	
	public double getTemperature() {
		return pdp.getTemperature();
	}

    public void initDefaultCommand() {
    }
}


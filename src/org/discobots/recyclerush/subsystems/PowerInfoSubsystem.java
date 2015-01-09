
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
	
	public double getCurrentFromChannel(int channel) {
		return pdp.getCurrent(channel);
	}
	
	public double getTotalCurrent() {
		return pdp.getTotalCurrent();
	}
	
	public double getTemperature() {
		return pdp.getTemperature();
	}

    public void initDefaultCommand() {
    }
}


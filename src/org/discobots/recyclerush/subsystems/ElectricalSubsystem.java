package org.discobots.recyclerush.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.communication.HALControlWord;

public class ElectricalSubsystem extends Subsystem {

	PowerDistributionPanel pdp;
	Compressor cmp;

	public ElectricalSubsystem() {
		pdp = new PowerDistributionPanel();
		cmp = new Compressor();
	}

	public double getPDPVoltage() {
		return pdp.getVoltage();
	}

	public double getCurrentFromPDPChannel(int channel) {
		return pdp.getCurrent(channel);
	}

	public double getPDPTotalCurrent() {
		double totalCurrent = 0;
		for (int i = 0; i < 16; i++) {
			totalCurrent += pdp.getCurrent(i);
		}
		return totalCurrent;
	}
	
	public double getPDPTemperature() {
		return pdp.getTemperature();
	}

	public void setCompressor(boolean var) {
		if (var) {
			cmp.start();
		} else {
			cmp.stop();
		}
	}
	
	public boolean getCompressorState() {
		return cmp.enabled();
	}
	
	public boolean getPressureSwitchState() {
		return cmp.getPressureSwitchValue();
	}
	
	public boolean getCompressorControlLoopState() {
		return cmp.getClosedLoopControl();
	}
	
	public double getCompressorCurrent() {
		return cmp.getCompressorCurrent();
	}
	
	// consider adding other compressor debug stuff?
	
	public void initDefaultCommand() {
	}
}

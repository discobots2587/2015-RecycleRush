package org.discobots.recyclerush.subsystems;

import org.discobots.recyclerush.HW;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClawSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Solenoid claw;

	public ClawSubsystem() {
		claw = new Solenoid(HW.solClaw);
		claw.set(true);
	}

	public void setClaw(boolean clawSPD) {
		claw.set(clawSPD);
	}

	public boolean getClawValue() {
		return claw.get();
	}

	public void initDefaultCommand() {
	}
}

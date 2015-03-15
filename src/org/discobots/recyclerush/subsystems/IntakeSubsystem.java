package org.discobots.recyclerush.subsystems;

import org.discobots.recyclerush.HW;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Solenoid intake;

	public IntakeSubsystem() {
		intake = new Solenoid(HW.solIntake);
		intake.set(false);
	}

	public void setIntake(boolean intakeSPD) {
		intake.set(intakeSPD);
	}

	public boolean getIntakeValue() {
		return intake.get();
	}

	public void initDefaultCommand() {
	}
}

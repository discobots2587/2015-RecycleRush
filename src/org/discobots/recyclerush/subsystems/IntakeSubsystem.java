package org.discobots.recyclerush.subsystems;

import org.discobots.recyclerush.HW;
import org.discobots.recyclerush.commands.SetIntakeCommand;
import org.discobots.recyclerush.commands.drive.TankDriveCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Solenoid intakeA, intakeB;

	public IntakeSubsystem() {

		intakeA = new Solenoid(HW.intakeA);
		intakeB = new Solenoid(HW.intakeB);
	}

	public void setIntake(boolean intakeSPD) {
		intakeA.set(intakeSPD);
		intakeA.set(intakeSPD);
	}

	public boolean getIntakeValue() {
		return intakeA.get() && intakeB.get();
	}

	public void initDefaultCommand() {
	}
}

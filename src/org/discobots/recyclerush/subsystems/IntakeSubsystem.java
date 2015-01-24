package org.discobots.recyclerush.subsystems;

import org.discobots.recyclerush.HW;
import org.discobots.recyclerush.commands.IntakeCommand;
import org.discobots.recyclerush.commands.drive.TankDriveCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	CANTalon intakeLeft, intakeRight;

	public IntakeSubsystem() {

		intakeLeft = new CANTalon(HW.intakeLeft);
		intakeRight = new CANTalon(HW.intakeRight);
	}

	public void setIntakeSpeed(double intakeSPD) {
		intakeLeft.set(intakeSPD);
		intakeLeft.set(intakeSPD);
	}

	public void initDefaultCommand() {
	}
}

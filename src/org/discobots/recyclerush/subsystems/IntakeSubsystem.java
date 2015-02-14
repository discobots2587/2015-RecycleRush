package org.discobots.recyclerush.subsystems;

import org.discobots.recyclerush.HW;
import org.discobots.recyclerush.commands.drive.TankDriveCommand;
import org.discobots.recyclerush.commands.intake.SetIntakeCommand;

import edu.wpi.first.wpilibj.CANTalon;
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
		intake = new Solenoid(HW.solenoidIntake);
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

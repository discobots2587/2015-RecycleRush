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
	Solenoid intakeA, intakeB;

	public IntakeSubsystem() {
		System.out.println("error, intake is passive, active code is disabled, 1");
		//intakeA = new Solenoid(HW.intakeA);
		//intakeB = new Solenoid(HW.intakeB);
	}

	public void setIntake(boolean intakeSPD) {
		System.out.println("error, intake is passive, active code is disabled, 2");
		//intakeA.set(intakeSPD);
		//intakeA.set(intakeSPD);
	}

	public boolean getIntakeValue() {
		System.out.println("error, intake is passive, active code is disabled, 3");
		return false;
		//return intakeA.get() && intakeB.get();
	}

	public void initDefaultCommand() {
	}
}

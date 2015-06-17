package org.discobots.recyclerush.subsystems;

import org.discobots.recyclerush.HW;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Solenoid intake;
	CANTalon aIntakeL, aIntakeR;
	DoubleSolenoid intakeR, intakeL;
	boolean closed=false;
	
	public IntakeSubsystem() {
		intakeR = new DoubleSolenoid(HW.intakeRight1,HW.intakeRight2);
		intakeL = new DoubleSolenoid(HW.intakeLeft1,HW.intakeLeft2);
		intake = new Solenoid(HW.solIntake);
		aIntakeL = new CANTalon(HW.motorIntakeLeft);
		aIntakeR = new CANTalon(HW.motorIntakeRight);
		intake.set(false);
	}
	public void setIntakeDoubleSol(boolean closed)
	{
		this.closed=closed;
		if(closed==true)
		{
			intakeR.set(DoubleSolenoid.Value.kForward);
			intakeL.set(DoubleSolenoid.Value.kForward);
		}
		else
		intakeR.set(DoubleSolenoid.Value.kReverse);
		intakeL.set(DoubleSolenoid.Value.kReverse);

		
	}
	public void setIntake(boolean intakeSPD) {
		intake.set(intakeSPD);//sets claw intake
	}
	public void setActiveIntake(double direction)//sets active intake speed
	{
		aIntakeL.set(direction);
		aIntakeR.set(direction);
	}
	public boolean getIntakeValue() {
		return intake.get();
	}

	public void initDefaultCommand() {
	}
}

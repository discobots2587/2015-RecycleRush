package org.discobots.recyclerush.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.discobots.recyclerush.HW;
import org.discobots.recyclerush.commands.drive.TankDriveCommand;
import org.discobots.recyclerush.commands.lift.VariableLiftCommand;

/**
 *
 */
public class LiftSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	CANTalon liftMotor1, liftMotor2;

	public LiftSubsystem() {
		liftMotor1 = new CANTalon(HW.motorLift1);
		//liftMotor2 = new CANTalon(HW.motorLift2);
	}

	public void setLiftSpeed(double liftSPD) {
		liftMotor1.set(liftSPD);
		//liftMotor2.set(liftSPD);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new VariableLiftCommand());
	}

}

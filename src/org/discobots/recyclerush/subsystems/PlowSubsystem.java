package org.discobots.recyclerush.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.discobots.recyclerush.HW;
import org.discobots.recyclerush.commands.plow.VariablePlowCommand;
/**
 *
 */
public class PlowSubsystem extends Subsystem {
	
    Talon talonLeft, talonRight;

    public PlowSubsystem() {
    	talonLeft = new Talon(HW.motorPlowLeft);
    	talonRight = new Talon(HW.motorPlowRight);
    }
    
    public void setSpeedRight(double speed) {
    	talonRight.set(speed);
    }
    
    public void setSpeedLeft(double speed) {
		talonLeft.set(-speed);
    	
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new VariablePlowCommand());
    }
}


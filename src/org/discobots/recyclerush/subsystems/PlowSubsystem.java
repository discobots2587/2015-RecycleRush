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
    DigitalInput limitLeftIn, limitRightIn;
    DigitalInput limitLeftOut, limitRightOut;

    public PlowSubsystem() {
    	talonLeft = new Talon(HW.motorPlowLeft);
    	talonRight = new Talon(HW.motorPlowRight);
    	
    	limitLeftIn = new DigitalInput(HW.buttonPlowLeftIn);
    	limitRightIn = new DigitalInput(HW.buttonPlowRightIn);
    	limitLeftOut = new DigitalInput(HW.buttonPlowLeftOut);
    	limitRightOut = new DigitalInput(HW.buttonPlowRightOut);
    }
    
    public boolean getLimitLeftIn() {
    	return limitLeftIn.get();
    }
    
    public boolean getLimitRightIn() {
    	return limitRightIn.get();
    }
    
    public boolean getLimitLeftOut() {
    	return limitLeftOut.get();
    }
    
    public boolean getLimitRightOut() {
    	return limitRightOut.get();
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


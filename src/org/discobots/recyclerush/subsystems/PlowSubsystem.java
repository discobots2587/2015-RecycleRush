package org.discobots.recyclerush.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.discobots.recyclerush.HW;
/**
 *
 */
public class PlowSubsystem extends Subsystem {
   
	public enum Motor {
    	RIGHT, LEFT;
    }
	
    Talon right, left;
    

    public PlowSubsystem() {
    	right = new Talon(HW.motorPlowRight);
    	left = new Talon(HW.motorPlowLeft);
    }
    
    public void setSpeed(double speed, Motor motor) {
    	if(motor == Motor.RIGHT) {
    		right.set(speed);
    	}
    	else if(motor == Motor.LEFT) {
    		left.set(-speed);
    	}
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


package org.discobots.recyclerush.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.discobots.recyclerush.HW;
/**
 *
 */
public class PlowSubsystem extends Subsystem {
   
	public enum Motor {
    	RIGHT, LEFT;
    }
	
    CANTalon right, left;
    

    public PlowSubsystem() {
    	right = new CANTalon(HW.motorPlowRight);
    	left = new CANTalon(HW.motorPlowLeft);
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


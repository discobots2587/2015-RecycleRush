package org.discobots.recyclerush.subsystems;

import org.discobots.recyclerush.HW;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class WingSubsystem extends Subsystem {
    
	Solenoid left, right;
 
	public WingSubsystem() {
		//left = new Solenoid(HW.solFlapLeft);
		right = new Solenoid(HW.solFlapRight);
	}
	
	public void set(boolean pos) {
		//left.set(pos);
		right.set(pos);
	}
	
	public boolean get() {
		return right.get();
	}
	
    public void initDefaultCommand() {
    }
}


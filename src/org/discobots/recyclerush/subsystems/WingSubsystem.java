package org.discobots.recyclerush.subsystems;

import org.discobots.recyclerush.HW;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class WingSubsystem extends Subsystem {
    
	DoubleSolenoid left, right;
	int position;
	public WingSubsystem() {
		left = new DoubleSolenoid(HW.solFlapLeft1, HW.solFlapLeft2);
		right = new DoubleSolenoid(HW.solFlapRight1, HW.solFlapRight2);
	}
	
	public void set(int pos) {
		 position = pos;
		if (pos==-1)
		{
			left.set(DoubleSolenoid.Value.kReverse);
			right.set(DoubleSolenoid.Value.kReverse);
		}
		if (pos==0)
		{
			left.set(DoubleSolenoid.Value.kOff);
			right.set(DoubleSolenoid.Value.kOff);
		}
		if (pos==1)
		{
			left.set(DoubleSolenoid.Value.kForward);
			right.set(DoubleSolenoid.Value.kForward);
		}	
	}
	
	public int get() {
		return position;
	}
	
    public void initDefaultCommand() {
    }
}


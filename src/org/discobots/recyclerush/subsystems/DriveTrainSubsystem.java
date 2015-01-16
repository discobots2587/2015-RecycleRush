package org.discobots.recyclerush.subsystems;

import org.discobots.recyclerush.HW;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Talon TfrontRight = new Talon(HW.frontRightTalon);
	Talon TbackRight = new Talon(HW.backRightTalon);
	Talon TfrontLeft = new Talon(HW.frontLeftTalon);
	Talon TbackLeft = new Talon(HW.backLeftTalon);
	Talon TCenterDropDown = new Talon(HW.centerDropDownTalon);
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    

}


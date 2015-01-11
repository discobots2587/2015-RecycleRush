
package org.discobots.recyclerush.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand() {
    	addSequential(new SetTestMotor(0, -0.5));
    	addSequential(new SetTestMotor(1, 0.5));
    	addSequential(new SetTestMotor(2, -0.5));
    	addSequential(new SetTestMotor(3, 0.5));
    	
    	addSequential(new WaitCommand(2.0));
    	
    	addSequential(new SetTestMotor(0, -1.0));
    	addSequential(new SetTestMotor(1, 1.0));
    	addSequential(new SetTestMotor(2, -1.0));
    	addSequential(new SetTestMotor(3, 1.0));

    	addSequential(new WaitCommand(2.0));
    	
    	addSequential(new SetTestMotor(0, 1.0));
    	addSequential(new SetTestMotor(1, -1.0));
    	addSequential(new SetTestMotor(2, 1.0));
    	addSequential(new SetTestMotor(3, -1.0));
    	
    	addSequential(new WaitCommand(2.0));
    	
    	addSequential(new SetTestMotor(0, 0.0));
    	addSequential(new SetTestMotor(1, 0.0));
    	addSequential(new SetTestMotor(2, 0.0));
    	addSequential(new SetTestMotor(3, 0.0));

    	
    }

}

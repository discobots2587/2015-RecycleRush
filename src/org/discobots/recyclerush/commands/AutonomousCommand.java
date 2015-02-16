
package org.discobots.recyclerush.commands;

import org.discobots.recyclerush.commands.drive.AutonomousArcadeDriveCommand;
import org.discobots.recyclerush.commands.wings.SetWingCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand() {
    	
    	
    }
    
    private void autonomousMode0Init() {
    	// Do Nothing
    }
    
    private void autonomousMode1Init() {
    	// TWO BINS
    	addSequential(new SetWingCommand(true));
    	addSequential(new WaitCommand(1));
    	addSequential(new AutonomousArcadeDriveCommand(-.5, 0, 2));
    	addSequential(new WaitCommand(.5));
    	addSequential(new SetWingCommand(false));
    	addSequential(new WaitCommand(1));
    	addSequential(new AutonomousArcadeDriveCommand(.5, 0, 5));
    	addSequential(new WaitCommand(.5));
    	addSequential(new SetWingCommand(true));
    }
    
    private void autonomousMode2Init() {
    	// THREE TOTES
    }

}

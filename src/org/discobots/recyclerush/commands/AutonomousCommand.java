
package org.discobots.recyclerush.commands;

import org.discobots.recyclerush.commands.drive.AutonomousArcadeDriveCommand;
import org.discobots.recyclerush.commands.wings.SetWingCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand(int mode) {
    	switch(mode) {
    	case 1:
    		autonomousMode1Init();
    		break;
    	case 2:
    		autonomousMode2Init();
    		break;
    	default:
    		autonomousMode0Init();
    	}
    	
    }
    
    private void autonomousMode0Init() {
    	// Do Nothing
    }
    
    private void autonomousMode1Init() {
    	// TWO BINS FROM BUMP
    	addSequential(new SetWingCommand(true)); // both wing down
    	addSequential(new AutonomousArcadeDriveCommand(-0.4, 0, 3)); // move back at 0.5 speed backward while wings go down
    	addSequential(new WaitCommand(.5)); // wait half a second
    	addSequential(new SetWingCommand(false)); // wings come up
    	addSequential(new WaitCommand(1)); // wait, stabilize
    	addSequential(new AutonomousArcadeDriveCommand(.5, 0, 5)); // drive forward at 0.5 speed forward for 5 seconds
    	addSequential(new WaitCommand(.5)); // wait half a second
    	addSequential(new SetWingCommand(true)); // wings come down 
    }
    
    private void autonomousMode2Init() {
    	// 
    }

}

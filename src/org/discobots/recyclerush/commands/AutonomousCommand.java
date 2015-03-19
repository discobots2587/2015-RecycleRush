
package org.discobots.recyclerush.commands;

import org.discobots.recyclerush.commands.auton.AutonomousArcadeDriveCommand;
import org.discobots.recyclerush.commands.auton.MoveForwardHoloCommand;
import org.discobots.recyclerush.commands.auton.MoveSidewayHoloCommand;
import org.discobots.recyclerush.commands.intake.ToggleIntakeCommand;
import org.discobots.recyclerush.commands.lift.RaiseLiftCommand;
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
    	addSequential(new ToggleIntakeCommand());
		addSequential(new MoveForwardHoloCommand(450,0.5)); //Forward for 5 seconds at half speed
		addSequential(new WaitCommand(.25));
		addSequential(new RaiseLiftCommand(1,800));
		addSequential(new MoveForwardHoloCommand(1700,-0.5)); //Backwards for 2.5 seconds at half speed
		addSequential(new WaitCommand(.25));
		addSequential(new RaiseLiftCommand(-1,1000));
		addSequential(new WaitCommand(.25));
		addSequential(new MoveForwardHoloCommand(500,-0.5)); //Backwards for .25 seconds at half speed
		addSequential(new MoveSidewayHoloCommand(3000,1)); //Moving Sideways (right) for 5 seconds at half speed
		addSequential(new WaitCommand(.25));
		addSequential(new MoveForwardHoloCommand(2200,0.5)); //Forward for 2.75 seconds at half speed
		addSequential(new WaitCommand(.25));
		addSequential(new RaiseLiftCommand(1,1000));
		addSequential(new MoveForwardHoloCommand(2500,-0.5)); //Backwards for 2.5 seconds at half speed
		addSequential(new RaiseLiftCommand(-1,1000));
		addSequential(new MoveForwardHoloCommand(1000,-0.5));
    }
    
    private void autonomousMode2Init() {
    	// WINGS, TWO BINS FROM BUMP
    	addSequential(new SetWingCommand(true)); // both wings down
    	addSequential(new WaitCommand(3));
    	addSequential(new AutonomousArcadeDriveCommand(-1, 0, 600)); // move back at 0.5 speed backward while wings go down
    	addSequential(new WaitCommand(.5)); // wait half a second
    	addSequential(new SetWingCommand(false)); // wings come up
    	addSequential(new WaitCommand(5)); // wait, stabilize
    	addSequential(new AutonomousArcadeDriveCommand(.5, 0, 5000)); // drive forward at 0.5 speed forward for 5 seconds
    	addSequential(new WaitCommand(.5)); // wait half a second
    	addSequential(new SetWingCommand(true)); // wings come down 
    	addSequential(new WaitCommand(0.5));
    	addSequential(new AutonomousArcadeDriveCommand(.6, 0, 250)); 
    }

}

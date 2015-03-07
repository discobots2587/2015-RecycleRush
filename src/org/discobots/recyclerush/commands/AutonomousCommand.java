package org.discobots.recyclerush.commands;


import org.discobots.recyclerush.commands.autonomous.MoveForwardHoloCommand;
import org.discobots.recyclerush.commands.autonomous.MoveSidewayHoloCommand;
import org.discobots.recyclerush.commands.lift.RaiseLiftCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {

	long time;
	
	public AutonomousCommand(int mode) {
    	switch(mode)
    	{
    		case 0: autonomousMode0Init(); break;
    		case 1: autonomousMode1Init(); break;
    		case 2: autonomousMode2Init(); break;
    	}    	
    }

	public void autonomousMode0Init() {
		// Do nothing
	}

	public void autonomousMode1Init() {
		addSequential(new MoveForwardHoloCommand(5000,0.5)); //Forward for 5 seconds at half speed
		addSequential(new WaitCommand(.25));
		addSequential(new RaiseLiftCommand(1,1000));
		addSequential(new MoveForwardHoloCommand(-2500,0.5)); //Backwards for 2.5 seconds at half speed
		addSequential(new WaitCommand(.25));
		addSequential(new RaiseLiftCommand(-1,1000));
		addSequential(new WaitCommand(.25));
		addSequential(new MoveSidewayHoloCommand(2000,0.5)); //Moving Sideways (right) for 5 seconds at half speed
		addSequential(new WaitCommand(.25));
		addSequential(new MoveForwardHoloCommand(2500,0.5)); //Forward for 2.5 seconds at half speed
		addSequential(new WaitCommand(.25));
		addSequential(new RaiseLiftCommand(1,1000));
		addSequential(new MoveForwardHoloCommand(-2500,0.5)); //Backwards for 2.7 seconds at half speed
		addSequential(new RaiseLiftCommand(-1,1000));
		addSequential(new MoveForwardHoloCommand(-1000,0.5));
		
	}

	public void autonomousMode2Init() {
	}
	
	public void intialize()
	{
		super.initialize();
        time = System.currentTimeMillis();
	}
	
	public boolean isFinished()
	{
		if (super.isFinished()) {
            System.out.println("[Debug] Autonomous completed in " + (System.currentTimeMillis() - this.time) + " ms.");
            return true;
        } else {
            return false;
        }
	}
}

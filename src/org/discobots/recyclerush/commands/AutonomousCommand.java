
package org.discobots.recyclerush.commands;

import org.discobots.recyclerush.Robot;
import org.discobots.recyclerush.commands.auton.AutonomousArcadeDriveCommand;
import org.discobots.recyclerush.commands.auton.AutonomousArcadeDriveRampCommand;
import org.discobots.recyclerush.commands.auton.MoveForwardHoloCommand;
import org.discobots.recyclerush.commands.auton.MoveSidewayHoloCommand;
import org.discobots.recyclerush.commands.intake.SetActiveIntakeCommand;
import org.discobots.recyclerush.commands.intake.ToggleIntakeCommand;
import org.discobots.recyclerush.commands.intake.ToggleIntakeSolenoids;
import org.discobots.recyclerush.commands.lift.RaiseLiftCommand;
import org.discobots.recyclerush.commands.lift.SetLiftCommand;
import org.discobots.recyclerush.commands.wings.SetWingCommand;
import org.discobots.recyclerush.commands.wings.ToggleWingCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonomousCommand extends CommandGroup {
    public AutonomousCommand(int mode) {
    	switch(mode) {
    	case 3:
    		autonomousMode3Init();
    		break;
    	case 1:
    		autonomousMode1Init();
    		break;
    	case 2:
    		autonomousMode2Init();
    		break;
    	case 4:
    		autonomousMode4Init();
    		break;
     	case 5:
    		autonomousMode5Init();
    		break;
    	case 6:
    		autonomousMode6Init();
    		break;
    	default:
    		autonomousMode0Init();
      	}
    	
    }
    
    private void autonomousMode0Init() {
    	// Do Nothing
    }
    
    private void autonomousMode6Init(){	
    	if(Robot.liftSub.getLiftHeightInches()>5.0 && Robot.liftSub.isAtBottom()==false)
    	{
    		addSequential(new RaiseLiftCommand(-1, 2000)); //make sure intake is down	
    }}
    
    private void autonomousMode1Init() { 
    	addSequential(new ToggleIntakeCommand());
		addSequential(new MoveForwardHoloCommand(500,0.25)); //Forward for 5 seconds at half speed
		addSequential(new WaitCommand(.5));
		addSequential(new RaiseLiftCommand(1,500));
		addSequential(new MoveForwardHoloCommand(2100,-0.5)); //Backwards for 2.5 seconds at half speed
		addSequential(new WaitCommand(.25));
		addSequential(new RaiseLiftCommand(-1,1000));
		addSequential(new WaitCommand(.25));
		/*addSequential(new MoveForwardHoloCommand(500,-0.25)); //Backwards for .25 seconds at half speed
		addSequential(new MoveSidewayHoloCommand(3000,1)); //Moving Sideways (right) for 5 seconds at half speed
		addSequential(new WaitCommand(.25));
		addSequential(new MoveForwardHoloCommand(2200,0.5)); //Forward for 2.75 seconds at half speed
		addSequential(new WaitCommand(.25));
		addSequential(new RaiseLiftCommand(1,500));
		addSequential(new MoveForwardHoloCommand(2500,-0.5)); //Backwards for 2.5 seconds at half speed
		addSequential(new RaiseLiftCommand(-1,1000));
		addSequential(new MoveForwardHoloCommand(1000,-0.5));*/
    }
    
    private void autonomousMode2Init() {
    	// WINGS, TWO BINS FROM BUMP
    	//addSequential(new SetWingCommand(-1)); // both wings down
    	//addSequential(new WaitCommand(3));
    	//addSequential(new CheckWingStateCommand());
    	if (Robot.wingSub.get()==-1 || Robot.wingSub.get()==0)
    	{
    	addSequential(new SetWingCommand(1)); // wings come down
    	}
    	addSequential(new WaitCommand(0.5)); // wait to lower

    	addSequential(new AutonomousArcadeDriveCommand(-.75,0,100));
    	addSequential(new AutonomousArcadeDriveCommand(-1, 0, 550));// move back at 0.5 speed backward while wings go down
    	//addSequential(new WaitCommand(.3));
    	addSequential(new AutonomousArcadeDriveCommand(-0.5, 0, 750));
    	//addSequential(new WaitCommand(.3));
    	addSequential(new AutonomousArcadeDriveCommand(-0.25, 0, 500));
    	
    	
    	//addSequential(new WaitCommand(.5)); // wait half a second
    	addSequential(new SetWingCommand(-1)); // wings come up
    	addSequential(new WaitCommand(4)); // wait to raise, catch trashcan
    	addSequential(new AutonomousArcadeDriveCommand(.75, 0, 2500)); // drive forward at 0.5 speed forward for 5 seconds
    	/*addSequential(new WaitCommand(1.5)); // wait half a second
    	addSequential(new SetWingCommand(-1)); // wings come down 
    	addSequential(new WaitCommand(0.75));
    	addSequential(new AutonomousArcadeDriveCommand(.6, 0, 250));
    	addSequential(new WaitCommand(.5));
    	addSequential(new SetWingCommand(1));*/
    	if(Robot.liftSub.getLiftHeightInches()>5.0 && Robot.liftSub.isAtBottom()==false)
    	{
    		addSequential(new RaiseLiftCommand(-1, 2000)); //make sure intake is down	
    }
    }
    private void autonomousMode3Init() {
    	addSequential(new ToggleIntakeCommand());
    	addSequential(new RaiseLiftCommand(1,500));
    	addSequential(new MoveForwardHoloCommand(2000,.5));
    	//addSequential(new ToggleIntakeCommand());
    	addSequential(new MoveForwardHoloCommand(500,-1.0));
    }

    private void autonomousMode4Init(){
    addSequential(new ToggleIntakeCommand());
    addSequential(new RaiseLiftCommand(1,200));
    addSequential(new SetActiveIntakeCommand(1));
    addSequential(new WaitCommand(.5));
    //addSequential(new ToggleIntakeSolenoids(1)); //for when wings are added back in
    addSequential(new ToggleWingCommand());
    addSequential(new SetActiveIntakeCommand(1));
    addSequential(new WaitCommand(.5));
    addSequential(new SetActiveIntakeCommand(0));
    //addSequential(new ToggleIntakeSolenoids(1)); //for when wings are added back in
    addSequential(new ToggleWingCommand());
    

    }
    private void autonomousMode5Init(){
    	if(Robot.liftSub.getLiftHeightInches()>5.0 && Robot.liftSub.isAtBottom()==false)
    	{
    		addSequential(new RaiseLiftCommand(-1, 2000)); //make sure intake is down	
    }
    	
    	addSequential(new WaitCommand(.5));
    	addSequential(new MoveForwardHoloCommand(500, .5)); //move forwards a bit
    	addSequential(new ToggleIntakeCommand());
    	addSequential(new WaitCommand(.5));
    	addSequential(new RaiseLiftCommand(1, 1000)); //raises recycle bin or tote
    	addSequential(new WaitCommand(.5));
    	addSequential(new MoveForwardHoloCommand(1500, .5)); //move to loading station
    }
    }


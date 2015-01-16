
package org.discobots.recyclerush;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.discobots.recyclerush.commands.AutonomousCommand;
import org.discobots.recyclerush.subsystems.DriveTrainSubsystem;
import org.discobots.recyclerush.subsystems.PowerInfoSubsystem;
import org.discobots.recyclerush.utils.Dashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
 
	public static PowerInfoSubsystem powerInfoSub;
	public static OI oi;
	public static DriveTrainSubsystem driveTrainSub;

	AutonomousCommand autonomousCommand;

	/**
	* This function is run when the robot is first started up and should be
	* used for any initialization code.
	*/
	public void robotInit() {
		// global oi & subsystem code
		oi = new OI();
		powerInfoSub = new PowerInfoSubsystem();
		driveTrainSub = new DriveTrainSubsystem();
		// autonomous command
		autonomousCommand = new AutonomousCommand();
        
		// dashboard init
		Dashboard.init();
	}
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		Dashboard.update();
	}

	public void autonomousInit() {
		if (autonomousCommand != null) 
			autonomousCommand.start();
	}

	/**
	* This function is called periodically during autonomous
	*/
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		Dashboard.update();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to 
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) 
			autonomousCommand.cancel();
	}

	/**
	* This function is called when the disabled button is hit.
	* You can use it to reset subsystems before shutting down.
	*/
	public void disabledInit(){

	}

	/**
	* This function is called periodically during operator control
	*/
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		Dashboard.update();
    }
    
	/**
	* This function is called periodically during test mode
	*/
	public void testPeriodic() {
	LiveWindow.run();
		Dashboard.update();
	}
}

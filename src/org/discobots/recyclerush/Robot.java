package org.discobots.recyclerush;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.discobots.recyclerush.commands.AutonomousCommand;
import org.discobots.recyclerush.subsystems.DriveTrainSubsystem;
import org.discobots.recyclerush.subsystems.ElectricalSubsystem;
import org.discobots.recyclerush.utils.Dashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static ElectricalSubsystem electricalSub;
	public static DriveTrainSubsystem driveTrainSub;
	public static OI oi;
	public static LiftSubsystem liftSub;
	
	public static long loopExecutionTime = 0;

	AutonomousCommand autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		// global oi & subsystem code
		oi = new OI();
		electricalSub = new ElectricalSubsystem();
		driveTrainSub = new DriveTrainSubsystem();
		liftSub	= new LiftSubsystem();
		// autonomous command
		autonomousCommand = new AutonomousCommand();

		// dashboard init
		Dashboard.init();
	}

	public void disabledPeriodic() {
		long start = System.currentTimeMillis();
		Scheduler.getInstance().run();
		Dashboard.updateDriver();
		long end = System.currentTimeMillis();
		loopExecutionTime = end - start;
	}

	public void autonomousInit() {
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		long start = System.currentTimeMillis();
		Scheduler.getInstance().run();
		Dashboard.updateDriver();
		long end = System.currentTimeMillis();
		loopExecutionTime = end - start;
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
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		long start = System.currentTimeMillis();
		Scheduler.getInstance().run();
		Dashboard.updateDriver();
		long end = System.currentTimeMillis();
		loopExecutionTime = end - start;
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		long start = System.currentTimeMillis();
		LiveWindow.run();
		Scheduler.getInstance().run();
		Dashboard.updateDriver();
		long end = System.currentTimeMillis();
		loopExecutionTime = end - start;
	}
}

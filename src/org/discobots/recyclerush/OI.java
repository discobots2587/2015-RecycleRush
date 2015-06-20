package org.discobots.recyclerush;


import org.discobots.recyclerush.commands.ShutdownSensors;
import org.discobots.recyclerush.commands.SpeedUpCommand;
import org.discobots.recyclerush.commands.ToggleCompressor;
import org.discobots.recyclerush.commands.auton.AutomatedStackingCommand;
import org.discobots.recyclerush.commands.claw.ToggleClawCommand;
import org.discobots.recyclerush.commands.drive.AssistedHolonomicDriveCommand;
import org.discobots.recyclerush.commands.drive.CycleDriveCommand;
import org.discobots.recyclerush.commands.drive.ToggleDriveRampingCommand;
import org.discobots.recyclerush.commands.drive.ToggleDriveTrainSpeedConstant;
import org.discobots.recyclerush.commands.intake.SetActiveIntakeCommand;
import org.discobots.recyclerush.commands.intake.ToggleIntakeCommand;
import org.discobots.recyclerush.commands.intake.ToggleIntakeSolenoids;
import org.discobots.recyclerush.commands.lift.SetLiftCommand;
import org.discobots.recyclerush.commands.plow.SetPlowCommand;
import org.discobots.recyclerush.commands.wings.ToggleWingCommand;
import org.discobots.recyclerush.utils.GamePad;
import org.discobots.recyclerush.utils.GamePad.DPadButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private GamePad gp1 = new GamePad(0);
	private GamePad xbox = new GamePad(1); 

	// JOYSTICK 2
	private Button b_dpadU = new DPadButton(gp1, GamePad.DPAD_Y, true);
	private Button b_dpadD = new DPadButton(xbox, GamePad.DPAD_Y, false);
	private Button b_dpadR = new DPadButton(xbox, GamePad.DPAD_X, true);
	private Button b_dpadL = new DPadButton(xbox, GamePad.DPAD_X, false);
	private Button b_bumpR = new JoystickButton(xbox, 6);
	private Button b_bumpL = new JoystickButton(xbox, 5);
	private double b_triggers = (float) xbox.getRawAxis(3);
	private Button b_sBack = new JoystickButton(xbox, 7);
	private Button b_sStar = new JoystickButton(xbox, 8);
	private Button b_btnA = new JoystickButton(xbox, 1);
	private Button b_btnX = new JoystickButton(xbox, 3);
	private Button b_btnB = new JoystickButton(xbox, 2);
	private Button b_btnY = new JoystickButton(xbox, 4);
	private Button b_clicR = new JoystickButton(xbox, 10);
	private Button b_clicL = new JoystickButton(xbox, 9);
	// JOYSTICK 1
	private Button b2_dpadU = new DPadButton(gp1, GamePad.DPAD_Y, true);
	private Button b2_dpadD = new DPadButton(gp1, GamePad.DPAD_Y, false);
	private Button b2_dpadR = new DPadButton(gp1, GamePad.DPAD_X, true);
	private Button b2_dpadL = new DPadButton(gp1, GamePad.DPAD_X, false);
	private Button b2_bumpR = new JoystickButton(gp1, GamePad.BTN_RB);
	private Button b2_bumpL = new JoystickButton(gp1, GamePad.BTN_LB);
	private Button b2_trigR = new JoystickButton(gp1, GamePad.BTN_RT);
	private Button b2_trigL = new JoystickButton(gp1, GamePad.BTN_LT);
	private Button b2_sBack = new JoystickButton(gp1, GamePad.BTN_BACK);
	private Button b2_sStar = new JoystickButton(gp1, GamePad.BTN_START);
	private Button b2_btnA = new JoystickButton(gp1, GamePad.BTN_A);
	private Button b2_btnX = new JoystickButton(gp1, GamePad.BTN_X);
	private Button b2_btnB = new JoystickButton(gp1, GamePad.BTN_B);
	private Button b2_btnY = new JoystickButton(gp1, GamePad.BTN_Y);
	private Button b2_clicR = new JoystickButton(gp1, GamePad.AXISBTN_R);
	private Button b2_clicL = new JoystickButton(gp1, GamePad.AXISBTN_L);

	public OI() {
		// first gamepad
		// drive commands control analog sticks on joy 1
		b2_trigR.whenPressed(new SetLiftCommand(1));
		b2_trigR.whenReleased(new SetLiftCommand(0));

		b2_trigL.whenPressed(new SetLiftCommand(-1));
		b2_trigL.whenReleased(new SetLiftCommand(0));

		b2_bumpR.whenPressed(new SetLiftCommand(0.5));
		b2_bumpR.whenReleased(new SetLiftCommand(0));

		b2_bumpL.whenPressed(new SetLiftCommand(-0.5));
		b2_bumpL.whenReleased(new SetLiftCommand(0));

		b2_btnA.whenPressed(new ToggleIntakeCommand());
		b2_btnX.whenPressed(new ToggleWingCommand());
		b2_btnY.whileHeld(new ToggleIntakeSolenoids(1));
		b2_btnY.whenReleased(new ToggleIntakeSolenoids(-1));
		b2_btnB.whenPressed(new ToggleClawCommand());
		
		b2_sStar.whenPressed(new ShutdownSensors());
		b2_sBack.whenPressed(new CycleDriveCommand());

		b2_dpadU.whenPressed(new SetActiveIntakeCommand(1.0));
		b2_dpadU.whenReleased(new SetActiveIntakeCommand(0.0));

		b2_dpadL.whenPressed(new SpeedUpCommand());
		
		b2_dpadD.whenPressed(new SetActiveIntakeCommand(-1.0));
		b2_dpadD.whenReleased(new SetActiveIntakeCommand(0.0));
		
		

		


		// second gamepad
		if (b_triggers<=-0.3)//right trigger pressed
		{
			new SetLiftCommand(1);
		}
		if(-0.3<b_triggers && b_triggers<0.3)
		{
			new SetLiftCommand(0);
		}
		if (b_triggers>=0.3)
		{
			new SetLiftCommand(-1);
		}
		b_bumpR.whenPressed(new SetLiftCommand(0.5));
		b_bumpR.whenReleased(new SetLiftCommand(0));

		b_bumpL.whenPressed(new SetLiftCommand(-0.5));
		b_bumpL.whenReleased(new SetLiftCommand(0));

	//	b_btnA.whenPressed(new ToggleIntakeCommand());
	//	b_btnB.whenPressed(new AutomatedStackingCommand()); // one cycle, stoppable by any button
	//	b_btnY.whenPressed(new ShutdownSensors());

	//	b_sBack.whenPressed(new ToggleDriveRampingCommand());
		
		b_btnA.whenPressed(new ToggleIntakeCommand());
		b_btnX.whenPressed(new ToggleWingCommand());
		b_btnY.whileHeld(new ToggleIntakeSolenoids(1));
		b_btnY.whenReleased(new ToggleIntakeSolenoids(-1));
		b_btnB.whenPressed(new ToggleClawCommand());
		
		b_dpadU.whenPressed(new SetActiveIntakeCommand(1.0));
		b_dpadU.whenReleased(new SetActiveIntakeCommand(0.0));

		b_dpadL.whenPressed(new SpeedUpCommand());
		
		b_dpadD.whenPressed(new SetActiveIntakeCommand(-1.0));
		b_dpadD.whenReleased(new SetActiveIntakeCommand(0.0));
		b_sStar.whenPressed(new ShutdownSensors());
		b_sBack.whenPressed(new CycleDriveCommand());
	}
	
	public double getRawAnalogStickALX() {
		return gp1.getLX();
	}

	public double getRawAnalogStickALY() {
		return gp1.getLY();
	}

	public double getRawAnalogStickARX() {
		return gp1.getRX();
	}

	public double getRawAnalogStickARY() {
		return gp1.getRY();
	}

	public double getRawAnalogStickBLX() {
		return xbox.getLX();

	}

	public double getRawAnalogStickBLY() {
		return xbox.getLY();

	}

	public double getRawAnalogStickBRX() {
		return xbox.getRX();

	}

	public double getRawAnalogStickBRY() {
		return xbox.getRY();

	}

}

package org.discobots.recyclerush;

import edu.wpi.first.wpilibj.buttons.Button;

import org.discobots.recyclerush.commands.ShutdownSensors;
import org.discobots.recyclerush.commands.ToggleCompressor;
import org.discobots.recyclerush.commands.autonomous.AutomatedStackingCommand;
import org.discobots.recyclerush.commands.drive.AssistedHolonomicDriveCommand;
import org.discobots.recyclerush.commands.drive.CycleDriveCommand;
import org.discobots.recyclerush.commands.drive.SetScalingCommand;
import org.discobots.recyclerush.commands.drive.ToggleDriveRampingCommand;
import org.discobots.recyclerush.commands.intake.ToggleIntakeCommand;
import org.discobots.recyclerush.commands.lift.LiftControllerCommand;
import org.discobots.recyclerush.commands.lift.SetLiftCommand;
import org.discobots.recyclerush.commands.plow.SetPlowCommand;
import org.discobots.recyclerush.commands.lift.SetLiftSetpointCommand;
import org.discobots.recyclerush.utils.GamePad;
import org.discobots.recyclerush.utils.GamePad.DPadButton;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private GamePad gp1 = new GamePad(0);
	private GamePad gp2 = new GamePad(1);
	// JOYSTICK 1
	private Button b_dpadU = new DPadButton(gp1, GamePad.DPAD_Y, true);
	private Button b_dpadD = new DPadButton(gp1, GamePad.DPAD_Y, false);
	private Button b_dpadR = new DPadButton(gp1, GamePad.DPAD_X, true);
	private Button b_dpadL = new DPadButton(gp1, GamePad.DPAD_X, false);
	private Button b_bumpR = new JoystickButton(gp1, GamePad.BTN_RB);
	private Button b_bumpL = new JoystickButton(gp1, GamePad.BTN_LB);
	private Button b_trigR = new JoystickButton(gp1, GamePad.BTN_RT);
	private Button b_trigL = new JoystickButton(gp1, GamePad.BTN_LT);
	private Button b_sBack = new JoystickButton(gp1, GamePad.BTN_BACK);
	private Button b_sStar = new JoystickButton(gp1, GamePad.BTN_START);
	private Button b_btnA = new JoystickButton(gp1, GamePad.BTN_A);
	private Button b_btnX = new JoystickButton(gp1, GamePad.BTN_X);
	private Button b_btnB = new JoystickButton(gp1, GamePad.BTN_B);
	private Button b_btnY = new JoystickButton(gp1, GamePad.BTN_Y);
	private Button b_clicR = new JoystickButton(gp1, GamePad.AXISBTN_R);
	private Button b_clicL = new JoystickButton(gp1, GamePad.AXISBTN_L);
	// JOYSTICK 2
	private Button b2_dpadU = new DPadButton(gp2, GamePad.DPAD_Y, true);
	private Button b2_dpadD = new DPadButton(gp2, GamePad.DPAD_Y, false);
	private Button b2_dpadR = new DPadButton(gp2, GamePad.DPAD_X, true);
	private Button b2_dpadL = new DPadButton(gp2, GamePad.DPAD_X, false);
	private Button b2_bumpR = new JoystickButton(gp2, GamePad.BTN_RB);
	private Button b2_bumpL = new JoystickButton(gp2, GamePad.BTN_LB);
	private Button b2_trigR = new JoystickButton(gp2, GamePad.BTN_RT);
	private Button b2_trigL = new JoystickButton(gp2, GamePad.BTN_LT);
	private Button b2_sBack = new JoystickButton(gp2, GamePad.BTN_BACK);
	private Button b2_sStar = new JoystickButton(gp2, GamePad.BTN_START);
	private Button b2_btnA = new JoystickButton(gp2, GamePad.BTN_A);
	private Button b2_btnX = new JoystickButton(gp2, GamePad.BTN_X);
	private Button b2_btnB = new JoystickButton(gp2, GamePad.BTN_B);
	private Button b2_btnY = new JoystickButton(gp2, GamePad.BTN_Y);
	private Button b2_clicR = new JoystickButton(gp2, GamePad.AXISBTN_R);
	private Button b2_clicL = new JoystickButton(gp2, GamePad.AXISBTN_L);

	public OI() {
		// first gamepad
		b_trigR.whenPressed(new SetLiftCommand(1));
		b_trigR.whenReleased(new SetLiftCommand(0));

		b_trigL.whenPressed(new SetLiftCommand(-1));
		b_trigL.whenReleased(new SetLiftCommand(0));

		b_bumpR.whenPressed(new SetLiftCommand(0.5));
		b_bumpR.whenReleased(new SetLiftCommand(0));

		b_bumpL.whenPressed(new SetLiftCommand(-0.5));
		b_bumpL.whenReleased(new SetLiftCommand(0));

		b_btnA.whenPressed(new ToggleIntakeCommand());
		
		//b_btnX.whenPressed(new SetLiftSetpointCommand(0));
		
		b_btnX.whenPressed(new ToggleCompressor());
		
		b_btnB.whenPressed(new SetScalingCommand());
		
		b_btnY.whenPressed(new AutomatedStackingCommand()); // one cycle, stoppable by any button

		b_sStar.whenPressed(new ShutdownSensors());
		
		b_sBack.whenPressed(new CycleDriveCommand());

		b_dpadU.whenPressed(new SetPlowCommand(1.0));
		b_dpadU.whenReleased(new SetPlowCommand(0.0));

		b_dpadD.whenPressed(new SetPlowCommand(-1.0));
		b_dpadD.whenReleased(new SetPlowCommand(0.0));
		// second gamepad
		

		b2_trigR.whenPressed(new SetLiftCommand(1));
		b2_trigR.whenReleased(new SetLiftCommand(0));

		b2_trigL.whenPressed(new SetLiftCommand(-1));
		b2_trigL.whenReleased(new SetLiftCommand(0));

		b2_bumpR.whenPressed(new SetLiftCommand(0.5));
		b2_bumpR.whenReleased(new SetLiftCommand(0));

		b2_bumpL.whenPressed(new SetLiftCommand(-0.5));
		b2_bumpL.whenReleased(new SetLiftCommand(0));

		b2_btnA.whenPressed(new ToggleIntakeCommand());
		
		b2_btnX.whenPressed(new AssistedHolonomicDriveCommand());
		
		// drive commands control analog sticks on joy 1
		// VariablePlowCommand controls analog sticks on joy 2
		
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
		return gp2.getLX();
	}

	public double getRawAnalogStickBLY() {
		return gp2.getLY();
	}

	public double getRawAnalogStickBRX() {
		return gp2.getRX();
	}

	public double getRawAnalogStickBRY() {
		return gp2.getRY();
	}
}

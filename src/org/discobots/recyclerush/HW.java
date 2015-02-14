package org.discobots.recyclerush;

/**
 * The HW is a mapping from the ports sensors and actuators are wired into to a
 * variable name. This provides flexibility changing wiring, makes checking the
 * wiring easier and significantly reduces the number of magic numbers floating
 * around.
 */
public class HW {
	/* CAN */// Check roboRio web interface for these values
	public final static int motorBackLeft = 10;
	public final static int motorCFrontLeft = 11;
	public final static int motorCBackRight = 12;
	public final static int motorCFrontRight = 13;
	public final static int motorCenterDropDown = 14;
	public final static int motorLift1 = 15;

	/* PWM */
	public final static int motorPlowLeft = 0;
	public final static int motorPlowRight = 1;
	public final static int motorPFrontLeft = 2;
	public final static int motorPBackRight = 3;
	public final static int motorPFrontRight = 4;

	/* Pneumatics */
	public final static int solenoidIntake = 0;
	public final static int dsolCenterDropdownA = 1; // dsol is double solenoid
	public final static int dsolCenterDropdownB = 2;

	/* Relay */

	/* DIO */// 0-9 are on roboRio, 10-25 are on MXP
	public final static int encoderForwardA = 0;
	public final static int encoderForwardB = 1;
	public final static int encoderLiftA = 2;
	public final static int encoderLiftB = 3;
	public final static int encoderSidewayA = 4;
	public final static int encoderSidewayB = 5;
	public final static int buttonPlowLeftIn = 6;
	public final static int buttonPlowRightIn = 7;
	public final static int buttonPlowLeftOut = 8;
	public final static int buttonPlowRightOut = 9;
	public final static int buttonLiftTop = 10;
	public final static int buttonLiftBottom = 11;

	public final static int lidarControlDrive = 19;
	public final static int lidarControlLift = 20;

	/* Analog In */
	public final static int gyroscope = 0;

	/* I2C */
	public final static int i2cLidarAddress = 0x62;

	/* Constants */
	public final static double encoderForwardConstant = (6) /* radius */ * Math.PI * (1 / 2 /* ratio */); // output in inches
	public final static double encoderSidewaysConstant = 4 * Math.PI;
	public final static double encoderLiftConstant = 4 * Math.PI;
	public final static double encoderCountsPerRevolution = 200 * 4; // x4  for k4x
	
	/* Lift Positions */
	public final static double liftPosToteOneRaise = 12 + 16; // One on ground, one tote held
	public final static double liftPosToteTwoRaise = 12 + 12 + 16; // two on ground, one tote held, elevated
	public final static double liftPosToteThreeRaise = 12 + 12 + 12 + 16;
	public final static double liftPosToteFourRaise = 12 + 12 + 12 + 12 + 16;
	public final static double liftPosBinRaise = 36;
	public final static double liftPosBinGrab = 18;
	
}

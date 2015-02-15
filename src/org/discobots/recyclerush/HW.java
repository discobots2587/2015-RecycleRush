package org.discobots.recyclerush;

/**
 * The HW is a mapping from the ports sensors and actuators are wired into to a
 * variable name. This provides flexibility changing wiring, makes checking the
 * wiring easier and significantly reduces the number of magic numbers floating
 * around.
 */
public class HW {
	/* CAN */// Check roboRio web interface for these values
	public final static int motorFrontLeft = 30;
	public final static int motorBackLeft = 31;
	public final static int motorFrontRight = 32;
	public final static int motorBackRight = 33;
	public final static int motorSideways = 34;
	
	public final static int motorLiftLeft = 36;
	public final static int motorLiftRight = 37;
	
	public final static int motorPlowLeft = 35;
	public final static int motorPlowRight = 38;

	/* PWM */

	/* Pneumatics */
	public final static int solIntake = 0;
	public final static int solFlapLeft = 1;
	public final static int solFlapRight = 2;
	public final static int dsolCenterDropdownA = 3; 
	public final static int dsolCenterDropdownB = 4;

	/* Relay */

	/* DIO */// 0-9 are on roboRio, 10-25 are on MXP
	public final static int encoderForwardA = 0;
	public final static int encoderForwardB = 1;
	public final static int encoderSidewayA = 2;
	public final static int encoderSidewayB = 3;
	public final static int buttonLiftTop = 4;
	public final static int buttonLiftBottom = 5;

	public final static int lidarControlDrive = 6;
	public final static int lidarControlLift = 7;

	/* Analog In */
	public final static int aGyroscope = 0;
	public final static int aPressureSensor = 1;

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

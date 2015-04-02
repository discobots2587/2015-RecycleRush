package org.discobots.recyclerush;

/**
 * The HW is a mapping from the ports sensors and actuators are wired into to a
 * variable name. This provides flexibility changing wiring, makes checking the
 * wiring easier and significantly reduces the number of magic numbers floating
 * around.
 */
public class HW {
	/* CAN */// Check roboRio web interface for these values
	public final static int motorFrontLeft = 30; // LF
	public final static int motorBackLeft = 31; // LR
	public final static int motorFrontRight = 33; // RF
	public final static int motorBackRight = 32; // RR
	public final static int motorSideways = 38;// NOW UNUSED
	
	public final static int motorLiftLeft = 37; // RL
	public final static int motorLiftRight = 34; // RW
	
	public final static int motorPlowLeft = 35;
	public final static int motorPlowRight = 38;

	/* PWM */

	/* Pneumatics */
	public final static int solIntake = 0;
	//public final static int solFlapLeft = 4;
	public final static int solFlapRight = 3;
	public final static int solClaw = 1;

	/* Relay */

	/* DIO */// 0-9 are on roboRio, 10-25 are on MXP
	public final static int encoderForwardA = 0;
	public final static int encoderForwardB = 1;
	public final static int encoderSidewayA = 2;
	public final static int encoderSidewayB = 3;
	public final static int buttonLiftTop = 9;
	public final static int buttonLiftBottom = 8;

	public final static int lidarControlLift = 7;

	/* Analog In */
	public final static int aGyroscope = 0; // GYRO
	public final static int aPressureSensor = 1; // PSEN

	/* I2C */
	public final static int i2cLidarAddress = 0x62;

	/* Constants */
	
	/* Lift Positions */
	public final static double liftPosToteOneRaise = 12 + 16+4; // One on ground, one tote held
	public final static double liftPosToteTwoRaise = (2*12) + 16+4; // two on ground, one tote held, elevated
	public final static double liftPosToteThreeRaise = (3*12) + 16+4;
	public final static double liftPosToteFourRaise = (4*12) + 16+4;
	public final static double liftPosBinRaise = 36+4;
	public final static double liftPosBinGrab = 18+4;
	
}

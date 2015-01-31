package org.discobots.recyclerush;

/**
 * The HW is a mapping from the ports sensors and actuators are wired into to a
 * variable name. This provides flexibility changing wiring, makes checking the
 * wiring easier and significantly reduces the number of magic numbers floating
 * around.
 */
public class HW {
	/* CAN */// Check roboRio web interface for these values
	public static int motorBackLeft = 10;
	public static int motorFrontLeft = 11;
	public static int motorBackRight = 12;
	public static int motorFrontRight = 13;
	public static int motorCenterDropDown = 14;
	//public static int motorPlowRight = 15;
	//public static int motorPlowLeft = 16;
	public static int motorLift1 = 15;
	public static int motorLift2 = 18;
	
	/* PWM */
	public static int motorPlowRight = 0;
	public static int motorPlowLeft = 1;

	/* Pneumatics */
	public static int dsolCenterDropdownA = 0; // dsol is double solenoid
	public static int dsolCenterDropdownB = 1;
	public static int intakeA = 2;
	public static int intakeB = 3;

	/* Relay */

	/* DIO */
	public static int encoderForwardLA = 0;
	public static int encoderForwardLB = 1;
	public static int encoderForwardRA = 2;
	public static int encoderForwardRB = 3;
	public static int encoderSidewayA = 4;
	public static int encoderSidewayB = 5;
	
	/* Analog In */
	public final static int gyroscope = 0;


	/* I2C */
	public static final int i2cLidarAddress = 0x62;
	
	/* Constants */
	public final static double wheelForwardCircumference = 6*Math.PI; //inches
	public final static double wheelSidewayCircumference = 4*Math.PI;
	public final static double encoderCountsPerRevolution = 200*4; //x4 for k4x
}

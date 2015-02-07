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
	public final static int motorFrontLeft = 11;
	public final static int motorBackRight = 12;
	public final static int motorFrontRight = 13;
	public final static int motorCenterDropDown = 14;
	public final static int motorLift1 = 15;
	public final static int motorLift2 = 18;
	
	/* PWM */
	public final static int motorPlowRight = 1;
	public final static int motorPlowLeft = 0;

	/* Pneumatics */
	public final static int dsolCenterDropdownA = 0; // dsol is double solenoid
	public final static int dsolCenterDropdownB = 1;

	/* Relay */

	/* DIO */
	public final static int encoderForwardA = 0;
	public final static int encoderForwardB = 1;
	public final static int encoderSidewayA = 4;
	public final static int encoderSidewayB = 5;
	public final static int encoderLiftA = 2;
	public final static int encoderLiftB = 3;

	public final static int buttonLiftTop = 8;
	public final static int buttonLiftBottom = 9;
	
	/* Analog In */
	public final static int gyroscope = 0 ;

	/* I2C */
	public final static int i2cLidarAddress = 0x62;
	
	/* Constants */
	public final static double wheelForwardCircumference = 6*Math.PI; //inches
	public final static double wheelSidewayCircumference = 4*Math.PI;
	public final static double encoderCountsPerRevolution = 200*4; //x4 for k4x
	
}

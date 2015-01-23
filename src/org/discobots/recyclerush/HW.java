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
	public static int motorPlowRight = 15;
	public static int motorPlowLeft = 16;

	/* PWM */

	/* Pneumatics */
	public static int dsolCenterDropdownA = 0; // dsol is double solenoid
	public static int dsolCenterDropdownB = 1;

	/* Relay */

	/* DIO */

	/* Analog In */
	public static int gyroscope = 1;

	/* I2C */
	public static final int i2cLidarAddress = 0x62;

}

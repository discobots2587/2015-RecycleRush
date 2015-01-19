package org.discobots.recyclerush;

/**
 * The HW is a mapping from the ports sensors and actuators are wired into to a
 * variable name. This provides flexibility changing wiring, makes checking the
 * wiring easier and significantly reduces the number of magic numbers floating
 * around.
 */
public class HW {
	/* CAN */ // Check roboRio web interface for these values
	public static int motorBackLeft = 10;
	public static int motorFrontLeft= 11;
	public static int motorBackRight = 12;
	public static int motorFrontRight = 13;
	public static int motorCenterDropDown = 14;
	
	/* PWM  */
	
	/* Pneumatics */

	/* Relay */

	/* DIO */

	/* Analog In */

	/* I2C */
	public static final int i2cLidarAddress = 0x62;
	
}

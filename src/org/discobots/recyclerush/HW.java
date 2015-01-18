package org.discobots.recyclerush;

/**
 * The HW is a mapping from the ports sensors and actuators are wired into to a
 * variable name. This provides flexibility changing wiring, makes checking the
 * wiring easier and significantly reduces the number of magic numbers floating
 * around.
 */
public class HW {
	/* CAN */ // Check roboRio web interface for these values
	public static int motorBackRight = 0;
	public static int motorBackLeft = 1;
	public static int motorFrontRight = 2;
	public static int motorFrontLeft= 3;
	public static int motorCenterDropDown = 4;
	
	/* PWM  */
	
	/* Pneumatics */

	/* Relay */

	/* DIO */

	/* Analog In */

	/* I2C */
	public static final int i2cLidarAddress = 0x62;
	
}

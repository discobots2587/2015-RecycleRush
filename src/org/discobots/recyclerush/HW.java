package org.discobots.recyclerush;

/**
 * The HW is a mapping from the ports sensors and actuators are wired into to a
 * variable name. This provides flexibility changing wiring, makes checking the
 * wiring easier and significantly reduces the number of magic numbers floating
 * around.
 */
public class HW {
	/* Motors */
	public static int frontRightTalon = 0;
	public static int backRightTalon = 1;
	public static int frontLeftTalon = 2;
	public static int backLeftTalon = 3;
	public static int centerDropDownTalon = 4;
	/* Solenoids */

	/* Relays */

	/* Digital IO */

	/* Analog In */

	/* I2C */
	public static final int i2cLidarAddress = 0x62;
}

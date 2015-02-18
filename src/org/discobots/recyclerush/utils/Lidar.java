package org.discobots.recyclerush.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

/*public class Lidar {
	private static final LidarController lidarController = new LidarController();

	public static class LidarController {
		private I2C i2c;
		private Thread updateThread;
		private Vector lidarVector;
		private List<Lidar> lidarList;
		private int counter;

		private final int LIDAR_CONFIG_REGISTER = 0x00;
		private final int LIDAR_DISTANCE_REGISTER = 0x8f;
		private final int LIDAR_ADDRESS = 0x62;

		
		 * This exists because we have multiple lidars and only one lidar port.
		 * This ensures we poll the i2c address in sequence. Hopefully the
		 * multi-threading works :/
		 */
		// public LidarController() {
	//		i2c = new I2C(Port.kMXP, LIDAR_ADDRESS);
		//	lidarVector = new Vector();
		//	lidarList = new ArrayList<Lidar>();
	//		counter = 0;
//			updateThread = new Thread() {

				//public void wait(int timeMs) {
			//		try {
		//				Thread.sleep(timeMs);
		//			} catch (InterruptedException e) {
			//			e.printStackTrace();
		//			}
	//			}

	

	//private double distance;
	//private DigitalOutput output;


	//private void setDigOutState(boolean a) {
	//	output.set(a);
//	}

	//public synchronized double getDistanceIn() {
	//	return distance / 2.54;
	//}

//	private synchronized void setDistanceCm(double a) {
	//	this.distance = a;
//	}
	//		};
package org.discobots.recyclerush.utils;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class Lidar {
	public static final int REGISTER_INITIATE_RANGING = 0x00;
	public static final int DATA_INITIATE_RANGING = 0x04;
	public static final int REGISTER_GET_HIGHLOW = 0x8f;
	
	private I2C i2cDevice;
	private Thread lidarThread;
	private boolean running;
	private volatile float distance;
	
	public Lidar(int deviceAddress) {
		i2cDevice = new I2C(Port.kOnboard, deviceAddress);
		lidarThread = new LidarThread();
		start();
	}
	
	public synchronized void start() {
		if (!running) {
			this.lidarThread = new LidarThread(); 
			// i hope user isn't dumb, stop cannot run immediately before start
		}
		running = true;
		this.lidarThread.start();
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	public synchronized double getDistanceCm() {
		return this.distance;
	}
	
	public synchronized double getDistanceIn() {
		return this.distance / 2.54;
	}
	
	private class LidarThread extends Thread {
		public void run() {
			while (running) {
				if (!i2cDevice.write(REGISTER_INITIATE_RANGING, DATA_INITIATE_RANGING)) {
					System.out.println("i2c device write fail, Lidar");
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				byte[] buffer = new byte[2];
				if (!i2cDevice.read(REGISTER_GET_HIGHLOW, 2, buffer)) {
					System.out.println("i2c device read fail, Lidar");
				}
				distance = (buffer[0] << 8) + buffer[2];
				try {
					Thread.sleep(29);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

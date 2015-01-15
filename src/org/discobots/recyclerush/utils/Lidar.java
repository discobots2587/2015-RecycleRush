package org.discobots.recyclerush.utils;

import org.discobots.recyclerush.Robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class Lidar {
	public static final int REGISTER_INITIATE_RANGING = 0x00;
	public static final int DATA_INITIATE_RANGING = 0x04;
	public static final int REGISTER_GET_HIGHLOW = 0x8f;
	
	private I2C i2cDevice;
	private Thread lidarThread;
	private boolean running;
	private volatile float distance; // should we used synchronized blocks?ppp
	
	public Lidar(int deviceAddress) {
		i2cDevice = new I2C(Port.kOnboard, deviceAddress);
		lidarThread = new Thread() {
			public void run() {
				while (running) {
					while (!i2cDevice.write(REGISTER_INITIATE_RANGING, DATA_INITIATE_RANGING)) {
						try {
							Thread.sleep(2);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					byte[] buffer = new byte[2];
					while(!i2cDevice.read(REGISTER_GET_HIGHLOW, 2, buffer)) {
						try {
							Thread.sleep(2);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					distance = (buffer[0] << 8) + buffer[2];
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}
	
	public void start() {
		running = true;
		this.lidarThread.start();
	}
	
	public void stop() {
		running = false;
	}
	
	public volatile double getDistanceCm() {
		return this.distance;
	}
	
	public volatile double getDistanceIn() {
		return this.distance / 2.54;
	}

}

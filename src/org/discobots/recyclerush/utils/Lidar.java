package org.discobots.recyclerush.utils;

import java.util.TimerTask;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.PIDSource;

public class Lidar implements PIDSource {
	private I2C i2c;
	private byte[] distance;
	private java.util.Timer updater;
	private final int LIDAR_ADDR = 0x62;
	private final int LIDAR_CONFIG_REGISTER = 0x00;
	private final int LIDAR_DISTANCE_REGISTER = 0x8f;

	public Lidar(int a) {
		i2c = new I2C(Port.kMXP, LIDAR_ADDR);
		distance = new byte[2];
		updater = new java.util.Timer();
	}

	// Distance in cm
	public int getDistanceCm() {
		return (int) Integer.toUnsignedLong(distance[0] << 8)
				+ Byte.toUnsignedInt(distance[1]);
	}

	public double pidGet() {
		return getDistanceCm();
	}

	// Start 10Hz polling
	public void start() {
		updater.scheduleAtFixedRate(new LIDARUpdater(), 0, 100);
	}

	// Start polling for period in milliseconds
	public void start(int period) {
		updater.scheduleAtFixedRate(new LIDARUpdater(), 0, period);
	}

	public void stop() {
		updater.cancel();
		updater = new java.util.Timer();
	}

	// Update distance variable
	public void update() {
		i2c.write(LIDAR_CONFIG_REGISTER, 0x04); // Initiate measurement
		Timer.delay(0.04); // Delay for measurement to be taken
		i2c.read(LIDAR_DISTANCE_REGISTER, 2, distance); // Read in measurement
		Timer.delay(0.005); // Delay to prevent over polling
	}

	// Timer task to keep distance updated
	private class LIDARUpdater extends TimerTask {
		public void run() {
			while (true) {
				update();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
/*

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
		i2cDevice = new I2C(Port.kMXP, deviceAddress);
		lidarThread = new LidarThread();
	}

	public synchronized void start() {
		if (!running) {
			this.lidarThread = new LidarThread();
			this.lidarThread.start();
			// i hope user isn't dumb, stop cannot run immediately before start
		}
		running = true;
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
				if (!i2cDevice.write(REGISTER_INITIATE_RANGING,
						DATA_INITIATE_RANGING)) {
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
				distance = (int) Integer.toUnsignedLong(buffer[0] << 8)
						+ Byte.toUnsignedInt(buffer[1]);
				System.out.println(distance);
				try {
					Thread.sleep(49);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}*/
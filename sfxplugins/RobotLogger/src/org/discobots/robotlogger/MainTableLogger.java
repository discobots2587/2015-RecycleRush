package org.discobots.robotlogger;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class Logger implements Runnable {
	public static void main(String[] args) {
		System.out.println("RobotLogger, (c) Discobots 2587");
		System.out.println("Authors: Thomas McDonald, Nolan Shah");
		System.out.println("Source Code is available on GitHub");
		System.out.println("https://github.com/discobots2587/");
		
		Logger logger = new Logger();
		logger.run();
	}

	NetworkTable smartTable;
	Object sema;
	PrintWriter fileWriter;
	LinkedHashMap<String, Object> dataMap;
	boolean newData;
	long dataWriteIndex = 0;

	public Logger() {
		System.out.println("[D] Loading date & time for file.");
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		String outputFileName = System.getProperty("user.home") + "\\Robot Logs\\log-" + currentDateTime.getYear() + "."
				+ currentDateTime.getMonth().getValue() + "."
				+ currentDateTime.getDayOfMonth() + "."
				+ currentDateTime.getHour() + "." + currentDateTime.getMinute()
				+ "." + currentDateTime.getSecond() + ".log";
		
		try {
			System.out.println("[D] Creating and Loading file.");
			File file = new File(outputFileName);
			System.out.println("[D] File URL: " + file.getPath());
			
			Path path = Paths.get(System.getProperty("user.home") + "\\Robot Logs\\");
			Files.createDirectories(path);

			file.createNewFile();
			fileWriter = new PrintWriter(file, "UTF-8");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(32);
		}
		
		System.out.println("[D] Configuring application memory storage");
		dataMap = new LinkedHashMap<String, Object>();
		newData = true;

		sema = new Object();
		
		System.out.println("[D] Configuring network tables connection");
		NetworkTable.setClientMode();
		NetworkTable.setTeam(2587);
		smartTable = NetworkTable.getTable("Debug");
		smartTable.addTableListener(new ITableListener() {

			@Override
			public void valueChanged(ITable source, String key, Object value,
					boolean isNew) {
				if (isNew) {
					Logger.this.newData = true;
				}
				synchronized (sema) {
					dataMap.put(key, value);
				}
			}
		});
	}

	public void run() {
		System.out.println("[D] Beginning to update & write data...");
		while (true) {
			synchronized (sema) {
				if (newData) {
					dataWriteIndex++;
					String outputLine = "1," + dataWriteIndex + ",";
					for (String key : this.dataMap.keySet()) {
						outputLine += (key + ",");
					}
					this.fileWriter.println(outputLine);
					this.newData = false;
				}

				dataWriteIndex++;
				String outputLine = "0," + dataWriteIndex + ",";
				for (String key : this.dataMap.keySet()) {
					outputLine += (this.dataMap.get(key) + ",");
				}
				this.fileWriter.println(outputLine);

				fileWriter.flush();
				try {
					Thread.sleep(50);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}

	}
}

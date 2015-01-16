import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.wpilibj.networktables.NetworkTable;


public class MainTableLogger {
	public static void main(String[] args)
	{
		new MainTableLogger().run();
	}
	public void run()
	{
		NetworkTable.setClientMode();
		NetworkTable.setTeam(2587);
		NetworkTable smartTable = NetworkTable.getTable("SmartDashboard");
		
		while(true)
		{
			try
			{
				Thread.sleep(500);
			}
			catch(InterruptedException ex)
			{
				ex.printStackTrace();
			}
			double x = smartTable.getNumber("X",0.0);
			double y = smartTable.getNumber("Y",0.0);
			
		}
		
		
	}
}

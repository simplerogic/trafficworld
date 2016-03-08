package exercise1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SwingIORW {
	private static String param;
	private static int val;
	private static Map<String, Integer> paramval = new HashMap<String, Integer>();
	public SwingIORW() {
		//Swingclass swing = new Swing();
		//writeFile("SimProperties.txt");
		readFile("simproperties.txt");
		
	}
				
	public static void readFile(String filename){
			try{
				FileReader read = new FileReader(filename);
				BufferedReader bRead = new BufferedReader(read);
				while(bRead.ready()){
					String s = bRead.readLine();					
					Integer val = Integer.parseInt(s.substring(14));
					param = s.substring(0, 13).trim();
					paramval.put(param, val);	
				}
				bRead.close();
				read.close();
			}catch (Exception e){
				e.printStackTrace();
			}		
			setHashVariables();
	}

	private static void setHashVariables() {
		TrafficWorld.setSimTime(paramval.get("SimTime"));
		TrafficWorld.setSpawnRate(paramval.get("CarSpawnRate"));
		TrafficWorld.setRedSpawnRate(paramval.get("RedSpawn%"));
		TrafficWorld.setRedSpawnRate(paramval.get("YellowSpawn%"));
		TrafficWorld.setRedSpawnRate(paramval.get("BlueSpawn%"));
		TrafficWorld.setRedSpawnRate(paramval.get("PurpleSpawn%"));
	}
	
}

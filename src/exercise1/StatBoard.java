package exercise1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StatBoard {
		private double avgpint;
		private Map<String, Integer> blocksPassed = new HashMap<String, Integer>();

	public StatBoard(List<Intersection> intersectionList) {
		
		System.out.println("Simulation Time: " + TrafficWorld.getSimTime());
		System.out.println("Total Blocks Traveled: " + Intersection.getAmountCarsIn());
		blocksTraveled();
		System.out.println("Average Number of Cars Per intersection: " + averageCarsPerIntersection(intersectionList));
		System.out.println("Average Innerarrival time: " + averageInterArrivalTime(intersectionList));
	}

	public double calcAvg(String color){
		if (color.equals("red")){
			return (double)(Intersection.getAmountCarsInRed() / TrafficWorld.getSpawnNumbers(color));
		} else if (color.equals("yellow")){
			return (double)(Intersection.getAmountCarsInYellow() / TrafficWorld.getSpawnNumbers(color));
		} else if (color.equals("blue")){
			return (double)(Intersection.getAmountCarsInYellow() / TrafficWorld.getSpawnNumbers(color));
		} else if (color.equals("purple")){
			return (double)(Intersection.getAmountCarsInYellow() / TrafficWorld.getSpawnNumbers(color));
		} else {
			return 0;
		}
	}
	
	public double averageCarsPerIntersection(List<Intersection> intersectionList){
		avgpint = (double)TrafficWorld.getTotalSpawns() / intersectionList.size();
		return avgpint;
	}
	
	public double averageInterArrivalTime(List<Intersection> intersectionList){
		double innerarrivalTime = 0;
		double innerarrivalAvg = 0;
		double average = 0;
		int carCount;
		for(Intersection i : intersectionList){
			carCount = i.getAmountCarsIn();
			if(carCount == 0){
				carCount = 1;
			}
			innerarrivalAvg += TrafficWorld.getSimTime() / carCount;
			}
		
		if(innerarrivalAvg == 0){
			average = 0;
		}
		else{
			average = innerarrivalAvg / intersectionList.size();
		}
		
		return average;
	}
	public void blocksTraveled(){
		blocksPassed.put("red", Intersection.getAmountCarsInRed());
		blocksPassed.put("yellow", Intersection.getAmountCarsInYellow());
		blocksPassed.put("blue", Intersection.getAmountCarsInBlue());
		blocksPassed.put("purple", Intersection.getAmountCarsInPurple());
		   for(Entry<String, Integer> bp : blocksPassed.entrySet()) {
		        String key = bp.getKey();
		        System.out.print("Blocks Passed by " + key + "cars: ");
		        Integer value = bp.getValue();
		        System.out.print(value);
		        System.out.println(" Avg: " + calcAvg(key));
		    }
	}
}


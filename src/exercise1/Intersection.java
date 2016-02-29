package exercise1;

import java.util.ArrayList;
import java.util.List;

import exercise1.TrafficLight.Color;
import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.sound.SoundUtils;

public class Intersection extends Actor {
	private int lightCounter = 0, horizCounter = 0;
	private TrafficLight.Color verticalColor, horizontalColor;
	private int GREENCount = 100;
	private int YELLOWCount = 25;
	private int REDCount = GREENCount + YELLOWCount;
	private Car c;
	private TrafficLight tl = null, tl2 = null, tl3 = null, tl4 = null;
	private static int amountCarsIn = 0, amountCarsInYellow = 0, amountCarsInRed = 0, amountCarsInBlue = 0, amountCarsInPurple = 0;

	
	private ArrayList<Car> currentCars = new ArrayList<Car>();
	private ArrayList<Car> previousCars = new ArrayList<Car>();
	private ArrayList<Car> inCurrentCars = new ArrayList<Car>();
	private ArrayList<Car> inPreviousCars = new ArrayList<Car>();

	
	public Intersection(int roadwidth) {
		GreenfootImage intersection = new GreenfootImage(roadwidth, roadwidth);
		setImage(intersection);
	}
	public void addLightsVertical() {
		verticalColor = TrafficLight.Color.GREEN;
		tl = new TrafficLight(verticalColor);
		getWorld().addObject(tl, this.getX(), this.getY() - tl.getImage().getHeight());
		tl2 = new TrafficLight(verticalColor);
		tl2.setRotation(180);
		getWorld().addObject(tl2, this.getX(), this.getY() + tl2.getImage().getHeight());
	}
	public void addLightsHorizontal() {
		horizontalColor = TrafficLight.Color.RED;
		tl3 = new TrafficLight(horizontalColor);
		getWorld().addObject(tl3, this.getX() + tl.getImage().getHeight(), this.getY());
		tl3.setRotation(90);
		tl4 = new TrafficLight(horizontalColor);
		getWorld().addObject(tl4, this.getX() - tl.getImage().getHeight(), this.getY());
		tl4.setRotation(270);
	}
	@SuppressWarnings("unchecked")
	private void notifyLeavingCars() {
		for (Car c : previousCars){
			if (!currentCars.contains(c)){
				c.notifyLeaving(this);
				amountCarsIn++;
				if (c.getClass().equals(RedCar.class)){
					amountCarsInRed++;
				} else if (c.getClass().equals(YellowCar.class)){ 
					amountCarsInYellow++;
				} else if (c.getClass().equals(BlueCar.class)){ 
					amountCarsInBlue++;
				} else if (c.getClass().equals(PurpleCar.class)){ 
					amountCarsInPurple++;
				}
			}
			
		}
		previousCars = currentCars;
	}
	@SuppressWarnings("unchecked")
	private void notifyInCars() {
		inCurrentCars = (ArrayList<Car>) getIntersectingObjects(Car.class);
		for (Car c : inCurrentCars){
			if (!inPreviousCars.contains(c)){
				c.notifyInside(this);
				
			}
		}
		inPreviousCars = inCurrentCars;

	}
	@SuppressWarnings("unchecked")
	private void notifyApproachingCars() {
		currentCars = (ArrayList<Car>) getObjectsInRange(40, Car.class);
		for (Car c : currentCars){
			if (!previousCars.contains(c)){
				c.notifyApproaching(this);
				
			}
			
		}
	}
	public Color getHorizontalTrafficLights(){
		return horizontalColor;
	}
	public Color getVerticalTrafficLights(){
		return verticalColor;
	}
	public static int getAmountCarsIn(){
		return amountCarsIn;
	}
	public static int getAmountCarsInYellow(){
		return amountCarsInYellow;
	}
	public static int getAmountCarsInRed(){
		return amountCarsInRed;
	}
	public static int getAmountCarsInBlue(){
		return amountCarsInBlue;
	}
	public static int getAmountCarsInPurple(){
		return amountCarsInPurple;
	}
	
	public void act() {
		lightCounter++;
		switch (verticalColor) {
		case GREEN:
			if (lightCounter == GREENCount) {
				verticalColor = TrafficLight.Color.YELLOW;
				tl.setColor(verticalColor);
				tl2.setColor(verticalColor);
				lightCounter = 0;
			}
			break;
		case YELLOW:
			if (lightCounter == YELLOWCount) {
				verticalColor = TrafficLight.Color.RED;
				tl.setColor(verticalColor);
				tl2.setColor(verticalColor);
				lightCounter = 0;
			}
			break;
		case RED:
			if (lightCounter == REDCount) {
				verticalColor = TrafficLight.Color.GREEN;
				tl.setColor(verticalColor);
				tl2.setColor(verticalColor);
				lightCounter = 0;
			}
			break;
		}
		horizCounter++;
		switch (horizontalColor) {
		case GREEN:
			if (horizCounter == GREENCount) {
				horizontalColor = TrafficLight.Color.YELLOW;
				tl3.setColor(horizontalColor);
				tl4.setColor(horizontalColor);
				horizCounter = 0;
			}
			break;
		case YELLOW:
			if (horizCounter == YELLOWCount) {
				horizontalColor = TrafficLight.Color.RED;
				tl3.setColor(horizontalColor);
				tl4.setColor(horizontalColor);
				horizCounter = 0;
			}
			break;
		case RED:
			if (horizCounter == REDCount) {
				horizontalColor = TrafficLight.Color.GREEN;
				tl3.setColor(horizontalColor);
				tl4.setColor(horizontalColor);
				horizCounter = 0;
			}
			break;
		}
		notifyInCars();
		notifyApproachingCars();
		notifyLeavingCars();
	}
}




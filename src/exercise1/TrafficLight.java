package exercise1;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class TrafficLight extends Actor{
	public enum Color{
		GREEN,
		YELLOW,
		RED;
	}
	
	
	String[] colorImages = {"images\\trafficLightGreen.png","images\\trafficLightYellow.png","images\\trafficLightRed.png"};

	public TrafficLight(Color initialColor){
		GreenfootImage image = new GreenfootImage(colorImages[initialColor.ordinal()]);
		setImage(image);
	}
	
	public void setColor(Color newColor){
		setImage(colorImages[newColor.ordinal()]);
	}
	
	
	public void act(){
		
	}
	
}

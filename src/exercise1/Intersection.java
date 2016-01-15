package exercise1;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Intersection extends Actor{
	private int lightCounter = 0;
	private TrafficLight.Color verticalColor;
    private int GREENCount = 100;
	private int YELLOWCount = 25;
	private int REDCount = GREENCount + YELLOWCount;
	private TrafficLight tl;
	
	public Intersection(int roadwidth){
		GreenfootImage intersection = new GreenfootImage(roadwidth,roadwidth);
		setImage(intersection);
	}
	
	public void addLightsVertical(){
		verticalColor = TrafficLight.Color.GREEN;
		TrafficLight tl = new TrafficLight(verticalColor);
		getWorld().addObject(tl, this.getX(), this.getY() - tl.getImage().getHeight());
		TrafficLight tl2 = new TrafficLight(verticalColor);
		tl2.setRotation(180);
		getWorld().addObject(tl2, this.getX(), this.getY() + tl2.getImage().getHeight());
	}
	
	public void addLightsHorizontal(){
		verticalColor = TrafficLight.Color.GREEN;
		TrafficLight tl = new TrafficLight(verticalColor);
		tl.setRotation(90);
		getWorld().addObject(tl, this.getX() + tl.getImage().getHeight(), this.getY());
		TrafficLight tl2 = new TrafficLight(verticalColor);
		tl2.setRotation(270);
		getWorld().addObject(tl2, this.getX() - tl.getImage().getHeight(), this.getY());
	}
	
	
	/*public void act(){
		lightCounter++;
		switch(verticalColor){
			case GREEN:
				if (lightCounter == GREENCount){
					verticalColor = TrafficLight.Color.YELLOW;
					tl.setColor(verticalColor);
					lightCounter = 0;
				}
				break;
			case YELLOW:
				if (lightCounter == YELLOWCount){
					verticalColor = TrafficLight.Color.RED;
					tl.setColor(verticalColor);
					lightCounter = 0;
				}
				break;
			case RED:
				if (lightCounter == REDCount){
					verticalColor = TrafficLight.Color.GREEN;
					tl.setColor(verticalColor);
					lightCounter = 0;
				}
				break;
		}
	}*/
}

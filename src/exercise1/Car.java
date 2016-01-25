package exercise1;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import java.util.Random;

public class Car extends Actor implements IntersectionListener{
	Random rand = new Random();
	int rotation, movement, carcolor;
	double x, y;
	private String currState;
	private String prevState;
	private TrafficLight.Color horlight, vertlight;
	private Intersection intersection;
	private int speed = 4;
	private final int FULLSPEED = 3;
	private CarState state;
	Intersection curIntersection;
		
	public Car(World world, int rotation, double x, double y, int movement) {
		this.rotation = rotation;
		this.x = x;
		this.y = y;
		this.movement = movement;
		
		carcolor = Greenfoot.getRandomNumber(4);
		GreenfootImage image = null;
		String[] cartype = new String[4];
		cartype[0] = "images\\topCarBlue.png";
		cartype[1] = "images\\topCarPurple.png";
		cartype[2] = "images\\topCarRed.png";
		cartype[3] = "images\\topCarYellow.png";
		image = new GreenfootImage(cartype[carcolor]);
		setRotation(rotation);
		this.setImage(image);
		world.addObject(this, (int) x, (int) y);
		state = CarState.OUTSIDE;
	}
	public enum CarState{
		OUTSIDE,APPROACHING,INSIDE;
		
	}
	
	public int getCarRotation(){
		return rotation;
	}
	
	public void act() {
		move(speed);

		System.out.println(horlight +" " +vertlight);
		if (this.isAtEdge()) {
			if (rotation == 0) {
				this.setLocation(0, getY());
			} else if (rotation == 180) {
				this.setLocation(TrafficWorld.WIDTH, getY());
			}
			
			if (rotation == 90) {
				this.setLocation(getX(), 0);
			} else if (rotation == 270) {
				this.setLocation(getX(), TrafficWorld.HEIGHT);
			}
		}
		System.out.println(speed);
		switch(state){
		case OUTSIDE:
			speedUp();
			break;
		case APPROACHING:
			if (getCarRotation() == 180  || getCarRotation() == 0){
				if (horlight.equals(TrafficLight.Color.GREEN)){
					speedUp();
				} else {
					slowDown();
				}
			} else if (getCarRotation() == 90 || getCarRotation() == 270){
				if (vertlight.equals(TrafficLight.Color.GREEN)){
					speedUp();
				} else {
					slowDown();
				}
		}
			break;
		case INSIDE:
			if (getCarRotation() == 180 || getCarRotation() == 0){
				if (horlight.equals(TrafficLight.Color.GREEN)){
					speedUp();
				} else if (horlight.equals(TrafficLight.Color.RED) && speed > 1){
					speedUp();
				} else if (horlight.equals(TrafficLight.Color.RED) && speed <= 1){
					stop();
				}
			} 
			if (getCarRotation() == 270 || getCarRotation() == 90){
				if (vertlight.equals(TrafficLight.Color.GREEN)){
					speedUp();
				} else if (vertlight.equals(TrafficLight.Color.RED) && speed > 1){
					speedUp();
				} else if (vertlight.equals(TrafficLight.Color.RED) && speed <= 1){
					speed = 0;
				}
			}
			break;
		}
		System.out.println(state);
	}

	private void stop() {
		if (speed < 0){
		speed--;
		}
		System.out.println("STOPPING");
	}

	private void slowDown() {
		if (speed > 1){
		 speed-=1;
		}
	}

	private void speedUp() {
		if (speed < FULLSPEED){
		speed+=1;
		}
	}

	@Override
	public void notifyApproaching(Intersection intersection) {
		state = CarState.APPROACHING;
		curIntersection = intersection;
		horlight = intersection.getHorizontalTrafficLights();
		vertlight= intersection.getVerticalTrafficLights();
		
	}

	@Override
	public void notifyLeaving(Intersection intersection) {
		state = CarState.OUTSIDE;
		curIntersection = intersection;
		horlight = intersection.getHorizontalTrafficLights();
		vertlight= intersection.getVerticalTrafficLights();
	}

	@Override
	public void notifyInside(Intersection intersection) {
		state = CarState.INSIDE;
		curIntersection = intersection;
		horlight = intersection.getHorizontalTrafficLights();
		vertlight= intersection.getVerticalTrafficLights();
	}

	
}

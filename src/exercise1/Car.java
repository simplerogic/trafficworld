package exercise1;

import java.util.ArrayList;
import java.util.Random;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;


public abstract class Car extends Actor implements IntersectionListener{
	Random rand = new Random();
	int rotation, movement, carcolor;
	double x, y;
	private String currState;
	private String prevState;
	private TrafficLight.Color horlight, vertlight;
	private Intersection intersection;
	protected int speed = 4;
	private final int FULLSPEED = 3;
	protected CarState state;
	protected Intersection curIntersection;
	protected ArrayList<Car> collidingCars = new ArrayList<Car>();
	private boolean collided = false;
	private int explosionCounter = 1;
	private int turnChance, turnPercentage = 6;
	private static int amountCarsIn;


		
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
	
	
	
	public void turn(String turnDir){
		if (turnDir.equals("right")){
			if (this.getRotation() == 270){
				this.setRotation(0);
			} else {
				this.setRotation(getRotation() + 90);
			}
		} else if (turnDir.equals("left")){
			if (this.getRotation() == 0){
				this.setRotation(270);
			} else {
				this.setRotation(this.getRotation() - 90);
			}
		}
		
	}
	
	protected void collisionCheck(){
		try{
			if(this.getWorld() != null){
				if (this.isTouching(Car.class)){
					throw new Error("car collision");
				}
			}
		
		} catch(Error carCollision){
			ArrayList<Car> carCollisionList = new ArrayList<Car>();
			carCollisionList.add(this);
			for(Car c : (ArrayList<Car>) this.getIntersectingObjects(Car.class)){
				carCollisionList.add(c);
			}
			for(Car c : carCollisionList){
				c.collided = true;
			}
		}
	}

	
	public void act() {
		move(speed);
		collisionCheck();
		edgeCheck();
		
		carStateSwitch();
	}

	protected void explosion() {
		if (collided){
			assert(explosionCounter<4);
			this.setImage("images/explosion" + explosionCounter +".png");
			explosionCounter++;
			if (explosionCounter == 4){
				this.getWorld().removeObject(this);
			}
		}
	}

	protected void carStateSwitch() {
		switch(state){
		case OUTSIDE:
			speedUp();
			break;
		case INSIDE:
			if (getRotation() == 180 || getRotation() == 0){
				if (curIntersection.getHorizontalTrafficLights().equals(TrafficLight.Color.GREEN)){
					speedUp();
				} else if (curIntersection.getHorizontalTrafficLights().equals(TrafficLight.Color.YELLOW)){
					speedUp();
				} else if (curIntersection.getHorizontalTrafficLights().equals(TrafficLight.Color.RED)){
					stop();
				}
			} 
			if (getRotation() == 270 || getRotation() == 90){
				if (curIntersection.getVerticalTrafficLights().equals(TrafficLight.Color.GREEN)){
					speedUp();
				} else if (curIntersection.getVerticalTrafficLights().equals(TrafficLight.Color.YELLOW) ){
					speedUp();
				} else if (curIntersection.getVerticalTrafficLights().equals(TrafficLight.Color.RED) ){
					stop();
				}
			}
			

			break;
		case APPROACHING:
			if (getRotation() == 180  || getRotation() == 0){
				if (curIntersection.getHorizontalTrafficLights().equals(TrafficLight.Color.GREEN)){
					speedUp();
				} else {
					slowDown();
				}
			} else if (getRotation() == 90 || getRotation() == 270){
				if (curIntersection.getVerticalTrafficLights().equals(TrafficLight.Color.GREEN)){
					speedUp();
				} else {
					slowDown();
					//stop();
				}
			}
			break;

		}
	}


	protected boolean edgeCheck() {
		if (this.getWorld() != null) {
			if (this.isAtEdge()) {
				return true;
			}
		}
		return false;
	}
	
	protected void stop() {
		speed = 0;
	}

	protected void slowDown() {
		if (speed > 1){
		 speed--;
		}
	}

	protected void speedUp() {
		if (speed < FULLSPEED){
		speed++;
		}
	}

	@Override
	public void notifyApproaching(Intersection intersection) {
		state = CarState.APPROACHING;
		curIntersection = intersection;

		
	}

	@Override
	public void notifyLeaving(Intersection intersection) {
		state = CarState.OUTSIDE;
		curIntersection = intersection;
		
		
		
	}

	@Override
	public void notifyInside(Intersection intersection) {
		state = CarState.INSIDE;
		curIntersection = intersection;
	}

	
}

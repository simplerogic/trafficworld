package exercise1;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import java.util.Random;

public class Car extends Actor {
	Random rand = new Random();
	int rotation, movement, carcolor;
	double x, y;

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
	}
	
	public int getCarRotation(){
		return rotation;
	}
	
	public void act() {
		move(movement);
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

	}
}

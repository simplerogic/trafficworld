package exercise1;

import greenfoot.World;

public class PurpleCar extends Car{
	private int turnChance = 0;
	private int turnPercentage = 4;
	public PurpleCar(World world, int rotation, double x, double y, int movement) {
		super(world, rotation, x, y, movement);
		this.setImage("images\\topCarPurple.png");
	}
	public void act(){
		carStateSwitch();
		turnIfVertical();
		turnIfHorizontal();
		move(speed);
		if (edgeCheck()){
			this.getWorld().removeObject(this);
		}
		collisionCheck();
		explosion();
	}
	
	public void turnIfVertical() {
		turnChance = rand.nextInt(turnPercentage);
		if (curIntersection != null && this.getWorld() != null) {
			assert(curIntersection.getHorizontalTrafficLights().equals(TrafficLight.Color.GREEN) || curIntersection.getHorizontalTrafficLights().equals(TrafficLight.Color.YELLOW));
			if (this.getRotation() == 90) {
				if (turnChance == 0 && (curIntersection.getY() - (50 / 4)) == getY()) {
					turn("left");
				}
			} else if (this.getRotation() == 270) {
				turnChance = rand.nextInt(turnPercentage);
				if (turnChance == 0 && (curIntersection.getY() + (50 / 4)) == getY()) {
					turn("left");
				}
			}
		}
	}

	public void turnIfHorizontal() {
		turnChance = rand.nextInt(turnPercentage);
		if (curIntersection != null && this.getWorld() != null) {
			assert (curIntersection.getHorizontalTrafficLights().equals(TrafficLight.Color.GREEN) || curIntersection.getHorizontalTrafficLights().equals(TrafficLight.Color.YELLOW));
			if (this.getRotation() == 0) {
				if (turnChance == 0 && (curIntersection.getX() - (50 / 4)) == this.getX()) {
					turn("left");
				} else if (this.getRotation() == 180) {
					turnChance = rand.nextInt(turnPercentage);
					if (turnChance == 0 && (curIntersection.getX() + (50 / 4)) == this.getX()) {
						turn("left");
					}
				}
			}
		}
	}
	
}
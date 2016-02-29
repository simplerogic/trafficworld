package exercise1;

import greenfoot.World;

public class YellowCar extends Wrapable {
	private int turnChance = 0;
	private int turnPercentage = 2;

	public YellowCar(World world, int rotation, double x, double y, int movement) {
		super(world, rotation, x, y, movement);
		this.setImage("images\\topCarYellow.png");
	}

	public void act() {
		carStateSwitch();
		turnIfVertical();
		turnIfHorizontal();
		if (this.edgeCheck()) {
			wrap(this.getRotation());
		}
		move(speed);		
		collisionCheck();
		explosion();

	}

	public void turnIfVertical() {
		turnChance = rand.nextInt(turnPercentage);
		if (curIntersection != null && this.getWorld() != null) {
			assert(curIntersection.getHorizontalTrafficLights().equals(TrafficLight.Color.GREEN) || curIntersection.getHorizontalTrafficLights().equals(TrafficLight.Color.YELLOW));
			if (this.getRotation() == 90) {
				if (turnChance == 0 && (curIntersection.getY() - (50 / 4)) == getY()) {
					turn("right");
				}
			} else if (this.getRotation() == 270) {
				if (turnChance == 0 && (curIntersection.getY() + (50 / 4)) == getY()) {
					turn("right");
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
					turn("right");
				}
				} else if (this.getRotation() == 180) {
					if (turnChance == 0 && (curIntersection.getX() + (50 / 4)) == this.getX()) {
						turn("right");
					}
			}
		}
	}
}
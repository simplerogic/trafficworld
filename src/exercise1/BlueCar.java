package exercise1;

import greenfoot.World;

public class BlueCar extends Car {
	private boolean left = false;

	public BlueCar(World world, int rotation, double x, double y, int movement) {
		super(world, rotation, x, y, movement);
		this.setImage("images\\topCarBlue.png");
	}

	public void act() {
		carStateSwitch();
		turnIfVertical();
		turnIfHorizontal();
		move(speed);
		if (edgeCheck()) {
			this.getWorld().removeObject(this);
		}
		collisionCheck();
		explosion();
	}

	public void turnIfVertical() {
		if (curIntersection != null && this.getWorld() != null) {
			if (!left) {
				if (this.getRotation() == 90) {
					if ((curIntersection.getY() + (50 / 4)) == getY()) {
						turn("left");
						left = true;
					}
				} else if (this.getRotation() == 270) {
					if ((curIntersection.getY() - (50 / 4)) == getY()) {
						turn("left");
						left = true;
					}
				}
			} else if (left) {
				if (this.getRotation() == 90) {
					if ((curIntersection.getY() - (50 / 4)) == getY()) {
						turn("right");
						left = false;
					}
				} else if (this.getRotation() == 270) {
					if ((curIntersection.getY() + (50 / 4)) == getY()) {
						turn("right");
						left = false;
					}
				}
			}
		}
	}

	public void turnIfHorizontal() {
		if (curIntersection != null && this.getWorld() != null) {
			if (!left) {
				if (this.getRotation() == 0) {
					if ((curIntersection.getX() + (50 / 4)) == this.getX()) {
						turn("left");
						left = true;
					} else if (this.getRotation() == 180) {
						if ((curIntersection.getX() - (50 / 4)) == this.getX()) {
							turn("left");
							left = true;
						}
					}
				}
			} else if (left) {
				if (this.getRotation() == 0) {
					if ((curIntersection.getX() - (50 / 4)) == this.getX()) {
						turn("right");
						left = false;
					} else if (this.getRotation() == 180) {
						if ((curIntersection.getX() + (50 / 4)) == this.getX()) {
							turn("right");
							left = false;
						}
					}
				}
			}
		}
	}
}
package exercise1;

import greenfoot.World;

public class Wrapable extends Car{
	private int rotation;
	private int x,y;
	public Wrapable(World world, int rotation, double x, double y, int movement) {
		super(world, rotation, x, y, movement);
	}
	
	
	protected void wrap(int rotation){
			this.rotation = rotation;
			x = 0;
			y = 0;
			if (rotation == 0){
				y = getY();
			} else if (rotation == 270){
				x = getX();
				y = TrafficWorld.HEIGHT;
			} else if (rotation == 180){
				x = TrafficWorld.WIDTH;
				y = getY();
			} else if (rotation == 90){
				x = getX();
			}
			setLocation((int)x,(int)y);
			
	}
}

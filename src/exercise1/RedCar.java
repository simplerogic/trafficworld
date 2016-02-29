package exercise1;

import greenfoot.World;

public class RedCar extends Wrapable{

	
	public RedCar(World world, int rotation, double x, double y, int movement) {
		super(world, rotation, x, y, movement);
		this.setImage("images\\topCarRed.png");
	}
	public void act(){
		carStateSwitch();
		if (edgeCheck()){
			wrap(this.getRotation());
		}
		move(speed);
		collisionCheck();
		explosion();
	}
}

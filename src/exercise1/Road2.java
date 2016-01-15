package exercise1;

import java.awt.Color;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class Road2 extends Actor{
	private int width;
	private int height;
	private int y;
	private int x;
	//5 horiz 7 vert 125 pixel spacing gap = (WH-(#ROADS*ROADWIDTH))/#ROADS-1)
	//YPOS = {gap + ROADWIDTH * N} + ROADWIDTH / 2
	public Road2(World world, int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		GreenfootImage image = new GreenfootImage(width, height);
		image.setColor(Color.DARK_GRAY);
		image.fill();
		setImage(image);
		world.addObject(this, x, y);
	}
	public void act(){
		
	}
}

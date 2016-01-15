package exercise1;

import java.awt.Color;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class TrafficWorld extends World {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 750;
	private static final int CELL_SIZE = 1;
	private static int horizontalspacing,verticalspacing, gap;
	private static int horizontalroadnum = 5, verticalroadnum = 7;
	private static Road[] HorizontalRoads = new Road[horizontalroadnum];
	private static Road2[] VerticalRoads = new Road2[verticalroadnum];
	public static int roadwidth = 50;
	private static double roadoffset;
	private static int startposx, startposy;
	public TrafficWorld(){
		super(WIDTH,HEIGHT,CELL_SIZE);
		GreenfootImage background = this.getBackground();
		background.setColor(Color.green.darker());
		background.fill();
		setPaintOrder(TrafficLight.class,Car.class, Road.class);
		
		gap = ((HEIGHT - (roadwidth * horizontalroadnum)) / (horizontalroadnum - 1));
		horizontalspacing = roadwidth + gap;
		gap = ((WIDTH - (roadwidth * verticalroadnum)) / (verticalroadnum - 1));
		verticalspacing = roadwidth + gap;
		
		roadoffset = (roadwidth*.25) ;
		

		placeIntersections();
		placeHorizontalRoads();
		placeVerticalRoads();
		
	}
	private void placeIntersections() {
		for (int i = 25; i < WIDTH; i+=verticalspacing+1){
			for (int j = 25; j < HEIGHT; j+=horizontalspacing) {
				Intersection intersection = new Intersection(roadwidth);
				this.addObject(intersection, i, j);
				intersection.addLightsVertical();
				intersection.addLightsHorizontal();
			}
		}
	}
	private void placeVerticalRoads() {
		for (int i = roadwidth / 2; i < WIDTH; i+=verticalspacing + 1){
			startposy = Greenfoot.getRandomNumber(HEIGHT);
			Car car = new Car(this, 90, i - roadoffset, startposy, 2);
			startposy = Greenfoot.getRandomNumber(HEIGHT);
			Car car2 = new Car(this, 270, i + roadoffset, startposy,2);
			Road2 road2 = new Road2(this,i, HEIGHT / 2 ,roadwidth,HEIGHT);
			this.addObject(road2,  0, 0);
		}
	}
	private void placeHorizontalRoads() {
		for (int i = roadwidth/2; i < 750; i+=horizontalspacing){
			startposx = Greenfoot.getRandomNumber(WIDTH);
			Car car = new Car(this, 180, startposx, roadoffset + i , 2);
			startposx = Greenfoot.getRandomNumber(WIDTH);
			Car car2 = new Car(this, 0, startposx, roadoffset + i - roadwidth / 2 , 2);
			Road road = new Road(this, WIDTH / 2 ,i,roadwidth,WIDTH);
			this.addObject(road,  0, 0);
		}
	}

}

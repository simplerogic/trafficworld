package exercise1;

import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class TrafficWorld extends World {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 750;
	private static final int CELL_SIZE = 1;
	Random rand = new Random();
	private static int horizontalspacing,verticalspacing, gap;
	private static int horizontalroadnum = 5, verticalroadnum = 7;
	private static Road[] HorizontalRoads = new Road[horizontalroadnum];
	private static Road2[] VerticalRoads = new Road2[verticalroadnum];
	public static int roadwidth = 50;
	private static double roadoffset;
	
	private static int xSpawn, ySpawn;
	private static int spawnTimer = 0, spawnRate = 50;
	private int[] horizSpawns = new int[5];
	private int[] horizSpawnsA = new int[5];
	private int[] vertSpawns = new int[7];
	private int[] vertSpawnsA = new int[7];
	
	
	public TrafficWorld(){
		super(WIDTH,HEIGHT,CELL_SIZE);
		GreenfootImage background = this.getBackground();
		background.setColor(Color.green.darker());
		background.fill();
		setPaintOrder(TrafficLight.class,Car.class, Road.class);
		
		gap = ((HEIGHT - (roadwidth * horizontalroadnum)) / (horizontalroadnum - 1));
		horizontalspacing = roadwidth + gap;
		System.out.println(horizontalspacing);
		gap = ((WIDTH - (roadwidth * verticalroadnum)) / (verticalroadnum - 1));
		verticalspacing = roadwidth + gap;
		System.out.println(verticalspacing);
		roadoffset = (roadwidth*.25);
		//Car test = new Car(this,180,50,200,1);

		
		placeIntersections();
		placeHorizontalRoads();
		placeVerticalRoads();
	}
	
	

	
	
	
	
	private void spawnCar(){
		xSpawn = rand.nextInt(WIDTH);
		ySpawn = rand.nextInt(HEIGHT);
		spawnTimer++;
		if (spawnTimer == spawnRate){
			if (rand.nextInt(2) == 0){
				if (rand.nextInt(2) == 0){
					Car car = new Car(this,180,xSpawn,horizSpawnsA[rand.nextInt(5)],2);
				} else if (rand.nextInt(2) == 1){
					Car car = new Car(this,0,xSpawn,horizSpawns[rand.nextInt(5)],2);
				}
			} else if (rand.nextInt(2) == 1) {
				if (rand.nextInt(2) == 0){
					Car car = new Car(this,270,vertSpawnsA[rand.nextInt(7)],ySpawn,2);
				} else if (rand.nextInt(2) == 1){
					Car car = new Car(this,90,vertSpawns[rand.nextInt(7)],ySpawn,2);
				}
			}
			spawnTimer = 0;
		}
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
		int c=0;
		for (int i = roadwidth / 2; i < WIDTH; i+=verticalspacing + 1){
			vertSpawns[c] = ((int) (i - roadoffset));
			vertSpawnsA[c] = (int) (i + roadoffset);
			Road2 road2 = new Road2(this,i, HEIGHT / 2 ,roadwidth,HEIGHT);
			this.addObject(road2,  0, 0);
			c++;
		}
	}
	private void placeHorizontalRoads() {
		int c=0;
		for (int i = roadwidth/2; i < 750; i+=horizontalspacing){
			horizSpawns[c] = ((int) (roadoffset + i));
			horizSpawnsA[c] = (int) (roadoffset + i - roadwidth / 2);
			Road road = new Road(this, WIDTH / 2 ,i,roadwidth,WIDTH);
			this.addObject(road,  0, 0);
			c++;
		}
	}
	public void act(){
		spawnCar();

	}
}

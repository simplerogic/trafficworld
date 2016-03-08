package exercise1;

import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

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
	public static int roadwidth = 50;
	private static double roadoffset;
	private int colorChance = 0;
	private static int xSpawn, ySpawn;
	private static int spawnTimer = 0, spawnRate = 50;
	private int[][] horizSpawns = new int[5][5];
	private int[][] vertSpawns = new int[7][7];
	private static int simTime = 0, simMaxTicks = 1000;
	private static int redSpawn = 0, yellowSpawn = 0, blueSpawn = 0, purpleSpawn = 0;
	private static int[] spawnNumbers = new int[4];
	private static List<Intersection> intersectionList = new ArrayList<Intersection>();
	private static int redSpawnRate, yellowSpawnRate, blueSpawnRate, purpleSpawnRate;

	
	
	public TrafficWorld(){
		super(WIDTH,HEIGHT,CELL_SIZE);
		SwingIORW swing = new SwingIORW();
		GreenfootImage background = this.getBackground();
		background.setColor(Color.green.darker());
		background.fill();
		setPaintOrder(TrafficLight.class,Car.class, Road.class);
		
		gap = ((HEIGHT - (roadwidth * horizontalroadnum)) / (horizontalroadnum - 1));
		horizontalspacing = roadwidth + gap;
		gap = ((WIDTH - (roadwidth * verticalroadnum)) / (verticalroadnum - 1));
		verticalspacing = roadwidth + gap;
		roadoffset = (roadwidth*.25);
		//Car test = new Car(this,180,50,200,1);
		PurpleCar pcar = new PurpleCar(this,180,900,13,2);


		
		placeIntersections();
		placeHorizontalRoads();
		placeVerticalRoads();
	}
	public static int getSimTime(){
		return simTime;
	}
	
	public static int getSpawnNumbers(String color){
		if (color.equals("red")){
			return redSpawn;
		} else if (color.equals("yellow")){
			return yellowSpawn;
		} else if (color.equals("blue")){
			return blueSpawn;
		} else if (color.equals("purple")){
			return purpleSpawn;
		} else {
			return -1;
		}
	}
	
	private void colorSwitch(){
		colorChance = rand.nextInt(4);
		switch(colorChance){
		case 0:
			spawnRed();
			redSpawn++;
			break;
		case 1:
			spawnBlue();
			blueSpawn++;
			break;
		case 2:
			spawnPurple();
			purpleSpawn++;
			break;
		case 3:
			spawnYellow();
			yellowSpawn++;

			break;
		}
	}

	private void spawnPurple() {
		if (rand.nextInt(2) == 0){
			if (rand.nextInt(2) == 0){
				PurpleCar pcar = new PurpleCar(this,180,xSpawn,horizSpawns[1][rand.nextInt(5)],2);
			} else if (rand.nextInt(2) == 1){
				PurpleCar pcar = new PurpleCar(this,0,xSpawn,horizSpawns[0][rand.nextInt(5)],2);
			}
		} else if (rand.nextInt(2) == 1) {
			if (rand.nextInt(2) == 0){
				PurpleCar pcar = new PurpleCar(this,270,vertSpawns[1][rand.nextInt(7)],ySpawn,2);
			} else if (rand.nextInt(2) == 1){
				PurpleCar pcar = new PurpleCar(this,90,vertSpawns[0][rand.nextInt(7)],ySpawn,2);
			}
		}
	}
	private void spawnYellow() {
		if (rand.nextInt(2) == 0){
			if (rand.nextInt(2) == 0){
				YellowCar ycar = new YellowCar(this,180,xSpawn,horizSpawns[1][rand.nextInt(5)],2);
			} else if (rand.nextInt(2) == 1){
				YellowCar ycar = new YellowCar(this,0,xSpawn,horizSpawns[0][rand.nextInt(5)],2);
			}
		} else if (rand.nextInt(2) == 1) {
			if (rand.nextInt(2) == 0){
				YellowCar ycar = new YellowCar(this,270,vertSpawns[1][rand.nextInt(7)],ySpawn,2);
			} else if (rand.nextInt(2) == 1){
				YellowCar ycar = new YellowCar(this,90,vertSpawns[0][rand.nextInt(7)],ySpawn,2);
			}
		}
	}
	private void spawnRed() {
		if (rand.nextInt(2) == 0){
			if (rand.nextInt(2) == 0){
				RedCar rcar = new RedCar(this,180,xSpawn,horizSpawns[1][rand.nextInt(5)],2);
			} else if (rand.nextInt(2) == 1){
				RedCar rcar = new RedCar(this,0,xSpawn,horizSpawns[0][rand.nextInt(5)],2);
			}
		} else if (rand.nextInt(2) == 1) {
			if (rand.nextInt(2) == 0){
				RedCar rcar = new RedCar(this,270,vertSpawns[1][rand.nextInt(7)],ySpawn,2);
			} else if (rand.nextInt(2) == 1){
				RedCar rcar = new RedCar(this,90,vertSpawns[0][rand.nextInt(7)],ySpawn,2);
			}
		}
	}
	private void spawnBlue() {
		if (rand.nextInt(2) == 0){
			if (rand.nextInt(2) == 0){
				BlueCar bcar = new BlueCar(this,180,xSpawn,horizSpawns[1][rand.nextInt(5)],2);
			} else if (rand.nextInt(2) == 1){
				BlueCar bcar = new BlueCar(this,0,xSpawn,horizSpawns[0][rand.nextInt(5)],2);
			}
		} else if (rand.nextInt(2) == 1) {
			if (rand.nextInt(2) == 0){
				BlueCar bcar = new BlueCar(this,270,vertSpawns[1][rand.nextInt(7)],ySpawn,2);
			} else if (rand.nextInt(2) == 1){
				BlueCar bcar = new BlueCar(this,90,vertSpawns[0][rand.nextInt(7)],ySpawn,2);
			}
		}
	}
	public static int getTotalSpawns(){
		return redSpawn + purpleSpawn + yellowSpawn + purpleSpawn;
	}
	private void spawnCar(){
		xSpawn = rand.nextInt(WIDTH);
		ySpawn = rand.nextInt(HEIGHT);
		spawnTimer++;
		if (spawnTimer == spawnRate){
			colorSwitch();
			spawnTimer = 0;
		}
	}
	
	private void placeIntersections() {
		for (int i = 25; i < WIDTH; i+=verticalspacing+1){
			for (int j = 25; j < HEIGHT; j+=horizontalspacing) {
				Intersection intersection = new Intersection(roadwidth);
				intersectionList.add(intersection);
				this.addObject(intersection, i, j);
				intersection.addLightsVertical();
				intersection.addLightsHorizontal();
			}
		}
	}
	private void placeVerticalRoads() {
		int c=0;
		for (int i = roadwidth / 2; i < WIDTH; i+=verticalspacing + 1){
			vertSpawns[0][c] = ((int) (i - roadoffset));
			vertSpawns[1][c] = (int) (i + roadoffset);
			Road2 road2 = new Road2(this,i, HEIGHT / 2 ,roadwidth,HEIGHT);
			this.addObject(road2,  0, 0);
			c++;
		}
	}
	private void placeHorizontalRoads() {
		int c=0;
		for (int i = roadwidth/2; i < 750; i+=horizontalspacing){
			horizSpawns[0][c] = ((int) (roadoffset + i));
			horizSpawns[1][c] = (int) (roadoffset + i - roadwidth / 2);
			Road road = new Road(this, WIDTH / 2 ,i,roadwidth,WIDTH);
			this.addObject(road,  0, 0);
			c++;
		}
	}
	
	public static void setSimTime(Integer newSpawnTicks) {
		simMaxTicks = newSpawnTicks;
	}
	public static void setSpawnRate(Integer newSpawnRate) {
		spawnRate = newSpawnRate;
	}
	public static void setRedSpawnRate(int newRedSpawnRate) {
		redSpawnRate = newRedSpawnRate;
	}
	public static void setYellowSpawnRate(int newYellowSpawnRate) {
		yellowSpawnRate = newYellowSpawnRate;
	}
	public static void setBlueSpawnRate(int newBlueSpawnRate) {
		blueSpawnRate = newBlueSpawnRate;
	}
	public static void setPurpleSpawnRate(int newPurpleSpawnRate) {
		purpleSpawnRate = newPurpleSpawnRate;
	}
	
	public void act(){
		spawnCar();
		simTime++;
		if (simTime == simMaxTicks) {
			  Greenfoot.stop();
			  StatBoard sb = new StatBoard(intersectionList);
		}
		
	}
}

package exercise1;

import greenfoot.GreenfootImage;

public enum TrafficLightColor {	
	GREEN{
		@Override
		public void drawLight(GreenfootImage image){
			image = new GreenfootImage("images\\trafficLightGreen");
		}
	},
	YELLOW{
		@Override
		public void drawLight(GreenfootImage image){
			image = new GreenfootImage("images\\trafficLightYellow");
		}
	},
	RED{
		@Override
		public void drawLight(GreenfootImage image){
			image = new GreenfootImage("images\\trafficLightRed");
		}
	};

	public void drawLight(GreenfootImage image) {
		// TODO Auto-generated method stub
		
	}
	

	
}

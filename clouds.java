package contents;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import contents.gamescreen;
import contents.resource;

public class clouds {
	
	private BufferedImage cloudpic;
	private List<cloudss> cloud;
	
	public clouds(gamescreen gamee) {
		cloudpic = resource.getresourceImage("images/cloud.png"); // picture of cloud
		cloud = new ArrayList<cloudss>();
		
		//positions of clouds
		cloudss cloud1 = new cloudss();
		cloud1.posX = 100;
		cloud1.posY = 50;
		cloud.add(cloud1);
		
		cloud1 = new cloudss();
		cloud1.posX = 250;
		cloud1.posY = 30;
		cloud.add(cloud1);
		
		cloud1 = new cloudss();
		cloud1.posX = 400;
		cloud1.posY = 90;
		cloud.add(cloud1);
		
		cloud1 = new cloudss();
		cloud1.posX = 500;
		cloud1.posY = 60;
		cloud.add(cloud1);
		
		cloud1 = new cloudss();
		cloud1.posX = 625;
		cloud1.posY = 80;
		cloud.add(cloud1);
	}
	
	public void update() {
		for(cloudss cld : cloud) {
			cld.posX--; //prints clouds to the left
		}
		
		cloudss firstcloud = cloud.get(0);
		if(firstcloud.posX + cloudpic.getWidth() < 0) {
			firstcloud.posX = 625; //width of screen
			//creates a loop for printing clouds
			cloud.remove(firstcloud); //removes first cloud after stepping out of border
			cloud.add(firstcloud); //adds cloud after the first set of clouds
		}
	}
	
	public void draw(Graphics g) {
		for(cloudss cld : cloud) {
			g.drawImage(cloudpic, (int) cld.posX, (int) cld.posY, null); //draws pic of cloud
		}
	}
	
	private class cloudss {
		float posX; //position on x-axis
		float posY; //position on y-axis
	}

}
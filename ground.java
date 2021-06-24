package contents;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import contents.gamescreen;

import static contents.gamescreen.yground;

import contents.resource;

public class ground {
	
	private List<landimage> imagelist;
	private BufferedImage landpic1, landpic2, landpic3;
	private Random rnd;
	
	public ground(gamescreen gamee) {
		rnd = new Random();
		landpic1 = resource.getresourceImage("images/grass1.png"); //picture of land
		landpic2 = resource.getresourceImage("images/grass2.png"); //picture of land
		landpic3 = resource.getresourceImage("images/grass3.png"); //picture of land
		imagelist = new ArrayList<landimage>();
		for(int i = 0; i < 6; i++) {
			landimage landpic = new landimage();
			landpic.posX = (int)(i * landpic1.getWidth());
			landpic.image = getlandimage(); //gets picture of land
			imagelist.add(landpic); //adds picture of land
		}
	}
	
	public void update() {
		for(landimage landpic : imagelist) {
			landpic.posX -= 3; //prints land to the left,, number indicates speed
		}
		landimage first = imagelist.get(0);
		if(first.posX + landpic1.getWidth() < 0) { //if out of boundary
			//it adds another image after last image
			first.posX = imagelist.get(imagelist.size() - 1).posX + landpic1.getWidth();
			imagelist.add(first);
			imagelist.remove(0);
		}
	}
	
	public void draw(Graphics g) {
		for(landimage landpic:imagelist) {
			//draws the land
			g.drawImage(landpic.image, landpic.posX, (int) yground, null);
		}
	}
	
	private BufferedImage getlandimage() {
		int r = rnd.nextInt(5); //gets a random number between 0 and 4
		switch(r) {
		case 0 : return landpic3; //prints this if random number is 0
		case 1 : return landpic2; //prints this if random number is 1
		default : return landpic1; //prints this if number is not 0 or 1
		}
	}
	
	private class landimage {
		int posX; //x position of land
		BufferedImage image;
	}

}

package contents;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import contents.gamescreen;
import contents.resource;

public class obstaclebonk {
	private List<bonk> bonks; //list of enemies
	private Random rnd;
	private BufferedImage obstacle1, obstacle2;
	private character crctr;
	private gamescreen screen;
	
	public obstaclebonk(character crctr, gamescreen screen) {
		this.screen = screen;
		this.crctr = crctr;
		bonks = new ArrayList<bonk>(); //list of enemies
		obstacle1 = resource.getresourceImage("images/enemy1.png"); //obstacle pic
		obstacle2 = resource.getresourceImage("images/enemy2.png"); //another obstacle pic
		rnd = new Random();
		
		bonks.add(getRandomobstacle()); //adds obstacle to enemy list
	}
	
	public void update() {
		for(bonk b : bonks) {
			b.update();
			if(b.over() && !b.scoree()) { //if character jumps over obstacle
				screen.addscore(10); //it adds score
				b.setscoree(true);
			}
			if(b.getBound().intersects(crctr.getBound())) { //if character collides with enemy
				crctr.setnotdead(false); //character is dead
			}
		}
		bonk firstbonk = bonks.get(0);
		if(firstbonk.outofbound()) { //if obstacle is out of the screen
			bonks.remove(firstbonk);
			bonks.add(getRandomobstacle()); //it prints a new random obstacle
		}
	}
	
	public void draw(Graphics g) {
		for(bonk b : bonks) {
			b.draw(g); //draws obstacles
		}
	}
	
	private obstacle getRandomobstacle() { //to get random obstacles
		obstacle obstacles;
		obstacles = new obstacle(crctr);
		obstacles.setX(625); //position of obstacle
		if(rnd.nextBoolean()) {
			obstacles.setImage(obstacle1);
		}
		else {
			obstacles.setImage(obstacle2);
		}
		return obstacles;
	}

}
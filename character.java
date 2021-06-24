package contents;

import static contents.gamescreen.gravity;
import static contents.gamescreen.yground;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import contents.animation;
import contents.resource;

public class character {
	private float x = 0;
	private float y = 0;
	private float yspeed = 0;
	private animation running; //images
	private Rectangle rect; //rectangle
	private Boolean notdead = true; //boolean
	
	public character() {
		running = new animation(200); //character running speed in milliseconds
		//prints alternately to make character look like it's running
		running.addFrame(resource.getresourceImage("images/virus1.png")); //pic of character
		running.addFrame(resource.getresourceImage("images/virus2.png")); //pic of character
		rect = new Rectangle(); //rectangle
	}
	
	public void update() {
		running.update();
		if(y >= yground - running.getFrame().getHeight()) { //this is what makes it stay on the ground
			yspeed = 0;
			y = yground - running.getFrame().getHeight();
		}
		else {
			yspeed+=gravity; //allows it to fall back down
			y+=yspeed; //allows it to jump
		}
		
		rect.x = (int) x; //x coordinate
		rect.y = (int) y; //y coordinate
		rect.width = running.getFrame().getWidth(); //rect width is same as image width
		rect.height = running.getFrame().getHeight(); //rect height is same as image height
	}
	
	public Rectangle getBound() {
		return rect;
	}
	
	public void draw(Graphics g) {
		//draws rectangle where image is
		g.drawRect((int) x, (int) y, running.getFrame().getWidth(), running.getFrame().getHeight());
		g.drawImage(running.getFrame(), (int) x, (int) y, null); //draws image
	}
	
	public void jump() {
		if(yspeed == 0) { //it only jumps if character position = ground (won't jump if character is on air)
			//it prints upward if key is pressed
			yspeed -= 4; //height of jump
			y += yspeed;
		}
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float ySpeed() {
		return yspeed;
	}
	public void ySpeed(float yspeed) {
		this.yspeed = yspeed;
	}
	
	public void setnotdead(boolean notded) {
		notdead = notded;
	}
	public boolean getnotdead() {
		return notdead;
	}
	

}

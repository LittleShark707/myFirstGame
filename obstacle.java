package contents;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import contents.resource;

public class obstacle extends bonk { //extension of the bonk class(enemy class)
	
	private BufferedImage image; //image
	private int posX, posY; //positions
	private Rectangle rect; //rectangle
	private character crctr; //character
	private boolean scoree = false; //the score
	
	public obstacle(character crctr) {
		this.crctr = crctr;
		//image = resource.getresourceImage("images/enemy1.png");
		posX = 600; //position on x-axis
		posY = 155; //position on y-axis
		rect = new Rectangle(); //rectangle
	}
	
	public void update() {
		posX -= 3; //goes to the left,, the number indicates the speed
		rect.x = posX; //position of rectangle on x
		rect.y = posY; //position of rectangle on y
		rect.width = image.getWidth(); //width of image
		rect.height = image.getHeight(); //height of image
	}
	
	@Override
	public Rectangle getBound() {
		return rect;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawRect(posX, posY, image.getWidth(), image.getHeight()); //draws rectangle
		g.drawImage(image, posX, posY, null); //draws image
	}
	
	public void setX(int x) {
		posX = x;
	}
	public void setY(int y) {
		posY = y;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	@Override
	public boolean outofbound() {
		return(posX + image.getWidth() < 0);
	}
	
	@Override
	public boolean over() {
		return(crctr.getX() > posX);
	}
	
	@Override
	public boolean scoree() {
		return scoree;
	}
	@Override
	public void setscoree(boolean scoree) {
		this.scoree = scoree;
	}

}

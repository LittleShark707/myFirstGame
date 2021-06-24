package contents;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class bonk { //allows u to declare a method
	public abstract Rectangle getBound(); //get rectangle
	public abstract void draw(Graphics g); //lets u draw graphics
	public abstract void update(); //method for game state
	public abstract boolean outofbound(); //boolean for if image is out of bound
	public abstract boolean over(); //boolean for if player jumps over obstacle
	public abstract boolean scoree(); //boolean for score
	public abstract void setscoree(boolean scoree); //sets the score

}

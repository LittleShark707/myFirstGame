package contents;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class game extends JFrame {
	
	private gamescreen screen;
	
	public game() {
		super("Kinda like the offline Chrome game"); //prints title
		setSize(625, 285); //size of frame
		setLocation(300, 150); //location of the gui on screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //to close the program
		screen = new gamescreen(); //makes up the game screen
		add(screen); //adds the screen with all the pictures and stuff
		addKeyListener(screen); //detects if a key is pressed
	}
	
	public void start() {
		screen.start(); //starts the screen
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		game window = new game();
		window.setVisible(true); //allows window to be displayed
		window.start(); //starts the window
	}
	
}


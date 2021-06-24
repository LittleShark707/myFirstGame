package contents;

import static contents.gamescreen.yground;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;

import contents.obstacle;
import contents.character;
import contents.clouds;
import contents.obstaclebonk;
import contents.ground;
import contents.resource;

public class gamescreen extends JPanel implements Runnable, KeyListener {
	public static final int rest = 0; //state before pressing key
	public static final int play = 1; //playing state
	public static final int end = 2; //game over
	public static final float gravity = 0.1f; //gravity || lets character jump higher and fall slower
	public static final float yground = 210; //sets ground boundary(by pixel)
	private character crctr; //the character
	private Thread thread; //execution of program
	private ground landd; //the land
	private clouds cloud; //the clouds
	private obstaclebonk nmebonk; //the obstacles
	private int score; //the score
	private int state = rest; //the state
	private BufferedImage gameover; //game over
	
	public gamescreen() {
		setBackground(Color.WHITE); //makes the background color white
		thread = new Thread(this); //thread
		crctr = new character(); //character
		crctr.setX(50); //character position on x-axis
		crctr.setY(161); //character position on y-axis
		landd = new ground(this); //land
		cloud = new clouds(this); //clouds
		nmebonk = new obstaclebonk(crctr, this); //obstacles
		gameover = resource.getresourceImage("images/game_over.png"); //game over image
	}
	
	public void start() {
		thread.start(); //causes thread to begin execution
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				update();
				repaint(); //will keep drawing the rectangle
				Thread.sleep(10); //measured in milliseconds,, bigger number = runs slower
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		switch(state) {
		case play:
			crctr.update();
			landd.update();
			cloud.update();
			nmebonk.update();
			if(!crctr.getnotdead()) { //if character dies
				state = end; //game over
				try {
					log(); //if game over, it prints the game log(score,date,time) in notepad
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		}
		
	}
	
	public void addscore(int score) {
		this.score += score; //adds points to current score
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g); //will keep drawing the background to make object look mobile
		
		switch(state) {
		case rest: //prints only the character if game has not started
			crctr.draw(g); //draws the character
			break;
		case play: //objects below are printed once a key is pressed
			//it's in this specific order so that the character is displayed at the front
			cloud.draw(g); //draws the clouds
			landd.draw(g); //draws the land
			nmebonk.draw(g); //draws obstacles
			crctr.draw(g); //draws the main character
			g.drawString("score: " + String.valueOf(score), 500, 20); //prints the score
			break;
		case end:
			cloud.draw(g);
			landd.draw(g);
			nmebonk.draw(g);
			crctr.draw(g);
			g.drawString("score: " + String.valueOf(score), 500, 20);
			g.drawImage(gameover, 195, 75, null); //draws game over image
			break;
		}
		
	}
	
	private void log() throws IOException{
		// TODO Auto-generated method stub throws IOException {
		Date d = new Date(); //sets variable for date
		d.toString(); //converts to string
		SimpleDateFormat f = new SimpleDateFormat("HH:mm - E MM/dd/yyyy"); //format
		
		String directory = "C:\\Users\\user\\Desktop\\"; //location of file
		String fname = "filename.txt"; //the file name
		File file = new File(directory + fname); //file and directory
		FileWriter fw = new FileWriter(file, true); //writes characters
		PrintWriter pw = new PrintWriter(fw); //prints characters to file
		
		pw.println("===================================="); //divider
		pw.println("player bonked an obstacle"); //if player hits obstacle
		pw.println("score: " + String.valueOf(score)); //prints score
		pw.println("time played: " + f.format(d)); //prints date and time
		pw.close();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//crctr.jump();
		System.out.println("key pressed"); //prints if key is pressed
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("key released"); //prints if key is released
		
		switch(e.getKeyCode()) { //detects key
		case KeyEvent.VK_SPACE: //if space bar is pressed
			if(state == rest) { //when game is at rest and space bar is pressed
				state = play; //the game will play
			}
			else if(state == play) { //while the game is running
				crctr.jump(); //jump method is implemented when key is pressed
			}
			else if(state == end) { //if end state
				state = end; //game ends,, close and run again to restart game
			}
			break; //break
		}
	}

}

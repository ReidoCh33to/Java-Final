package fp1_Package;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.UnsupportedAudioFileException;


//import sun.audio.*;

public class MyCanvas extends Canvas implements KeyListener,MouseListener {
	
	Goodguy link = new Goodguy(10,10,50,50,"files/Starship.png");
	LinkedList badguys = new LinkedList();
	LinkedList knives = new LinkedList();
	LinkedList bosses = new LinkedList();
	
	Integer kills = 0;
	int wave = 1;
	int total_kills = 20;
	int bgspeed = 10;
	int direction = 1;
	
	boolean status = true;
	
	End e = new End("files/Win.png");
	
	static Font font = new Font("SanSerif",Font.BOLD, 24 );
	
    AudioInputStream stream;
    AudioFormat format;
    DataLine.Info info;
    Clip clip;
	
	public MyCanvas() {
		this.setSize(1000,800); // set same size as MyScreen
	
		this.addKeyListener(this); // add the listener to your canvas
		
				
		Random rand = new Random();
		String[] myimgs = {"files/Badguy.png","files/Badguy.png", "files/Badguy.png", "files/Badguy.png", "files/Badguy.png",};
		int count = 0;
		status = true;
		for(int i = 0; i<20; i++) {
			Badguy bg = new Badguy(rand.nextInt(this.getWidth()),rand.nextInt(this.getHeight()) ,50,50,myimgs[rand.nextInt(5)]);
			badguys.add(bg);
		}

		

		
		TimerTask repeatedTask = new TimerTask() {
	        public void run() {
	        	for(int i = 0; i < badguys.size(); i++) {// draw bad guys
	    			Badguy bg = (Badguy) badguys.get(i);
	    			bg.setxCoord(bg.getxCoord() - bgspeed);
	    			if (bg.getxCoord() < -70) {
	    			status = false;
	    			}
	        	}
	            repaint();
	        }
	    };
	    Timer timer = new Timer("Timer");
	     
	    long delay  = 1000L;
	    long period = 1000L;
	    timer.scheduleAtFixedRate(repeatedTask, delay, period);	
		
		this.setBackground(Color.BLACK);
	}
	
	
	@Override
	public void paint(Graphics g) {
		
		if (status == true && kills == 20 && wave == 1) {
				wave = wave + 1;
				kills = 0;
				total_kills = total_kills + 10;
				Random rand = new Random();
				String[] myimgs = {"files/Badguy.png","files/Badguy2.png", "files/Badguy2.png", "files/Boss.png", "files/Badguy.png",};
				for(int i = 0; i<30; i++) {
					Badguy bg = new Badguy(rand.nextInt(this.getWidth()),rand.nextInt(this.getHeight()) ,50,50,myimgs[rand.nextInt(5)]);
					badguys.add(bg);
				}
				for(int i = 0; i < badguys.size(); i++) {// draw bad guys
					Badguy bg = (Badguy) badguys.get(i);
					g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight(), this); 
					Rectangle ggr = new Rectangle(link.getxCoord(),link.getyCoord(),link.getWidth(),link.getHeight());
					Rectangle r = new Rectangle(bg.getxCoord(),bg.getyCoord(),bg.getWidth(),bg.getHeight());
					if (ggr.intersects(r)) {
						badguys.remove(i);
						kills++;
					}
					
					
					
					for(int j = 0; j < knives.size(); j++) {
						int hits = 0;
						Projectile k = (Projectile) knives.get(j);
						if (k.getxCoord() > this.getWidth()) { knives.remove(k); }
						k.setxCoord(k.getxCoord() + direction);
						g.drawImage(k.getImg(), k.getxCoord(), k.getyCoord(), k.getWidth(), k.getHeight(), this); 
						
						Rectangle kr = new Rectangle(k.getxCoord(),k.getyCoord(),k.getWidth(),k.getHeight());
						if (kr.intersects(r)) {
							hits = hits++;
							knives.remove(j);
							badguys.remove(i);
							kills++;
							
						}
					}
					repaint();
				}
		}
		if (status == true && kills == 30 && wave == 2) {
			wave = wave + 1;
			kills = 0;
			total_kills = total_kills + 10;
			bgspeed = 11;
			Random rand = new Random();
			String[] myimgs = {"files/Badguy.png","files/Badguy2.png", "files/DeathStar.png", "files/Badguy2.png", "files/Badguy.png",};
			for(int i = 0; i<40; i++) {
				Badguy bg = new Badguy(rand.nextInt(this.getWidth()),rand.nextInt(this.getHeight()) ,50,50,myimgs[rand.nextInt(5)]);
				badguys.add(bg);
			}
			for(int i = 0; i < badguys.size(); i++) {// draw bad guys
				Badguy bg = (Badguy) badguys.get(i);
				g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight(), this); 
				Rectangle ggr = new Rectangle(link.getxCoord(),link.getyCoord(),link.getWidth(),link.getHeight());
				Rectangle r = new Rectangle(bg.getxCoord(),bg.getyCoord(),bg.getWidth(),bg.getHeight());
				if (ggr.intersects(r)) {
					badguys.remove(i);
					kills++;
				}
				
				
				for(int j = 0; j < knives.size(); j++) {
					int hits = 0;
					Projectile k = (Projectile) knives.get(j);
					if (k.getxCoord() > this.getWidth()) { knives.remove(k); }
					k.setxCoord(k.getxCoord() + direction);
					g.drawImage(k.getImg(), k.getxCoord(), k.getyCoord(), k.getWidth(), k.getHeight(), this); 
					
					Rectangle kr = new Rectangle(k.getxCoord(),k.getyCoord(),k.getWidth(),k.getHeight());
					if (kr.intersects(r)) {
						hits = hits++;
						knives.remove(j);
						badguys.remove(i);
						kills++;
						
					}
				}
				repaint();
			}
	}
		if (status == true && kills == 40 && wave == 3) {
			wave = wave + 1;
			kills = 0;
			total_kills = total_kills + 10;
			bgspeed = 15;
			Random rand = new Random();
			String[] myimgs = {"Badguy.png","files/Badguy2.png", "files/DeathStar.png", "files/Boss.png", "files/Badguy.png",};
			for(int i = 0; i<50; i++) {
				Badguy bg = new Badguy(rand.nextInt(this.getWidth()),rand.nextInt(this.getHeight()) ,50,50,myimgs[rand.nextInt(5)]);
				badguys.add(bg);
			}
			for(int i = 0; i < badguys.size(); i++) {// draw bad guys
				Badguy bg = (Badguy) badguys.get(i);
				g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight(), this); 
				Rectangle ggr = new Rectangle(link.getxCoord(),link.getyCoord(),link.getWidth(),link.getHeight());
				Rectangle r = new Rectangle(bg.getxCoord(),bg.getyCoord(),bg.getWidth(),bg.getHeight());
				if (ggr.intersects(r)) {
					badguys.remove(i);
					kills++;
				}
				
				
				for(int j = 0; j < knives.size(); j++) {
					int hits = 0;
					Projectile k = (Projectile) knives.get(j);
					if (k.getxCoord() > this.getWidth()) { knives.remove(k); }
					k.setxCoord(k.getxCoord() + direction);
					g.drawImage(k.getImg(), k.getxCoord(), k.getyCoord(), k.getWidth(), k.getHeight(), this); 
					
					Rectangle kr = new Rectangle(k.getxCoord(),k.getyCoord(),k.getWidth(),k.getHeight());
					if (kr.intersects(r)) {
						hits = hits++;
						knives.remove(j);
						badguys.remove(i);
						kills++;
						
					}
				}
				repaint();
			}
	}
		if (status == true && kills == 50 && wave == 4) {
			wave = wave + 1;
			kills = 0;
			total_kills = total_kills + 100;
			bgspeed = 7;
			g.drawString("Final Round",420,75);
			Random rand = new Random();
			String[] myimgs = {"Badguy.png","files/Badguy2.png", "files/DeathStar.png", "files/Boss.png", "files/BG.png",};
			for(int i = 0; i<150; i++) {
				Badguy bg = new Badguy(rand.nextInt(this.getWidth()),rand.nextInt(this.getHeight()) ,50,50,myimgs[rand.nextInt(5)]);
				badguys.add(bg);
			}
			for(int i = 0; i < badguys.size(); i++) {// draw bad guys
				Badguy bg = (Badguy) badguys.get(i);
				g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight(), this); 
				Rectangle ggr = new Rectangle(link.getxCoord(),link.getyCoord(),link.getWidth(),link.getHeight());
				Rectangle r = new Rectangle(bg.getxCoord(),bg.getyCoord(),bg.getWidth(),bg.getHeight());
				if (ggr.intersects(r)) {
					badguys.remove(i);
					kills++;
				}
				
				
				for(int j = 0; j < knives.size(); j++) {
					int hits = 0;
					Projectile k = (Projectile) knives.get(j);
					if (k.getxCoord() > this.getWidth()) { knives.remove(k); }
					k.setxCoord(k.getxCoord() + direction);
					g.drawImage(k.getImg(), k.getxCoord(), k.getyCoord(), k.getWidth(), k.getHeight(), this); 
					
					Rectangle kr = new Rectangle(k.getxCoord(),k.getyCoord(),k.getWidth(),k.getHeight());
					if (kr.intersects(r)) {
						hits = hits++;
						knives.remove(j);
						badguys.remove(i);
						kills++;
						
					}
					
				}
				repaint();
			}
	}
		if (status == true && kills == 150 && wave == 5) {
			g.drawImage(e.getImg(), 0, 0, this);
			System.out.println("you win!");
		} 
 	
	
			
		else if(status == false) {
			System.out.println("You lose!");
			System.out.println("Your highscore is " + wave + " Waves!" );
			System.exit(0);
		}
		
		
		g.drawImage(link.getImg(), link.getxCoord(), link.getyCoord(), link.getWidth(), link.getHeight(), this); // draw good guy
		
				
		for(int i = 0; i < badguys.size(); i++) {// draw bad guys
			Badguy bg = (Badguy) badguys.get(i);
			g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight(), this); 
			Rectangle ggr = new Rectangle(link.getxCoord(),link.getyCoord(),link.getWidth(),link.getHeight());
			Rectangle r = new Rectangle(bg.getxCoord(),bg.getyCoord(),bg.getWidth(),bg.getHeight());
			if (ggr.intersects(r)) {
				badguys.remove(i);
				kills++;
			}
			
			
			for(int j = 0; j < knives.size(); j++) {
				int hits = 0;
				Projectile k = (Projectile) knives.get(j);
				if (k.getxCoord() > this.getWidth()) { knives.remove(k); }
				k.setxCoord(k.getxCoord() + direction);
				g.drawImage(k.getImg(), k.getxCoord(), k.getyCoord(), k.getWidth(), k.getHeight(), this); 
				
				Rectangle kr = new Rectangle(k.getxCoord(),k.getyCoord(),k.getWidth(),k.getHeight());
				if (kr.intersects(r)) {
					hits = hits++;
					knives.remove(j);
					badguys.remove(i);
					kills++;
					
				}
			}
			repaint();
		}
		
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("Wave "+ wave + ": You Have Killed "+ kills + " out of  " +  total_kills + " spaceships", 100, 25);
		g.drawString("Alive = " + status,725, 725);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		this.getParent().getParent().setBackground(Color.BLACK);
		
		if (e.getKeyCode() == 32) {
			Projectile knife = new Projectile(link.getxCoord(),link.getyCoord(),30,30,"files/bullet.jpg",link.facing);
			knives.add(knife);
		}
		if (e.getKeyCode() == 39) {
			direction = 1;
		}
		if (e.getKeyCode() == 37) {
			direction = -1;
		}
		
		link.moveIt(e.getKeyCode(),this.getWidth(),this.getHeight());
		repaint();
	
	}

		
	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println(e);
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getX());
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		

		
	}

}
package fp1_Package;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Goodguy {

	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	private Image img;
	public String facing;
	
	public Goodguy() {
		setxCoord(10);
		setyCoord(10);
		setWidth(30);
		setHeight(30);
		setImg("files/Starship.png");
		facing = "right";
	}
	
	public Goodguy(int x, int y, int w, int h, String imgpath) {
		setxCoord(x);
		setyCoord(y);
		setWidth(w);
		setHeight(h);
		setImg(imgpath);
	}
	
	public void moveIt(int direction, int w, int h) {
		int speed = 20;
		int x = getxCoord();
		int y = getyCoord();
		
		if (direction == 39) {
			x = x + speed;
			if (x > w) { x = x - speed * 3; } // check to see if user has moved off game area
			setxCoord(x);
			setImg("files/Starship.png");
			facing = "right";
		} else if (direction == 37) {
			if (x < 0) { x = x + speed * 3; } // check to see if user has moved off game area
			x = x - speed;
			setxCoord(x);
			setImg("files/LStarship.png");
			facing = "left";
		} else if (direction == 38) {
			if (y < 0) { y = y + speed * 3; } // check to see if user has moved off game area
			y = y - speed;
			setyCoord(y);
	
		} else if (direction == 40) {
			if (y > h - 10) { y = y - speed * 3; } // check to see if user has moved off game area
			y = y + speed;
			setyCoord(y);
		}
	}
	
	public void setImg(String imgpath) {
		this.img = Toolkit.getDefaultToolkit().getImage(imgpath);
	}
	
	public Image getImg() {
		return img;
	}

	public int getxCoord() {
		return this.xCoord;
	}
	
	public int getyCoord() {
		return this.yCoord;
	}

	public void setxCoord(int x) {
		//if (x > 600) {System.out.println("too big"); return; };
		this.xCoord = x;
	}
	
	public void setyCoord(int y) {
		this.yCoord = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int w) {
		this.width = w;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int h) {
		this.height = h;
	}

}

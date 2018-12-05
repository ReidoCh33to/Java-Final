package fp1_Package;

import java.awt.Image;
import java.awt.Toolkit;

public class End {
	
	private Image img;
	
	public End(String imgpath) {
		setImg("files/Win.jpg");
	}
	
	public void setImg(String imgpath) {
		this.img = Toolkit.getDefaultToolkit().getImage(imgpath);
	}
	
	public Image getImg() {
		return img;
	}

}

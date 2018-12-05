package fp1_Package;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MyScreen extends JFrame {
	
	public MyScreen() {
		// this is current instance, setters are mutator methods which change properties
		this.setSize(1000,800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}

	// main method is required to run the program
	public static void main(String[] args) {
		// instantiate an individual instance of MyScreen by calling on constructor
		MyScreen screen = new MyScreen();
		MyCanvas canvas = new MyCanvas();
		screen.getContentPane().add(canvas);
		
	}

}
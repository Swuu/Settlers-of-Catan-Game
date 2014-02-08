import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JTextArea;

// Makes a house, with the middle of the square at x , y
public class House extends WindowController {
	
	/* testing code
	public void begin() {
		MakeHouse (100 , 100 , 3 , canvas);
	}
	*/

	public static void MakeHouse (double x , double y , int playerNum , DrawingCanvas canvas) {
		FilledRect base = new FilledRect (x-12.5 , y-12.5, 25 , 25 , canvas);
		FilledArc roof = new FilledArc (x-19 , y-45 , 38 , 38 , 225 , 90 , canvas);
		switch (playerNum) {
			case 1:
				base.setColor(Color.RED);
				roof.setColor(Color.RED);
				break;
			case 2:
				base.setColor(Color.BLUE);
				roof.setColor(Color.BLUE);
				break;
			case 3:
				base.setColor(Color.GREEN);
				roof.setColor(Color.GREEN);
				break;
			case 4:
				base.setColor(Color.BLACK);
				roof.setColor(Color.BLACK);
				break;
		}
	}

	/* testing code
	public static void main (String [] args) {
		new House().startController(200, 200);
	}
	*/
} 

import objectdraw.*;
import java.awt.*;
import java.awt.Polygon;
import java.awt.Graphics;


public class Hexagon extends WindowController{
	
    Polygon polygon;
	//private DrawingCanvas canvas;
	
	public static final int SEVEN = 7;
    public static int[] xs;
    public static int[] ys;
    public int hexValue;
	
	//constuctor that will be called
	public Hexagon(int a, int b, int type, DrawingCanvas canvas){
        
        hexValue = type;
		//define the length of side of polygon
		double x = Math.sqrt(3)*32;
		//start points
		int a1 = a; //a is x axis
		int b1 = b; //b is y axis
		
		 //connect the dot points
		int a2 = (int)(a +((Math.sqrt(3.0)/2.0)*x));
		int b2 = (int)(b-(x/2.0));
		int a3 = (int)(a + (Math.sqrt(3.0)*x));
		int b3 = b1;
		int a4 = (int)(a + (Math.sqrt(3.0)*x));
		int b4 = (int)(b + x);
		int a5 = (int)(a + ((Math.sqrt(3.0)/2.0)*x));
		int b5 = (int)(b4 + (x/2.0));
		int a6 = a1;
		int b6 = b4;
		
		//end and start points
		int a7 = a1;
		int b7 = b1;
		
		//puts points in array to send to constructor
		xs = new int[7];
		ys = new int[7];
		
		//create w variable so code looks orderly
		int w = -1;
		xs[1+w] = a1;
		xs[2+w] = a2;
		xs[3+w] = a3;
		xs[4+w] = a4;
		xs[5+w] = a5;
		xs[6+w] = a6;
		xs[7+w] = a7;
		
		ys[1+w] = b1;
		ys[2+w] = b2;
		ys[3+w] = b3;
		ys[4+w] = b4;
		ys[5+w] = b5;
		ys[6+w] = b6;
		ys[7+w] = b7;
		
		for (int i = 0; i < 6; i++)
        {
            Line aLine = new Line(xs[i], ys[i], xs[i+1], ys[i+1], canvas);
            Color hexColor;
            if (hexValue == 1)
            {
                hexColor = new Color(210, 105, 30);
            }
            else if (hexValue == 2)
            {
                hexColor = new Color(0, 100, 0);
            }
            else if (hexValue == 3)
            {
                hexColor = new Color(105, 105, 105);
            }
            else if (hexValue == 4)
            {
                hexColor = new Color(220, 220, 220);
            }
            else if (hexValue == 5)
            {
                hexColor = new Color(255, 215, 0);
            }
            else
            {
                hexColor = new Color(0, 0, 0);
            }
            aLine.setColor(hexColor);
        }
		//calls actual constructor
		polygon = new Polygon(xs,ys,SEVEN);
	}

	/*public static void main(String[] args){
		new Hexagon(50,250).startController(500,500);
	}*/
    
    public boolean contains(double x, double y)
    {
        return polygon.contains(x, y);
    }
    
    public int jumpBackX()
    {
        (int)(a +((Math.sqrt(3.0)/2.0)*x))
    }
    
    public int jumpBackY()
    {
        
    }
}

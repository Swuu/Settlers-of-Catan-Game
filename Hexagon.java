import objectdraw.*;
import java.awt.*;
import java.awt.Polygon;
import java.awt.Graphics;


public class Hexagon extends WindowController{
	
    Polygon polygon;
	//private DrawingCanvas canvas;
	
	public static final int SEVEN = 7;
    private int[] xs;
    private int[] ys;
    public int hexValue;
    //define the length of side of polygon
    double length = Math.sqrt(3)*32;
	
	//constuctor that will be called
	public Hexagon(int a, int b, int type, DrawingCanvas canvas){
        
        hexValue = type;
		//start points
		int a0 = a; //a is x axis
		int b0 = b; //b is y axis
		
		 //connect the dot points
		int a1 = (int)(a + ((Math.sqrt(3.0)/2.0)*length));
		int b1 = (int)(b - (length/2.0));
        
		int a2 = (int)(a + (Math.sqrt(3.0)*length));
		int b2 = b0;
        
		int a3 = (int)(a + (Math.sqrt(3.0)*length));
		int b3 = (int)(b + length);
        
		int a4 = (int)(a + ((Math.sqrt(3.0)/2.0)*length));
		int b4 = (int)(b3 + (length/2.0));
        
		int a5 = a0;
		int b5 = b3;
		
		//end and start points
		
		//puts points in array to send to constructor
		xs = new int[7];
		ys = new int[7];
		
		xs[0] = a0;
		xs[1] = a1;
		xs[2] = a2;
		xs[3] = a3;
		xs[4] = a4;
		xs[5] = a5;
        xs[6] = a0;
		
		ys[0] = b0;
		ys[1] = b1;
		ys[2] = b2;
		ys[3] = b3;
		ys[4] = b4;
		ys[5] = b5;
        ys[6] = b0;
		
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
        System.out.println("x0 = " + xs[0]);
        System.out.println("y0 = " + ys[0]);
		polygon = new Polygon(xs,ys,SEVEN);
        System.out.println("TEST: " + xs[0] + "||" + xs[5]);
	}
    
    public boolean contains(double x, double y)
    {
        return polygon.contains(x, y);
    }
    
    public int jumpBackX()
    {
        
        int initX = (int)((double)xs[0] - (Math.sqrt(1.5)*length));
        System.out.println(xs[5]);
        return initX;
    }
    
    public int jumpBackY()
    {
        System.out.println(this.ys[5]);
        return (int)(0.5*length + ys[5]);
    }
    
    public int getXCoord()
    {
        return xs[2];
    }
    
    public int getYCoord()
    {
        return ys[2];
    }
}

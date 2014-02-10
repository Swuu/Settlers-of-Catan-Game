import objectdraw.*;
import java.awt.*;
import java.awt.Polygon;
import java.awt.Graphics;
import java.util.*;


public class Hexagon
{
	
    Polygon polygon;
	
	private static final int SEVEN = 7;
    private double[] xs;
    private double[] ys;
    private Line[] lineArray = new Line[6];
    private FilledArc[] arcArray = new FilledArc[6];
    private Location centerLoc;
    public int hexValue;
    private int rollValue;
    private double arcAngle =  120;
    private DrawingCanvas canvas;
    private Text cardText;
    public String cardType = "";
    
    //define the length of side of polygon
    public static final double LENGTH = 64;
	
	//constuctor that will be called
	public Hexagon(double a, double b, int type, DrawingCanvas aCanvas)
    {
        
        canvas = aCanvas;
        hexValue = type;
		//start points
		double a0 = a; //a is x axis
		double b0 = b; //b is y axis
		
		 //connect the dot points
		double a1 = (a + ((Math.sqrt(3.0)/2.0)*LENGTH));
		double b1 = (b - (LENGTH/2.0));
        
		double a2 = (a + (Math.sqrt(3.0)*LENGTH));
		double b2 = b0;
        
		double a3 = (a + (Math.sqrt(3.0)*LENGTH));
		double b3 = (b + LENGTH);
        
		double a4 = (a + ((Math.sqrt(3.0)/2.0)*LENGTH));
		double b4 = (b3 + (LENGTH/2.0));
        
		double a5 = a0;
		double b5 = b3;
		
        centerLoc = new Location(a1- LENGTH/4, b0);
		//end and start points
		
		//puts points in array to send to constructor
		xs = new double[7];
		ys = new double[7];
		
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
            lineArray[i] = new Line(xs[i], ys[i], xs[i+1], ys[i+1], canvas);
        }
        
        int[] intxs = new int[7];
        int[] intys = new int[7];
        
        for (int i = 0; i < 7; i++)
        {
            intxs[i] = (int)xs[i];
            intys[i] = (int)ys[i];
        }
		//calls actual constructor
		polygon = new Polygon(intxs, intys,SEVEN);
        fillHexagons();
	}

	public ArrayList<Coord> getTopCoords()
	{
		ArrayList<Coord> coords = new ArrayList<Coord>();
		coords.add(new Coord(new Location(xs[1], ys[1]), canvas));
		coords.add(new Coord(new Location(xs[2], ys[2]), canvas));	
		coords.add(new Coord(new Location(xs[6], ys[6]), canvas));
		return coords;
	}

	public ArrayList<Coord> getLeftCoords()
	{
		ArrayList<Coord> coords = new ArrayList<Coord>();
		coords.add(new Coord(new Location(xs[3], ys[3]), canvas));
		coords.add(new Coord(new Location(xs[4], ys[4]), canvas));
		coords.add(new Coord(new Location(xs[5], ys[5]), canvas));
		coords.add(new Coord(new Location(xs[6], ys[6]), canvas));
		return coords;
	}

	public ArrayList<Coord> getCoords()
	{
		ArrayList<Coord> coords = new ArrayList<Coord>();
		coords.add(new Coord(new Location(xs[1], ys[1]), canvas));
		coords.add(new Coord(new Location(xs[2], ys[2]), canvas));	
		coords.add(new Coord(new Location(xs[3], ys[3]), canvas));
		coords.add(new Coord(new Location(xs[4], ys[4]), canvas));
		coords.add(new Coord(new Location(xs[5], ys[5]), canvas));
		coords.add(new Coord(new Location(xs[6], ys[6]), canvas));
		return coords;
	}

	public ArrayList<Coord> getRightCoords()
	{
		ArrayList<Coord> coords = new ArrayList<Coord>();
		coords.add(new Coord(new Location(xs[2], ys[2]), canvas));	
		coords.add(new Coord(new Location(xs[3], ys[3]), canvas));
		coords.add(new Coord(new Location(xs[4], ys[4]), canvas));
		coords.add(new Coord(new Location(xs[5], ys[5]), canvas));
		return coords;
	}

	public ArrayList<Coord> getBottomCoords()
	{
		ArrayList<Coord> coords = new ArrayList<Coord>();
		coords.add(new Coord(new Location(xs[3], ys[3]), canvas));
		coords.add(new Coord(new Location(xs[4], ys[4]), canvas));
		coords.add(new Coord(new Location(xs[5], ys[5]), canvas));
		return coords;
	}
    
    public void fillHexagons()
    {
        /*arcArray[0] = new FilledArc(lineArray[0].getStart(), xs[1] - xs[0], ys[5]- ys[0], 270, arcAngle, canvas);
        arcArray[1] = new FilledArc(lineArray[1].getStart(), lineArray[1].getEnd(), , arcAngle, canvas);
        arcArray[2] = new FilledArc(lineArray[2].getStart(), lineArray[2].getEnd(), , arcAngle, canvas);
        arcArray[3] = new FilledArc(lineArray[3].getStart(), lineArray[3].getEnd(), , arcAngle, canvas);
        arcArray[4] = new FilledArc(lineArray[4].getStart(), lineArray[4].getEnd(), , arcAngle, canvas);
        arcArray[5] = new FilledArc(lineArray[5].getStart(), lineArray[5].getEnd(), , arcAngle, canvas);*/
        
    }
    
    public void printType()
    {
        
    }
    
    public boolean contains(double x, double y)
    {
        return polygon.contains(x, y);
    }
    
    public boolean contains(Location c)
    {
        return polygon.contains(c.getX(), c.getY());
    }
    
    public double jumpBackX()
    {
        
        double initX = ((double)xs[0] - (Math.sqrt(3.0)/2.0*LENGTH));
        return initX;
    }
    
    public double jumpBackY()
    {
        return (0.5*LENGTH + ys[5]);
    }
    
    public double getXCoord()
    {
        return xs[2];
    }
    
    public double getYCoord()
    {
        return ys[2];
    }
    
    public void setValue(int aType)
    {
        this.hexValue = aType;
        Color hexColor;
        for (int i = 0; i < 6; i ++)
        {
            if (hexValue == 1)
            {
                cardType = "Clay";
                hexColor = new Color(210, 105, 30);
            }
            else if (hexValue == 2)
            {
                cardType = "Lumber";
                hexColor = new Color(0, 100, 0);
            }
            else if (hexValue == 3)
            {
                cardType = "Ore";
                hexColor = new Color(105, 105, 105);
            }
            else if (hexValue == 4)
            {
                cardType = "Sheep";
                hexColor = new Color(220, 220, 220);
            }
            else if (hexValue == 5)
            {
                cardType = "Wheat";
                hexColor = new Color(255, 215, 0);
            }
            else
            {
                cardType = "Desert";
                hexColor = new Color(0, 0, 0);
            }
            lineArray[i].setColor(hexColor);
            cardText = new Text(cardType, centerLoc, canvas);
			cardText.setColor(Color.WHITE);
        }
    }
}

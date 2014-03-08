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
    private Location belowLoc;
    
    private int hexValue;
    private int rollValue;
    
    private double arcAngle =  120;
    private DrawingCanvas canvas;
    
    private Text cardText;
    private Text cardNum;
    
    public String cardType = "";
	public static final int skew = 1;
    
    //define the length of side of polygon
    public static final double LENGTH = 64;
	
	//constuctor that will be called
	public Hexagon(double a, double b, int type, int number, DrawingCanvas aCanvas)
    {
        
        canvas = aCanvas;
        hexValue = type;
        rollValue = number;
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
        belowLoc = new Location(a1-LENGTH/8, b0 + 20);
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
		coords.add(new Coord(new Location(xs[1], ys[1]-skew), canvas));
		coords.add(new Coord(new Location(xs[2]+skew, ys[2]-skew), canvas));	
		coords.add(new Coord(new Location(xs[6]-skew, ys[6]-skew), canvas));
		return coords;
	}

	public ArrayList<Coord> getBottomRightCoords()
	{
		ArrayList<Coord> coords = new ArrayList<Coord>();
		coords.add(new Coord(new Location(xs[3]+skew, ys[3]+skew), canvas));
		coords.add(new Coord(new Location(xs[4], ys[4]+skew), canvas));
		return coords;
	}

	public ArrayList<Coord> getCoords()
	{
		ArrayList<Coord> coords = new ArrayList<Coord>();
		coords.add(new Coord(new Location(xs[1], ys[1]-skew), canvas));
		coords.add(new Coord(new Location(xs[2]+skew, ys[2]-skew), canvas));	
		coords.add(new Coord(new Location(xs[3]+skew, ys[3]+skew), canvas));
		coords.add(new Coord(new Location(xs[4], ys[4]-skew), canvas));
		coords.add(new Coord(new Location(xs[5]-skew, ys[5]+skew), canvas));
		coords.add(new Coord(new Location(xs[6]-skew, ys[6]-skew), canvas));
		return coords;
	}

	public ArrayList<Coord> getTopRightCoords()
	{
		ArrayList<Coord> coords = new ArrayList<Coord>();
		coords.add(new Coord(new Location(xs[1], ys[1]-skew), canvas));
		coords.add(new Coord(new Location(xs[2]+skew, ys[2]-skew), canvas));		
		return coords;
	}

	public ArrayList<Coord> getBottomCoords()
	{
		ArrayList<Coord> coords = new ArrayList<Coord>();
		coords.add(new Coord(new Location(xs[3]+skew, ys[3]+skew), canvas));
		coords.add(new Coord(new Location(xs[4], ys[4]+skew), canvas));
		coords.add(new Coord(new Location(xs[5]-skew, ys[5]+skew), canvas));
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
    
    public int getHexValue()
    {
        return hexValue;
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
    
    public int getRollValue()
    {
        return rollValue;
    }
    
    public void setRollValue(int aNumber)
    {
        rollValue = aNumber;
        cardNum = new Text(rollValue, belowLoc, canvas);
        cardNum.setColor(Color.WHITE);
    }
}

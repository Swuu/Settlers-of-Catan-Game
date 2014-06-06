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
    private Location moreBelowLoc;
    
    private Location hexagonCenter;
    
    private int hexValue;
    private int rollValue;
    private int rollPossiblity;
    
    private DrawingCanvas canvas;
    
    private Text cardText;
    private Text cardNum;
    private Text cardNumPossibility;
    
    private FilledOval selectionBubble;
    
    public String cardType = "";
	public static final int skew = 1;
    
    //define the length of side of polygon
    public static final double LENGTH = 64;
    
    private boolean isEmbargo;
    private FilledOval embargoBubble;
	
	//constuctor that will be called
	public Hexagon(double x, double y, int type, int number, DrawingCanvas aCanvas)
    {
        
        canvas = aCanvas;
        hexValue = type;
        rollValue = number;
		//start points
		double x0 = x; //x0is x axis
		double y0 = y; //y0 is y axis
		
        //connect the dot points
		double x1 = (x0 + ((Math.sqrt(3.0)/2.0)*LENGTH));
		double y1 = (y0 - (LENGTH/2.0));
        
		double x2 = (x0 + (Math.sqrt(3.0)*LENGTH));
		double y2 = y0;
        
		double x3 = (x0 + (Math.sqrt(3.0)*LENGTH));
		double y3 = (y0 + LENGTH);
        
		double x4 = (x0 + ((Math.sqrt(3.0)/2.0)*LENGTH));
		double y4 = (y3 + (LENGTH/2.0));
        
		double x5 = x0;
		double y5 = y3;
		
        centerLoc = new Location(x1, y0);
        belowLoc = new Location(x1, y0 + 20);
        moreBelowLoc = new Location(x1, y0 + 40);
		
        hexagonCenter = new Location(x1-30, y0+2);
        selectionBubble = new FilledOval(hexagonCenter, 60, 60, canvas);
        selectionBubble.setColor(new Color(0, 145, 11, 125));
        selectionBubble.hide();
        embargoBubble = new FilledOval(hexagonCenter, 60, 60, canvas);
        embargoBubble.setColor(new Color(145, 0, 11, 125));
        embargoBubble.hide();
		
		//puts points in array to send to constructor
		xs = new double[7];
		ys = new double[7];
		
		xs[0] = x0;
		xs[1] = x1;
		xs[2] = x2;
		xs[3] = x3;
		xs[4] = x4;
		xs[5] = x5;
        xs[6] = x0;
		
		ys[0] = y0;
		ys[1] = y1;
		ys[2] = y2;
		ys[3] = y3;
		ys[4] = y4;
		ys[5] = y5;
        ys[6] = y0;
		
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
        
        isEmbargo = false;
	}

	public void hexagonRolled()
	{
		System.out.println(rollValue);	    
	}

    //adds Coord objects to the 3 top vertices of the Hexagon
	public ArrayList<Coord> getTopCoords()
	{
		ArrayList<Coord> coords = new ArrayList<Coord>();
		coords.add(new Coord(new Location(xs[1], ys[1]-skew), canvas));
		coords.add(new Coord(new Location(xs[2]+skew, ys[2]-skew), canvas));	
		coords.add(new Coord(new Location(xs[6]-skew, ys[6]-skew), canvas));
		return coords;
	}

    //adds Coord objects to the 2 bottom vertices of the Hexagon
	public ArrayList<Coord> getBottomRightCoords()
	{
		ArrayList<Coord> coords = new ArrayList<Coord>();
		coords.add(new Coord(new Location(xs[3]+skew, ys[3]+skew), canvas));
		coords.add(new Coord(new Location(xs[4], ys[4]+skew), canvas));
		return coords;
	}

    //adds Coord objects to all 6 vertices of the Hexagon
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

    //adds Coord objects to the top right vertices of the Hexagon
	public ArrayList<Coord> getTopRightCoords()
	{
		ArrayList<Coord> coords = new ArrayList<Coord>();
		coords.add(new Coord(new Location(xs[1], ys[1]-skew), canvas));
		coords.add(new Coord(new Location(xs[2]+skew, ys[2]-skew), canvas));		
		return coords;
	}

    //adds Coord objects to the bottom vertices of the Hexagon
	public ArrayList<Coord> getBottomCoords()
	{
		ArrayList<Coord> coords = new ArrayList<Coord>();
		coords.add(new Coord(new Location(xs[3]+skew, ys[3]+skew), canvas));
		coords.add(new Coord(new Location(xs[4], ys[4]+skew), canvas));
		coords.add(new Coord(new Location(xs[5]-skew, ys[5]+skew), canvas));
		return coords;
	}

	public ArrayList<Location> getVerticies()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		locs.add(new Location(xs[1], ys[1]-skew));
		locs.add(new Location(xs[2]+skew, ys[2]-skew));	
		locs.add(new Location(xs[3]+skew, ys[3]+skew));
		locs.add(new Location(xs[4], ys[4]-skew));
		locs.add(new Location(xs[5]-skew, ys[5]+skew));
		locs.add(new Location(xs[6]-skew, ys[6]-skew));
		return locs;
	}
    
    public boolean contains(double x, double y)
    {
        return polygon.contains(x, y);
    }
    
    public boolean contains(Location c)
    {
        return polygon.contains(c.getX(), c.getY());
    }
    
    public boolean bubbleContains(Location loc)
    {
        return selectionBubble.contains(loc);
    }
    
    //returns the X coord for the initial location of x0next row Hexagon
    public double jumpBackX()
    {
        
        double initX = ((double)xs[0] - (Math.sqrt(3.0)/2.0*LENGTH));
        return initX;
    }
    
    //returns the Y coord for the initial location of x0next row Hexagon
    public double jumpBackY()
    {
        return (0.5*LENGTH + ys[5]);
    }
    
    public double getXCoord(int i)
    {
        return xs[i];
    }
    
    public double getYCoord(int i)
    {
        return ys[i];
    }
    
    public int getHexValue()
    {
        return hexValue;
    }
    
    //sets the Hexagon's type
    public void setValue(int aType)
    {
        hexValue = aType;
        Color hexColor;
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
            embargoOn();
        }
        for (int i = 0; i < 6; i++)
            lineArray[i].setColor(hexColor);
        cardText = new Text(cardType, centerLoc, canvas);
        cardText.setColor(Color.WHITE);
        cardText.move(-(cardType.length()*8)/2, 0);
        
    }
    
    //get method to get rollValue
    public int getRollValue()
    {
        return rollValue;
    }
    
    //sets rollValue
    public void setRollValue(int aNumber)
    {
        rollValue = aNumber;
        switch (rollValue)
        {
            case 2: rollPossiblity = 1;
                break;
            case 3: rollPossiblity = 2;
                break;
            case 4: rollPossiblity = 3;
                break;
            case 5: rollPossiblity = 4;
                break;
            case 6: rollPossiblity = 5;
                break;
            case 8: rollPossiblity = 5;
                break;
            case 9: rollPossiblity = 4;
                break;
            case 10: rollPossiblity = 3;
                break;
            case 11: rollPossiblity = 2;
                break;
            case 12: rollPossiblity = 1;
                break;
        }
        cardNum = new Text(rollValue, belowLoc, canvas);
        cardNumPossibility = new Text(rollPossiblity, moreBelowLoc, canvas);
        cardNum.setColor(Color.WHITE);
        cardNumPossibility.setColor(Color.WHITE);
        cardNum.move(-(int)((Math.log10(rollValue))+1)*8/2, 0);
        cardNumPossibility.move(-(int)((Math.log10(rollValue))+1)*8/2, 0);
    }
    
    public void showSelectionBubble()
    {
        selectionBubble.show();
    }
    
    public void hideSelectionBubble()
    {
        selectionBubble.hide();
    }
    
    public void embargoOn()
    {
        isEmbargo = true;
        embargoBubble.show();
    }
    
    public void embargoOff()
    {
        isEmbargo = false;
        embargoBubble.hide();
    }
    
    public boolean embargoStatus()
    {
        return isEmbargo;
    }
}

import objectdraw.*;
import java.awt.*;

// Makes a house, with the middle of the square at x , y
public class SettlementShape
{
	private FilledRect base, window;
	private FramedRect frame1, frame2, cityRect;
    private Triangle roof;
    private int playerNum;
    private DrawingCanvas cnv;

    /* CONFIG */
    private double width = 24;
    private double height = 20;
    
    private double xCoord, yCoord;
    
	public SettlementShape (double x , double y , int aNum , DrawingCanvas canvas)
    {
        xCoord = x;
        yCoord = y;
        cnv = canvas;
        Constructor();
        playerNum = aNum;
        setColor();
    }

    public SettlementShape (Location aLocation, int aNum , DrawingCanvas canvas)
    {
        xCoord = aLocation.getX();
        yCoord = aLocation.getY();
        
        cnv = canvas;
        Constructor();
        playerNum = aNum;
        setColor();
    }
    
    public void Constructor()
    {
        frame1 = new FramedRect(xCoord-width/2 -1, yCoord-height/2 -1, width+1, height+1, cnv);
        base = new FilledRect(xCoord-width/2, yCoord-height/2, width, height, cnv);
        frame2 = new FramedRect(xCoord-width/6 -1, yCoord-height/6 -1, width/3 +1,
                                height/3 +1, cnv);
        window = new FilledRect(xCoord-width/6, yCoord-height/6, width/3, height/3,
                                cnv);
        roof = new Triangle(xCoord-width/2, yCoord-height/2, width, height, cnv);
        
        window.setColor(Color.YELLOW);
    }

    public void makeCity()
    {
        cityRect = new FramedRect(xCoord-width/2 - 2, yCoord-height/2 - 2, width + 2, height + 2, cnv);
    }
    
    public void setColor()
    {
        Color color = Color.BLACK;
        switch (playerNum)
        {
            case 0:
                color = Color.GRAY;
                break;
            case 1:
                color = Color.RED;
                break;
            case 2:
                color = Color.BLUE;
                break;
            case 3:
                color = Color.GREEN;
                break;
        }
        base.setColor(color);
        roof.tr_setColor(color);
    }
            
    public void moveTo(Location aLocation)
    {
        double x = aLocation.getX();
        double y = aLocation.getY();
        
        moveTo(x, y);
    }
    
    public void moveTo(double x, double y)
    {
        double preX = base.getX();
        double preY = base.getY();
        
        frame1.moveTo(x - width/2 - 1, y - height/2 - 1);
        frame2.moveTo(x - width/6 - 1, y - height/6 - 1);
        base.moveTo(x - width/2, y - height/2);
        window.moveTo(x - width/6, y - height/6);
        roof.moveTo(x - 1 - width/2, y - 1 - height/2);
    }
    
    public void sendToFront()
    {
        frame1.sendToFront();
        base.sendToFront();
        frame2.sendToFront();
        window.sendToFront();
        roof.tr_sendToFront();
    }
    
    public void hide()
    {
        frame1.hide();
        base.hide();
        frame2.hide();
        window.hide();
        roof.tr_hide();
    }
        
    public void show()
    {
        frame1.show();
        base.show();
        frame2.show();
        window.show();
        roof.tr_show();
    }

} 

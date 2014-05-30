import objectdraw.*;
import java.awt.*;

// Makes a house, with the middle of the square at x , y
public class SettlementShape
{
    private int playerNum;
    private VisibleImage base;
    private Toolkit toolkit;
    private DrawingCanvas canvas;

    /* CONFIG */
    private double width = 40;
    private double height = 40;
    
    private double xCoord, yCoord;
    
	public SettlementShape (double x , double y , int aNum , DrawingCanvas aCanvas)
    {
        toolkit = Toolkit.getDefaultToolkit();
        xCoord = x;
        yCoord = y;
        canvas = aCanvas;
        playerNum = aNum;
        makeSettlement();
    }

    public SettlementShape (Location aLocation, int aNum , DrawingCanvas aCanvas)
    {
        toolkit = Toolkit.getDefaultToolkit();
        xCoord = aLocation.getX();
        yCoord = aLocation.getY();
        canvas = aCanvas;
        playerNum = aNum;
        makeSettlement();
    }
    
    public void makeSettlement()
    {
        double x = xCoord - width/2;
        double y = yCoord - height/2;
        switch (playerNum)
        {
            case 0:
                base = new VisibleImage(toolkit.getImage("image/SpaceStationNeutralBase.png"), x, y, 41, 41, canvas);
                break;
            case 1:
                base = new VisibleImage(toolkit.getImage("image/SpaceStationRedBase.png"), x, y, 41, 41, canvas);
                break;
            case 2:
                base = new VisibleImage(toolkit.getImage("image/SpaceStationBlueBase.png"), x, y, 41, 41, canvas);
                break;
            case 3:
                base = new VisibleImage(toolkit.getImage("image/SpaceStationOrangeBase.png"), x, y, 41, 41, canvas);
                break;
            case 4:
                base = new VisibleImage(toolkit.getImage("image/SpaceStationPurpleBase.png"), x, y, 41, 41, canvas);
                break;
        }

    }

    public void makeCity()
    {
        double x = xCoord - width/2;
        double y = yCoord - height/2;
        base.hide();
        switch (playerNum)
        {
            case 0:
                base = new VisibleImage(toolkit.getImage("image/SpaceStationNeutralColony.png"), x, y, 41, 41, canvas);
                break;
            case 1:
                base = new VisibleImage(toolkit.getImage("image/SpaceStationRedColony.png"), x, y, 41, 41, canvas);
                break;
            case 2:
                base = new VisibleImage(toolkit.getImage("image/SpaceStationBlueColony.png"), x, y, 41, 41, canvas);
                break;
            case 3:
                base = new VisibleImage(toolkit.getImage("image/SpaceStationOrangeColony.png"), x, y, 41, 41, canvas);
                break;
            case 4:
                base = new VisibleImage(toolkit.getImage("image/SpaceStationPurpleColony.png"), x, y, 41, 41, canvas);
                break;
        }
    }
            
    public void moveTo(Location aLocation)
    {
        double x = aLocation.getX();
        double y = aLocation.getY();
        
        moveTo(x, y);
    }
    
    public void moveTo(double x, double y)
    {
        base.moveTo(x - width/2, y - height/2);
    }
    
    public void sendToFront()
    {
        base.sendToFront();
    }
    
    public void hide()
    {
        base.hide();
    }
        
    public void show()
    {
        base.show();
    }

} 

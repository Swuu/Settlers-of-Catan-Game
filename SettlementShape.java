import objectdraw.*;
import java.awt.*;

// Makes a house, with the middle of the square at x , y
public class SettlementShape
{
	private FilledRect base, window;
	private FramedRect frame1, frame2;
    private FilledArc roof;
    private Line left, right;
    private Line[] fill;
    private int playerNum;

    /* CONFIG */
    double width = 24;
    double height = 24;
    double arcX = 30;
    double arcY = 30;
    
	public SettlementShape (double x , double y , int aNum , DrawingCanvas canvas)
    {
		/*base = new FilledRect (x-12.5 , y-12.5, 25 , 25 , canvas);
		roof = new FilledArc (x-19 , y-45, 38 , 38 , 225 , 90 , canvas);
        playerNum = aNum;
        setColor();*/

        frame1 = new FramedRect(x-width/2 -1, y-height/2 -1, width+1, height+1,
                                                                canvas);
        base = new FilledRect(x-width/2, y-height/2, width, height, canvas);
        frame2 = new FramedRect(x-width/6 -1, y-height/6 -1, width/3 +1,
                                                    height/3 +1, canvas);
        window = new FilledRect(x-width/6, y-height/6, width/3, height/3,
                                                                canvas);
        roof = new FilledArc(x-arcX/2, y +height/2 -arcY/2, width, 
                                            height/2, 0, 180, canvas);
                                            
        left = new Line(x-width/2 - 1, y-height, x, y - height*3/2, canvas);
        right = new Line(x+width/2 + 1, y-height, x, y - height*3/2,canvas);
                                                                
        window.setColor(Color.YELLOW);
        //roof.setColor(Color.WHITE);
        playerNum = aNum;
        setColor();
    }

    public SettlementShape (Location aLocation, int aNum , DrawingCanvas canvas)
    {
        double x = aLocation.getX();
        double y = aLocation.getY();
        /*base = new FilledRect(x-12.5 , y, 25 , 25 , canvas);
        roof = new FilledArc(x-19 , y-45 , 38 , 38 , 225 , 90 , canvas);
        playerNum = aNum;
        setColor();*/
        
        frame1 = new FramedRect(x-width/2 -1, y-height/2 -1, width+2, height+2,
                                                                canvas);
        base = new FilledRect(x-width/2, y-height/2, width, height, canvas);
        frame2 = new FramedRect(x-width/6 -1, y-height/6 -1, width/3 +1,
                                                    height/3 +1, canvas);
        window = new FilledRect(x-width/6, y-height/6, width/3, height/3,
                                                                canvas);
        //roof = new FilledArc(x-arcX/2, y +height/2 -arcY/2, width, 
                                            //height/2, 0, 180, canvas);
                                            
        left = new Line(x-width/2 - 1, y-height, x, y - height*3/2, canvas);
        right = new Line(x+width/2 + 1, y-height, x, y - height*3/2,canvas);
                                                                
        window.setColor(Color.YELLOW);
        //roof.setColor(Color.WHITE);
        playerNum = aNum;
        setColor();
    }

    public void setColor()
    {
        switch (playerNum)
        {
            case 1:
            base.setColor(Color.RED);
            //roof.setColor(Color.RED);
            break;
            case 2:
            base.setColor(Color.BLUE);
            //roof.setColor(Color.BLUE);
            break;
            case 3:
            base.setColor(Color.GREEN);
            //roof.setColor(Color.GREEN);
            break;
            case 4:
            base.setColor(Color.BLACK);
            //roof.setColor(Color.BLACK);
            break;
            case 0:
            base.setColor(Color.GRAY);
            //roof.setColor(Color.GRAY);
            break;
        }
    }

    public void moveTo(Location aLocation)
    {
        double x = aLocation.getX();
        double y = aLocation.getY();
        frame1.moveTo(x - width/2 - 1, y - height/2 - 1);
        frame2.moveTo(x - width/6 - 1, y - height/6 - 1);
        base.moveTo(x - width/2, y - height/2);
        window.moveTo(x - width/6, y - height/6);
        left.moveTo(x - width/2 - 1, y - height/2);
        right.moveTo(x + width/2, y - height/2);
        //roof.moveTo(x -  arcX/2, y - height);
    }
    
    public void moveTo(double x, double y)
    {
        Location aLocation = new Location(x, y);
        frame1.moveTo(x - width/2 - 1, y - height/2 - 1);
        frame2.moveTo(x - width/6 - 1, y - height/6 - 1);
        base.moveTo(x - width/2, y - height/2);
        window.moveTo(x - width/6, y - height/6);
        left.moveTo(x - width/2 - 1, y - height/2);
        right.moveTo(x + width/2, y - height/2);
        //roof.moveTo(x -  arcX/2, y - height);
    }
    
    public void sendToFront()
    {
        frame1.sendToFront();
        frame2.sendToFront();
        base.sendToFront();
        window.sendToFront();
        left.sendToFront();
        right.sendToFront();
        //roof.sendToFront();
    }
    
    public void hide()
    {
        frame1.hide();
        frame2.hide();
        base.hide();
        window.hide();
        //roof.hide();
    }
        
    public void show()
    {
        frame1.show();
        frame2.show();
        base.show();
        window.show();
        //roof.show();
    }

} 

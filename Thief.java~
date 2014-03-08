import objectdraw.*;
import java.awt.*;

// Makes a Thief, with the middle of the square at x , y
public class Thief
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
    
	public Thief (double x , double y , int aNum , DrawingCanvas canvas)
    {
		FilledOval head = new FilledOval (x-width/2 -1, y-height/2 -1, width+1, height+1,
                                                                canvas);
        FilledOval body = new FilledOval (x-width/2 -1, y-height/2 -1, width+1, height+1,
                                                                canvas);
        FilledRect base = new FilledRect (x-width/2 -1, y-height/2 -1, width+1, height+1,
                                                                canvas);

        playerNum = aNum;
        setColor();
    }

    public Thief (Location aLocation, int aNum , DrawingCanvas canvas)
    {
        FilledOval head = new FilledOval (x-width/2 -1, y-height/2 -1, width+1, height+1,
                                                                canvas);
        FilledOval body = new FilledOval (x-width/2 -1, y-height/2 -1, width+1, height+1,
                                                                canvas);
        FilledRect base = new FilledRect (x-width/2 -1, y-height/2 -1, width+1, height+1,
                                                                canvas);

        playerNum = aNum;
        setColor();
    }

    public void setColor()
    {
        switch (playerNum)
        {
            case 1:
            base.setColor(Color.RED);
            head.setColor(Color.RED);
            body.setColor(Color.RED);
            break;
            case 2:
            base.setColor(Color.BLUE);
            head.setColor(Color.BLUE);
            body.setColor(Color.BLUE);
            break;
            case 3:
            base.setColor(Color.GREEN);
            head.setColor(Color.GREEN);
            body.setColor(Color.GREEN);
            break;
            case 4:
            base.setColor(Color.BLACK);
            head.setColor(Color.BLACK);
            body.setColor(Color.BLACK);
            break;
            case 0:
            base.setColor(Color.GREY);
            head.setColor(Color.GREY);
            body.setColor(Color.GREY);
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
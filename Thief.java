import objectdraw.*;
import java.awt.*;
import java.awt.Graphics;

// Makes a Thief, with the middle of the square at x , y
public class Thief extends WindowController
{
<<<<<<< HEAD
 private FilledRect base, window;
 private FramedRect frame1, frame2;
    private FilledArc roof;
    private Line left, right;
    private Line[] fill;
=======
	private FilledOval head, body;
	private FilledRect base;
    
>>>>>>> FETCH_HEAD
    private int playerNum;

    /*Configurations of the Thief body */
    double width = 24;
    double height = 24;
    double arcX = 30;
    double arcY = 30;
    
<<<<<<< HEAD
 public Thief (double x , double y , int aNum , DrawingCanvas canvas)
    {
  FilledOval head = new FilledOval (x-width/2 -1, y-height/2 -1, width+1, height+1,
=======
    public static void main(){
        JDrawingCanvas aCanvas = new JDrawingCanvas(500, 500);
        Thief thief = new Thief(20, 30, 2, aCanvas);
    }
    
	public Thief (double x , double y , int aNum , DrawingCanvas canvas)
    {
		head = new FilledOval (x-width/2 -1, y-height/2 -1, width+1, height+1,
>>>>>>> FETCH_HEAD
                                                                canvas);
        body = new FilledOval (x-width/2 -1, y-height/2 + height, width+1, (height*2)+1,
                                                                canvas);
        base = new FilledRect (x-width/2 -1, y-height/2 +height+10 , width+1, height+1,
                                                                canvas);

        playerNum = aNum;
        setColor();
    }

    public Thief (Location aLocation, int aNum , DrawingCanvas canvas)
    {
<<<<<<< HEAD
        double x = aLocation.getX();
        double y = aLocation.getY();
        FilledOval head = new FilledOval (x-width/2 -1, y-height/2 -1, width+1, height+1,
=======
        head = new FilledOval (aLocation.getX()-width/2 -1, aLocation.getX()-height/2 -1, width+1, height+1,
>>>>>>> FETCH_HEAD
                                                                canvas);
        body = new FilledOval (aLocation.getX()-width/2 -1, aLocation.getY()-height/2 -1, width+1, height+1,
                                                                canvas);
        base = new FilledRect (aLocation.getX()-width/2 -1, aLocation.getY()-height/2 -1, width+1, height+1,
                                                                canvas);

        playerNum = aNum;
        setColor();
    }

    public void setColor()
    {
        switch (playerNum)
        {
            case 1:
            base.setColor(new Color(255,0,0));
            head.setColor(new Color(255,0,0));
            body.setColor(new Color(255,0,0));
            break;
            case 2:
            base.setColor(new Color(0,0,255));
            head.setColor(new Color(0,0,255));
            body.setColor(new Color(0,0,255));
            break;
            case 3:
            base.setColor(new Color(0,255,0));
            head.setColor(new Color(0,255,0));
            body.setColor(new Color(0,255,0));
            break;
            case 4:
            base.setColor(new Color(0,0,0));
            head.setColor(new Color(0,0,0));
            body.setColor(new Color(0,0,0));
            break;
            case 0:
            base.setColor(new Color(105,105,105));
            head.setColor(new Color(105,105,105));
            body.setColor(new Color(105,105,105));
            break;
        }
    }

    public void moveTo(Location aLocation)
    {
        double x = aLocation.getX();
        double y = aLocation.getY();
        head.moveTo(x - width/2 - 1, y - height/2 - 1);
        body.moveTo(x - width/6 - 1, y - height/6 - 1);
        base.moveTo(x - width/2, y - height/2);
    }
    
    public void moveTo(double x, double y)
    {
        Location aLocation = new Location(x, y);
        head.moveTo(x - width/2 - 1, y - height/2 - 1);
        body.moveTo(x - width/6 - 1, y - height/6 - 1);
        base.moveTo(x - width/2, y - height/2);
    }

} 
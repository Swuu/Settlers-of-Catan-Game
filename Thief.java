import objectdraw.*;
import java.awt.*;
import java.awt.Graphics;

// Makes a Thief, with the middle of the square at x , y
public class Thief
{

 private FilledRect base, window;
 private FramedRect frame1, frame2;
    private FilledArc roof;
    private Line left, right;
    private Line[] fill;
	private FilledOval head, body;
	private FilledRect base;
    private int playerNum;

<<<<<<< HEAD
	public boolean isBlocked = false;
	public int theifnum = 0;

    /* CONFIG */
=======
    /*Configurations of the Thief body */
>>>>>>> FETCH_HEAD
    double width = 24;
    double height = 24;
    double arcX = 30;
    double arcY = 30;
    
<<<<<<< HEAD
	public Thief (double x , double y , int aNum , DrawingCanvas canvas)
    {
	FilledRect theif = new FilledRect (x-width/2 -1, y-height/2 -1, width+1, height+1, canvas);
=======
    //Comment this out***********
    public static void main(){
        JDrawingCanvas aCanvas = new JDrawingCanvas(500,500);

        Thief (250 , 100 , 50 , aCanvas);/*{
            FilledOval head = new FilledOval (x-width/2 -1, y-height/2 -1, width+1, height+1,canvas);
        }*/

    }
    

public Thief (double x , double y , int aNum , DrawingCanvas canvas){
        head = new FilledOval (x-width/2 -1, y-height/2 -1, width+1, height+1,
                               canvas);
    
        body = new FilledOval (x-width/2 -1, y-height/2 + height, width+1, (height*2)+1,
                               canvas);
    
        base = new FilledRect (x-width/2 -1, y-height/2 +height+10 , width+1, height+1,
                               canvas);
        /*this sets the player number for the method set color to pick what color the thief
         shall be*/
        playerNum = aNum;
        setColor();
    }

    public Thief (Location aLocation, int aNum, DrawingCanvas canvas)
    {
        double x = aLocation.getX();
        double y = aLocation.getY();
        FilledOval head = new FilledOval (x-width/2 -1, y-height/2 -1, width+1, height+1, canvas);

        head = new FilledOval (x-width/2 -1, y-height/2 -1, width+1, height+1, canvas);
        
        body = new FilledOval (x-width/2 -1, y-height/2 -1, width+1, height+1,canvas);
        
        base = new FilledRect (x-width/2 -1, y-height/2 -1, width+1, height+1,canvas);

>>>>>>> FETCH_HEAD
        playerNum = aNum;
        setColor();
    }

    public void setColor()
    {
        switch (playerNum)
        {
	/*
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
	*/
        }
    }
<<<<<<< HEAD
=======

    public void moveTo(Location aLocation)
    {
        double x = aLocation.getX();
        double y = aLocation.getY();
        head.moveTo(x - width/2 - 1, y - height/2 - 1);
        body.moveTo(x - width/6 - 1, y - height/6 - 1);
        base.moveTo(x - width/2, y - height/2);
    }
>>>>>>> FETCH_HEAD
    
    public void moveTo(double x, double y, int num)
    {
<<<<<<< HEAD
        theif.moveTo(x - width/2 - 1, y - height/2 - 1);
	changenum(num);
    }

    public void changenum (int num) {
	theifnum=num;
    }
    
    public void sendToFront()
    {
    	theif.sendToFront();
    }
    
    public void hide()
    {
	theif.hide();
    }
        
    public void show()
    {
	theif.show();
    }

} 
=======
        Location aLocation = new Location(x, y);
        head.moveTo(x - width/2 - 1, y - height/2 - 1);
        body.moveTo(x - width/6 - 1, y - height/6 - 1);
        base.moveTo(x - width/2, y - height/2);
    }

}
>>>>>>> FETCH_HEAD

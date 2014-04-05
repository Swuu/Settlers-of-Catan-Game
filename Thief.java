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

	public boolean isBlocked = false;
	public int theifnum = 0;

    /* CONFIG */
    double width = 24;
    double height = 24;
    double arcX = 30;
    double arcY = 30;
    
	public Thief (double x , double y , int aNum , DrawingCanvas canvas)
    {
	FilledRect theif = new FilledRect (x-width/2 -1, y-height/2 -1, width+1, height+1, canvas);
        playerNum = aNum;
        setColor();
    }

    public void setColor()
    {
        switch (playerNum)
        {
	/*
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
	*/
        }
    }
    
    public void moveTo(double x, double y, int num)
    {
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

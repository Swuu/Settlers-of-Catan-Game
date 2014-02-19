import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JTextArea;

// Makes a house, with the middle of the square at x , y
public class SettlementShape extends WindowController {
	private FilledRect base;
    private FilledArc roof;
    private int playerNum;

	public SettlementShape (double x , double y , int aNum , DrawingCanvas canvas)
    {
		base = new FilledRect (x-12.5 , y-12.5, 25 , 25 , canvas);
		roof = new FilledArc (x-19 , y-45, 38 , 38 , 225 , 90 , canvas);
        playerNum = aNum;
        setColor();
    }

    public SettlementShape (Location aLocation, int aNum , DrawingCanvas canvas)
    {
        double x = aLocation.getX();
        double y = aLocation.getY();
        base = new FilledRect (x-12.5 , y, 25 , 25 , canvas);
        roof = new FilledArc (x-19 , y-45 , 38 , 38 , 225 , 90 , canvas);
        playerNum = aNum;
        setColor();
    }

    public void setColor()
    {
        switch (playerNum)
        {
            case 1:
            base.setColor(Color.RED);
            roof.setColor(Color.RED);
            break;
            case 2:
            base.setColor(Color.BLUE);
            roof.setColor(Color.BLUE);
            break;
            case 3:
            base.setColor(Color.GREEN);
            roof.setColor(Color.GREEN);
            break;
            case 4:
            base.setColor(Color.BLACK);
            roof.setColor(Color.BLACK);
            break;
            case 0:
            base.setColor(Color.GRAY);
            roof.setColor(Color.GRAY);
            break;
        }
    }

    public void moveTo(Location aLocation)
    {
        base.moveTo(aLocation);
        roof.moveTo(aLocation);
    }
    
    public void moveTo(double x, double y)
    {
        Location aLocation = new Location(x, y);
        base.moveTo(aLocation);
        roof.moveTo(aLocation);
    }
    
    public void sendToFront()
    {
        base.sendToFront();
        roof.sendToFront();
    }
    
    public void hide()
    {
        base.hide();
        roof.hide();
    }
        
    public void show()
    {
        base.show();
        roof.show();
    }

} 

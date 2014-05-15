import objectdraw.*;
import java.awt.*;
import java.awt.Polygon;
import java.awt.Graphics;
import java.util.*;

public class Port
{
    Polygon polygon;
    
    private static final double LENGTH = 64;
    private Line[] lineArray = new Line[3];
    private DrawingCanvas canvas;
    private String cardType;
    private int portValue;
    private int[] xs;
    private int[] ys;
    
    public Port(double x, double y, int aType, int orientation, DrawingCanvas aCanvas)
    {
        canvas = aCanvas;
        
        double x0 = x;
        double y0 = y;
        
        double x1 = x;
        double y1 = y;
        
        double x2 = x;
        double y2 = y;
        
        /*12 oclock alighment*/
        if (orientation == 0)
        {
            x1 = (x0 + Math.sqrt(3.0)*LENGTH);
            y1 = y0;
            
            x2 = (x0 + ((Math.sqrt(3.0)/2.0)*LENGTH));
            y2 = (y0 + (LENGTH/2.0));
        }
        /*2 oclock alignment*/
        else if (orientation == 2)
        {
            x1 = (x0 + ((Math.sqrt(3.0)/2.0)*LENGTH));
            y1 = (y0 + 3*(LENGTH/2.0));
            
            x2 = x0;
            y2 = (y0 + LENGTH);
        }
        /*4 oclock alignment*/
        else if (orientation == 4)
        {
            x1 = (x0 + ((Math.sqrt(3.0)/2.0)*LENGTH));
            y1 = (y0 - 3*(LENGTH/2.0));
            
            x2 = x0;
            y2 = (y0 - LENGTH);
        }
        /*6 oclock alignment*/
        else if (orientation == 6)
        {
            x1 = (x0 + Math.sqrt(3.0)*LENGTH);
            y1 = y0;
            
            x2 = (x0 + ((Math.sqrt(3.0)/2.0)*LENGTH));
            y2 = (y0 - (LENGTH/2.0));
        }
        /*8 oclock alignment*/
        else if (orientation == 8)
        {
            x1 = (x0 - ((Math.sqrt(3.0)/2.0)*LENGTH));
            y1 = (y0 - 3*(LENGTH/2.0));
            
            x2 = x0;
            y2 = (y0 - LENGTH);
        }
        /*10 oclock alignment*/
        else if (orientation == 10)
        {
            x1 = (x0 - ((Math.sqrt(3.0)/2.0)*LENGTH));
            y1 = (y0 + 3*(LENGTH/2.0));
            
            x2 = x0;
            y2 = (y0 + LENGTH);
        }
        
        xs = new int[4];
        ys = new int[4];
        
        xs[0] = (int)x0;
        xs[1] = (int)x1;
        xs[2] = (int)x2;
        xs[3] = (int)x0;
        
        ys[0] = (int)y0;
        ys[1] = (int)y1;
        ys[2] = (int)y2;
        ys[3] = (int)y0;
        
        polygon = new Polygon(xs, ys, 4);
        
        lineArray[0] = new Line(x0, y0, x1, y1, canvas);
        lineArray[1] = new Line(x1, y1, x2, y2, canvas);
        lineArray[2] = new Line(x2, y2, x0, y0, canvas);
        
        setValue(aType);
    }
    
    public void setValue(int aType)
    {
        portValue = aType;
        Color portColor;
        if (portValue == 1)
        {
            cardType = "Clay";
            portColor = new Color(210, 105, 30);
        }
        else if (portValue == 2)
        {
            cardType = "Lumber";
            portColor = new Color(0, 100, 0);
        }
        else if (portValue == 3)
        {
            cardType = "Ore";
            portColor = new Color(105, 105, 105);
        }
        else if (portValue == 4)
        {
            cardType = "Sheep";
            portColor = new Color(220, 220, 220);
        }
        else if (portValue == 5)
        {
            cardType = "Wheat";
            portColor = new Color(255, 215, 0);
        }
        else
        {
            cardType = "3-1";
            portColor = new Color(0, 0, 0);
        }
        for (int i = 0; i < 3; i++)
            lineArray[i].setColor(portColor);
        
    }
}
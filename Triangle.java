import objectdraw.*;
import java.awt.*;

public class Triangle
{
    private Line left, right, bottom;
    private Line[] fill;
    private DrawingCanvas canvas;
    private boolean moving = false;
    private double width, height, x, y;
    
    public Triangle(double par_x, double par_y, double par_width,
                    double par_height, DrawingCanvas cnv)
    {
        width = par_width;
        height = par_height;
        x = par_x;
        y = par_y;
        canvas = cnv;
        double delta = height*(double)3/2;
        fill = new Line[(int)height*2];
        
        left = new Line(x - width/2 - 1, y - height, x,
                        y - delta, canvas);
        right = new Line(x + width/2 + 1, y - height, x,
                         y - delta, canvas);
        bottom = new Line(x - width/2, y - height, x + width/2,
                          y - height, canvas);
        draw2(left.getStart().getX(), left.getEnd().getY(), 0, width);

    }
    /*public void begin()
    {
        int x = 100;
        int y = 100;
        double delta = height*(double)3/2;
        fill = new Line[(int)height*2];
        
        left = new Line(x - width/2 - 1, y - height, x,
                        y - delta, canvas);
        right = new Line(x + width/2 + 1, y - height, x,
                         y - delta, canvas);
        bottom = new Line(x - width/2, y - height, x + width/2,
                          y - height, canvas);
        draw2(left.getStart().getX(), left.getEnd().getY(), 0, width);
    }*/
    
    public void draw2(double x, double y, int index, double delta)
    {
        double minY = bottom.getStart().getY();
        if (y != minY)
        {
            fill[index++] = new Line(x - 1, minY, x + delta/2, y, canvas);
            fill[index++] = new Line(x+delta/2, y, x + delta + 1, minY, canvas);
            draw2(++x, ++y, index, delta-2);
        }
    }
    
    public void cleanCanvas()
    {
        for (int index = 0; index < fill.length; index++)
            fill[index].removeFromCanvas();
    }
    
    public void moveTo(double x, double y)
    {
        double deltaX = x - left.getStart().getX();
        double deltaY = y - left.getStart().getY();
        left.move(deltaX, deltaY);
        right.move(deltaX, deltaY);
        bottom.move(deltaX, deltaY);
        for (int index = 0; index < fill.length; index++)
        {
            if (fill[index] != null)
                fill[index].move(deltaX, deltaY);
        }
    }
    
    public void tr_setColor(Color color)
    {
        left.setColor(color);
        right.setColor(color);
        bottom.setColor(color);
        for (int index = 0; index < fill.length; index++)
        {
            if (fill[index] != null)
                fill[index].setColor(color);
        }
    }
    
    public void onMousePress(Location begin)
    {
        moving = true;
    }
    
    public void onMouseRelease(Location begin)
    {
        moving = false;
    }

    public void onMouseDrag(Location start)
    {
        if (moving)
            moveTo(start.getX(), start.getY());
    }
    
    public void tr_hide()
    {
        left.hide();
        right.hide();
        bottom.hide();
        for (int index = 0; index < fill.length; index++)
        {
            if (fill[index] != null)
                fill[index].hide();
        }
    }
    
    public void tr_show()
    {
        left.show();
        right.show();
        bottom.show();
        for (int index = 0; index < fill.length; index++)
        {
            if (fill[index] != null)
                fill[index].show();
        }
    }
    
    public void tr_sendToFront()
    {
        left.sendToFront();
        right.sendToFront();
        bottom.sendToFront();
        for (int index = 0; index < fill.length; index++)
        {
            if (fill[index] != null)
                fill[index].sendToFront();
        }
    }
}

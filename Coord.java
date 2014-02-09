import objectdraw.*;
import java.awt.*;

public class Coord
{
    private boolean available;
    private FilledOval areaTester;
    private FilledOval selectionBubble;
    private double xCoord;
    private double yCoord;
    private double areaRadius;
    private int selectionRadius;

    public Coord(Location aLocation, DrawingCanvas canvas)
    {
        xCoord = aLocation.getX();
        yCoord = aLocation.getY();
        areaRadius = Hexagon.LENGTH/2;
        selectionRadius = 15;
        areaTester = new FilledOval(xCoord - areaRadius, yCoord - areaRadius, Hexagon.LENGTH+11, Hexagon.LENGTH+11, canvas);
        areaTester.setColor(new Color(0, 189, 0, 125));
        areaTester.hide();
        selectionBubble = new FilledOval(xCoord - selectionRadius, yCoord - selectionRadius, selectionRadius*2, selectionRadius*2, canvas);
        selectionBubble.setColor(new Color(0,0, 255, 125));
        available = true;
        selectionBubble.hide();
    }

    public void showSelectionRadius()
    {
        selectionBubble.show();
    }

    public void hideSelectionBubble()
    {
        selectionBubble.hide();
    }

    public void showAreaTester()
    {
        areaTester.show();
    }

    public void hideAreaTester()
    {
        areaTester.hide();
    }

    public void changeTesterColor(Color colour)
    {
        areaTester.setColor(colour);
    }

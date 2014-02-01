public class Coord
{
    private boolean available;
    private filledOval areaTester;
    private filledOval selectionBubble;
    private double xCoord;
    private double yCoord;
    private int areaRadius;
    private int selectionRadius;
    
    public Coord(Location aLocation, DrawingCanvas canvas)
    {
        xCoord = aLocation.getX();
        yCoord = aLocation.getY();
        areaRadius = LENGTH/2;
        selectionRadius = 15;
        areaTester = new filledOval(xCoord - areaRadius, yCoord - areaRadius, LENGTH, LENGTH, canvas);
        areaTester.hide();
        selectionBubble = new filledOval(xCoord - selectionRadius, yCoord - selectionRadius, selectionRadius*2, selectionRadius*2, canvas);
        available = true;
        selectionBubble.hide();
    }
}
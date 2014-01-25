import objectdraw.*;
import java.awt.*;

public class HexagonTester extends WindowController
{
    private static int width = 1000;
    private static int height = 500;
    private static Hexagon[] hexagonArray = new Hexagon[19];
    
    public static void main (String[] args)
    {
        new HexagonTester().startController(width, height);
    }
    
    public void begin()
    {
        hexagonArray[0] = new Hexagon(150, 50, 0, canvas);
        hexagonArray[1] = new Hexagon(hexagonArray[0].getXCoord() + 1,hexagonArray[0].getYCoord(), 1, canvas);
        hexagonArray[2] = new Hexagon(hexagonArray[1].getXCoord() + 1,hexagonArray[1].getYCoord(), 2, canvas);
        hexagonArray[3] = new Hexagon(hexagonArray[0].jumpBackX() + 19,hexagonArray[0].jumpBackY(), 3, canvas);
        /*hexagonArray[4] = new Hexagon(hexagonArray[3].xs[2] + 1,hexagonArray[3].ys[2], 1, canvas);
        hexagonArray[5] = new Hexagon(hexagonArray[4].xs[2] + 1,hexagonArray[4].ys[2], 1, canvas);
        hexagonArray[6] = new Hexagon(hexagonArray[5].xs[2] + 1,hexagonArray[5].ys[2], 1, canvas);
        hexagonArray[7] = new Hexagon(hexagonArray[1].xs[2] + 1,hexagonArray[1].ys[2], 1, canvas);
        hexagonArray[8] = new Hexagon(hexagonArray[1].xs[2] + 1,hexagonArray[1].ys[2], 1, canvas);
        hexagonArray[9] = new Hexagon(hexagonArray[1].xs[2] + 1,hexagonArray[1].ys[2], 1, canvas);
        hexagonArray[10] = new Hexagon(hexagonArray[1].xs[2] + 1,hexagonArray[1].ys[2], 1, canvas);
        hexagonArray[11] = new Hexagon(hexagonArray[1].xs[2] + 1,hexagonArray[1].ys[2], 1, canvas);*/
        
        
    }
}
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
        hexagonArray[0] = new Hexagon(100, 100, 0, canvas);
        hexagonArray[1] = new Hexagon(hexagonArray[0].xs[2] + 1,hexagonArray[0].ys[2], 1, canvas);
        hexagonArray[2] = new Hexagon(hexagonArray[1].xs[2] + 1,hexagonArray[1].ys[2], 1, canvas);
        hexagonArray[3] = new Hexagon(hexagonArray[0].xs[4] + 1,hexagonArray[1].ys[2], 1, canvas);
        hexagonArray[4] = new Hexagon(hexagonArray[1].xs[2] + 1,hexagonArray[1].ys[2], 1, canvas);
        hexagonArray[5] = new Hexagon(hexagonArray[1].xs[2] + 1,hexagonArray[1].ys[2], 1, canvas);
        hexagonArray[6] = new Hexagon(hexagonArray[1].xs[2] + 1,hexagonArray[1].ys[2], 1, canvas);
        hexagonArray[7] = new Hexagon(hexagonArray[1].xs[2] + 1,hexagonArray[1].ys[2], 1, canvas);
        hexagonArray[8] = new Hexagon(hexagonArray[1].xs[2] + 1,hexagonArray[1].ys[2], 1, canvas);
        hexagonArray[9] = new Hexagon(hexagonArray[1].xs[2] + 1,hexagonArray[1].ys[2], 1, canvas);
        hexagonArray[10] = new Hexagon(hexagonArray[1].xs[2] + 1,hexagonArray[1].ys[2], 1, canvas);
        hexagonArray[11] = new Hexagon(hexagonArray[1].xs[2] + 1,hexagonArray[1].ys[2], 1, canvas);
    }
}
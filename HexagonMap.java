import objectdraw.*;
import java.awt.*;

public class HexagonMap extends WindowController
{
    private static int width = 1000;
    private static int height = 500;
    private static Hexagon[] hexagonArray = new Hexagon[19];
    
    public HexagonMap(DrawingCanvas canvas)
    {
        hexagonArray[0] = new Hexagon(150, 50, 0, canvas);
        hexagonArray[1] = new Hexagon(hexagonArray[0].getXCoord() + 2,hexagonArray[0].getYCoord(), 1, canvas);
        hexagonArray[2] = new Hexagon(hexagonArray[1].getXCoord() + 2,hexagonArray[1].getYCoord(), 2, canvas);
        
        hexagonArray[3] = new Hexagon(hexagonArray[0].jumpBackX() - 1,hexagonArray[0].jumpBackY() + 2, 3, canvas);
        hexagonArray[4] = new Hexagon(hexagonArray[3].getXCoord() + 2,hexagonArray[3].getYCoord(), 1, canvas);
        hexagonArray[5] = new Hexagon(hexagonArray[4].getXCoord() + 2,hexagonArray[4].getYCoord(), 1, canvas);
        hexagonArray[6] = new Hexagon(hexagonArray[5].getXCoord() + 2,hexagonArray[5].getYCoord(), 1, canvas);
        
        hexagonArray[7] = new Hexagon(hexagonArray[3].jumpBackX() - 1,hexagonArray[3].jumpBackY() + 2, 1, canvas);
        hexagonArray[8] = new Hexagon(hexagonArray[7].getXCoord() + 2,hexagonArray[7].getYCoord(), 1, canvas);
        hexagonArray[9] = new Hexagon(hexagonArray[8].getXCoord() + 2,hexagonArray[8].getYCoord(), 1, canvas);
        hexagonArray[10] = new Hexagon(hexagonArray[9].getXCoord() + 2,hexagonArray[9].getYCoord(), 1, canvas);
        hexagonArray[11] = new Hexagon(hexagonArray[10].getXCoord() + 2,hexagonArray[10].getYCoord(), 1, canvas);
        
        hexagonArray[12] = new Hexagon(hexagonArray[8].jumpBackX() - 1,hexagonArray[8].jumpBackY() + 2, 3, canvas);
        hexagonArray[13] = new Hexagon(hexagonArray[12].getXCoord() + 2,hexagonArray[12].getYCoord(), 1, canvas);
        hexagonArray[14] = new Hexagon(hexagonArray[13].getXCoord() + 2,hexagonArray[13].getYCoord(), 1, canvas);
        hexagonArray[15] = new Hexagon(hexagonArray[14].getXCoord() + 2,hexagonArray[14].getYCoord(), 1, canvas);
        
        hexagonArray[16] = new Hexagon(hexagonArray[13].jumpBackX() - 1,hexagonArray[13].jumpBackY() + 2, 3, canvas);
        hexagonArray[17] = new Hexagon(hexagonArray[16].getXCoord() + 2,hexagonArray[16].getYCoord(), 1, canvas);
        hexagonArray[18] = new Hexagon(hexagonArray[17].getXCoord() + 2,hexagonArray[17].getYCoord(), 2, canvas);
        
        for (int i = 0; i < 17; i++)
        {
            hexagonArray[i].setValue((int)(Math.random() * 6) + 1);
        }
    }
    
    public void onMousePress(Location point)
    {
        for (int i = 0; i < 19; i++)
        {
            if (hexagonArray[i].contains(point))
            {
                System.out.println(i + " is pressed!");
                break;
            }
        }
    }
}
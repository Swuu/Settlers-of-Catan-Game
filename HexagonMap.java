import objectdraw.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.event.*;

public class HexagonMap extends WindowController implements MouseMotionListener
{
    private static int width = 1000;
    private static int height = 500;
    private int[] numHex = new int[6];

    private static Hexagon[] hexagonArray = new Hexagon[19];
	private ArrayList<Coord> coords;
    
    public HexagonMap(DrawingCanvas canvas)
    {
        
        numHex[1] = 3; //Clay
        numHex[2] = 4; //Lumber
        numHex[3] = 3; //Ore
        numHex[4] = 4; //Sheep
        numHex[5] = 4; //Wheat
        numHex[0] = 1; //Desert

		coords = new ArrayList<Coord>();

        hexagonArray[0] = new Hexagon(200, 50, 0, canvas);
        hexagonArray[1] = new Hexagon(hexagonArray[0].getXCoord() + 2,hexagonArray[0].getYCoord(), 1, canvas);
        hexagonArray[2] = new Hexagon(hexagonArray[1].getXCoord() + 2,hexagonArray[1].getYCoord(), 2, canvas);
       
		coords.addAll(hexagonArray[0].getTopCoords());
		coords.addAll(hexagonArray[1].getTopRightCoords());
		coords.addAll(hexagonArray[2].getTopRightCoords());

 
        hexagonArray[3] = new Hexagon(hexagonArray[0].jumpBackX() - 1,hexagonArray[0].jumpBackY() + 2, 3, canvas);
        hexagonArray[4] = new Hexagon(hexagonArray[3].getXCoord() + 2,hexagonArray[3].getYCoord(), 1, canvas);
        hexagonArray[5] = new Hexagon(hexagonArray[4].getXCoord() + 2,hexagonArray[4].getYCoord(), 1, canvas);
        hexagonArray[6] = new Hexagon(hexagonArray[5].getXCoord() + 2,hexagonArray[5].getYCoord(), 1, canvas);

		coords.addAll(hexagonArray[3].getTopCoords());
		coords.addAll(hexagonArray[4].getTopRightCoords());
		coords.addAll(hexagonArray[5].getTopRightCoords());
		coords.addAll(hexagonArray[6].getTopRightCoords());
        
        hexagonArray[7] = new Hexagon(hexagonArray[3].jumpBackX() - 1,hexagonArray[3].jumpBackY() + 2, 1, canvas);
        hexagonArray[8] = new Hexagon(hexagonArray[7].getXCoord() + 2,hexagonArray[7].getYCoord(), 1, canvas);
        hexagonArray[9] = new Hexagon(hexagonArray[8].getXCoord() + 2,hexagonArray[8].getYCoord(), 1, canvas);
        hexagonArray[10] = new Hexagon(hexagonArray[9].getXCoord() + 2,hexagonArray[9].getYCoord(), 1, canvas);
        hexagonArray[11] = new Hexagon(hexagonArray[10].getXCoord() + 2,hexagonArray[10].getYCoord(), 1, canvas);
     
		coords.addAll(hexagonArray[7].getTopCoords());
		coords.addAll(hexagonArray[8].getTopRightCoords());
		coords.addAll(hexagonArray[9].getTopRightCoords());
		coords.addAll(hexagonArray[10].getTopRightCoords());
		coords.addAll(hexagonArray[11].getTopRightCoords());
		
		coords.addAll(hexagonArray[7].getBottomCoords());
		coords.addAll(hexagonArray[8].getBottomRightCoords());
		coords.addAll(hexagonArray[9].getBottomRightCoords());
		coords.addAll(hexagonArray[10].getBottomRightCoords());
		coords.addAll(hexagonArray[11].getBottomRightCoords());

	  
        hexagonArray[12] = new Hexagon(hexagonArray[8].jumpBackX() - 1,hexagonArray[8].jumpBackY() + 2, 3, canvas);
        hexagonArray[13] = new Hexagon(hexagonArray[12].getXCoord() + 2,hexagonArray[12].getYCoord(), 1, canvas);
        hexagonArray[14] = new Hexagon(hexagonArray[13].getXCoord() + 2,hexagonArray[13].getYCoord(), 1, canvas);
        hexagonArray[15] = new Hexagon(hexagonArray[14].getXCoord() + 2,hexagonArray[14].getYCoord(), 1, canvas);
   
		coords.addAll(hexagonArray[12].getBottomCoords());
		coords.addAll(hexagonArray[13].getBottomRightCoords());
		coords.addAll(hexagonArray[14].getBottomRightCoords());
		coords.addAll(hexagonArray[15].getBottomRightCoords());
     
        hexagonArray[16] = new Hexagon(hexagonArray[13].jumpBackX() - 1,hexagonArray[13].jumpBackY() + 2, 3, canvas);
        hexagonArray[17] = new Hexagon(hexagonArray[16].getXCoord() + 2,hexagonArray[16].getYCoord(), 1, canvas);
        hexagonArray[18] = new Hexagon(hexagonArray[17].getXCoord() + 2,hexagonArray[17].getYCoord(), 2, canvas);

		coords.addAll(hexagonArray[16].getBottomCoords());
		coords.addAll(hexagonArray[17].getBottomRightCoords());
		coords.addAll(hexagonArray[18].getBottomRightCoords());
        
        for (int i = 0; i < 19; i++)
        {
            boolean validType = false;
            while (validType == false)
            {
                int typeValue = (int)(Math.random()*6);
                if (numHex[typeValue] > 0)
                {
                    hexagonArray[i].setValue(typeValue);
                    numHex[typeValue]--;
                    validType = true;
                }
            }
        }
		canvas.addMouseMotionListener(this);
		begin();
    }

	public ArrayList<Coord> getCoords()
	{
		return coords;
	}
    
	public void mouseDragged(MouseEvent evt) {}
/*
    public void mouseMoved(MouseEvent evt)
    {
        for (int i = 0; i < 19; i++)
        {
            if (hexagonArray[i].contains(point))
            {
                System.out.println(i + " is pressed!");
                break;
            }
        }
    }*/

	public void mouseMoved(MouseEvent evt)
	{
		for(Coord e: coords)
		{
			if(e.contains(new Location(evt.getX(), evt.getY())))
			{
				e.showSelectionRadius();
			}
			else
			{
				e.hideSelectionRadius();
			}
		}
	}
	
}

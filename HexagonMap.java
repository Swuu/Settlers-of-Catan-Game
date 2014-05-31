import objectdraw.*; 

import java.awt.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.event.*;

public class HexagonMap extends WindowController implements MouseMotionListener, MouseListener
{
    private static int width = 1000;
    private static int height = 500;
    private int[] numHex = new int[6];
    private int[] numNum = new int[13];
    private int selectCoord = 0;
    private boolean buyRoad = false;
    private static SettlementShape mapSettlement;
    private static SettlementShape mapCity;

    private CatanGame game;
    private DrawingCanvas canvas;

    private static Hexagon[] hexagonArray = new Hexagon[19];
    private static Port[] portArray = new Port[12];
    private static ArrayList<Coord> coords;
   
    public Hexagon[] getHexagonArray ()
    {
        return hexagonArray;
    }
 
    public HexagonMap(DrawingCanvas aCanvas, CatanGame aGame)
    {
        canvas = aCanvas;
        game = aGame;
        mapSettlement = new SettlementShape(0, 0, 0, canvas);
        mapCity = new SettlementShape(0, 0, 0, canvas);
        mapCity.makeCity();
        mapCity.sendToFront();
        mapCity.hide();
        mapSettlement.sendToFront();
        mapSettlement.hide();
        numHex[1] = 3; //Clay
        numHex[2] = 4; //Lumber
        numHex[3] = 3; //Ore
        numHex[4] = 4; //Sheep
        numHex[5] = 4; //Wheat
        numHex[0] = 1; //Desert
        
        numNum[0] = 0;
        numNum[1] = 0;
        numNum[2] = 1;
        numNum[3] = 2;
        numNum[4] = 2;
        numNum[5] = 2;
        numNum[6] = 2;
        numNum[7] = 0;
        numNum[8] = 2;
        numNum[9] = 2;
        numNum[10] = 2;
        numNum[11] = 2;
        numNum[12] = 1;

		coords = new ArrayList<Coord>();
        
        hexagonArray[0] = new Hexagon(200, 70, 0, 0, canvas);
        hexagonArray[1] = new Hexagon(hexagonArray[0].getXCoord(2) + 2,hexagonArray[0].getYCoord(2), 1, 0, canvas);
        hexagonArray[2] = new Hexagon(hexagonArray[1].getXCoord(2) + 2,hexagonArray[1].getYCoord(2), 2, 0, canvas);
       
		coords.addAll(hexagonArray[0].getTopCoords());
		coords.addAll(hexagonArray[1].getTopRightCoords());
		coords.addAll(hexagonArray[2].getTopRightCoords());

 
        hexagonArray[3] = new Hexagon(hexagonArray[0].jumpBackX() - 1,hexagonArray[0].jumpBackY() + 2, 3, 0, canvas);
        hexagonArray[4] = new Hexagon(hexagonArray[3].getXCoord(2) + 2,hexagonArray[3].getYCoord(2), 1, 0, canvas);
        hexagonArray[5] = new Hexagon(hexagonArray[4].getXCoord(2) + 2,hexagonArray[4].getYCoord(2), 1, 0, canvas);
        hexagonArray[6] = new Hexagon(hexagonArray[5].getXCoord(2) + 2,hexagonArray[5].getYCoord(2), 1, 0, canvas);

		coords.addAll(hexagonArray[3].getTopCoords());
		coords.addAll(hexagonArray[4].getTopRightCoords());
		coords.addAll(hexagonArray[5].getTopRightCoords());
		coords.addAll(hexagonArray[6].getTopRightCoords());
        
        hexagonArray[7] = new Hexagon(hexagonArray[3].jumpBackX() - 1,hexagonArray[3].jumpBackY() + 2, 1, 0, canvas);
        hexagonArray[8] = new Hexagon(hexagonArray[7].getXCoord(2) + 2,hexagonArray[7].getYCoord(2), 1, 0, canvas);
        hexagonArray[9] = new Hexagon(hexagonArray[8].getXCoord(2) + 2,hexagonArray[8].getYCoord(2), 1, 0, canvas);
        hexagonArray[10] = new Hexagon(hexagonArray[9].getXCoord(2) + 2,hexagonArray[9].getYCoord(2), 1, 0, canvas);
        hexagonArray[11] = new Hexagon(hexagonArray[10].getXCoord(2) + 2,hexagonArray[10].getYCoord(2), 1, 0, canvas);
     
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

	  
        hexagonArray[12] = new Hexagon(hexagonArray[8].jumpBackX() - 1,hexagonArray[8].jumpBackY() + 2, 3, 0, canvas);
        hexagonArray[13] = new Hexagon(hexagonArray[12].getXCoord(2) + 2,hexagonArray[12].getYCoord(2), 1, 0, canvas);
        hexagonArray[14] = new Hexagon(hexagonArray[13].getXCoord(2) + 2,hexagonArray[13].getYCoord(2), 1, 0, canvas);
        hexagonArray[15] = new Hexagon(hexagonArray[14].getXCoord(2) + 2,hexagonArray[14].getYCoord(2), 1, 0, canvas);
   
		coords.addAll(hexagonArray[12].getBottomCoords());
		coords.addAll(hexagonArray[13].getBottomRightCoords());
		coords.addAll(hexagonArray[14].getBottomRightCoords());
		coords.addAll(hexagonArray[15].getBottomRightCoords());
     
        hexagonArray[16] = new Hexagon(hexagonArray[13].jumpBackX() - 1,hexagonArray[13].jumpBackY() + 2, 3, 0, canvas);
        hexagonArray[17] = new Hexagon(hexagonArray[16].getXCoord(2) + 2,hexagonArray[16].getYCoord(2), 1, 0, canvas);
        hexagonArray[18] = new Hexagon(hexagonArray[17].getXCoord(2) + 2,hexagonArray[17].getYCoord(2), 2, 0, canvas);

		coords.addAll(hexagonArray[16].getBottomCoords());
		coords.addAll(hexagonArray[17].getBottomRightCoords());
		coords.addAll(hexagonArray[18].getBottomRightCoords());
        
        portArray[0] = new Port(hexagonArray[0].getXCoord(1) + 1,hexagonArray[0].getYCoord(1) - 2, 4, 0, canvas);
        portArray[1] = new Port(hexagonArray[1].getXCoord(1) + 1,hexagonArray[1].getYCoord(1) - 2, 4, 0, canvas);
        
        portArray[2] = new Port(hexagonArray[2].getXCoord(2) + 2,hexagonArray[2].getYCoord(2), 4, 2, canvas);
        portArray[3] = new Port(hexagonArray[6].getXCoord(2) + 2,hexagonArray[6].getYCoord(2), 4, 2, canvas);
        
        portArray[4] = new Port(hexagonArray[15].getXCoord(3) + 2,hexagonArray[15].getYCoord(3), 4, 4, canvas);
        portArray[5] = new Port(hexagonArray[18].getXCoord(3) + 2,hexagonArray[18].getYCoord(3), 4, 4, canvas);
        
        portArray[6] = new Port(hexagonArray[17].getXCoord(4) + 1,hexagonArray[17].getYCoord(4) + 2, 4, 6, canvas);
        portArray[7] = new Port(hexagonArray[16].getXCoord(4) + 1,hexagonArray[16].getYCoord(4) + 2, 4, 6, canvas);
        
        portArray[8] = new Port(hexagonArray[16].getXCoord(5) - 2,hexagonArray[16].getYCoord(5), 4, 8, canvas);
        portArray[9] = new Port(hexagonArray[12].getXCoord(5) - 2,hexagonArray[12].getYCoord(5), 4, 8, canvas);

        portArray[10] = new Port(hexagonArray[3].getXCoord(0) - 2,hexagonArray[3].getYCoord(0), 4, 10, canvas);
        portArray[11] = new Port(hexagonArray[0].getXCoord(0) - 2,hexagonArray[0].getYCoord(0), 4, 10, canvas);
        
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
        
        for (int j = 0; j < 19; j++)
        {
            boolean validNum = false;
            if (hexagonArray[j].getHexValue() == 0)
                j++;
            while (validNum == false)
            {
                int numValue = (int)(Math.random()*13);
                if (numNum[numValue] > 0)
                {
                    hexagonArray[j].setRollValue(numValue);
                    numNum[numValue]--;
                    validNum = true;
                }
            }
            
        }
		canvas.addMouseMotionListener(this);
		begin();
    }
    
    //newSettlement determines if selectCoordOn is for creating a new Settlement or upgrading a Settlement into a City
    public void selectCoordOn(int newSettlement)
    {
        if (newSettlement == 1)
            selectCoord = 1;
        else if (newSettlement == 2)
            selectCoord = 2;
        else if (newSettlement == 3)
            selectCoord = 3;
        game.toggleButtons(false);
    }

    public void selectCoordOff()
    {
        selectCoord = 0;
        game.toggleButtons(true);
    }
    
    public void buyRoadOn()
    {
        buyRoad = true;
    }
    
    public void buyRoadOff()
    {
        buyRoad = false;
    }
    
    public boolean canPushButtons()
    {
        return buyRoad==false && selectCoord == 0;
    }
    
    public boolean hasAvailableCoord()
    {
        for (Coord e: coords)
        {
            if (e.isAvailable())
                return true;
        }
        return false;
    }
    
    public boolean hasUpgradeableCoord()
    {
        for (Coord e: coords)
        {
            if (e.isUpgradeable() && e.coordSettlement.getPlayer() == game.currentPlayer())
                return true;
        }
        return false;
    }
    
	public ArrayList<Coord> getCoords()
	{
		return coords;
	}
   
	public void retResource(Hexagon hexagon)
	{
			ArrayList<Location> locs = hexagon.getVerticies();
			for(Location l: locs)
			{
				for(Coord e: coords)
           		{
                	if(!e.isAvailable()&&e.contains(l)&&!hexagon.embargoStatus())
                	{
                    	e.coordSettlement.getPlayer().addCard(hexagon.getHexValue());
					}
                }
            }
	}
 
	public void mouseDragged(MouseEvent evt) {}

	public void mouseMoved(MouseEvent evt)
	{
		if (selectCoord == 1 || selectCoord == 2)
        {
            if (selectCoord == 1)
            {
                mapSettlement.show();
                mapCity.hide();
            }
            if (selectCoord == 2)
            {
                mapCity.show();
                mapSettlement.hide();
            }
            canvas.addMouseListener(this);
            for(Coord e: coords)
            {
                if(e.contains(new Location(evt.getX(), evt.getY())))
                {
                    if (selectCoord == 1)
                        mapSettlement.moveTo(e.location());
                    if (selectCoord == 2)
                        mapCity.moveTo(e.location());
                    if (selectCoord == 1)
                        canBuildSettlement(evt, false);
                    else if (selectCoord == 2)
                        canUpgradeSettlement(evt, false);
                    e.showSelectionRadius();
					break;	
                }
                else
                {
                    if (selectCoord == 1)
                        mapSettlement.moveTo(evt.getX(), evt.getY());
                    if (selectCoord == 2)
                        mapCity.moveTo(evt.getX(), evt.getY());
                    e.hideSelectionRadius();
                }
            }
        }
        if (selectCoord == 3)
        {
            for(int i = 0; i < 19; i++)
            {
                if(hexagonArray[i].bubbleContains(new Location(evt.getX(), evt.getY())))
                {
                    hexagonArray[i].showSelectionBubble();
                }
                else
                {
                    hexagonArray[i].hideSelectionBubble();
                }
            }
        }
    }

	public void mouseEntered(MouseEvent evt) {}

	public void mouseExited(MouseEvent evt) {}
	
	public void mousePressed(MouseEvent evt) {}

	public void mouseReleased(MouseEvent evt) {}

    public void mouseClicked(MouseEvent evt)
    {
        if (selectCoord == 1)
        {
            canBuildSettlement(evt, true);
        }
        else if (selectCoord == 2)
        {
            canUpgradeSettlement(evt, true);
        }
        else if (selectCoord == 3)
        {
            moveEmbargo(evt);
        }
    }
    
    public void moveEmbargo(MouseEvent evt)
    {
        for(int i = 0; i < 19; i++)
        {
            if(hexagonArray[i].bubbleContains(new Location(evt.getX(), evt.getY())))
            {
                hexagonArray[i].embargoOn();
                selectCoordOff();
                hexagonArray[i].hideSelectionBubble();
            }
            else
            {
                hexagonArray[i].embargoOff();
            }
        }
    }
    
    public void canUpgradeSettlement(MouseEvent evt, boolean build)
    {
        for (Coord e: coords)
        {
            if (e.contains(new Location(evt.getX(), evt.getY()))&& e.isUpgradeable() && game.currentPlayer()==e.coordSettlement.getPlayer())
            {
                for(Coord c: coords)
                {
                    if(c==e && !c.isAvailable() && c.isUpgradeable())
                    {
                        c.changeSelcColor(new Color(0, 145, 11, 125));
                        if (build)
                        {
                            e.coordSettlement.makeCity();
                            e.makeUnupgradeable();
                            e.hideSelectionRadius();
                            mapCity.hide();
                            selectCoordOff();
                        }
                    }
                }
            }
            else
            {
                e.changeSelcColor(new Color(145, 0, 11, 125));
            }
        }
    }
    
    public void canBuildSettlement(MouseEvent evt, boolean build)
    {
    	for (Coord e: coords)
        {
            if (e.contains(new Location(evt.getX(), evt.getY()))&&e.isAvailable())
            {
            	e.changeSelcColor(new Color(0, 145, 11, 125));
                for(Coord c: coords)
            	{
            		if(c!=e&&!c.isAvailable()&&c.isHabitable(e.location())) 
            		{
            			e.changeSelcColor(new Color(145, 0, 11, 125));
            			return;
            		}
            	}
            	if(build)
            	{
            		e.coordSettlement = new GameSettlement(e, game.currentPlayer(), canvas);
                    e.makeUpgradeable();
                    e.hideSelectionRadius();
                    e.changeAvailability(false);
                    mapSettlement.hide();
                    selectCoordOff();
            	}
            }
        }
    }
}

/*WARNING: Be careful when modifiying this file on Dropbox!! If more than one person is modifiying it at a time and you
 * both save, then there is a chance for data loss and we all lose!*/
import java.awt.*;
import java.util.ArrayList;
import objectdraw.*;
import javax.swing.JTextArea;

public class Player implements CatanController
{
	private int player;
	public String playerName;
	private int victoryPoints;
	private int numClay;
	private int numLumber;
	private int numOre;
	private int numSheep;
	private int numWheat;
	private int numKnight;
	private int numBonusPoint;
	private int numMonopoly;
	private int numRoadBuilding;
	private int numYearOfPlenty;
	private int totalCards;
	private DrawingCanvas canvas, canvas2;
	private ArrayList <ResourceCard> hand;
 	public static boolean displayingResourceCards;
	public static JTextArea info;
 
	public Player(int currentPlayer, String currentPlayerName, DrawingCanvas aCanvas, DrawingCanvas aCanvas2, JTextArea out)
	{
		player = currentPlayer;
		playerName = currentPlayerName;
		canvas = aCanvas;
		canvas2 = aCanvas2;
		victoryPoints = 0;
		info = out;
	}
 
	public void addCard(int cardNumber)
	{
		if (cardNumber == 1)
 		{
			numClay++;
		}
		else if (cardNumber == 2)
		{
			numLumber++;
		}
		else if (cardNumber == 3)
		{
			numOre++;
		}
		else if (cardNumber == 4)
		{
			numSheep++;
		}
		else if (cardNumber == 5)
		{
			numWheat++;
		}
        totalCards = numClay + numLumber + numOre + numSheep + numWheat;
		//displayResourceHand();
	}
 	
 	public void addDevelopmentCard()
	{
		int dCardNumber = (int)(Math.random()*25);
		if (dCardNumber < 14)
 		{
			numKnight++;
		}
		else if (dCardNumber < 19)
		{
			numBonusPoint++;
		}
		else if (dCardNumber < 21)
		{
			numMonopoly++;
		}
		else if (dCardNumber < 23)
		{
			numRoadBuilding++;
		}
		else if (dCardNumber < 25)
		{
			numYearOfPlenty++;
		}
		//displayDevelopmentHand();
	}
 	
	public boolean buyItem(Item item)
	{
		if (numClay >= item.priceClay && numLumber >= item.priceLumber && numOre >= item.priceOre && numSheep >= item.priceSheep && numWheat >= item.priceWheat)
		{
			numClay -= item.priceClay;
			numLumber -= item.priceLumber;
			numOre -= item.priceOre;
			numSheep -= item.priceSheep;
			numWheat -= item.priceWheat;
		}
		else
		{
			//System.out.println("You've not enough minerals");
			info.append("You've not enough minerals\n");
            return false;
		}
        totalCards = numClay + numLumber + numOre + numSheep + numWheat;
		displayResourceHand();
        return true;
	}
	
	public void buyDevelopmentCard()
	{
		Item item = DCARD;
		if (numClay >= item.priceClay && numLumber >= item.priceLumber && numOre >= item.priceOre && numSheep >= item.priceSheep && numWheat >= item.priceWheat)
		{
			numClay -= item.priceClay;
			numLumber -= item.priceLumber;
			numOre -= item.priceOre;
			numSheep -= item.priceSheep;
			numWheat -= item.priceWheat;
			addDevelopmentCard();
		}
		else
		{
			info.append("You've not enough minerals\n");
		}
		//displayResourceHand();
	}
 
	public void displayResourceHand()
	{
		canvas2.clear();
		int x = 10;
		int y = 10;
        int increment;
        totalCards = numClay + numLumber + numOre + numSheep + numWheat;
        if (totalCards <= 7)
            increment = 110;
        else
        {
            increment  = 670/totalCards;
        }
		for(int i = 0; i < numClay; i++)
		{
			new ResourceCard( 1, canvas2).displayCard(new Location( x, y));
			x+=increment;
		}
		for(int i = 0; i < numLumber; i++)
		{
			new ResourceCard( 2, canvas2).displayCard(new Location( x, y));
			x+=increment;
		}
		for(int i = 0; i < numOre; i++)
		{
			new ResourceCard( 3, canvas2).displayCard(new Location( x, y));
			x+=increment;
		}
		for(int i = 0; i < numSheep; i++)
		{
			new ResourceCard( 4, canvas2).displayCard(new Location( x, y));
			x+=increment;
		}
		for(int i = 0; i < numWheat; i++)
		{
			new ResourceCard( 5, canvas2).displayCard(new Location( x, y));
			x+=increment;
		}
		displayingResourceCards = true;
		
	}
	
	public void displayDevelopmentHand()
	{
		canvas2.clear();
		int x = 10;
		int y = 30;
		for(int i = 0; i < numKnight; i++)
		{
			new DevelopmentCard( 1, canvas2).displayCard(new Location( x, y));
			x+=110;
		}
		for(int i = 0; i < numBonusPoint; i++)
		{
			new DevelopmentCard( 2, canvas2).displayCard(new Location( x, y));
			x+=110;
		}
		for(int i = 0; i < numMonopoly; i++)
		{
			new DevelopmentCard( 3, canvas2).displayCard(new Location( x, y));
			x+=110;
		}
		for(int i = 0; i < numRoadBuilding; i++)
		{
			new DevelopmentCard( 4, canvas2).displayCard(new Location( x, y));
			x+=110;
		}
		for(int i = 0; i < numYearOfPlenty; i++)
		{
			new DevelopmentCard( 5, canvas2).displayCard(new Location( x, y));
			x+=110;
		}
			//new Hexagon(80, 80);
		displayingResourceCards = false;
	}
	
	public void hideHand()
	{
	    canvas2.clear();
	}
	
	/* for trading purposes:
	    1) gives current quantities of resources
	    2) updates after a successful trade
	*/
	
	public int getClay() { return numClay; }
	public int getLumber() { return numLumber; }
	public int getOre() { return numOre; }
	public int getSheep() { return numSheep; }
    public int getWheat() { return numWheat; }
    
    public void updateResources(int qtyClay, int qtyLumber, int qtyOre,
                                int qtySheep, int qtyWheat)
    {
        numClay = qtyClay;
        numLumber = qtyLumber;
        numOre = qtyOre;
        numSheep = qtySheep;
        numWheat = qtyWheat;
    }
    
    public String getName()
    {
        return playerName;
    }
}

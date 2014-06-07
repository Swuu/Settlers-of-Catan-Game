import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
public class TitleScreen
{
	public ArrayList <String> names;
	
	public static void main(String[] args)
 	{
		int numPlayers;
  		Scanner scanner = new Scanner(System.in);
  		System.out.println("Welcome to Settlers of Catan!  \nHow many players will be playing?" +
       				   " (Pick between 3-4)");
  		ArrayList <String> names = new ArrayList<String>();
  		while(true)
  		{
   			numPlayers = scanner.nextInt();
   			if (numPlayers >= 3 && numPlayers <= 4)
   			{
	   			break;
   			}
   			else
   			{
	  			System.out.println("Please pick a number of players between 3 and 4\n");
   			}
  		}
  		for(int playerIndex = 0; playerIndex < numPlayers; playerIndex++)
  		{
   			while(true)
	  		{
	  			Scanner scanner2 =  new Scanner(System.in);
	    			System.out.println("\nPlayer " + (playerIndex + 1) + " Please enter your name.");
	    			String name = scanner2.nextLine();
	    			if (name.equals(""))
	    			{
	      				System.out.println("You must input a name");
	    			}
	    			else
	    			{
	      				names.add(name);
	      				break;
	   			}
	  		}
	 	 }
		System.out.println("Game Setup Complete. Let the game begin!");
	 }
}

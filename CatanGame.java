import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JTextArea;

public class CatanGame extends WindowController implements ActionListener, CatanController, Runnable
{
    private static Player playerOne;
 	private static Player playerTwo;
 	private static Player playerThree;
 	private static Player playerFour;
    private static HexagonMap gameBoard;
 	private static Player currentPlayer;
 	private static Text currentName;
 	private static int currentTurn;
 	private JTextArea info;
 	private static int menu;
 	private static JButton buttonOne;
 	private static JButton buttonTwo;
 	private static JButton buttonThree;
 	private static JButton buttonFour;
 	private static JButton buttonFive;
 	private static ArrayList<String> names;
 	private static ArrayList<Player> playerList;
	private static JDrawingCanvas canvas;
    private static JDrawingCanvas canvas2;
 
	public void run()
	{
		canvas= new JDrawingCanvas(1000,500);
		canvas2 = new JDrawingCanvas(800, 100);
        gameBoard = new HexagonMap(canvas);
		info = new JTextArea(5, 5);
		info.setEditable(false);
		playerList = new ArrayList<Player>();
  		playerOne = new Player(1, names.get(0), canvas, info);
  		playerList.add(playerOne);
  		playerTwo = new Player(2, names.get(1), canvas, info);
  		playerList.add(playerTwo);
  		playerThree = new Player(3, names.get(2), canvas, info);
  		playerList.add(playerThree);
  		if (names.size() == 4)
  		{
  			playerFour = new Player(4, names.get(3), canvas, info);
  			playerList.add(playerFour);
  		}
  		currentTurn = 0;
  		currentPlayer = playerList.get(currentTurn);
		currentPlayer.displayResourceHand(canvas2);
		currentName = new Text(currentPlayer.getName() + "'s turn.", canvas.getWidth()/2+700, canvas.getHeight()/2, canvas);
                currentName.setColor(Color.BLUE);
                info.append(currentPlayer.getName() + "'s turn.\n");
		

		JFrame frame = new JFrame("The Settlers of Catan");	//(JFrame) SwingUtilities.getWindowAncestor(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(getContentPane());		

		Container contentPane = getContentPane();
		contentPane.add(canvas);
    
		JPanel topPanel = new JPanel();
		//topPanel.add(new JScrollPane(info));
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JPanel bottomPanel = new JPanel();
        bottomPanel.add(canvas2, BorderLayout.SOUTH);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
		contentPane.add(new JScrollPane(info), BorderLayout.NORTH);
		JPanel rightPanel = new JPanel(new GridLayout(6, 1));
		buttonOne  = new JButton("Add Resource Card");
		buttonOne.addActionListener(this);
		buttonTwo  = new JButton("Buy Item");
		buttonTwo.addActionListener(this);
		buttonThree  = new JButton("Show Items");
		buttonThree.addActionListener(this);
		buttonFour = new JButton("Trade Resources");
		buttonFour.addActionListener(this);
		buttonFive = new JButton("Finish Turn");
		buttonFive.addActionListener(this);
		menu = 0;
		
		rightPanel.add(new JScrollPane(info));
		rightPanel.add(buttonOne);
		rightPanel.add(buttonTwo);
		rightPanel.add(buttonThree);
		rightPanel.add(buttonFour);
		rightPanel.add(buttonFive);
		
		contentPane.add(rightPanel, BorderLayout.EAST);
		//init();
		validate();
		frame.pack();
		frame.setVisible(true);
	}

	

	public void actionPerformed(ActionEvent evt) 
	{
		if (menu == 0)
		{
        	if (evt.getSource() == buttonOne)
			{
				currentPlayer.addCard((int)(Math.random()*5 + 1));
				if (currentPlayer.displayingResourceCards == true)
				{
					currentPlayer.displayResourceHand(canvas2);
				}
				else
				{
					currentPlayer.displayDevelopmentHand(canvas2);
				}
			}
			else if(evt.getSource() == buttonTwo)
			{
				buttonOne.setText("Road");
				buttonTwo.setText("Settlement");
				buttonThree.setText("City");
				buttonFour.setText("Development Card");
				buttonFive.setText("Back");
				menu = 1;
			}
			else if(evt.getSource() == buttonThree)
			{
				if (currentPlayer.displayingResourceCards == true)
				{
					currentPlayer.displayDevelopmentHand(canvas2);
					buttonThree.setText("Show Resources");
				}
				else
				{
					currentPlayer.displayResourceHand(canvas2);
					buttonThree.setText("Show Items");
				}
			}
			else if(evt.getSource() == buttonFour)
			{
				buttonOne.setText(playerOne.playerName);
				buttonTwo.setText(playerTwo.playerName);
				buttonThree.setText(playerThree.playerName);				
				if (names.size() == 4)
					buttonFour.setText(playerFour.playerName);
				else
				{
					buttonFour.setText("");
				    buttonFour.setEnabled(false);
				}
					
				if (currentTurn == 0)
				    buttonOne.setEnabled(false);
				else if (currentTurn == 1)
				    buttonTwo.setEnabled(false);
				else if (currentTurn == 2)
				    buttonThree.setEnabled(false);
				else if (currentTurn == 3)
				    buttonFour.setEnabled(false);
				
				buttonFive.setText("Back");
				menu = 2;
			}
			else
			{
				if (currentTurn == playerList.size() - 1)
				{
					currentTurn = 0;
				}
				else
				{
					currentTurn++;
				}
				currentPlayer = playerList.get(currentTurn);
				currentPlayer.displayResourceHand(canvas2);
				info.append(currentPlayer.getName() + "'s turn.\n");
                                currentName.setText(currentPlayer.getName() + "'s turn.");
                                switch(currentTurn)
                                {
                                        default:
                                                currentName.setColor(Color.BLUE);
                                                break;
                                        case 1:
                                                currentName.setColor(Color.RED);
                                                break;
                                        case 2:
                                                currentName.setColor(Color.GREEN);
                                                break;
                                        case 3:
                                                currentName.setColor(Color.YELLOW);
                                                break;
                                }

			}
		}
		
		/* a list of items to buy */
		else if (menu == 1)
		{
			if (evt.getSource() == buttonOne)
			{
				currentPlayer.buyItem(ROAD, canvas2);
				if (currentPlayer.displayingResourceCards == true)
				{
					currentPlayer.displayResourceHand(canvas2);
				}
				else
				{
					currentPlayer.displayDevelopmentHand(canvas2);
				}
			}
			else if (evt.getSource() == buttonTwo)
			{
				currentPlayer.buyItem(SETTLEMENT, canvas2);
				if (currentPlayer.displayingResourceCards == true)
				{
					currentPlayer.displayResourceHand(canvas2);
				}
				else
				{
					currentPlayer.displayDevelopmentHand(canvas2);
				}
			}
			else if (evt.getSource() == buttonThree)
			{
				currentPlayer.buyItem(CITY, canvas2);
				if (currentPlayer.displayingResourceCards == true)
				{
					currentPlayer.displayResourceHand(canvas2);
				}
				else
				{
					currentPlayer.displayDevelopmentHand(canvas2);
				}
			}
			else if(evt.getSource() == buttonFour)
			{
				currentPlayer.buyDevelopmentCard();
				if (currentPlayer.displayingResourceCards == true)
				{
					currentPlayer.displayResourceHand(canvas2);
				}
				else
				{
					currentPlayer.displayDevelopmentHand(canvas2);
				}
			}
			else
			{
			}
			returnToMainMenu();
		}
		
		/* after clicking on "trade"
		    a list of players to trade with */ 
		else if (menu == 2)
		{
			if (evt.getSource() == buttonOne)
			{
			    OpenTrade tradewindow = new OpenTrade(currentPlayer,
			                                            playerOne, canvas2);
			    SwingUtilities.invokeLater(tradewindow);
			}
			else if (evt.getSource() == buttonTwo)
			{
			    OpenTrade tradewindow = new OpenTrade(currentPlayer,
			                                            playerTwo, canvas2);
			    SwingUtilities.invokeLater(tradewindow);
			}
			else if (evt.getSource() == buttonThree)
			{
			    OpenTrade tradewindow = new OpenTrade(currentPlayer,
			                                            playerThree, canvas2);
			    SwingUtilities.invokeLater(tradewindow);
			}
			else if (evt.getSource() == buttonFour)
            {
			    OpenTrade tradewindow = new OpenTrade(currentPlayer,
			                                            playerFour, canvas2);
			    SwingUtilities.invokeLater(tradewindow);
			}
			returnToMainMenu();
		}
    }
 	
 	public static void returnToMainMenu()
 	{
 		buttonOne.setText("Add Resource Card");
		buttonTwo.setText("Buy Item");
		buttonThree.setText("Swap Displays");
		buttonFour.setText("Trade Resources");
		buttonFive.setText("Finish Turn");
		menu = 0;
		
		buttonOne.setEnabled(true);
		buttonTwo.setEnabled(true);
		buttonThree.setEnabled(true);
		buttonFour.setEnabled(true);
 	}
 	
	public static void main(String[] args)
	{
		int numPlayers;
  		Scanner scanner = new Scanner(System.in);
  		System.out.println("Welcome to Settlers of Catan!  \nHow many players will be playing?" +
       				   " (Pick between 3-4)");
  		names = new ArrayList<String>();
  		while(true)
  		{
			String stringPlayers;
   			stringPlayers = scanner.next();
			try
			{
				numPlayers = Integer.parseInt(stringPlayers);
			}
			catch (NumberFormatException nfe)
			{
				numPlayers = 0;
			}
   			if (numPlayers == 3 || numPlayers == 4) 
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
				boolean sameName = false;
				char[] nameChars = name.toCharArray();
        			nameChars[0] = Character.toUpperCase(nameChars[0]);
        			String finalName = new String(nameChars); 
        			name = finalName;
	    			if (name.equals(""))
	    			{
	      				System.out.println("You must input a name");
	    			}
	    			else
	    			{
					for(int i = 0; i < names.size(); i++)
					{
						if (name.equals(names.get(i)))
						{
							sameName = true;
							System.out.println("You must input a unique name");
							break;
						}
						else
						{
							sameName = false;
						}
					}
					if (sameName == false)
					{
	      					names.add(name);
	      					break;
					}
	   			}
	  		}
	 	 }
		System.out.println("\nGame Setup Complete. Let the game begin!");
		//new CatanGame().startController(1300, 800);
		CatanGame settlers = new CatanGame();
		SwingUtilities.invokeLater(settlers);
	}
} 

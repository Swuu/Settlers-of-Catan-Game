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

public class CatanGame extends WindowController implements ActionListener, 
                                                    CatanController, Runnable,
                                                    KeyListener
{
    private static Player playerOne;
 	private static Player playerTwo;
 	private static Player playerThree;
 	private static Player playerFour;
 	private static Player currentPlayer;
    private static int numPlayers;
    
    //private static ArrayList<String> names;
 	private static ArrayList<Player> playerList;
    
    private static HexagonMap gameBoard;
    private static CatanSetup startGame;
 	private static Text currentName;
 	private static int currentTurn;
 	
    private JTextArea info;
 	private static int menu;
 	private static JButton buttonOne;
 	private static JButton buttonTwo;
 	private static JButton buttonThree;
 	private static JButton buttonFour;
 	private static JButton buttonFive;
 	private static JButton rollDice;
	
    private static JDrawingCanvas canvas;
    private static JDrawingCanvas canvas2;
    private OpenTrade tradewindow;
    private DiceRoll rolling;
    private JFrame frame;
 
	public void run()
	{
		canvas= new JDrawingCanvas(1000,500);
		canvas2 = new JDrawingCanvas(800, 100);

		generateBackground(canvas); 
		
		gameBoard = new HexagonMap(canvas);
		
        info = new JTextArea(5, 5);
		info.setEditable(false);
		
        numPlayers = startGame.getNumOfPlayers();
        playerList = new ArrayList<Player>();
  		playerOne = new Player(1, startGame.getName(0), canvas, canvas2, info);
  		playerList.add(playerOne);
  		playerTwo = new Player(2, startGame.getName(1), canvas, canvas2, info);
  		playerList.add(playerTwo);
  		if (numPlayers == 3)
        {
            playerThree = new Player(3, startGame.getName(2), canvas, canvas2,
                                                                    info);
            playerList.add(playerThree);
  		}
        else if (numPlayers == 4)
  		{
  			playerThree = new Player(3, startGame.getName(2), canvas, canvas2,
  			                                                        info);
            playerList.add(playerThree);
            playerFour = new Player(4, startGame.getName(3), canvas, canvas2,
                                                                    info);
  			playerList.add(playerFour);
  		}
  		currentTurn = 0;
  		currentPlayer = playerList.get(currentTurn);
		currentPlayer.displayResourceHand();
		currentName = new Text(currentPlayer.getName() + "'s turn.", 
		                canvas.getWidth()/2+700, canvas.getHeight()/2, canvas);
                currentName.setColor(Color.BLUE);
                info.append(currentPlayer.getName() + "'s turn.\n");
		

		frame = new JFrame("The Settlers of Catan");	
		//(JFrame) SwingUtilities.getWindowAncestor(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(getContentPane());		

		Container contentPane = getContentPane();
		contentPane.add(canvas);
    
		JPanel topPanel = new JPanel();
		//topPanel.add(new JScrollPane(info));
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JPanel bottomPanel = new JPanel();
				
		rollDice = new JButton("(R)oll Dice");
		rollDice.addActionListener(this);
		rollDice.addKeyListener(this);
		bottomPanel.add(rollDice, BorderLayout.NORTH);
		
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
		frame.setLocationRelativeTo(null);
		frame.setMinimumSize(frame.getMinimumSize());
	}

	public static void generateBackground(DrawingCanvas canvas)
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();

                //double horizontalBuf = canvas.getWidth()-10;
                //double verticalBuf = canvas.getHeight()-10;
		VisibleImage water; 

		for(int row = 15; row < 2000; row+=50)
		{
			for(int col = 10; col < 2000; col+=50)
			{ 
				water = new VisibleImage(toolkit.getImage("water.jpg"), col, 
				                                    row, 50, 50, canvas);
                		water.sendBackward();
			}
		}
	}

	public void actionPerformed(ActionEvent evt) 
	{
	    if (evt.getSource() == rollDice)
	    {
	        rollDice();
	    }
	    
		if (menu == 0)
		{
        	if (evt.getSource() == buttonOne)
			{
				currentPlayer.addCard((int)(Math.random()*5 + 1));
				if (currentPlayer.displayingResourceCards == true)
				{
					currentPlayer.displayResourceHand();
				}
				else
				{
					currentPlayer.displayDevelopmentHand();
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
					currentPlayer.displayDevelopmentHand();
					buttonThree.setText("Show Resources");
				}
				else
				{
					currentPlayer.displayResourceHand();
					buttonThree.setText("Show Items");
				}
			}
			else if(evt.getSource() == buttonFour)
			{
				buttonOne.setText(playerOne.playerName);
				buttonTwo.setText(playerTwo.playerName);
				buttonThree.setText(playerThree.playerName);				
				if (numPlayers == 4)
					buttonFour.setText(playerFour.playerName);
				else
					buttonFour.setText("");
					
				toggleButtons(true);
				
				buttonFive.setText("Back");
				menu = 2;
			}
			else if (evt.getSource() == buttonFive)
			{
				if (currentTurn == playerList.size() - 1)
				{
					currentTurn = 0;
				}
				else
				{
					currentTurn++;
				}
				currentPlayer.hasRolled(false);
				currentPlayer.setScore(0);
				currentPlayer = playerList.get(currentTurn);
				currentPlayer.displayResourceHand();
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
				currentPlayer.buyItem(ROAD);
				if (currentPlayer.displayingResourceCards == true)
				{
					currentPlayer.displayResourceHand();
				}
				else
				{
					currentPlayer.displayDevelopmentHand();
				}
			}
			else if (evt.getSource() == buttonTwo)
			{
				if (currentPlayer.buyItem(SETTLEMENT))
                    gameBoard.selectCoordOn();
				if (currentPlayer.displayingResourceCards == true)
				{
					currentPlayer.displayResourceHand();
				}
				else
				{
					currentPlayer.displayDevelopmentHand();
				}
			}
			else if (evt.getSource() == buttonThree)
			{
				if (currentPlayer.buyItem(CITY))
                    gameBoard.selectCoordOn();
				if (currentPlayer.displayingResourceCards == true)
				{
					currentPlayer.displayResourceHand();
				}
				else
				{
					currentPlayer.displayDevelopmentHand();
				}
			}
			else if(evt.getSource() == buttonFour)
			{
				currentPlayer.buyDevelopmentCard();
				if (currentPlayer.displayingResourceCards == true)
				{
					currentPlayer.displayResourceHand();
				}
				else
				{
					currentPlayer.displayDevelopmentHand();
				}
			}
			else if (evt.getSource() == buttonFive)
			    returnToMainMenu();
		}
		
		/* after clicking on "trade"
		    a list of players to trade with */ 
		else if (menu == 2)
		{
            Player tradingPlayer = null;
			if (evt.getSource() == buttonOne)
			    tradingPlayer = playerOne;
		    else if (evt.getSource() == buttonTwo)
			    tradingPlayer = playerTwo;
			else if (evt.getSource() == buttonThree)
			    tradingPlayer = playerThree;
			else if (evt.getSource() == buttonFour)
			    tradingPlayer = playerFour;
			    
            if (tradingPlayer != null)
            {    
                tradewindow = new OpenTrade(currentPlayer,
                                                    tradingPlayer, this);
                SwingUtilities.invokeLater(tradewindow);
			}
			else if (evt.getSource() == buttonFive)
			{
			    returnToMainMenu();
			}
		}
    }
 	
 	public void keyPressed(KeyEvent key)
 	{
        if (key.getKeyCode() == KeyEvent.VK_R)
 	        rollDice();
 	}
 	public void keyReleased(KeyEvent key) {}
 	public void keyTyped(KeyEvent key) {}
 	/*
 	 * This method gives control over all buttons of the game.
 	 * It is used from OpenTrade.java to deactivate buttons upon start of
 	 * trading and to activate them back when the trade is completed
 	 */
 	public void toggleButtons(boolean bln)
 	{
 	    buttonOne.setEnabled(bln);
 	    buttonTwo.setEnabled(bln);
 	    buttonThree.setEnabled(bln);
 	    buttonFour.setEnabled(bln);
 	    buttonFive.setEnabled(bln);
 	    rollDice.setEnabled(bln);
 	    
 	    if (numPlayers == 3)
            buttonFour.setEnabled(false);
            
 	    if (bln)
 	    {
            if (currentTurn == 0)
                buttonOne.setEnabled(!bln);
            else if (currentTurn == 1)
                buttonTwo.setEnabled(!bln);
            else if (currentTurn == 2)
                buttonThree.setEnabled(!bln);
            else if (currentTurn == 3)
                buttonFour.setEnabled(!bln);
        }
 	}
 	
 	public void rollDice()
 	{
 	    if (currentPlayer.canRoll())
 	    {
 	        rolling = new DiceRoll(currentPlayer);
	        SwingUtilities.invokeLater(rolling);
	    }
	    else
	        JOptionPane.showMessageDialog(frame,
                            currentPlayer.getName() + ", you have already " +
                            "rolled this turn.", "Sorry...", 
                            JOptionPane.WARNING_MESSAGE);
	}
 	/*
 	 * This method is used for "Finish Turn" button.
 	 * Resets buttons' names to their defaults and sets 'menu' to 0
 	 */
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
 	
 	/*
 	    *** MAIN METHOD ***
 	 */
	public static void main(String[] args)
	{
        startGame = new CatanSetup();
        boolean done = startGame.isDone();
        while (done == false)
        {
            done = startGame.isDone();
            System.out.print("");
        }
        CatanGame settlers = new CatanGame();
        SwingUtilities.invokeLater(settlers);
	}
}

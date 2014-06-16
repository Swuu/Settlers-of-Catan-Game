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
 	/*private static JButton rollDice;*/
	
    private static JDrawingCanvas canvas;
    private static JDrawingCanvas canvas2;
    private OpenTrade tradewindow;
    private DiceRoll rolling;
    private JFrame frame;
    
    private boolean gameStart;
    private boolean firstRound;
 
	public void run()
	{
		canvas= new JDrawingCanvas(1000,500);
		canvas2 = new JDrawingCanvas(800, 100);

        generateBackground(canvas); 
		
		gameBoard = new HexagonMap(canvas, this);
		
        info = new JTextArea(5, 5);
		info.setEditable(false);
		
        numPlayers = startGame.getNumOfPlayers();
        playerList = new ArrayList<Player>();
  		playerOne = new Player(1, startGame.getName(0), this);
  		playerList.add(playerOne);
  		playerTwo = new Player(2, startGame.getName(1), this);
  		playerList.add(playerTwo);
  		if (numPlayers == 3)
        {
            playerThree = new Player(3, startGame.getName(2), this);
            playerList.add(playerThree);
  		}
        else if (numPlayers == 4)
  		{
  			playerThree = new Player(3, startGame.getName(2), this);
            playerList.add(playerThree);
            playerFour = new Player(4, startGame.getName(3), this);
  			playerList.add(playerFour);
  		}
  		if(startGame.isDebugMode())
		{
			System.out.println("Welcome to debug mode.");
			System.out.println("The three players start with 10,000 of each resource");
			System.out.println("The resources are in your hand but are invisible. Enjoy!");
            ROAD.changePrice(0,0,0,0,0);
            SETTLEMENT.changePrice(0,0,0,0,0);
            CITY.changePrice(0,0,0,0,0);
            DCARD.changePrice(0,0,0,0,0);
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
				
		/*rollDice = new JButton("(R)oll Dice");
		rollDice.addActionListener(this);
		rollDice.addKeyListener(this);
		bottomPanel.add(rollDice, BorderLayout.NORTH);*/
		
        bottomPanel.add(canvas2, BorderLayout.SOUTH);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
		contentPane.add(new JScrollPane(info), BorderLayout.NORTH);
		JPanel rightPanel = new JPanel(new GridLayout(6, 1));
		buttonOne  = new JButton("Roll Dice");
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
        
        gameStart = true;
        firstRound = true;

		validate();
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setMinimumSize(frame.getMinimumSize());
        
        gameBoard.selectCoordOn(1); //allows the first player to start building settlements
	}

	public static void generateBackground(DrawingCanvas canvas)
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();

                double horizontalBuf = canvas.getWidth()-10;
                double verticalBuf = canvas.getHeight()-10;
		VisibleImage water;
        water = new VisibleImage(toolkit.getImage(
                                "../image/NewSpaceBackground.png"), 
                                 0, 0, 1300, 700, canvas);
        water.sendBackward();
	}

	public void actionPerformed(ActionEvent evt) 
	{
	    if (gameBoard.canPushButtons())
        {
            
            if (menu == 0)
            {
                if (evt.getSource() == buttonOne)
                {
                    rollDice();
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
					
                    toggleButtons(true, "--trade");
                    
                    buttonFive.setText("Back");
                    menu = 2;
                }
                else if (evt.getSource() == buttonFive)
                {
                    if (gameStart)
                    {
                        if (firstRound)
                        {
                            if (currentTurn == playerList.size() - 1)
                            {
                                firstRound = false;
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
                            gameBoard.selectCoordOn(1);
                        }
                        else
                        {
                            if (currentTurn == 0)
                            {
                                gameStart = false;
                                toggleButtons(true, "--std");
                                Hexagon [] hexagonArray = gameBoard.getHexagonArray();
                                for (int i=0 ; i<hexagonArray.length ; i++)
                                {
                                    gameBoard.retResource(hexagonArray[i]);
                                }
                            }
                            else
                            {
                                currentTurn--;
                                gameBoard.selectCoordOn(1);
                            }
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
                        currentPlayer.setScore(0);
                        currentPlayer = playerList.get(currentTurn);
                        currentPlayer.hasRolled(false);
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
                        checkVictory();
                    }
                }
            }
            
            /* a list of items to buy */
            else if (menu == 1)
            {
                if (evt.getSource() == buttonOne)
                {
                    if(currentPlayer.buyItem(ROAD))
                    {
                        if (currentPlayer.displayingResourceCards == true)
                        {
                            currentPlayer.displayResourceHand();
                        }
                        else
                        {
                            currentPlayer.displayDevelopmentHand();
                        }
                    }
                }
                else if (evt.getSource() == buttonTwo)
                {
                    if (gameBoard.hasAvailableCoord() && currentPlayer.buyItem(SETTLEMENT))
                    {
                        gameBoard.selectCoordOn(1);
                        if (currentPlayer.displayingResourceCards == true)
                        {
                            currentPlayer.displayResourceHand();
                        }
                        else
                        {
                            currentPlayer.displayDevelopmentHand();
                        }
                    }
                }
                else if (evt.getSource() == buttonThree)
                {
                    if (gameBoard.hasUpgradeableCoord() && currentPlayer.buyItem(CITY))
                    {
                        gameBoard.selectCoordOn(2);
                        if (currentPlayer.displayingResourceCards == true)
                        {
                            currentPlayer.displayResourceHand();
                        }
                        else
                        {
                            currentPlayer.displayDevelopmentHand();
                        }
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
 	public void toggleButtons(boolean bln, String currentState)
 	{
        if (!bln)
        {
            buttonOne.setEnabled(bln);
            buttonTwo.setEnabled(bln);
            buttonThree.setEnabled(bln);
            buttonFour.setEnabled(bln);
            buttonFive.setEnabled(bln);
            /*rollDice.setEnabled(bln);*/
        }
        
        else
        {
            if (currentState.equals("--start"))
            {
                buttonFive.setEnabled(bln);
            }
            
            if (currentState.equals("--std"))
            {
                buttonOne.setEnabled(bln);
                buttonTwo.setEnabled(bln);
                buttonThree.setEnabled(bln);
                buttonFour.setEnabled(bln);
                buttonFive.setEnabled(bln);
            }
            
            if (currentState.equals("--trade"))
            {
                if (currentTurn == 0)
                    buttonOne.setEnabled(!bln);
                else if (currentTurn == 1)
                    buttonTwo.setEnabled(!bln);
                else if (currentTurn == 2)
                    buttonThree.setEnabled(!bln);
                else if (currentTurn == 3)
                    buttonFour.setEnabled(!bln);
                if (numPlayers == 3)
                    buttonFour.setEnabled(false);
            }   
        }
    }
 	
    /* return whether the game is at the state of initial start */
    public boolean ifStart()
    {
        return gameStart;
    }
    
 	public void rollDice()
 	{
	    Hexagon [] hexagonArray = gameBoard.getHexagonArray();

 	    if (currentPlayer.canRoll())
 	    {
 	        rolling = new DiceRoll(this);
	        SwingUtilities.invokeLater(rolling);
			int rollNumber = rolling.getScore(); // get score from rolling dice
	    
            if (rollNumber == 7)
            {
                gameBoard.selectCoordOn(3);
            }
		    for (int i=0 ; i<hexagonArray.length ; i++)
		    {
				if (rollNumber == hexagonArray[i].getRollValue())
				{
				    gameBoard.retResource(hexagonArray[i]);
				}
	    	}
			if (currentPlayer.displayingResourceCards == true)
            {
                    currentPlayer.displayResourceHand();
            }
            else
            {
                    currentPlayer.displayDevelopmentHand();
            }
	
		}
	    else
	        JOptionPane.showMessageDialog(frame,
                            currentPlayer.getName() + ", you have already " +
                            "rolled this turn.", "Sorry...", 
                            JOptionPane.WARNING_MESSAGE);
	}
    
    public Player currentPlayer()
    {
        return currentPlayer;
    }
    
 	/*
 	 * This method is used for "Finish Turn" button.
 	 * Resets buttons' names to their defaults and sets 'menu' to 0
 	 */
 	public static void returnToMainMenu()
 	{
 		buttonOne.setText("Roll Dice");
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

    //checks if current player has won, if he does then game ends
    public void checkVictory()
    {
        if (currentPlayer.getPoints() >= 10)
            gameEnd();
    }
 	
    public void gameEnd()
    {
        int n = JOptionPane.showConfirmDialog(frame,
                currentPlayer.getName() + " Wins!",
                "Victory!", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        
        System.exit(0);
    }
    
    public DrawingCanvas getCanvas()
    {
        return canvas;
    }
    
    public DrawingCanvas getCanvasTwo()
    {
        return canvas2;
    }
    
    public HexagonMap getGameBoard()
    {
        return gameBoard;
    }
    
    public JTextArea getInfo()
    {
        return info;
    }
 	/*
 	    *** MAIN METHOD ***
 	 */
	public static void main(String[] args)
	{
		TitleScreen titleScreen = new TitleScreen();
		while(!titleScreen.isSelectionMade())
		{
			int a = 01;
			System.out.print("");
		}
        startGame = titleScreen.getSetup();
        CatanGame settlers = new CatanGame();
        SwingUtilities.invokeLater(settlers);
	
	}
}

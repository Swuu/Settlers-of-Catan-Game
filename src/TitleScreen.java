import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class TitleScreen extends JFrame
{
	/**@author Michael Gonzalez
	 * This class displays the games title screens and handles the various modes
	 * that load the game. Currently, you can only start a new game or enter 
	 * debug mode, however in the future you will be able to load a game,
	 * enter a networked game, and edit game settings from within this class.
	 */
	private JPanel buttonPanel;
	private JPanel main;
	private CatanSetup setup;
	private NetworkFrame network;
	private boolean buttonSelected;
	private boolean selectionMade;
	private Thread hasClosedChecker;
	public TitleScreen()
	{
		super("Enceladus");
		ImageIcon backgroundIcon = new ImageIcon("res/image/Titlescreen.png");
		JLabel background = new JLabel(backgroundIcon);
		setSize(new Dimension(950,639));
		main = new JPanel(new BorderLayout());
		main.add(background, BorderLayout.CENTER);
		add(main);
		formatButtonPanel();
		main.add(buttonPanel, BorderLayout.EAST);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
	}
	public void formatButtonPanel()
	{
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5,1,15,15));
		ImageIcon newIcon = new ImageIcon("res/image/NewGameButton.png");
		ImageIcon loadIcon = new ImageIcon("res/image/LoadGameButton.png");
		ImageIcon networkIcon = new ImageIcon("res/image/NetworkButton.png");
		ImageIcon optionsIcon = new ImageIcon("res/image/OptionsButton.png");
		JButton newButton = new JButton(newIcon);
		JButton loadButton = new JButton(loadIcon);
		JButton networkButton = new JButton(networkIcon);
		JButton optionsButton = new JButton(optionsIcon);
		JButton debug = new JButton("Debug");
		newButton.addActionListener(new ButtonListener());
		loadButton.addActionListener(new ButtonListener());
		networkButton.addActionListener(new ButtonListener());
		optionsButton.addActionListener(new ButtonListener());
		debug.addActionListener(new ButtonListener());
		buttonPanel.add(newButton);
		buttonPanel.add(loadButton);
		buttonPanel.add(networkButton);
		buttonPanel.add(optionsButton);
		buttonPanel.add(debug);
	}
	/**This method returns a boolean to be read by CatanGame to inform it when
	 * a game mode is selected.
	 * @return returns true when the game is ready to run, else false
	 */
	public boolean isSelectionMade()
	{
		return selectionMade;
	}
	/**This method ensures the backwards compatability with the previous
	 * new game functionality of CatanGame. That is, it returns a CatanSetup
	 * object with name values and debug information before CatanGame knows 
	 * it exists. 
	 * @return
	 */
	public CatanSetup getSetup()
	{
		return setup;
	}
	public void printDimensions()
	{
		System.out.println(getWidth() +" " + getHeight());
	}
	public static void main(String[] args) {
		TitleScreen title = new TitleScreen();
	}
	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			if(buttonSelected == true)
				return;
			JButton button = (JButton) evt.getSource();
			//Action for Debug Button
			if(button.getText().equals("Debug"))
			{
				setup = new CatanSetup(true);
				selectionMade = true;
				setVisible(false);
				return; //Stops the debug button from checking for an icon it doesn't have
			}
			//Action for New Button
			if((button.getIcon()).toString().equals("res/image/NewGameButton.png"))
			{
				buttonSelected = true;
				setup = new CatanSetup();
				hasClosedChecker = (new Thread(new WindowManager(WindowManager.NEWGAME)));
				hasClosedChecker.start();
				
			}
			//Action for Network Button
			if((button.getIcon()).toString().equals("res/image/NetworkButton.png"))
			{
				buttonSelected = true;
				network = new NetworkFrame();
				hasClosedChecker = (new Thread(new WindowManager(WindowManager.NETWORK)));
				hasClosedChecker.start();
			}
			if((button.getIcon()).toString().equals("res/image/OptionsButton.png"))
			{
				printDimensions();
				return;
			}
		}
	}
	class WindowManager implements Runnable {
		/**This thread prevents a player from clicking more than one button at a time */
		private int type;
		public static final int NEWGAME = 1;
		public static final int LOADGAME = 2;
		public static final int NETWORK = 3;
		public static final int OPTIONS = 4;
		public WindowManager(int type)
		{
			this.type = type;
		}
		public void run()
		{
			switch(type)
			{
			case NEWGAME: handleNewGame();
				break;
			case NETWORK: handleNetwork();
				break;
			}
			
		}
		private void handleNewGame()
		{
			while(!setup.isDone())
			{
				try
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException ex)
				{	
				}
				if(setup.hasClosed())
				{
					buttonSelected = false;
					return;
				}
			}
			selectionMade = true;
			setVisible(false);
		}
		private void handleNetwork()
		{
			while(!network.isDone())
			{
				try
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException ex)
				{	
				}
				if(network.hasClosed())
				{
					buttonSelected = false;
					return;
				}
			}
			selectionMade = true;
			setVisible(false);
		}
	}
}

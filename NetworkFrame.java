import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class NetworkFrame extends JFrame
{
	private NetworkController controller;
	private JPanel hostPanel;
	private JPanel clientPanel;
	private JPanel confirmPanel;
	private ButtonGroup buttons;
	private JRadioButton hostButton;
	private JRadioButton clientButton;
	private JComboBox numDropDown;
	private JTextField ipTextField;
	private JTextField nameField;
	private boolean hasClosed;
	private boolean done;
	public NetworkFrame()
	{
		super("Network");
		setSize(300,400);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setLayout(new GridLayout(3,1));
		buttons = new ButtonGroup();
		makeHostPanel();
		makeClientPanel();
		makeConfirmPanel();
		add(hostPanel);
		add(clientPanel);
		add(confirmPanel);
		revalidate();
		repaint();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt)
			{
				closeFrame();
			}
		});
	}
	/**Creates the host panel */
	public void makeHostPanel()
	{
		//Makes the host button
		hostButton = new JRadioButton("Host", true);
		hostButton.setMnemonic('H');
		hostButton.addActionListener(new RadioListener());
		JPanel ipPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel numOfPlayerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttons.add(hostButton);
		URL whatismyip;
		try {
			whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(
	                whatismyip.openStream()));
			String ip = in.readLine(); //you get the IP as a String
			ipPanel.add(new JLabel("	My IP: " + ip));
		} catch (MalformedURLException e) {

			e.printStackTrace();
			ipPanel.add(new JLabel("My IP: ERROR"));
		} catch (IOException e) {
			ipPanel.add(new JLabel("My IP: ERROR"));
			e.printStackTrace();
		}
		String[] validNums = {"3","4"};
		numDropDown = new JComboBox<String>(validNums);
		numOfPlayerPanel.add(new JLabel("Number of Players"));
		numOfPlayerPanel.add(numDropDown);
		//Adds all components to hostPanel
		hostPanel = new JPanel(new GridLayout(3,1));
		hostPanel.setBorder(new LineBorder(Color.BLACK, 1));
		hostPanel.add(hostButton);
		hostPanel.add(ipPanel);
		hostPanel.add(numOfPlayerPanel);
		hostPanel.repaint();
	}
	public void makeClientPanel()
	{
		clientButton = new JRadioButton("Client", false);
		clientButton.setMnemonic('C');
		clientButton.addActionListener(new RadioListener());
		JPanel ipPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ipTextField = new JTextField(16);
		ipTextField.setEnabled(false);
		ipPanel.add(new JLabel("Host IP: "));
		ipPanel.add(ipTextField);
		buttons.add(clientButton);
		clientPanel = new JPanel(new GridLayout(3,1));
		clientPanel.setBorder(new LineBorder(Color.BLACK,1));
		clientPanel.add(clientButton);
		clientPanel.add(ipPanel);
		clientPanel.repaint();
	}
	public void makeConfirmPanel()
	{
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		nameField = new JTextField(10);
		nameField.addKeyListener(new KeyAdapter(){
			@Override
		      public void keyPressed(KeyEvent e) 
		      {
		    	  if (e.getKeyCode() == KeyEvent.VK_ENTER)
		    		  startNetwork();
		      }
		});
		namePanel.add(new JLabel("Name: "));
		namePanel.add(nameField);
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");
		okButton.addActionListener(new ButtonListner());
		cancelButton.addActionListener(new ButtonListner());
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		confirmPanel = new JPanel(new GridLayout(3,1));
		confirmPanel.setBorder(new LineBorder(Color.BLACK,1));
		confirmPanel.add(new JPanel());
		confirmPanel.add(namePanel);
		confirmPanel.add(buttonPanel);
		confirmPanel.repaint();
	}
	public boolean isDone()
	  {
	    return done;
	  }
	  public boolean hasClosed()
	  {
		  return hasClosed;
	  }
	  public void closeFrame()
	  {
		  hasClosed = true;
		  setVisible(false);
	  }
	  public void startNetwork()
	  {
		  if(hostButton.isSelected())
			{
				if(!nameField.getText().equals(""))
				{
					int numOfPlayers = Integer.parseInt((String)numDropDown.getSelectedItem());
					controller = new HostController(numOfPlayers, nameField.getText());
					System.out.println("Tried to create the controller");
				}
				else
					JOptionPane.showMessageDialog(null, "You must enter a name");
			}
	  }
	public static void main(String[] args) {
		NetworkFrame frame = new NetworkFrame();
		frame.repaint();

	}
	class RadioListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			JRadioButton button = (JRadioButton)evt.getSource();
			if(button.getText().equals("Host"))
			{
				ipTextField.setEnabled(false);
				numDropDown.setEnabled(true);
			}
			if(button.getText().equals("Client"))
			{
				ipTextField.setEnabled(true);
				numDropDown.setEnabled(false);
			}
		}
	}
	class ButtonListner implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			JButton button = (JButton)evt.getSource();
			if(button.getText().equals("OK"))
			{
				startNetwork();	
			}
			if(button.getText().equals("Cancel"))
			{
				closeFrame();
			}
		}
	}

}

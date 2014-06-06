import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class WaitingRoom extends JFrame
{
	private JPanel chatPanel;
	private JPanel playerPanel;
	private JPanel startPanel;
	private JTextArea chatTextArea;
	private JTextField chatTextField;
	private JLabel[] playerNames;
	private NetworkController controller;
	private int roomSize;
	public WaitingRoom(NetworkController controller, int size)
	{
		super("Waiting Room");
		this.controller = controller;
		Thread control = new Thread(controller);
		control.start();
		roomSize = size;
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		JPanel panel = new JPanel(new BorderLayout());
		add(panel);
		makeChatPanel();
		makePlayerPanel();
		makeStartPanel();
		panel.add(chatPanel, BorderLayout.CENTER);
		panel.add(playerPanel, BorderLayout.EAST);
		panel.add(startPanel, BorderLayout.SOUTH);
		revalidate();
		repaint();
	}
	public void makeChatPanel()
	{
		JPanel textFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sendMessage();
			}
		});
		chatTextField = new JTextField(30);
		chatTextField.addKeyListener(new KeyAdapter() {
		      @Override
		      public void keyPressed(KeyEvent e) 
		      {
		    	  if (e.getKeyCode() == KeyEvent.VK_ENTER)
		    		  sendMessage();
		      }
		});
		textFieldPanel.add(chatTextField);
		textFieldPanel.add(sendButton);
		chatPanel = new JPanel(new BorderLayout());
		chatTextArea = new JTextArea("", 1,1);
		chatTextArea.setEditable(false);
		chatTextArea.setWrapStyleWord(true);
		JScrollPane scrollBar = new JScrollPane(chatTextArea);
		chatPanel.add(new JLabel("Chat"), BorderLayout.NORTH);
		chatPanel.add(scrollBar, BorderLayout.CENTER);
		chatPanel.add(textFieldPanel, BorderLayout.SOUTH);
	}
	public void makePlayerPanel()
	{
		playerPanel = new JPanel(new BorderLayout());
		playerPanel.add(new JLabel("Players"), BorderLayout.NORTH);
		playerNames = new JLabel[roomSize];
		JPanel namePanel = new JPanel(new GridLayout(10,1));
		for(int i = 0; i < roomSize; i++)
		{
			playerNames[i] = new JLabel();
			namePanel.add(playerNames[i]);
		}
		playerPanel.add(namePanel, BorderLayout.CENTER);
	}
	public void makeStartPanel()
	{
		JButton startButton = new JButton("Start");
		JButton disconnectButton = new JButton("Disconnect");
		startPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		startPanel.add(startButton);
		startPanel.add(disconnectButton);
	}
	public void sendMessage()
	{
		try 
		{
			controller.sendMessage(chatTextField.getText());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		chatTextField.setText("");
	}
	public void updateChat(String message)
	{
		chatTextArea.append(message + "\n");
	}
	public void refreshPlayers(String[] players)
	{
		for(int i = 0; i < players.length; i++)
		{
			if(i == 0)
				playerNames[i].setText("Player " + (i+1) + ": " + players[i] + " (Host)");
			else if(players[i] != null)
				playerNames[i].setText("Player " + (i+1) + ": " + players[i]);
			else
				playerNames[i].setText("Player " + (i+1) + ":");
		}
		//for(int i = players.length; i < roomSize; i++)
			//playerNames[i].setText("Player " + (i+1) + ": ");
	}
	public static void main(String[] args) {
		//WaitingRoom main = new WaitingRoom();

	}

}

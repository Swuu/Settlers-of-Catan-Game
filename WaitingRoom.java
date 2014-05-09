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
	private HostController host;
	private NetworkController controller;
	public WaitingRoom(NetworkController controller)
	{
		super("Waiting Room");
		this.controller = controller;
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
		chatTextArea.append(controller.getName() + ": " + chatTextField.getText() + "\n");
		chatTextField.setText("");
	}
	public void updateChat()
	{
		
	}
	public static void main(String[] args) {
		//WaitingRoom main = new WaitingRoom();

	}

}

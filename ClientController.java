import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;
public class ClientController extends NetworkController 
{
	private DataInputStream statusInput;
	private BufferedReader stringInput;
	private ObjectInputStream objectInput;
	private DataOutputStream statusOutput;
	private PrintWriter stringOutput;
	private ObjectOutputStream objectOutput;
	public ClientController(String hostIP, String playerName)
	{
		name = playerName;
		System.out.println(hostIP);
		try 
		{
			Socket statusSocket = new Socket(hostIP, 8000);
			Socket stringSocket = new Socket(hostIP, 8001);
			Socket objectSocket = new Socket(hostIP, 8002);
			statusInput = new DataInputStream(statusSocket.getInputStream());
			stringInput = new BufferedReader(new InputStreamReader(stringSocket.getInputStream()));
			objectInput = new ObjectInputStream(objectSocket.getInputStream());
			statusOutput = new DataOutputStream(statusSocket.getOutputStream());
			stringOutput = new PrintWriter(stringSocket.getOutputStream(), true);
			objectOutput = new ObjectOutputStream(objectSocket.getOutputStream());
			setup();
			room = new WaitingRoom(this, numberOfPlayers);
			room.refreshPlayers(playerNames);
		}
		catch (UnknownHostException e)
		{
			JOptionPane.showMessageDialog(null, "Error: Unknown Hosts");
		}
		catch (ConnectException e)
		{
			JOptionPane.showMessageDialog(null, "Error: Connection refused");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public void run()
	{
		while(true)
		{
			try {
				switch(statusInput.readInt())
				{
					case MESSAGE: receieveMessage();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	private void setup() throws IOException
	{
		System.out.println("Trying to get playerNumber");
		playerNumber = statusInput.readInt();
		System.out.println("playerNumber is " + playerNumber);
		numberOfPlayers = statusInput.readInt();
		System.out.println("Trying to send name: " + name);
		stringOutput.println(name);
		System.out.println("Sent name");
		try 
		{
			System.out.println("Trying to recieve array of names");
			playerNames = (String[]) objectInput.readObject();
			System.out.println("Recieved array of names");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	private void receieveMessage() throws IOException
	{
		String message = stringInput.readLine();
		room.updateChat(message);
	}
	@Override
	protected void sendMessage(String message) throws IOException 
	{
		statusOutput.writeInt(MESSAGE);
		stringOutput.println(message);
		
	}

}

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
		catch (ClassNotFoundException e) 
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
					break;
					case REFRESH: recieveRefresh();
					break;
				}
			}
			catch (IOException | ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}
	private void setup() throws IOException, ClassNotFoundException
	{
		numberOfPlayers = statusInput.readInt();
		stringOutput.println(name);
		recieveRefresh();
	}
	private void receieveMessage() throws IOException
	{
		String message = stringInput.readLine();
		room.updateChat(message);
	}
	private void recieveRefresh() throws IOException, ClassNotFoundException
	{
		playerNames = (String[]) objectInput.readObject();
		playerNumber = statusInput.readInt();
		if(room != null)
			room.refreshPlayers(playerNames);
	}
	@Override
	protected void sendMessage(String message) throws IOException 
	{
		statusOutput.writeInt(MESSAGE);
		stringOutput.println(message);
		
	}
	

}

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
			room = new WaitingRoom(this);
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
	private void setup() throws IOException
	{
		playerNumber = statusInput.readInt();
		stringOutput.println(name);
		try 
		{
			playerNames = (String[]) objectInput.readObject();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public void handleChat(String message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}

import java.io.*;
import java.net.*;
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
			playerNumber = statusInput.readInt();
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void handleChat(String message) {
		// TODO Auto-generated method stub
		
	}

}

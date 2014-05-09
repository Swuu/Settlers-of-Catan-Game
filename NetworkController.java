import java.io.*;
import java.net.*;
public abstract class NetworkController 
{
	protected static final int SENDMESSAGE = 1;
	protected int numberOfPlayers;
	protected int playerNumber;
	protected int numberOfConnected;
	protected String name;
	protected String[] playerNames;
	protected PrintWriter output;
	protected BufferedReader input;
	protected WaitingRoom room;
	public abstract void handleChat(String message);
	protected void sendCommand(int messageType, String content)
	{
		
	}
	protected String getName()
	{
		return name;
	}
	
}

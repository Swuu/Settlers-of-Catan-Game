import java.io.*;
import java.net.*;
public abstract class NetworkController implements Runnable
{
	protected static final int DISCONNECT = 0;
	protected static final int MESSAGE = 1;
	protected static final int STARTGAME = 2;
	protected static final int SYSMESSAGE =3 ;
	protected static final int REFRESH = 4;
	protected int numberOfPlayers;
	protected int playerNumber;
	protected int numberOfConnected;
	protected String name;
	protected String[] playerNames;
	protected PrintWriter output;
	protected BufferedReader input;
	protected WaitingRoom room;
	protected abstract void sendMessage(String message) throws IOException;
	//protected abstract void receieveMessage();
	protected String getName()
	{
		return name;
	}
	public abstract void run();
	
}

import java.io.*;
import java.net.*;
import java.util.*;
public class HostController extends NetworkController implements Runnable
{
	private LinkedList<HandleAClient> clientList;
	private ArrayDeque<TaskDequeElement> taskDeque;
	public HostController(int numPlayers, String playerName)
	{
		numberOfPlayers = numPlayers;
		playerNumber = 1;
		numberOfConnected = 1;
		playerNames = new String[numberOfPlayers];
		playerNames[0] = playerName;
		clientList = new LinkedList<HandleAClient>();
		taskDeque = new ArrayDeque<TaskDequeElement>();
		name = playerName;
		room = new WaitingRoom(this, numberOfPlayers);
		room.refreshPlayers(playerNames);
		new Thread(new TaskDequeManager(taskDeque)).start();
	}
	public void start()
	{
		
	}
	class TaskDequeManager implements Runnable
	{
		private Deque<TaskDequeElement> taskDeque;
		TaskDequeElement elem;
		TaskDequeManager(Deque<TaskDequeElement> deque)
		{
			taskDeque = deque;
		}
		@Override
		public void run() 
		{
			while(true)
			{
				if(taskDeque.size() != 0 )
				{
					elem = taskDeque.removeFirst();
					try {
						elem.execute();
					} 
					catch (IOException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		
	}
	class TaskDequeElement
	{
		private int playerNum;
		private int actionFlag;
		private Object data;
		TaskDequeElement(int num, int flag, Object data)
		{
			playerNum = num;
			actionFlag = flag;
			this.data = data;
		}
		public int getPlayerNum() { return playerNum; }
		public int getActionFlag() { return actionFlag; }
		public Object getData() { return data; }
		public void execute() throws IOException
		{
			switch(actionFlag)
			{
				case MESSAGE: sendMessage();
			}
		}
		private void sendMessage() throws IOException
		{
			String message = playerNames[playerNum - 1] + ": " + ((String) data);
			room.updateChat(message);
			for(int i = 0; i < clientList.size(); i++)
				clientList.get(i).sendMessage(message);
		}
	}
	class HandleAClient implements Runnable
	{
		private DataInputStream statusInput;
		private BufferedReader stringInput;
		private ObjectInputStream objectInput;
		private DataOutputStream statusOutput;
		private PrintWriter stringOutput;
		private ObjectOutputStream objectOutput;
		private int playerNumber;
		public HandleAClient(Socket statusSocket, Socket stringSocket, Socket objectSocket)
		{
			System.out.println("Trying to construct HandleAClient");
			try
			{
				System.out.println("Trying to make data streams");
				System.out.println("Trying to make statusInput");
				statusInput = new DataInputStream(statusSocket.getInputStream());
				System.out.println("Trying to make stringInput");
				stringInput = new BufferedReader(new InputStreamReader(stringSocket.getInputStream()));
				System.out.println("Trying to make objectInput");
				//objectInput = new ObjectInputStream(objectSocket.getInputStream());
				System.out.println("Trying to make statusOutput");
				statusOutput = new DataOutputStream(statusSocket.getOutputStream());
				System.out.println("Trying to make stringOutput");
				stringOutput = new PrintWriter(stringSocket.getOutputStream(), true);
				System.out.println("Trying to make objectOutput");
				objectOutput = new ObjectOutputStream(objectSocket.getOutputStream());
				System.out.println("Trying to setup");
				setup();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
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
		private void receieveMessage() throws IOException
		{
			String message = stringInput.readLine();
			TaskDequeElement task = new TaskDequeElement(playerNumber, MESSAGE, message);
			taskDeque.add(task);
		}
		private void sendMessage(String message) throws IOException
		{
			statusOutput.writeInt(MESSAGE);
			stringOutput.println(message);
		}
		private void setup() throws IOException
		{
			System.out.println("Running setup");
			playerNumber = numberOfConnected+1;
			System.out.println("Trying to send client playerNumber");
			statusOutput.writeInt(playerNumber);
			statusOutput.writeInt(numberOfPlayers);
			System.out.println("playerNumber recieved.\nTrying to recieve playerName");
			playerNames[playerNumber-1] = stringInput.readLine();
			System.out.println("playerName recieved.\nTrying to send other playerNames");
			objectOutput.writeObject(playerNames);
			System.out.println("Sent playerNames");
			room.refreshPlayers(playerNames);
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
		{
			ServerSocket statusSSocket = new ServerSocket(8000); //Double SS means Server Socket
			ServerSocket stringSSocket = new ServerSocket(8001);
			ServerSocket objectSSocket = new ServerSocket(8002);
			while(true)
			{
				Socket statusSocket = statusSSocket.accept();
				System.out.println("Client connected #" + numberOfPlayers);
				if (numberOfPlayers > numberOfConnected)
				{
					System.out.println("Trying to connect String Socket");
					Socket stringSocket = stringSSocket.accept();
					System.out.println("String socket connected\nTrying to connect Object Socket");
					Socket objectSocket = objectSSocket.accept();
					System.out.println("Object socket connected");
					HandleAClient task = new HandleAClient(statusSocket, stringSocket, objectSocket);
					new Thread(task).start();
					clientList.add(task);
					numberOfConnected++;
				}
				else
				{
					//sendCommand(SENDMESSAGE, "Error: This room is full");
					statusSocket.close();
				}
			}
		}
		catch(IOException ex)
		{
			
		}
	}
	@Override
	protected void sendMessage(String message) 
	{
		TaskDequeElement task = new TaskDequeElement(playerNumber, MESSAGE, message);
		taskDeque.add(task);
	}
	
}

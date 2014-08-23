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
	/**This inner class handles all incoming and outgoing requests on the host
	 * controller. It takes the actions received by the clients and the host 
	 * player and adds them to the deck based on priority. If the action
	 * recieved action is not a client disconnecting, then the action is placed
	 * at the back of the deck.
	 **/
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
				try
				{
					Thread.sleep(100);
				} catch (InterruptedException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(taskDeque.size() != 0 )
				{
					elem = taskDeque.removeFirst();
					try {
						elem.execute();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			}
			
		}
		
	}
	/**This inner class represents an action to be placed on the 
	 * TaskDeckManager. Each element contains information on how the actions
	 * should be executed across the network.
	 * @param num The player number of the action to be executed
	 * @param flag The flag header of the action to be executed
	 * @param data The data to be processed by the action
	 **/
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
				break;
				case SYSMESSAGE: sendSystemMessage();
				break;
				case REFRESH: sendRefresh();
				break;
			}
		}
		private void sendMessage() throws IOException
		{
			String message = playerNames[playerNum - 1] + ": " + ((String) data);
			room.updateChat(message);
			for(int i = 0; i < clientList.size(); i++)
				clientList.get(i).sendMessage(message);
		}
		private void sendSystemMessage() throws IOException
		{
			room.updateChat((String) data);
			for(int i = 0; i < clientList.size(); i++)
				clientList.get(i).sendMessage((String) data);
		}
		private void sendRefresh() throws IOException
		{
			room.refreshPlayers(playerNames);
			for(int i = 0; i < clientList.size(); i++)
				clientList.get(i).sendRefresh();
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
		private String playerName;
		private boolean connected;
		public HandleAClient(Socket statusSocket, Socket stringSocket, Socket objectSocket)
		{
			System.out.println("Trying to construct HandleAClient");
			try
			{
				connected = true;
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
			catch (IOException | InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		public void run()
		{
			while(connected)
			{
				
				try {
					Thread.sleep(100);
					switch(statusInput.readInt())
					{
						case MESSAGE: receieveMessage();
					}
				}
				catch (IOException | InterruptedException e)
				{
					String message = "Player " + playerNumber +" (" + playerNames[playerNumber-1] + ") has disconnected.";
					taskDeque.add(new TaskDequeElement(0, SYSMESSAGE, message));
					disconnect(playerNumber);
					connected = false;
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
		private void sendFlag(int flag) throws IOException
		{
			statusOutput.writeInt(flag);
		}
		private void sendRefresh() throws IOException
		{
			objectOutput.writeObject(playerNames);
			statusOutput.writeInt(playerNumber);
		}
		private void setup() throws IOException, InterruptedException
		{
			
			playerNumber = numberOfConnected+1;
			statusOutput.writeInt(numberOfPlayers);
			playerName = stringInput.readLine();
			playerNames[playerNumber-1] = playerName;
			sendRefresh();
			room.refreshPlayers(playerNames);
			Thread.sleep(500);
			String message = playerNames[playerNumber - 1] + " has logged on.";
			taskDeque.add(new TaskDequeElement(playerNumber, SYSMESSAGE, message));
		}
		public void setPlayerNumber(int num)
		{
			playerNumber = num;
		}
		public void setName(String name)
		{
			playerName = name;
		}
		public String getName() { return playerName; }
		public int getPlayerNumber() { return playerNumber; }
	}
	@Override
	public void run() {
		try
		{
			ServerSocket statusSSocket = new ServerSocket(8000); //Double SS means Server Socket
			ServerSocket stringSSocket = new ServerSocket(8001);
			ServerSocket objectSSocket = new ServerSocket(8002);
			while(true)
			{
				Socket statusSocket = statusSSocket.accept();
				System.out.println("Client connected #" + numberOfConnected);
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
	public void disconnect(int player) 
	{
		clientList.remove(player-2);
		for(int i = 1; i < clientList.size()+1; i++)
		{
			playerNames[i] = clientList.get(i).getName();
			clientList.get(i).setPlayerNumber(i+1);
		}
		for(int i = clientList.size() + 1; i < playerNames.length; i++)
			playerNames[i] = "";
		room.refreshPlayers(playerNames);
		sendRefresh();
		numberOfConnected--;
	}
	private void sendRefresh() 
	{
		TaskDequeElement task = new TaskDequeElement(0, REFRESH, null);
		taskDeque.addFirst(task);
		
	}
	@Override
	protected void sendMessage(String message) 
	{
		TaskDequeElement task = new TaskDequeElement(playerNumber, MESSAGE, message);
		taskDeque.add(task);
	}
	
}

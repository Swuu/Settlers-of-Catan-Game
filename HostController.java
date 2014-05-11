import java.io.*;
import java.net.*;
import java.util.*;
public class HostController extends NetworkController implements Runnable
{
	private LinkedList<HandleAClient> clientList;
	private Deque<TaskDequeElement> taskDeque;
	public HostController(int numPlayers, String playerName)
	{
		numberOfPlayers = numPlayers;
		playerNumber = 1;
		numberOfConnected = 1;
		playerNames = new String[numberOfPlayers];
		playerNames[0] = playerName;
		name = playerName;
		room = new WaitingRoom(this);
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
			if(taskDeque.size() != 0 )
			{
				elem = taskDeque.removeFirst();
				elem.execute();
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
		public void execute()
		{
			
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
			try
			{
				statusInput = new DataInputStream(statusSocket.getInputStream());
				stringInput = new BufferedReader(new InputStreamReader(stringSocket.getInputStream()));
				objectInput = new ObjectInputStream(objectSocket.getInputStream());
				statusOutput = new DataOutputStream(statusSocket.getOutputStream());
				stringOutput = new PrintWriter(stringSocket.getOutputStream(), true);
				objectOutput = new ObjectOutputStream(objectSocket.getOutputStream());
				setup();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		private void setup() throws IOException
		{
			playerNumber = numberOfConnected;
			statusOutput.writeInt(numberOfConnected);
			playerNames[playerNumber-1] = stringInput.readLine();
			objectOutput.writeObject(playerNames);
		}
		public void run()
		{
			
		}
	}
	@Override
	public void handleChat(String message) {
		// TODO Auto-generated method stub
		
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
				if (numberOfPlayers > numberOfConnected)
				{
					Socket stringSocket = stringSSocket.accept();
					Socket objectSocket = objectSSocket.accept();
					HandleAClient task = new HandleAClient(statusSocket, stringSocket, objectSocket);
					new Thread(task).start();
					clientList.add(task);
					numberOfConnected++;
				}
				else
				{
					sendCommand(SENDMESSAGE, "Error: This room is full");
					statusSocket.close();
				}
			}
		}
		catch(IOException ex)
		{
			
		}
	}
}

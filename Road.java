import objectdraw.*;
import java.awt.*;

public class Road{
	private Path path;
	private Player player;
	//road constructor
	public Road(Path a, Player p){
		path = a;
		player = p;
	}
	//returns owner of the Road
	public Player getOwner()
		return player;
	}
	//returns Path that this road is on.
	public Path getPath(){
		return path;
	}
}
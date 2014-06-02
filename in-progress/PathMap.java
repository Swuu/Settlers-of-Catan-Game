import objectdraw.*;
import java.awt.*;
import java.util.ArrayList;

public class PathMap{
	private ArrayList<Coord> coords=HexagonMap.getCoords();
	private int length_hex=64;
	private ArrayList<Path> paths;
	public PathMap(){
		ArrayList<Coord> coords2=coords;
		paths = new ArrayList<Path>();
		for (int i=0; i<coords.size()-1; i++){
			for (int j=i+1; j<coords2.size(); j++){
				if(areAdjacent(coords[i], coords2[j]))
					paths.add(new Path(coords[i], coords2[j], this));
			}
		}
	}

	public boolean areAdjacent(Coord a, Coord b){
		double lowerbound = length_hex-2;
		double upperbound = length_hex+2;
		double dist = getDistance(a, b);
		if (dist>lowerbound && dist<upperbound)
			return true;
		else return false;
	}

	public double getDistance(Coord p, Coord q){

		double x1=p.location().getX(); //x coordinate of q
		double y1=p.location().getY(); //y coordinate of p
		double x2=q.location().getX(); //x coordinate of q
		double y2=q.location().getY(); //y coordinate of q

		double distance = Math.sqrt((x2-x1)*(x2-x1) + 
							(y2-y1)*(y2-y1));
		return distance;
	}
	public ArrayList<Coord> getCoordList(){
		return coords;
	}
	public ArrayList<Path> getPathList(){
		return paths;
	}
}
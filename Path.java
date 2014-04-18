public class Path{
	private boolean isRoad;
	private Coord coord1;
	private Coord coord2;
	private Road road;
	private PathMap pathmap;
	public Path(Coord a, Coord b, Pathmap pmap){
		isRoad = false;
		coord1=a;
		coord2=b;
		pathmap=pmap;

	}

	public void buildRoad(){
		road = new Road();//build road
		isRoad = true;	
	}

	public boolean isThisARoad(){
		return isRoad;
	}

	public boolean isRoadLegal(){

	/*
	 * - either an adjacent road or a settlement next to it.
	*/

	/* check if adjacent road exists */

	/* check if first coord connects to another coord*/
		ArrayList<Coord> path_list = new ArrayList();
		path_list = pathmap.getPathList();
		

		for (int i = 0; i < path_list.size(); i++){
			if (equals(path_list[i]))
				return false;
			else if ( )
		}
	}

	public boolean equals(Path poop){
		int x1=coord1.getX();
		int y1=coord1.getY();
		int x2=coord2.getX();
		int y2=coord2.getY();

		int poopx1=poop.getCoord1.getX();
		int poopy1=poop.getCoord1.getY();
		int poopx2=poop.getCoord2.getX();
		int poopy2=poop.getCoord2.getY();

		if (x1 == poopx1 && y1 == poopy1 && x2 == poopx2 &&
			y2 == poopy2)
			return true;
		else if(x1==poopx2 && y1==poopy2 && x2 == poopx1 && 
			y2 == poopy1) 
			return true;
		else return false;
	}
	public boolean isAdjacentTo(Path poop){
		
	}
	public Coord getCoord1(){
		return coord1;
	}
	public Coord getCoord2(){
		return coord2;
	}
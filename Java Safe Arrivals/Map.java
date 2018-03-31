import java.util.ArrayList;

public class Map {
	private Intersection[] intersections;
	private ArrayList<Intersection> epiCenters;
	
	
	
	public Map(Intersection [] intersections) {
		this.intersections = intersections;
		epiCenters = new ArrayList<Intersection>();
		for(Intersection i : intersections)
			if (i.risk() == 3)
				epiCenters.add(i);
		
	}
	
	public ArrayList<Intersection> epiCenters(){
		return epiCenters;
	}
	
	public ArrayList<Intersection> intersectionInRadius(Intersection center, double radius){ //radius in km
		ArrayList<Intersection> output = new ArrayList<Intersection>();
		for (int i =0; i < intersections.length; i++) {
			if (center.getDist(intersections[i]) < radius)
				output.add(intersections[i]);
		}
		return output;
	}
	
	public double getBearing(Intersection p1, Intersection p2) {
		return 0;
	}
	
	public ArrayList<Intersection> alongRoad(Intersection p1, Intersection p2){
		
		return new ArrayList<Intersection>();
	}
	
	public ArrayList<Intersection> buildRoute(){
		return null;
	}

}

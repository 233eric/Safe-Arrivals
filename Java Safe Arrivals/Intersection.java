
public class Intersection implements Comparable<Intersection>{
	private double lat;
	private double lon;
	private int risk;
	private int frequency;
	
	public Intersection(double lat, double lon, int frequency, int risk) {
		this.lat = lat;
		this.lon = lon;
		this.risk = risk;
		this.frequency = frequency;
	}
	public double lat() {
		return lat;
	}
	public double lon() {
		return lon;
	}
	
	public int risk() {
		return risk;
	}
	public int frequency() {
		return frequency;
	}
	
	public double getDist(Intersection p1, Intersection p2) {
		int R = 6371; // Radius of the earth in km
		double dLat = deg2rad(p2.lat() - p1.lat());  // deg2rad below
		double dLon = deg2rad(p2.lon()-p1.lon()); 
		double a = 
		Math.sin(dLat/2) * Math.sin(dLat/2) +
		Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
		Math.sin(dLon/2) * Math.sin(dLon/2); 
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		double d = R * c; // Distance in km
		return d;
	}
	
	public double deg2rad(double deg) {
		return deg *(Math.PI/180);
	}
	
	public String toString() {
		return "(" + this.lat + "," + this.lon + ")" + this.frequency + " " + this.risk;
	}
	
	public int compareTo(Intersection p) {
		return this.frequency.compareTo(p.frequency());
	}

}

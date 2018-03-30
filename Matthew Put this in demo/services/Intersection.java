package services;
public class Intersection implements Comparable<Intersection>{
	private double lat;
	private double lon;
	private int frequency;
	private int risk;
	private String street1;
	private String street2;
	
	public Intersection(double lat, double lon, String street1, String street2) {
		this.lat = lat;
		this.lon = lon;
		this.frequency = 0;
		this.risk = 0;
		this.street1 = street1;
		this.street2 = street2;
	}
	
	public double getLat() {
		return lat;
	}
	public double getLon() {
		return lon;
	}
	
	public int risk() {
		return risk;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
		this.risk = frequency /50;
	}
	public int frequency() {
		return frequency;
	}
	public String getStreet1() {
		return street1;
	}
	public String getStreet2() {
		return street2;
	}
	
	public double getDist(Intersection p) {
		Intersection p1 = this;
		Intersection p2 = p;
		int R = 6371; // Radius of the earth in km
		double dLat = deg2rad(p2.getLat() - p1.getLat());  // deg2rad below
		double dLon = deg2rad(p2.getLon()- p1.getLon()); 
		double a = 
		Math.sin(dLat/2) * Math.sin(dLat/2) +
		Math.cos(deg2rad(p1.getLat())) * Math.cos(deg2rad(p2.getLat())) * 
		Math.sin(dLon/2) * Math.sin(dLon/2); 
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		double d = R * c; // Distance in km
		return d;
	}
	
	public double deg2rad(double deg) {
		return deg *(Math.PI/180);
	}
	
	public String toString() {
		return "(" + this.lat + "," + this.lon + ") " + this.street1 + "at " + this.street2;
	}
	
	public boolean equals(Intersection i) {
		return (this.lat == i.getLat() && this.lon == i.getLon());
	}
	
	public int compareTo(Intersection p) {
		return ((Integer)this.frequency).compareTo(p.frequency());
	}
	
	public int compareBy(Intersection p,Intersection dest) {
		return ((Double)this.getDist(dest)).compareTo(p.getDist(dest));
	}
	

}

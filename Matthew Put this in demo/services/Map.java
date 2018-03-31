package services;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;


public class Map {
	private HashMap<String,ArrayList<Intersection>> connections;
	private HashSet<Intersection> visited;
	private Stack<Intersection> path;
	private Stack<Intersection> tempPath;
	private double minPathDistance;
	private HashMap<String,Intersection> tracePath;
	
	
	
	
	public Map(String fileName){
		BufferedReader br = null;
		String line = "";
		this.connections = new HashMap<String,ArrayList<Intersection>>();
		this.visited = new HashSet<Intersection>();
		this.path = null;
		this.tempPath = new Stack<Intersection>();
		this.minPathDistance = Double.MAX_VALUE;
		
		try {
			br = new BufferedReader(new FileReader(fileName));
			@SuppressWarnings("unused")
			String header = br.readLine();
			//int counter = 0;
			while((line = br.readLine()) != null) {
				String[] parsed = line.split(",");
				try {					
					if (!parsed[12].equals("D/E")) {
						//System.out.println(counter++);
						double lat = Double.parseDouble(parsed[5]);
						double lon = Double.parseDouble(parsed[6]);
						Intersection temp = new Intersection(lat,lon,parsed[11],parsed[12]);
						if (!connections.containsKey(parsed[11])) {
							ArrayList<Intersection> tempList = new ArrayList<Intersection>();
							tempList.add(temp);
							connections.put(parsed[11], tempList);
						}
						else {
							connections.get(parsed[11]).add(temp);
						}
						
						if (!connections.containsKey(parsed[12])) {
							ArrayList<Intersection> tempList = new ArrayList<Intersection>();
							tempList.add(temp);
							connections.put(parsed[12], tempList);
						}
						else {
							connections.get(parsed[12]).add(temp);
						}
						
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	public HashMap<String, ArrayList<Intersection>> getConnections(){
		return connections;
	}
	
	public void dfs(Intersection p1, Intersection p2, double curDist){
		visited.add(p1);
		tempPath.add(p1);
		if (p1.getDist(p2) == 0) {
			double curPathDistance = getPathDist();
			System.out.println(curPathDistance);
			if (curPathDistance < minPathDistance) {
				path = new Stack<Intersection>();
				minPathDistance = curPathDistance;
				path.addAll(tempPath);	
				System.out.println(minPathDistance + " km");
				return;
			}
					
		}
		
		ArrayList<Intersection> alongRoad = new ArrayList<Intersection>();
		for (Intersection i : connections.get(p1.getStreet1()))
			if (i.getDist(p2) < curDist)
				alongRoad.add(i);
		for (Intersection i : connections.get(p1.getStreet2()))
			if (i.getDist(p2) < curDist)
				alongRoad.add(i);
		SortByDist.sortMerge(alongRoad, p2);
		
		Iterator<Intersection> connectedIntersection = alongRoad.iterator();
		while (connectedIntersection.hasNext()) {
			Intersection next = connectedIntersection.next();
			
			if (!visited.contains(next)) {
				//System.out.println(next.getDist(p2));
				dfs(next,p2,next.getDist(p2));
				visited.remove(tempPath.peek());
				tempPath.pop();
			}
		}	
	}
	
	public Stack<Intersection> iterativeDFS(Intersection start, Intersection end){
		this.visited = new HashSet<Intersection>();
		Stack<Intersection> stack = new Stack<Intersection>();
		tracePath = new HashMap<String,Intersection>();
		
		stack.push(start);
		
		while(!stack.empty()) {
			Intersection cur = stack.peek();
			stack.pop();
			visited.add(cur);
			//System.out.println(cur);
			double curDist = cur.getDist(end);
			if (curDist == 0) {
				path = tracePath(start,end);
				return path;
			}
			
			ArrayList<Intersection> alongRoad = new ArrayList<Intersection>();
			for (Intersection i : connections.get(cur.getStreet1()))
				if (i.getDist(end) < curDist)
					alongRoad.add(i);
			for (Intersection i : connections.get(cur.getStreet2()))
				if (i.getDist(end) < curDist)
					alongRoad.add(i);
			SortByDist.sortMerge(alongRoad, end);
			Collections.reverse(alongRoad);
			Iterator<Intersection> connectedIntersection = alongRoad.iterator();
			while (connectedIntersection.hasNext()) {
				Intersection next = connectedIntersection.next();
				if (!visited.contains(next)) {
					stack.push(next);
					tracePath.put(next.getStreet1()+next.getStreet2(), cur);
				}
			}	
			
		}
		
		return null;
	}
	
	public double getPathDist() {
		double dist = 0;
		for (int i = 0; i <path.size()-1; i++) {
			dist += path.get(i).getDist(path.get(i+1));
		}
		return dist;
	}
	
	public Stack<Intersection> tracePath(Intersection p1, Intersection p2){
		Stack<Intersection> output = new Stack<Intersection>();
		Intersection cur = p2;
		while(!cur.equals(p1)) {
			output.add(cur);
			cur = tracePath.get(cur.getStreet1()+cur.getStreet2());
		}
		output.add(p1);
		Collections.reverse(output);
		return output;
	}
	
	

}

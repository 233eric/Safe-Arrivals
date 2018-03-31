package services;

import java.util.Stack;

public class Path {
	private final Stack<Intersection> path;
	private final double pathLength;
	
	public Path(Stack<Intersection> path,double pathLength) {
		this.path = path;
		this.pathLength = pathLength;
	}
	
	public Stack<Intersection> getPath(){
		return path;
	}
	public double getPathLength() {
		return pathLength;
	}
	

}

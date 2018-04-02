import java.io.*;
import java.util.ArrayList;

public class SafeArrivalRun {
	public static void main(String[] args) throws IOException {
		String input = "parsedCollisions.txt";
		int numLines = SafeArrivalParse.countLines(input);
		System.out.println(numLines);
		Intersection safeArray[] = new Intersection[numLines];
		SafeArrivalParse.organizeText(safeArray, input);
		Merge.sortMerge(safeArray, safeArray.length);
		
		Map laMap = new Map(safeArray);
		ArrayList<Intersection> epi = laMap.epiCenters();
		
		for(Intersection i : epi)
			for(Intersection j : laMap.intersectionInRadius(i,0.15))
				System.out.println(j);
		
		
	}

}

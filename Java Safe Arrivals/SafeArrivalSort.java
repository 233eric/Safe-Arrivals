import java.io.*;

public class SafeArrivalSort {

	public static int countLines(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    } finally {
	        is.close();
	    }
	}
	
	public static void organizeText(double[][] arr, String filename) throws IOException{
		 File file = new File(filename);
		 BufferedReader br = new BufferedReader(new FileReader(file));
		 String st;
		 int count = 0;
		 double [] splitDub = new double[4];
		 while ((st = br.readLine()) != null) {
			 String[] splitString = st.split(" ");
			 for (int i = 0; i < 4; i++) {
				 splitDub[i] = Double.parseDouble(splitString[i]);
			 }
			 arr[count] = new Intersection(splitDub[0],splitDub[1],(int)splitDub[2],(int)splitDub[3])
		 }
	}
	
	public static void main(String[] args) throws IOException {
		String input = "parsedCollisions.txt";
		int numLines = countLines(input);
		System.out.println(numLines);
		Intersection safeArray[] = new Intersection[numLines];
		organizeText(safeArray, input);
		
		System.out.println(safeArray[5]);
		

	}

}

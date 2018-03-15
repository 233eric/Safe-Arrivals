import java.io.*;

public class SafeArrivalParse {

	private static BufferedReader br;

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
	
	public static void organizeText(Intersection[] arr, String filename) throws IOException{
		 File file = new File(filename);
		 br = new BufferedReader(new FileReader(file));
		 String st;
		 int count = 0;
		 double [] splitDub = new double[3];
		 while ((st = br.readLine()) != null) {
			 String[] splitString = st.split(" ");
			 for (int i = 0; i < 3; i++) {
				 splitDub[i] = Double.parseDouble(splitString[i]);
			 }
			 arr[count] = new Intersection(splitDub[0],splitDub[1],(int)splitDub[2]);
			 count++;
		 }
	}

}

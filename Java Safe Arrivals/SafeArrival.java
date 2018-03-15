import java.io.BufferedReader;
import java.io.*;
import java.net.*;
import org.json.*;

public class SafeArrival {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String ori = br.readLine();
//		String des = br.readLine();
		
		String theURL = "https://maps.googleapis.com/maps/api/directions/json?key=";
		String apiKey = "AIzaSyCzcL1raU5fiQkZQO83GMx10rd3Vxhnj8c";
		
		String origin = "288 Broadway Avenue Hamilton";
		String dest = "McMaster University";
		String mode = "driving";
		
		theURL += apiKey + "&origin=" + origin + "&destination=" + dest + "&mode=" + mode;
		theURL = theURL.replace(" ","%20");
		
		System.out.println(theURL);
		
		URL url = new URL(theURL);
		
//		HttpURLConnection con = (HttpURLConnection) url.openConnection();
//		con.setRequestMethod("GET");
//		con.setConnectTimeout(5000);
//		con.setReadTimeout(5000);
//		int status = con.getResponseCode();
//		BufferedReader in = new BufferedReader(
//				new InputStreamReader(con.getInputStream()));
//				String inputLine;
//				StringBuffer content = new StringBuffer();
//				while ((inputLine = in.readLine()) != null) {
//				    content.append(inputLine);
//				}
//				in.close();
//		con.disconnect();
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		int status = con.getResponseCode();
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
				String inputLine;
				String content = "";
				while ((inputLine = in.readLine()) != null) {
				    content += inputLine + "\n";
				}
				in.close();
		con.disconnect();
		
		System.out.println(content);
		String contentStr = "[" + content.toString() + "]";
		
		JSONObject obj = new JSONObject(content);
		
		System.out.println(obj);
		
//		System.out.println("");
//		JSONArray jsonObj = new JSONArray(new JSONTokener (contentStr));
		
		System.out.println("");
		
		System.out.println(obj.getJSONArray("routes"));
		
		JSONArray arr = obj.getJSONArray("routes");
		System.out.println(arr.getJSONObject(0));
		
		obj = arr.getJSONObject(0);
		System.out.println(obj.getJSONArray("legs"));
		
		arr = obj.getJSONArray("legs");
		obj = arr.getJSONObject(0);
		
		arr = obj.getJSONArray("steps");
		System.out.println(arr);
		System.out.println("");
		
		for (int i = 0; i < arr.length(); i++) {
			System.out.println((arr.getJSONObject(i)).getJSONObject("start_location"));
			if (i == arr.length()-1)
				System.out.println((arr.getJSONObject(i)).getJSONObject("end_location"));
		}
		
//		obj = arr.getJSONObject(1);
//		System.out.println(obj);
		

	}

}

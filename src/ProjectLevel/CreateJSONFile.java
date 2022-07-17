package ProjectLevel;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

public class CreateJSONFile {
	public static final String jsonFilePath = "input\\jsonfile.json";
	private static double out;
	private static String rl;
	
	public CreateJSONFile() {
		out = new Predict().result;
		Analysis();
		Create();
	}
	
	private static void Analysis() {
		if(out==0) {
			rl="Medium";
		}else if(out==1) {
			rl="Low";
		}else if(out==2) {
			rl="High";
		}else {
			rl="Null";
		}
	}
	
	private static void Create() {
		JSONObject obj = new JSONObject();
		
		//JSONObject class creates a json object,
		//provides a put function to insert the details into json object
		obj.put("reusabilityLevel", rl);

		
		try (FileWriter file = new FileWriter(jsonFilePath)) {
			//File Writer creates a file in write mode at the given location 
			file.write(obj.toJSONString());

			//write function is use to write in file,
			//here we write the Json object in the file
			file.flush();

		}
		catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(obj);
		//to print our JSon object
	}
}

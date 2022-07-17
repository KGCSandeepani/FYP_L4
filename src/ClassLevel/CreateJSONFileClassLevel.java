package ClassLevel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CreateJSONFileClassLevel {
	public static final String jsonFilePathClassLevel = "output\\json_cl.json";
	private static double[] output;
	private static String[] rl;
	private List<String> packageName;
	private List<String> className;
	FileWriter file;
	
	public CreateJSONFileClassLevel() {
		output = new PredictClassLevel().results;
		rl = new String[output.length];
		Analysis();
		DataRead();
		Create();
	}
	
	private static void Analysis() {
		for (int i = 0; i < output.length; i++) {
			if(output[i]==0) {
				rl[i]="High";
			}else if(output[i]==2) {
				rl[i]="Low";
			}else if(output[i]==1) {
				rl[i]="Medium";
			}else {
				rl[i]="Null";
			}
		}
		
	}
	
	private void Create() {
		
		//JSONObject obj = new JSONObject();
 
        JSONArray classes = new JSONArray();

        for (int i = 0; i < output.length; i++) {
			JSONObject ob = new JSONObject();
			ob.put("package", packageName.get(i));
			ob.put("class", className.get(i));
			ob.put("reusabilityLevel", rl[i]);
			
			classes.add(ob);
		}
        //obj.put("Classes", classes);
        
        try {
        	file = new FileWriter(jsonFilePathClassLevel);
            file.write(classes.toJSONString());
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
 
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
//		try (FileWriter file = new FileWriter(jsonFilePathClassLevel)) {
//			for (int i = 0; i < output.length; i++) {
//				JSONObject obj = new JSONObject();
//				obj.put("package", packageName.get(i));
//				obj.put("class", className.get(i));
//				obj.put("reusabilityLevel", rl[i]);
//				
//				file.write(obj.toJSONString());
//				file.flush();
//			}
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
		
			
	}
	
	private void DataRead() {
		
        String csvFile = "input\\testDataClassLevel.csv";
        BufferedReader br = null;
        
        String line = "";
        String cvsSplitBy = ",";
        
        packageName = new ArrayList<String>();  
        className = new ArrayList<String>(); 
        try {
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while ((line = br.readLine()) != null) {              
                Object[] data = line.split(cvsSplitBy);            
                packageName.add((String)data[0]);
                className.add((String)data[1]);                          
            }
                        	
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

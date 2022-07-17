package ProjectLevel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestDataCreationOld {
	
	public static void TestDataFileCreation() {
		String csvFile = "C:\\Users\\Chamodi Sandeepani\\Desktop\\Android-Universal-Image-Loader-master.csv";
	    BufferedReader br = null;
	    FileWriter csvWriter = null;
	    String line = "";
	    String cvsSplitBy = ",";
	    List<Object> firstRowList=new ArrayList<Object>();  
	    List<Object> secondRowList=new ArrayList<Object>(); 
	    try {
	        br = new BufferedReader(new FileReader(csvFile));
	        line = br.readLine();
	        while ((line = br.readLine()) != null) {              
	            Object[] data = line.split(cvsSplitBy);
	            System.out.println("1 :" + data[0] + " , 2 :" + data[1] );                            
	            firstRowList.add(data[0]);  
	            secondRowList.add(data[1]);  
	        }
	                  
	        	csvWriter = new FileWriter("new.csv");           	
	
	        	for (int i=0;i<firstRowList.size()-1;i++) {
	        	    csvWriter.append(firstRowList.get(i)+",");           	
	        	}
	        	csvWriter.append(firstRowList.get(firstRowList.size()-1)+"\n");
	        	
	        	for (int i=0;i<secondRowList.size()-1;i++) {
	        	    csvWriter.append(secondRowList.get(i)+",");           	
	        	}
	        	csvWriter.append(secondRowList.get(secondRowList.size()-1)+"\n");
		        	
	//        	for (Object rowData : secondRowList) {
	//        	    csvWriter.append(rowData+",");           	
	//        	}
	//        	csvWriter.append("\n");
	        	
	        	csvWriter.flush();
	        	
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	                csvWriter.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
}

package ProjectLevel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainDataCreation{
	private static final File trainDataFile = new File("trainDataset.csv");	
	private String fileName;
	private String metric[]= {"Lines of Code","Cyclomatic Complexity","Weight Methods per Class","Depth of Inheritance Tree",
    		"Number of Children","Coupling between Objects","Response for Class","Lack of Cohesion of Methods"};
	private List<String> firstRowList;
	private List<Object> secondRowList;
	
	public TrainDataCreation(String fname) {
		fileName = fname+".csv";
		trainDataRead();
		trainDataWrite();
	}
		
	
	private void trainDataRead() {
		
        String csvFile = "input\\"+fileName;
        BufferedReader br = null;
        
        String line = "";
        String cvsSplitBy = ",";
        
        firstRowList = new ArrayList<String>(Arrays.asList(metric));  
        secondRowList = new ArrayList<Object>(); 
        try {
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while ((line = br.readLine()) != null) {              
                Object[] data = line.split(cvsSplitBy);
                System.out.println("1 :" + data[0] + " , 2 :" + data[1] );  
                if(firstRowList.contains(data[0])) {
                	secondRowList.add(data[1]);
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
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
	private void trainDataWrite() {
		BufferedWriter bufferedWriter = null;
        FileWriter csvWriter = null;
		try {
			csvWriter = new FileWriter(trainDataFile,true);  
            bufferedWriter=new BufferedWriter(csvWriter);
            
        	if (trainDataFile.length() == 0) {
        		bufferedWriter.write("Project,");
        		for (int i=0;i<firstRowList.size()-1;i++) {
        			bufferedWriter.append(firstRowList.get(i)+",");           	
            	}
        		bufferedWriter.append(firstRowList.get(firstRowList.size()-1)+"\n");           	
            	
            }
        	bufferedWriter.append(fileName+",");
        	for (int i=0;i<secondRowList.size()-1;i++) {
        		bufferedWriter.append(secondRowList.get(i)+",");           	
        	}
        	bufferedWriter.append(secondRowList.get(secondRowList.size()-1)+"\n");
      	
        	bufferedWriter.flush();
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                	bufferedWriter.close();                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
}
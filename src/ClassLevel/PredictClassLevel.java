package ClassLevel;

import java.util.Arrays;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.clusterers.SimpleKMeans;
//import weka.clusterers.SimpleKMeans;
import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class PredictClassLevel {
	private static SimpleKMeans modelClassLevel;
	public double[] results;
	
	  public PredictClassLevel() {
		  test();
	  }
	  
	  private void test() {		  
		  try {
				ClusterEvaluation eval = new ClusterEvaluation();
		        DataSource src1 = new DataSource("input\\testDataClassLevel.csv");
		        Instances tdt = src1.getDataSet();
		        
		        String[] options = new String[2];
		        options[0] = "-R";                                    // "range"
		        options[1] = "1,2,6,7";                                   // attribute
		        Remove remove = new Remove();                         // new instance of filter
		        remove.setOptions(options);                           // set options
		        remove.setInputFormat(tdt);                          // inform filter about dataset **AFTER** setting options
		        Instances newData = Filter.useFilter(tdt, remove);   // apply filter	
		        
		        modelClassLevel=(SimpleKMeans) weka.core.SerializationHelper.read("ModelClassLevel.model");
		        eval.setClusterer(modelClassLevel);
		        eval.evaluateClusterer(newData);
		        System.out.println("=============================== evaluation ===============================");
		        System.out.println(eval.clusterResultsToString());
		        //System.out.println("# of clusters: " + eval.getNumClusters());
		        //System.out.println(Arrays.toString(eval.getClusterAssignments()));
		        results=eval.getClusterAssignments();
		        System.out.println(Arrays.toString(results));
		        
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
}
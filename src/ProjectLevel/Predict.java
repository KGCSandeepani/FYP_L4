package ProjectLevel;

import java.util.Arrays;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
//import weka.clusterers.SimpleKMeans;
import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;

public class Predict {
	private static EM model;
	public double result;
	
	  public Predict() {
		  test();
	  }
	  
	  private void test() {
		  double[] results;
		  try {
				ClusterEvaluation eval = new ClusterEvaluation();
		        DataSource src1 = new DataSource("input\\testData.csv");
		        Instances tdt = src1.getDataSet();
		        model=(EM) weka.core.SerializationHelper.read("EM.model");
		        eval.setClusterer(model);
		        eval.evaluateClusterer(tdt);
		        System.out.println("=============================== evaluation ===============================");
		        System.out.println(eval.clusterResultsToString());
		        //System.out.println("# of clusters: " + eval.getNumClusters());
		        //System.out.println(Arrays.toString(eval.getClusterAssignments()));
		        results=eval.getClusterAssignments();
		        System.out.println(Arrays.toString(results));
		        result = results[0];
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
}
package client;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree.Algorithm;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree.BadDecisionException;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree.Categories;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree.DecisionTree;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree.ID3Algorithm;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree.UnknownDecisionException;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Timeseries.EWMA;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Timeseries.PEWMA;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Timeseries.TimeSeries;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPointType;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DoubleDataPoint;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.StringDataPoint;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.resource.ReadCSV;

public class IoT1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DecisionTree dt = new DecisionTree();
		Algorithm algorithm=null;
		dt.setAlgorithm(algorithm);
		dt.setFeatures(new String[]{"Outlook",  "Temperature", "Humidity", "Wind"});
		try {
//			dt.addExample(new StringDataPoint[]{(new StringDataPoint("Outlook",1,DataPointType.STRING,"Sunny")), (new StringDataPoint("Temperature",1,DataPointType.STRING,"Hot")), (new StringDataPoint("Humidity",1,DataPointType.STRING,"High")), (new StringDataPoint("Wind",1,DataPointType.STRING,"Weak"))}, Categories.FALSE)
//            .addExample(new StringDataPoint[]{(new StringDataPoint("Outlook",1,DataPointType.STRING,"Sunny")), (new StringDataPoint("Temperature",1,DataPointType.STRING,"Hot")), (new StringDataPoint("Humidity",1,DataPointType.STRING,"High")), (new StringDataPoint("Wind",1,DataPointType.STRING,"Strong"))}, Categories.FALSE)
//            .addExample(new StringDataPoint[]{(new StringDataPoint("Outlook",1,DataPointType.STRING,"Overcast")), (new StringDataPoint("Temperature",1,DataPointType.STRING,"Hot")), (new StringDataPoint("Humidity",1,DataPointType.STRING,"High")), (new StringDataPoint("Wind",1,DataPointType.STRING,"Weak"))}, Categories.TRUE)
//            .addExample(new StringDataPoint[]{(new StringDataPoint("Outlook",1,DataPointType.STRING,"Rain")), (new StringDataPoint("Temperature",1,DataPointType.STRING,"Mild")), (new StringDataPoint("Humidity",1,DataPointType.STRING,"High")), (new StringDataPoint("Wind",1,DataPointType.STRING,"Weak"))}, Categories.FALSE)
//            .addExample(new StringDataPoint[]{(new StringDataPoint("Outlook",1,DataPointType.STRING,"Rain")), (new StringDataPoint("Temperature",1,DataPointType.STRING,"Cool")), (new StringDataPoint("Humidity",1,DataPointType.STRING,"Normal")), (new StringDataPoint("Wind",1,DataPointType.STRING,"Weak"))}, Categories.TRUE)
//            .addExample(new StringDataPoint[]{(new StringDataPoint("Outlook",1,DataPointType.STRING,"Rain")), (new StringDataPoint("Temperature",1,DataPointType.STRING,"Cool")), (new StringDataPoint("Humidity",1,DataPointType.STRING,"Normal")), (new StringDataPoint("Wind",1,DataPointType.STRING,"Strong"))}, Categories.FALSE)
//            .addExample(new StringDataPoint[]{(new StringDataPoint("Outlook",1,DataPointType.STRING,"Overcast")), (new StringDataPoint("Temperature",1,DataPointType.STRING,"Cool")), (new StringDataPoint("Humidity",1,DataPointType.STRING,"Normal")), (new StringDataPoint("Wind",1,DataPointType.STRING,"Strong"))}, Categories.TRUE)
//            .addExample(new StringDataPoint[]{(new StringDataPoint("Outlook",1,DataPointType.STRING,"Sunny")), (new StringDataPoint("Temperature",1,DataPointType.STRING,"Mild")), (new StringDataPoint("Humidity",1,DataPointType.STRING,"High")), (new StringDataPoint("Wind",1,DataPointType.STRING,"Weak"))}, Categories.FALSE)
//            .addExample(new StringDataPoint[]{(new StringDataPoint("Outlook",1,DataPointType.STRING,"Sunny")), (new StringDataPoint("Temperature",1,DataPointType.STRING,"Cool")), (new StringDataPoint("Humidity",1,DataPointType.STRING,"Normal")), (new StringDataPoint("Wind",1,DataPointType.STRING,"Weak"))}, Categories.TRUE)
//            .addExample(new StringDataPoint[]{(new StringDataPoint("Outlook",1,DataPointType.STRING,"Rain")), (new StringDataPoint("Temperature",1,DataPointType.STRING,"Mild")), (new StringDataPoint("Humidity",1,DataPointType.STRING,"Normal")), (new StringDataPoint("Wind",1,DataPointType.STRING,"Weak"))}, Categories.TRUE)
//            .addExample(new StringDataPoint[]{(new StringDataPoint("Outlook",1,DataPointType.STRING,"Sunny")), (new StringDataPoint("Temperature",1,DataPointType.STRING,"Mild")), (new StringDataPoint("Humidity",1,DataPointType.STRING,"Normal")), (new StringDataPoint("Wind",1,DataPointType.STRING,"Strong"))}, Categories.TRUE)
//            .addExample(new StringDataPoint[]{(new StringDataPoint("Outlook",1,DataPointType.STRING,"Overcast")), (new StringDataPoint("Temperature",1,DataPointType.STRING,"Mild")), (new StringDataPoint("Humidity",1,DataPointType.STRING,"High")), (new StringDataPoint("Wind",1,DataPointType.STRING,"Strong"))}, Categories.TRUE)
//            .addExample(new StringDataPoint[]{(new StringDataPoint("Outlook",1,DataPointType.STRING,"Overcast")), (new StringDataPoint("Temperature",1,DataPointType.STRING,"Hot")), (new StringDataPoint("Humidity",1,DataPointType.STRING,"Normal")), (new StringDataPoint("Wind",1,DataPointType.STRING,"Weak"))}, Categories.TRUE)
//            .addExample(new StringDataPoint[]{(new StringDataPoint("Outlook",1,DataPointType.STRING,"Rain")), (new StringDataPoint("Temperature",1,DataPointType.STRING,"Mild")), (new StringDataPoint("Humidity",1,DataPointType.STRING,"High")), (new StringDataPoint("Wind",1,DataPointType.STRING,"Strong"))}, Categories.FALSE);
			
			
			dt.addExample(   new Double[]{0.1, 0.4, 0.7, 0.9}, Categories.FALSE)
			.addExample(   new Double[]{0.1, 0.4, 0.7, 1.0}, Categories.FALSE)
			.addExample(   new Double[]{0.2, 0.4, 0.7, 0.9}, Categories.TRUE)
			.addExample(   new Double[]{0.3, 0.5, 0.7, 0.9}, Categories.KATI)
			.addExample(   new Double[]{0.3, 0.6, 0.8, 0.9}, Categories.KATI)
			.addExample(   new Double[]{0.3, 0.6, 0.8, 1.0}, Categories.KATI)
			.addExample(   new Double[]{0.2, 0.6, 0.8, 1.0}, Categories.TRUE)
			.addExample(   new Double[]{0.1, 0.5, 0.7, 0.9}, Categories.FALSE)
			.addExample(   new Double[]{0.1, 0.6, 0.8, 0.9}, Categories.TRUE)
			.addExample(   new Double[]{0.3, 0.5, 0.8, 0.9}, Categories.KATI)
			.addExample(   new Double[]{0.1, 0.5, 0.8, 1.0}, Categories.TRUE)
			.addExample(   new Double[]{0.2, 0.5, 0.7, 1.0}, Categories.TRUE)
			.addExample(   new Double[]{0.2, 0.4, 0.8, 0.9}, Categories.TRUE)
			.addExample(   new Double[]{0.3, 0.5, 0.7, 1.0}, Categories.KATI);
		} catch (UnknownDecisionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(dt.toString());
		Map<String, Double> case1 = new HashMap<String, Double>();
		case1.put("Outlook", 0.3);
	    case1.put("Temperature", 0.4);
	    case1.put("Humidity", 0.7);
	    case1.put("Wind", 1.0);
//	    case1.put("Outlook", (new StringDataPoint("Outlook",1,DataPointType.STRING,"Rain")));
//	    case1.put("Temperature", (new StringDataPoint("Temperature",1,DataPointType.STRING,"Hot")));
//	    case1.put("Humidity", (new StringDataPoint("Humidity",1,DataPointType.STRING,"High")));
//	    case1.put("Wind", (new StringDataPoint("Wind",1,DataPointType.STRING,"Strong")));
		try {
			System.out.println(dt.apply(case1));
		} catch (BadDecisionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
				
		// ArrayList<DoubleDataPoint> data = new ArrayList<DoubleDataPoint>();
		// ReadCSV<DoubleDataPoint> reader = new ReadCSV<DoubleDataPoint>();
		// try {
		// reader.openFile("test.csv");
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		// data = reader.readData(",", 2, DataPointType.DOUBLE);
		// TimeSeries<DoubleDataPoint> movingAverage = new
		// PEWMA<DoubleDataPoint>(0.2,0.5);
		//// TimeSeries<DoubleDataPoint> movingAverage = new
		// EWMA<DoubleDataPoint>(0.2);
		// for (DoubleDataPoint dataPoint : data) {
		// movingAverage.addDataPoint(dataPoint);
		// }
		//
		// System.out.println(movingAverage.getAverage());

	}

}

package client;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Timeseries.PEWMA;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Timeseries.TimeSeries;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.clustering.kMeans.Cluster;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.clustering.kMeans.KMeans;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPointType;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DoubleDataPoint;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.resource.ReadCSV;

public class IoT1 {

	public static void main(String[] args) {
		// String pointsFilePath = "data.csv";
		// KMeans kMeans = new KMeans(pointsFilePath, 2);
		// List<Cluster> pointsClusters = kMeans.getPointsClusters();
		// for (int i = 0; i < kMeans.k; i++)
		// System.out.println("Cluster " + i + ": " + pointsClusters.get(i));

		ArrayList<DoubleDataPoint> data = new ArrayList<DoubleDataPoint>();
		ReadCSV<DoubleDataPoint> reader = new ReadCSV<DoubleDataPoint>();
		try {
			reader.openFile("test.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		data = reader.readData(",", 2, DataPointType.DOUBLE);
		TimeSeries<DoubleDataPoint> movingAverage = new PEWMA<DoubleDataPoint>(0.5, 0.01);
		// TimeSeries<DoubleDataPoint> movingAverage = new
		// EWMA<DoubleDataPoint>(0.2);
		for (DoubleDataPoint dataPoint : data) {
			movingAverage.addDataPoint(dataPoint);
			System.out.println(movingAverage.getAverage());
		}

		System.out.println(movingAverage.getAverage());

	}
}
//
// import java.io.FileNotFoundException;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;
//
// import
// ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree.Algorithm;
// import
// ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree.BadDecisionException;
// import
// ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree.Categories;
// import
// ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree.DecisionTree;
// import
// ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree.ID3Algorithm;
// import
// ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree.UnknownDecisionException;
// import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Timeseries.EWMA;
// import
// ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Timeseries.PEWMA;
// import
// ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Timeseries.TimeSeries;
// import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;
// import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPointType;
// import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DoubleDataPoint;
// import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DoubleVector;
// import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.StringDataPoint;
// import ucy.cs.LInC.IoT.LowCostDataMining.Framework.resource.ReadCSV;
//
// public class IoT1 {
//
// public static void main(String[] args) {
// // TODO Auto-generated method stub
//
// DecisionTree<Double> dt = new DecisionTree();
// ID3Algorithm algorithm = null;
// dt.setAlgorithm(algorithm);
// dt.setFeatures(new String[] { "Heart", "Calories" });
// try {
// int[] columns = { 1, 2 };
// int lbl = 4;
// ArrayList<DoubleVector> data = new ArrayList<DoubleVector>();
// ArrayList<String> classes = new ArrayList<String>();
// ReadCSV<DoubleDataPoint> reader = new ReadCSV<DoubleDataPoint>();
// try {
// reader.openFile("train.csv");
// } catch (FileNotFoundException e) {
// e.printStackTrace();
// }
// classes = reader.readData(data, ",", columns, lbl, DataPointType.DOUBLE);
// for (int i = 0; i < data.size(); i++) {
// for (int j = 0; j < data.get(i).getValue().length; j++) {
// if (classes.get(i).equals("IN_DEFAULT_ZONE_1")) {
// dt.addExample(data.get(i).getValue(), Categories.ZONE1);
// } else if (classes.get(i).equals("null")) {
// dt.addExample(data.get(i).getValue(), Categories.NONE);
// } else
// System.err.println("Something went wrong!");
// }
// }
// // dt.addExample(new Double[] { 0.1, 0.4, 0.7, 0.9 },
// // Categories.FALSE)
// // .addExample(new Double[] { 0.1, 0.4, 0.7, 1.0 },
// // Categories.FALSE)
// // .addExample(new Double[] { 0.2, 0.4, 0.7, 0.9 }, Categories.TRUE)
// // .addExample(new Double[] { 0.3, 0.5, 0.7, 0.9 }, Categories.KATI)
// // .addExample(new Double[] { 0.3, 0.6, 0.8, 0.9 }, Categories.KATI)
// // .addExample(new Double[] { 0.3, 0.6, 0.8, 1.0 }, Categories.KATI)
// // .addExample(new Double[] { 0.2, 0.6, 0.8, 1.0 }, Categories.TRUE)
// // .addExample(new Double[] { 0.1, 0.5, 0.7, 0.9 },
// // Categories.FALSE)
// // .addExample(new Double[] { 0.1, 0.6, 0.8, 0.9 }, Categories.TRUE)
// // .addExample(new Double[] { 0.3, 0.5, 0.8, 0.9 }, Categories.KATI)
// // .addExample(new Double[] { 0.1, 0.5, 0.8, 1.0 }, Categories.TRUE)
// // .addExample(new Double[] { 0.2, 0.5, 0.7, 1.0 }, Categories.TRUE)
// // .addExample(new Double[] { 0.2, 0.4, 0.8, 0.9 }, Categories.TRUE)
// // .addExample(new Double[] { 0.3, 0.5, 0.7, 1.0 },
// // Categories.KATI);
// } catch (Exception e)
//
// {
// // catch (UnknownDecisionException e)
// //
// // {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
//
// try {
// int[] columns = { 1, 2 };
// int lbl = 4;
// ArrayList<DoubleVector> data = new ArrayList<DoubleVector>();
// ArrayList<String> classes = new ArrayList<String>();
// ReadCSV<DoubleDataPoint> reader = new ReadCSV<DoubleDataPoint>();
// try {
// reader.openFile("test.csv");
// } catch (FileNotFoundException e) {
// e.printStackTrace();
// }
// classes = reader.readData(data, ",", columns, lbl, DataPointType.DOUBLE);
// for (int i = 0; i < data.size(); i++) {
// Map<String, Double> case1 = new HashMap<String, Double>();
// case1.put("Calories", data.get(i).getValue()[1]);
// case1.put("Heart", data.get(i).getValue()[0]);
// try {
// System.out.println(dt.apply(case1));
// } catch (BadDecisionException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }
// } catch (Exception e) {
// e.printStackTrace();
// }
//
// Map<String, Double> case1 = new HashMap<String, Double>();
// case1.put("Heart", 98.0);
// case1.put("Calories", 8.3954);
// try {
// System.out.println(dt.apply(case1));
// } catch (BadDecisionException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
//
// // ArrayList<DoubleDataPoint> data = new ArrayList<DoubleDataPoint>();
// // ReadCSV<DoubleDataPoint> reader = new ReadCSV<DoubleDataPoint>();
// // try {
// // reader.openFile("test.csv");
// // } catch (FileNotFoundException e) {
// // e.printStackTrace();
// // }
// // data = reader.readData(",", 2, DataPointType.DOUBLE);
// // TimeSeries<DoubleDataPoint> movingAverage = new
// // PEWMA<DoubleDataPoint>(0.5, 0.01);
// //// TimeSeries<DoubleDataPoint> movingAverage = new
// // EWMA<DoubleDataPoint>(0.2);
// // for (DoubleDataPoint dataPoint : data) {
// // movingAverage.addDataPoint(dataPoint);
// // }
// //
// // System.out.println(movingAverage.getAverage());
//
// }
//
// }

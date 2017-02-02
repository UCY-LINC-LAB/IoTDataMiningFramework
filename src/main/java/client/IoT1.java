package client;

import java.util.ArrayList;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.MovingAverage;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.TimeSeries;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.resource.ReadCSV;

public class IoT1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<DataPoint> data = new ArrayList<DataPoint>();
		ReadCSV reader = new ReadCSV();
		data = reader.readData("test.csv", ",", 2);
		TimeSeries movingAverage = new MovingAverage();
		for (DataPoint dataPoint : data) {
			movingAverage.addDataPoint(dataPoint);
		}
		
		System.out.println(movingAverage.getMedian());
		
	}

}

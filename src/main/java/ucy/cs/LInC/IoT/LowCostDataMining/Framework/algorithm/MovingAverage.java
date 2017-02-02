package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DoubleDataPoint;

public class MovingAverage extends TimeSeries {
	
	public void addDataPoint(DataPoint dp){
		count++;
		this.median=(this.median+dp.getValue())/this.count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TimeSeries test = new MovingAverage();
		DataPoint dp= new DoubleDataPoint("dataPoint"+count, "double", new Double(2.5), 1);
		test.addDataPoint(dp);
		DataPoint dp2= new DoubleDataPoint("dataPoint"+count, "double", new Double(3.5), 1);
		test.addDataPoint(dp2);
		
		System.out.println(test.getMedian());

	}

}

package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Timeseries;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;

public class CumulativeMovingAverage<T extends DataPoint> implements TimeSeries<T> {
	
	double median;
	int count;

	public CumulativeMovingAverage() {
		this.count = 0;
		this.median = 0;
	}

	public double getMedian() {
		return this.median;
	}

	public void addDataPoint(T dp) {
		// TODO Auto-generated method stub
		this.count++;
		this.median = this.median + ((Double)dp.getValue() - this.median) / this.count;
	}

}

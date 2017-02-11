package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Timeseries;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;

public class CumulativeMovingAverage<T extends DataPoint> implements TimeSeries<T> {
	
	double avg;
	int count;

	public CumulativeMovingAverage() {
		this.count = 0;
		this.avg = 0;
	}

	public double getAverage() {
		return this.avg;
	}

	public double addDataPoint(T dp) {
		// TODO Auto-generated method stub
		this.count++;
		this.avg = this.avg + ((Double)dp.getValue() - this.avg) / this.count;
		return this.avg;
	}

}

package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Timeseries;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;

public class CumulativeMovingAverage<T extends DataPoint> implements TimeSeries<T> {

	private double avg;
	private int count;
	private double sigma;

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
		this.avg = this.avg + ((Double) dp.getValue() - this.avg) / this.count;
		return this.avg;
	}

	public void reset() {
		this.avg = 0.0;
		this.count = 0;
	}

	@Override
	public double getStdDev() {
		// TODO Auto-generated method stub
		return this.sigma;
	}

	@Override
	public void calculateStdDev(T dp) {
		// TODO Auto-generated method stub
		this.sigma=Math.sqrt(Math.pow((Double)dp.getValue()-this.avg,2)/this.count);
	}

}

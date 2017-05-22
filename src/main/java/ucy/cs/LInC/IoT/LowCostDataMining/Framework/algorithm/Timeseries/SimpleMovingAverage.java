/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Timeseries;

import java.util.ArrayList;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;

/**
 * @author hamdy
 *
 */
public class SimpleMovingAverage<T extends DataPoint> implements TimeSeries<T> {

	private double avg;
	private int count;
	private int windowSize;
//	DataPoint outOfScope;
	private ArrayList<DataPoint> window = new ArrayList<DataPoint>();
	private double sigma;

	public SimpleMovingAverage(int windowSize) {
		this.count = 0;
		this.avg = 0;
		this.windowSize = windowSize;
	}

	public double getAverage() {
		// TODO Auto-generated method stub
		return this.avg;
	}

	public double addDataPoint(DataPoint dp) {
		// TODO Auto-generated method stub
		this.count++;
//		if (this.count == 1) {
//			this.outOfScope = new DoubleDataPoint(dp.getName(), dp.getSequenceID(), (Double) dp.getValue());
//		} else 
		if (count > this.windowSize) {
			this.avg = this.avg + (Double) dp.getValue() / this.count - (Double) window.remove(0).getValue() / this.count;
			window.add(dp);
		} else{
			window.add(dp);
			this.avg = this.avg + (Double) dp.getValue() / this.count;
		}
		return this.avg;	
	}
	
	public void reset() {
		this.avg = 0.0;
		this.count = 0;	
		this.window=new ArrayList<DataPoint>();
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

/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm;

import java.util.ArrayList;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DoubleDataPoint;

/**
 * @author hamdy
 *
 */
public class SimpleMovingAverage<T extends DataPoint> implements TimeSeries<T> {

	double median;
	int count;
	int windowSize;
	DataPoint outOfScope;
	ArrayList<DataPoint> window = new ArrayList<DataPoint>();

	public SimpleMovingAverage(int windowSize) {
		this.count = 0;
		this.median = 0;
		this.windowSize = windowSize;
	}

	public double getMedian() {
		// TODO Auto-generated method stub
		return this.median;
	}

	public void addDataPoint(DataPoint dp) {
		// TODO Auto-generated method stub
		this.count++;
//		if (this.count == 1) {
//			this.outOfScope = new DoubleDataPoint(dp.getName(), dp.getSequenceID(), (Double) dp.getValue());
//		} else 
		if (count > this.windowSize) {
			this.median = this.median + (Double) dp.getValue() / this.count - (Double) window.remove(0).getValue() / this.count;
			window.add(dp);
		} else{
			window.add(dp);
			this.median = this.median + (Double) dp.getValue() / this.count;
		}
			
	}

}

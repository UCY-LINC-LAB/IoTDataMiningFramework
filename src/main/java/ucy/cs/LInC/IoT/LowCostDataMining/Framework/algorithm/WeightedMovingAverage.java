/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;

/**
 * @author hamdy
 *
 */
public class WeightedMovingAverage<T extends DataPoint> implements TimeSeries<T> {

	double median;
	int count;
	double numerator;
	double total;

	public double getMedian() {
		// TODO Auto-generated method stub
		return this.median;
	}

	public void addDataPoint(T dp) {
		// TODO Auto-generated method stub
		this.count++;
		this.total = this.total + (Double) dp.getValue();
		

	}

}

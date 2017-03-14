/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Timeseries;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;

/**
 * @author hamdy
 *
 */
public class EWMA<T extends DataPoint> implements TimeSeries<T> {

	double avg = 0.0;
	double a;

	public EWMA(double a) throws RuntimeException {
		if (a > 1)
			throw new RuntimeException();
		else
			this.a = a;
	}

	public double getAverage() {
		// TODO Auto-generated method stub
		return this.avg;
	}

	public double addDataPoint(T dp) {
		// TODO Auto-generated method stub
		if (this.avg == 0.0) {
			this.avg = (Double) dp.getValue();
		} else {
			this.avg = calculateEWMA(this.a, this.avg, (Double) dp.getValue());
		}
		return this.avg;
	}

	public double calculateEWMA(double a, double avg, double value) {
		return a * avg + (1 - a) * value;
	}

	public void reset() {
		this.avg = 0.0;
	}

}

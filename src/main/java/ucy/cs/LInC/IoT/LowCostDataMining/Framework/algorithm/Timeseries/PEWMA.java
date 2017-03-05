/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Timeseries;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;

/**
 * @author hamdy
 *
 */
public class PEWMA<T extends DataPoint> extends EWMA<T> {

	double b;
	double sigma = 0.0;
	double z = 0.0;
//	double s1 = 0.0;
//	double s2 = 0.0;
	double prob = 0.0;

	public PEWMA(double a, double b) {
		super(a);
		// this.a=a;
		this.b = b;
	}

	public double getAverage() {
		// TODO Auto-generated method stub
		return this.avg;
	}

	public double addDataPoint(T dp) {
		// TODO Auto-generated method stub

		
		this.z=((Double)dp.getValue()-this.avg)/this.sigma;
		this.prob=probability(this.z);
		this.a = this.a * (1 - this.b * this.prob);
		this.avg=calculateEWMA(this.a*(1-this.b*this.prob),this.avg,(Double)dp.getValue());
		
//		this.s1 = calculateEWMA(this.a, this.avg, (Double) dp.getValue());
//		this.s2 = calculateEWMA(this.a, this.avg, Math.pow((Double) dp.getValue(), 2));
//		this.sigma = Math.sqrt(this.s2 + Math.pow(this.s1, 2));
		this.sigma=calculateSigma(dp);
		return this.avg;
	}

	public double probability(double z) {

		return (1/Math.sqrt(2*Math.PI))*Math.exp(-(Math.pow(z, 2))/2);
		
	}
	
	public double calculateSigma(T dp){
		return Math.sqrt(calculateEWMA(this.a, this.avg, (Double) dp.getValue()) + Math.pow(calculateEWMA(this.a, this.avg, Math.pow((Double) dp.getValue(), 2)), 2));
	}
	
	@Override
	public void reset() {
		this.avg = 0.0;
		this.prob=0.0;
		this.z=0.0;
		this.sigma=0.0;
	}

}

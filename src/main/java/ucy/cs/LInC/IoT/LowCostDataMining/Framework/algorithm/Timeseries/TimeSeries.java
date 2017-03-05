/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Timeseries;

/**
 * @author hamdy
 *
 */
public interface TimeSeries <T> {
		
	public double getAverage();
	
	public double addDataPoint(T dp);
	
	public void reset();

//	void addDataPoint(DataPoint dp);

	
		
	

}

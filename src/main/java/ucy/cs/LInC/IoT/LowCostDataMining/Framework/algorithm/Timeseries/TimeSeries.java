/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Timeseries;

/**
 * @author hamdy
 *
 */
public interface TimeSeries <T> {
		
	public double getMedian();
	
	public void addDataPoint(T dp);

//	void addDataPoint(DataPoint dp);

	
		
	

}
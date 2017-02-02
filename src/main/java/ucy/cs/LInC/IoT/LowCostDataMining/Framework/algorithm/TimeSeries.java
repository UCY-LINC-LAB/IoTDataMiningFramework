/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm;

import java.util.ArrayList;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;

/**
 * @author hamdy
 *
 */
public abstract class TimeSeries {
	
	static double median=0;
	static int count=0;
	
	public double getMedian(){
		return this.median;
	}
	
	public abstract void addDataPoint(DataPoint dp);
	
		
	

}

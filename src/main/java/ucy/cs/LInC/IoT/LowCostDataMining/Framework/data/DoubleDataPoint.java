package ucy.cs.LInC.IoT.LowCostDataMining.Framework.data;
/**
 * 
 */

/**
 * @author hmicha01
 *
 */
public class DoubleDataPoint extends DataPoint{
	double value;
	
	public DoubleDataPoint(String name, String type, double value, int timestamp){
		count++;
		this.setSequenceID(count);
		this.setName(name);
		this.setType(type);
		this.setValue(value);
		this.setTimestamp(timestamp);
	}
	
	/*
	 * 
	 * ------------SETTERS--------------
	 * 
	 * */
	public void setValue(double value){
		this.value=value;
	}
	
	/*
	 * 
	 * ------------GETTERS--------------
	 * 
	 * */
	public double getValue(){
		return this.value;
	}
}

package ucy.cs.LInC.IoT.LowCostDataMining.Framework.data;
/**
 * 
 */

/**
 * @author hmicha01
 *
 */
public class IntArrDataPoint extends DataPoint{

	int[] value;
	
	IntArrDataPoint(String name, String type, int[] value, int timestamp){
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
	public void setValue(int[] value){
		this.value=value;
	}
	
	/*
	 * 
	 * ------------GETTERS--------------
	 * 
	 * */
	public int[] getValue(){
		return this.value;
	}
	
}

/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.data;

/**
 * @author hamdy
 *
 */
public class DoubleArrDataPoint extends DataPoint {

	double[] value;

	DoubleArrDataPoint(String name, String type, double[] value, int timestamp) {
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
	 */
	public void setValue(double[] value) {
		this.value = value;
	}

	/*
	 * 
	 * ------------GETTERS--------------
	 * 
	 */
	public double[] getValue() {
		return this.value;
	}

}

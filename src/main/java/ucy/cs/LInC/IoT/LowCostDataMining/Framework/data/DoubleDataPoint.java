/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.data;

/**
 * @author hamdy
 *
 */
public class DoubleDataPoint extends DataPoint {

	Double value;
	
	public DoubleDataPoint(String name, int timestamp, double value) {
		super(name, timestamp, DataPointType.DOUBLE);
		this.value = value;
		construct();
	}

	@Override
	protected void construct() {
		System.out.println("Building Double");
		// add accessories
	}
	
	public Double getValue(){
		return this.value;
	}

}

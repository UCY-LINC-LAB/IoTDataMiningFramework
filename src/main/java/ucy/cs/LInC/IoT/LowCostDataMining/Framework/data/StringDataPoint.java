/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.data;

/**
 * @author hamdy
 *
 */
public class StringDataPoint extends DataPoint {

	String value;
	
	public StringDataPoint(String name, int timestamp, DataPointType type, String value) {
		super(name, timestamp, type);
		this.value=value;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void construct() {
		// TODO Auto-generated method stub
		
	}
	
	

}

/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.data;

/**
 * @author hamdy
 *
 */
public class DoubleVector extends DataPoint{

	Double[] value;
	
	public DoubleVector(String name, int timestamp, double[] value) {
		super(name, timestamp, DataPointType.DOUBLE);
		this.value=new Double[value.length];
		for (int i=0;i<value.length;i++){
			this.value[i] = value[i];
		}
		construct();
	}

	@Override
	protected void construct() {
//		System.out.println("Building Double");
		// add accessories
	}
	
	public Double[] getValue(){
		return this.value;
	}
	
}

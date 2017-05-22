/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.data;

import java.sql.Timestamp;
import java.util.Optional;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.label.Label;

/**
 * @author hamdy
 *
 */
public class DoubleVector extends DataPoint{

	Double[] value;
	
	public DoubleVector(String name, Timestamp timestamp, double[] value) {
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

	@Override
	public Label getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Object> getValue(String column) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

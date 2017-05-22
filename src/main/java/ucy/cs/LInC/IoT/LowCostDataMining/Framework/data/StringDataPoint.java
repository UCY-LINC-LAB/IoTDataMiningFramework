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
public class StringDataPoint extends DataPoint {

	String value;
	
	public StringDataPoint(String name, Timestamp timestamp, DataPointType type, String value) {
		super(name, timestamp, type);
		this.value=value;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void construct() {
		// TODO Auto-generated method stub
		
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

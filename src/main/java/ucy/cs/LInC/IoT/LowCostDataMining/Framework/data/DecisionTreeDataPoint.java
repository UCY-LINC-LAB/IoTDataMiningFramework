package ucy.cs.LInC.IoT.LowCostDataMining.Framework.data;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.label.Label;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

/**
 * Simple {@link DataSample} implementation which uses {@link HashMap} to keep
 * data in columns.
 * 
 *
 */
public class DecisionTreeDataPoint extends DataPoint {

	private Map<String, Object> values = Maps.newHashMap();

	/** Column name which contains data labels. */
//	private String labelColumn;

	private DecisionTreeDataPoint(String name, Timestamp timestamp, String labelColumn, String[] header, Object... dataValues) {
		super(name, timestamp, DataPointType.DTdata);
		this.name = labelColumn;
		for (int i = 0; i < header.length; i++) {
			this.values.put(header[i], dataValues[i]);
		}
	}

	@Override
	public Optional<Object> getValue(String column) {
		return Optional.ofNullable(values.get(column));
	}

	/**
	 * Assigned label of training data.
	 * 
	 * @return Label.
	 */
	public Label getLabel() {
		return (Label) values.get(name);
	}

	/**
	 * Create data sample without labels which is used on trained tree.
	 */
	public static DecisionTreeDataPoint newClassificationDataSample(Timestamp timestamp, String[] header, Object... values) {
		Preconditions.checkArgument(header.length == values.length);
		return new DecisionTreeDataPoint("trained", timestamp, null, header, values);
	}

	/**
	 * @param labelColumn
	 * @param header
	 * @param values
	 * @return
	 */
	public static DecisionTreeDataPoint newSimpleDataSample(Timestamp timestamp, String labelColumn, String[] header, Object... values) {
		Preconditions.checkArgument(header.length == values.length);
		return new DecisionTreeDataPoint("tested",timestamp,labelColumn, header, values);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "SimpleDataSample [values=" + values + "]";
	}

	@Override
	protected void construct() {
		// TODO Auto-generated method stub
		
	}

}

/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.data;

import java.sql.Timestamp;
import java.util.Optional;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.feature.Feature;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.label.Label;

/**
 * @author hamdy
 *
 */
public abstract class DataPoint {

	static int sequenceID = 0;
	String name;
	Object value;
	Timestamp timestamp;
	private DataPointType type;

	public DataPoint(String name, Timestamp timestamp, DataPointType type) {
		// build meta-data
		sequenceID++;
		this.setType(type);
		this.setName(name);
		this.setTimestamp(timestamp);
	}
	
	/**
     * Syntactic sugar to check if data has feature.
     * 
     * @param feature Feature.
     * 
     * @return True if data has feature and false otherwise.
     */
    public boolean has(Feature feature) {
        return feature.belongsTo(this);
    }
    
    /**
     * Assigned label of training data.
     * 
     * @return Label.
     */
    public abstract Label getLabel();

	// Do subclass level processing in this method
	protected abstract void construct();

	public Object getValue() { //throw exception to specify order
		return this.value;
	}
	
	/**
     * Get sample data value from specified column.
     * 
     * @return Data value.
     */
    public abstract Optional<Object> getValue(String column);

	/*
	 * 
	 * ------------SETTERS-------------- summarization, dist items, min max,
	 * avrg se window
	 * 
	 */
	public void setSequenceID(int seq) {
		this.sequenceID = seq;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public void setType(DataPointType type) {
		this.type = type;
	}

	/*
	 * 
	 * ------------GETTERS--------------
	 * 
	 */
	public int getSequenceID() {
		return this.sequenceID;
	}

	public String getName() {
		return this.name;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public DataPointType getType() {
		return this.type;
	}

}

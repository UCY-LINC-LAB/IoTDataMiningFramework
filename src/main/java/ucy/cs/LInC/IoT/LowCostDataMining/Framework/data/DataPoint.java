/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.data;

/**
 * @author hamdy
 *
 */
public abstract class DataPoint {

	static int sequenceID = 0;
	String name;
	Object value;
	int timestamp;
	private DataPointType type;

	public DataPoint(String name, int timestamp, DataPointType type) {
		this.type = type;
		buildMetaData();
	}

	private void buildMetaData() {
		// build meta-data
		sequenceID++;
		this.setName(name);
		this.setTimestamp(timestamp);
	}

	// Do subclass level processing in this method
	protected abstract void construct();

	public Object getValue() { //throw exception to specify order
		return this.value;
	}

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

	public void setTimestamp(int timestamp) {
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

	public int getTimestamp() {
		return this.timestamp;
	}

	public DataPointType getType() {
		return this.type;
	}

}

package ucy.cs.LInC.IoT.LowCostDataMining.Framework.data;
/**
 * 
 */

/**
 * @author hmicha01
 *
 */
public abstract class DataPoint {

//	static int count; //na fygei
	static int sequenceID=0;
	String name;
	String type;
//	Object value;
	int timestamp;
	
//	DataPoint(int seq, String name, String type, int timestamp){
//		this.sequenceID=seq;
//		this.name=name;
//		this.type=type;
////		this.value=value;
//		this.timestamp=timestamp;
//	}
	
	/*
	 * 
	 * ------------SETTERS-------------- summarization, dist items, min max, avrg se window
	 * 
	 * */
	public void setSequenceID(int seq){
		this.sequenceID=seq;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public void setType(String type){
		this.type=type;
	}
	
	public void setTimestamp(int timestamp){
		this.timestamp=timestamp;
	}
	
	
	
	/*
	 * 
	 * ------------GETTERS--------------
	 * 
	 * */
	public int getSequenceID(){
		return this.sequenceID;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getType(){
		return this.type;
	}
	
//	public int getSequenceID(){
//		return this.sequenceID;
//	}
	public int getTimestamp(){
		return this.timestamp;
	}
	
	public abstract Double getValue();
	
	
}

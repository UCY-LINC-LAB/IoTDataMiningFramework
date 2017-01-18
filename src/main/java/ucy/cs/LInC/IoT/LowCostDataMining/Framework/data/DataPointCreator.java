package ucy.cs.LInC.IoT.LowCostDataMining.Framework.data;
/**
 * 
 */

/**
 * @author hmicha01
 *
 */
public class DataPointCreator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DataPoint dp= new DoubleDataPoint("name", "type", 1.1, 1);
		System.out.println(dp.name+" "+dp.sequenceID+" "+dp.timestamp+" "+dp.type+" "+dp.value);
		
	}

}

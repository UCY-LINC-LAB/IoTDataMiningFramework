package ucy.cs.LInC.IoT.LowCostDataMining.Framework.data;

public class DataPointCreator {

	public static DataPoint createDataPoint(String name, int timestamp, DataPointType type, double value) {

		DataPoint dp = null;

		switch (type) {
		case DOUBLE:
			DoubleDataPoint dp1 = new DoubleDataPoint(name, timestamp, value);
			Bound<DoubleDataPoint> doubleDataPoint = new Bound <DoubleDataPoint> (dp1);			
			dp=doubleDataPoint.getDataPoint();
			break;
		default:
			System.out.println("Undefined type."); //throw exception
			break;
		}
		return dp;
	}
}

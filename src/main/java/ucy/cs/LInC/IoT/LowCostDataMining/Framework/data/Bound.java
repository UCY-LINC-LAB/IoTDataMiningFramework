package ucy.cs.LInC.IoT.LowCostDataMining.Framework.data;

/**
 * This class only accepts type parameters as any class which extends class
 * DataPoint or class DataPoint itself. Passing any other type will cause
 * compiler time error
 */
public class Bound<T extends DataPoint> {

	private T objRef;

	public Bound(T obj) {
		this.objRef = obj;
	}

	public T getDataPoint() {
//		dp = this.objRef;
		return this.objRef;
	}
}
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
public class ClusteringDataPoint extends DataPoint{

	private int index = -1; // denotes which Cluster it belongs to
	public double[] points;

	public ClusteringDataPoint(String name, Timestamp timestamp, DataPointType type, double[] points) {

		super(name, timestamp, type);
		this.points = new double[points.length];
		for (int i = 0; i < points.length; i++) {
			this.points[i] = points[i];
		}
	}

	public Double getSquareOfDistance(ClusteringDataPoint anotherPoint) {
		Double retVal = 0.0;
		for (int i = 0; i < this.points.length; i++) {
			retVal += Math.pow((this.points[i] - anotherPoint.points[i]), 2);
		}
		return retVal;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String toString() {
		String retVal=name+",";
		int i;
		for (i=0;i<points.length-1;i++){
			retVal+=points[i]+",";
		}
		retVal+=points[i]+"";
		return retVal;
	}

	@Override
	public Label getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void construct() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Object> getValue(String column) {
		// TODO Auto-generated method stub
		return null;
	}
}

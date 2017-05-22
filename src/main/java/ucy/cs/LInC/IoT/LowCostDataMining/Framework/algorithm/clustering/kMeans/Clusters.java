/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.clustering.kMeans;

import java.util.*;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.ClusteringDataPoint;

/**
 *
 */
public class Clusters extends ArrayList<Cluster> {

	private static final long serialVersionUID = 1L;
	private final List<ClusteringDataPoint> allPoints;
	private boolean isChanged;

	public Clusters(List<ClusteringDataPoint> allPoints) {
		this.allPoints = allPoints;
	}

	/**
	 * @param point
	 * @return the index of the Cluster nearest to the point
	 */
	public Integer getNearestCluster(ClusteringDataPoint point) {
		double minSquareOfDistance = Double.MAX_VALUE;
		int itsIndex = -1;
		for (int i = 0; i < size(); i++) {
			double squareOfDistance = point.getSquareOfDistance(get(i).getCentroid());
			if (squareOfDistance < minSquareOfDistance) {
				minSquareOfDistance = squareOfDistance;
				itsIndex = i;
			}
		}
		return itsIndex;
	}

	public boolean updateClusters() {
		for (Cluster cluster : this) {
			cluster.updateCentroid();
			cluster.getPoints().clear();
		}
		isChanged = false;
		assignPointsToClusters();
		return isChanged;
	}

	public void assignPointsToClusters() {
		for (ClusteringDataPoint point : allPoints) {
			int previousIndex = point.getIndex();
			int newIndex = getNearestCluster(point);
			if (previousIndex != newIndex)
				isChanged = true;
			Cluster target = get(newIndex);
			point.setIndex(newIndex);
			target.getPoints().add(point);
		}
	}
}

/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.clustering.kMeans;

import java.util.*;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.ClusteringDataPoint;

/**
 *
 */
public class Cluster {

	private final List<ClusteringDataPoint> points;
	private ClusteringDataPoint centroid;

	public Cluster(ClusteringDataPoint firstPoint) {
		points = new ArrayList<ClusteringDataPoint>();
		centroid = firstPoint;
	}

	public ClusteringDataPoint getCentroid() {
		return centroid;
	}

	public void updateCentroid() {
		// double newx = 0d, newy = 0d, newz = 0d;
		double[] newPoints = new double[points.get(0).points.length];
		int count = 0;
		for (ClusteringDataPoint point : points) {
			for (int i = 0; i < point.points.length; i++) {
				count++;
				newPoints[i] += (point.points[i] - newPoints[i]) / count;
			}
			// newx += point.x; newy += point.y; newz += point.z;
		}
		// centroid = new ClusteringDataPoint(newx / points.size(), newy /
		// points.size(), newz / points.size());
		centroid = new ClusteringDataPoint(points.get(0).getName(), points.get(0).getTimestamp(), points.get(0).getType(), newPoints);
	}

	public List<ClusteringDataPoint> getPoints() {
		return points;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder("This cluster contains the following points:\n");
		for (ClusteringDataPoint point : points)
			builder.append(point.toString() + ",\n");
		return builder.deleteCharAt(builder.length() - 2).toString();
	}
}

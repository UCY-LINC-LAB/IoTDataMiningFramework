/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.clustering.kMeans;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.ClusteringDataPoint;

public class KMeans {

	private static final Random random = new Random();
	public final List<ClusteringDataPoint> allPoints;
	public final int k;
	private Clusters pointClusters; // the k Clusters

	/**
	 * @param pointsFile
	 *            : the csv file for input points
	 * @param k
	 *            : number of clusters
	 */
	public KMeans(String pointsFile, int k) {
		if (k < 2)
			new Exception("The value of k should be 2 or more.").printStackTrace();
		this.k = k;
		List<ClusteringDataPoint> points = new ArrayList<ClusteringDataPoint>();
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(pointsFile), "UTF-8");
			BufferedReader reader = new BufferedReader(read);
			String line;
			while ((line = reader.readLine()) != null)
				points.add(getPointByLine(line));
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		this.allPoints = Collections.unmodifiableList(points);
	}

	private ClusteringDataPoint getPointByLine(String line) {
		String[] strPoints = line.split(",");
		double[] points = new double[strPoints.length-2];
		for (int i=1;i<strPoints.length-1;i++){
			points[i-1]=Double.parseDouble(strPoints[i]);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    Date parsedDate;
	    Timestamp timestamp=null;
	    try {
			parsedDate = dateFormat.parse(strPoints[0]);
		    timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ClusteringDataPoint(strPoints[0], timestamp, null, points);
	}

	/**
	 * step 1: get random seeds as initial centroids of the k clusters
	 */
	private void getInitialKRandomSeeds() {
		pointClusters = new Clusters(allPoints);
		List<ClusteringDataPoint> kRandomPoints = getKRandomPoints();
		for (int i = 0; i < k; i++) {
			kRandomPoints.get(i).setIndex(i);
			pointClusters.add(new Cluster(kRandomPoints.get(i)));
		}
	}

	private List<ClusteringDataPoint> getKRandomPoints() {
		List<ClusteringDataPoint> kRandomPoints = new ArrayList<ClusteringDataPoint>();
		boolean[] alreadyChosen = new boolean[allPoints.size()];
		int size = allPoints.size();
		for (int i = 0; i < k; i++) {
			int index = -1, r = random.nextInt(size--) + 1;
			for (int j = 0; j < r; j++) {
				index++;
				while (alreadyChosen[index])
					index++;
			}
			kRandomPoints.add(allPoints.get(index));
			alreadyChosen[index] = true;
		}
		return kRandomPoints;
	}

	/**
	 * step 2: assign points to initial Clusters
	 */
	private void getInitialClusters() {
		pointClusters.assignPointsToClusters();
	}

	/**
	 * step 3: update the k Clusters until no changes in their members occur
	 */
	private void updateClustersUntilNoChange() {
		boolean isChanged = pointClusters.updateClusters();
		while (isChanged)
			isChanged = pointClusters.updateClusters();
	}

	/**
	 * do K-means clustering with this method
	 */
	public List<Cluster> getPointsClusters() {
		if (pointClusters == null) {
			getInitialKRandomSeeds();
			getInitialClusters();
			updateClustersUntilNoChange();
		}
		return pointClusters;
	}

}
/**
 * 
 */
package client;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.clustering.kMeans.Cluster;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.clustering.kMeans.KMeans;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.ClusteringDataPoint;

/**
 * @author hamdy
 *
 */
public class IoTapplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String pointsFilePath = "trainingDataApplication2.csv";
		KMeans kMeans = new KMeans(pointsFilePath, 2);
		List<Cluster> pointsClusters = kMeans.getPointsClusters();

		// write predictions to file
		FileWriter fileWriter;
		for (int i = 0; i < kMeans.k; i++) {
			try {
				fileWriter = new FileWriter(new File("cluster" + i+".csv"));
				fileWriter.append("timestamp,heart,calories,confidence,zone").append("\n");
				for (ClusteringDataPoint clust : pointsClusters.get(i).getPoints()) {
					fileWriter.append(clust.toString()).append("\n");
				}
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

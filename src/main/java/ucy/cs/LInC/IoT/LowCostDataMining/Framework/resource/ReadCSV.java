package ucy.cs.LInC.IoT.LowCostDataMining.Framework.resource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPointType;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DoubleDataPoint;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DoubleVector;

public class ReadCSV <T extends DataPoint>{

	public void readAll(String filename, String delimiter) {

		BufferedReader br = null;
		String line = "";
		int count = 0;

		try {
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				count++;
				// use given delimiter as separator
				String[] str = line.split(delimiter);
				System.out.print("Line #" + count + ": ");
				for (int i = 0; i < str.length; i++) {
					System.out.print(str[i] + " ");
				}
				System.out.println();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void readSome(String filename, String delimiter, int limit) {// range

		BufferedReader br = null;
		String line = "";
		int count = 0;

		try {
			br = new BufferedReader(new FileReader(filename));
			while (((line = br.readLine()) != null) && limit != 0) {
				limit--;
				count++;
				// use given delimiter as separator
				String[] str = line.split(delimiter);
				System.out.print("Line #" + count + ": ");
				for (int i = 0; i < str.length; i++) {
					System.out.print(str[i] + " ");
				}
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void getLine(String filename, String delimiter, int num) {

		BufferedReader br = null;
		String line = "";
		System.out.print("Line #" + num + ": ");

		try {
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				num--;
				if (num == 0)
					break;
			}
			// use given delimiter as separator
			String[] str = line.split(delimiter);
			for (int i = 0; i < str.length; i++) {
				System.out.print(str[i] + " ");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	BufferedReader br = null;

	public void openFile(String filename) throws FileNotFoundException {
		this.br = new BufferedReader(new FileReader(filename));
	}
	
	public void closeFile(){
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<DoubleDataPoint> readData(String delimiter, int column, DataPointType type) {

		ArrayList<DoubleDataPoint> data = new ArrayList<DoubleDataPoint>();
		String line = "";
		int count = 0;

		try {
			while ((line = br.readLine()) != null) {
				// use given delimiter as separator
				String[] str = line.split(delimiter);
				DoubleDataPoint dp = new DoubleDataPoint("dataPoint" + count, new Timestamp(count), Double.parseDouble(str[column]));
//				Bound<DoubleDataPoint> doubleDataPoint = new Bound <DoubleDataPoint> (dp);
				data.add(dp);
				count++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return data;
	}
	
	public ArrayList<String> readData(ArrayList<T> data, String delimiter, int[] columns, int lblCol, DataPointType type) {

//		HashMap<String,DoubleVector> data = new HashMap<String,DoubleVector>();
		
		String line = "";
		int count = 0;
		double[] colData = new double[columns.length];
		ArrayList<String> lbls = new ArrayList<String>();
		try {
			while ((line = br.readLine()) != null) {
				// use given delimiter as separator
				String[] str = line.split(delimiter);
				for (int i=0;i<columns.length;i++){
					colData[i]=Double.parseDouble(str[columns[i]]);
				}
				
				DoubleVector dp = new DoubleVector("dataPoint" + count, new Timestamp(count), colData);
//				Bound<DoubleVector> doubleDataPoint = new Bound <DoubleVector> (dp);
				data.add((T)dp);
				lbls.add(str[lblCol]);
				count++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return lbls;
	}

//	public static void main(String args[]) {
//		ReadCSV csv = new ReadCSV();
//		// csv.readAll("test.csv", ",");
//		// csv.readSome("test.csv", ",",3);
//		// csv.getLine("test.csv", ",", 8);
//		ArrayList<DataPoint> res = csv.readData("test.csv", ",", 2);
//		for (DataPoint dataPoint : res) {
//			System.out.println(dataPoint.getName() + " " + (dataPoint.getValue().doubleValue() + 1));
//		}
//	}

}

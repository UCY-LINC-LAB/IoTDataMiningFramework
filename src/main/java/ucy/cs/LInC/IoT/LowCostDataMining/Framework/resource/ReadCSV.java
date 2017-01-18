package ucy.cs.LInC.IoT.LowCostDataMining.Framework.resource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DoubleDataPoint;

public class ReadCSV {

	public void readAll(String filename, String delimiter) {

		BufferedReader br = null;
		String line = "";
		int count=0;

		try {
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				count++;
				// use given delimiter as separator
				String[] str = line.split(delimiter);
				System.out.print("Line #"+count+": ");
				for (int i=0;i<str.length;i++){
					System.out.print(str[i]+" ");
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
	
	public void readSome(String filename, String delimiter, int limit) {

		BufferedReader br = null;
		String line = "";
		int count=0;

		try {
			br = new BufferedReader(new FileReader(filename));
			while (((line = br.readLine()) != null)&&limit!=0) {
				limit--;
				count++;
				// use given delimiter as separator
				String[] str = line.split(delimiter);
				System.out.print("Line #"+count+": ");
				for (int i=0;i<str.length;i++){
					System.out.print(str[i]+" ");
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
		System.out.print("Line #"+num+": ");

		try {
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				num--;
				if (num==0)
					break;								
			}
			// use given delimiter as separator
			String[] str = line.split(delimiter);			
			for (int i=0;i<str.length;i++){
				System.out.print(str[i]+" ");
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

	public ArrayList<DataPoint> readData(String filename, String delimiter, int column){
	
		ArrayList <DataPoint> data=new ArrayList<DataPoint>();
		BufferedReader br = null;
		String line = "";
		int count=0;

		try {
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {				
				// use given delimiter as separator
				String[] str = line.split(delimiter);
				DataPoint dp= new DoubleDataPoint("dataPoint"+count, "double", Double.parseDouble(str[count]), 1);
				data.add(dp);
				count++;
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
		return data;
	}
	
	public static void main(String args[]){
		ReadCSV csv= new ReadCSV();
		csv.readAll("test.csv", ",");
		csv.readSome("test.csv", ",",3);
		csv.getLine("test.csv", ",", 8);
	}

}

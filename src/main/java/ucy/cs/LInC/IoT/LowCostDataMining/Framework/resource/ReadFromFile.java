/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.resource;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author hmicha01
 *
 */
public class ReadFromFile implements DataResource {

	public String data;
	
	public void fileToString(String resource) {
		String content = null;
		File file = new File(resource); 
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		data = content;
	}
	
	public static void main(String[] args){
		ReadFromFile test= new ReadFromFile();
		test.fileToString("test.txt");
		System.out.println(test.data);
	}

}

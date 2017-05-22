/**
 * 
 */
package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Timeseries;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPointType;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DoubleDataPoint;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.resource.ReadCSV;

/**
 * @author hamdy
 *
 */
public class PEWMATest {

	static ArrayList<DoubleDataPoint> data = new ArrayList<DoubleDataPoint>();
	TimeSeries<DoubleDataPoint> movingAverage = new PEWMA<DoubleDataPoint>(0.2,0.5);

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ReadCSV<DoubleDataPoint> reader = new ReadCSV<DoubleDataPoint>();
		try {
			reader.openFile("test.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		data = reader.readData(",", 2, DataPointType.DOUBLE);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void setUp() throws Exception {
		movingAverage.reset();
	}

	@Test
	public void overallTest() {
		for (DoubleDataPoint dataPoint : data) {
			movingAverage.addDataPoint(dataPoint);
		}
		double result = movingAverage.getAverage();
		double expectedValue = 0.7963203945994382;
		if (result != expectedValue)
			fail("The expected average differs from the Moving average");
	}

	@Test
	public void testAddDataPoint1() {
		DoubleDataPoint d = new DoubleDataPoint("test", new Timestamp(1), 0.1);
		double res = movingAverage.addDataPoint(d);
		if (!((new Double(res)) instanceof Double))
			fail();
	}

}

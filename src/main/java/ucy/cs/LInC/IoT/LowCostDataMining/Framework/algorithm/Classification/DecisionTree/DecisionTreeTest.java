package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree;

/**
 *
 */

import org.junit.*;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPointType;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.StringDataPoint;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

public class DecisionTreeTest {
	private DecisionTree makeOne() {
		return new DecisionTree();
	}

	private DecisionTree makeOutlookTree() {
		try {
			// example data from
			// http://www.cise.ufl.edu/~ddd/cap6635/Fall-97/Short-papers/2.htm
			return makeOne().addExample(new Double[] { 0.1, 0.4, 0.7, 0.9 }, Categories.FALSE)
					.addExample(new Double[] { 0.1, 0.4, 0.7, 1.0 }, Categories.FALSE)
					.addExample(new Double[] { 0.2, 0.4, 0.7, 0.9 }, Categories.TRUE)
					.addExample(new Double[] { 0.3, 0.5, 0.7, 0.9 }, Categories.KATI)
					.addExample(new Double[] { 0.3, 0.6, 0.8, 0.9 }, Categories.KATI)
					.addExample(new Double[] { 0.3, 0.6, 0.8, 1.0 }, Categories.KATI)
					.addExample(new Double[] { 0.2, 0.6, 0.8, 1.0 }, Categories.TRUE)
					.addExample(new Double[] { 0.1, 0.5, 0.7, 0.9 }, Categories.FALSE)
					.addExample(new Double[] { 0.1, 0.6, 0.8, 0.9 }, Categories.TRUE)
					.addExample(new Double[] { 0.3, 0.5, 0.8, 0.9 }, Categories.KATI)
					.addExample(new Double[] { 0.1, 0.5, 0.8, 1.0 }, Categories.TRUE)
					.addExample(new Double[] { 0.2, 0.5, 0.7, 1.0 }, Categories.TRUE)
					.addExample(new Double[] { 0.2, 0.4, 0.8, 0.9 }, Categories.TRUE)
					.addExample(new Double[] { 0.3, 0.5, 0.7, 1.0 }, Categories.KATI);
			// .setFeatures(new String[]{"Outlook", "Temperature", "Humidity",
			// "Wind"})
			// .addExample(new StringDataPoint[]{(new
			// StringDataPoint("Outlook",1,DataPointType.STRING,"Sunny")), (new
			// StringDataPoint("Temperature",1,DataPointType.STRING,"Hot")),
			// (new StringDataPoint("Humidity",1,DataPointType.STRING,"High")),
			// (new StringDataPoint("Wind",1,DataPointType.STRING,"Weak"))},
			// Categories.FALSE)
			// .addExample(new StringDataPoint[]{(new
			// StringDataPoint("Outlook",1,DataPointType.STRING,"Sunny")), (new
			// StringDataPoint("Temperature",1,DataPointType.STRING,"Hot")),
			// (new StringDataPoint("Humidity",1,DataPointType.STRING,"High")),
			// (new StringDataPoint("Wind",1,DataPointType.STRING,"Strong"))},
			// Categories.FALSE)
			// .addExample(new StringDataPoint[]{(new
			// StringDataPoint("Outlook",1,DataPointType.STRING,"Overcast")),
			// (new
			// StringDataPoint("Temperature",1,DataPointType.STRING,"Hot")),
			// (new StringDataPoint("Humidity",1,DataPointType.STRING,"High")),
			// (new StringDataPoint("Wind",1,DataPointType.STRING,"Weak"))},
			// Categories.TRUE)
			// .addExample(new StringDataPoint[]{(new
			// StringDataPoint("Outlook",1,DataPointType.STRING,"Rain")), (new
			// StringDataPoint("Temperature",1,DataPointType.STRING,"Mild")),
			// (new StringDataPoint("Humidity",1,DataPointType.STRING,"High")),
			// (new StringDataPoint("Wind",1,DataPointType.STRING,"Weak"))},
			// Categories.FALSE)
			// .addExample(new StringDataPoint[]{(new
			// StringDataPoint("Outlook",1,DataPointType.STRING,"Rain")), (new
			// StringDataPoint("Temperature",1,DataPointType.STRING,"Cool")),
			// (new
			// StringDataPoint("Humidity",1,DataPointType.STRING,"Normal")),
			// (new StringDataPoint("Wind",1,DataPointType.STRING,"Weak"))},
			// Categories.TRUE)
			// .addExample(new StringDataPoint[]{(new
			// StringDataPoint("Outlook",1,DataPointType.STRING,"Rain")), (new
			// StringDataPoint("Temperature",1,DataPointType.STRING,"Cool")),
			// (new
			// StringDataPoint("Humidity",1,DataPointType.STRING,"Normal")),
			// (new StringDataPoint("Wind",1,DataPointType.STRING,"Strong"))},
			// Categories.FALSE)
			// .addExample(new StringDataPoint[]{(new
			// StringDataPoint("Outlook",1,DataPointType.STRING,"Overcast")),
			// (new
			// StringDataPoint("Temperature",1,DataPointType.STRING,"Cool")),
			// (new
			// StringDataPoint("Humidity",1,DataPointType.STRING,"Normal")),
			// (new StringDataPoint("Wind",1,DataPointType.STRING,"Strong"))},
			// Categories.TRUE)
			// .addExample(new StringDataPoint[]{(new
			// StringDataPoint("Outlook",1,DataPointType.STRING,"Sunny")), (new
			// StringDataPoint("Temperature",1,DataPointType.STRING,"Mild")),
			// (new StringDataPoint("Humidity",1,DataPointType.STRING,"High")),
			// (new StringDataPoint("Wind",1,DataPointType.STRING,"Weak"))},
			// Categories.FALSE)
			// .addExample(new StringDataPoint[]{(new
			// StringDataPoint("Outlook",1,DataPointType.STRING,"Sunny")), (new
			// StringDataPoint("Temperature",1,DataPointType.STRING,"Cool")),
			// (new
			// StringDataPoint("Humidity",1,DataPointType.STRING,"Normal")),
			// (new StringDataPoint("Wind",1,DataPointType.STRING,"Weak"))},
			// Categories.TRUE)
			// .addExample(new StringDataPoint[]{(new
			// StringDataPoint("Outlook",1,DataPointType.STRING,"Rain")), (new
			// StringDataPoint("Temperature",1,DataPointType.STRING,"Mild")),
			// (new
			// StringDataPoint("Humidity",1,DataPointType.STRING,"Normal")),
			// (new StringDataPoint("Wind",1,DataPointType.STRING,"Weak"))},
			// Categories.TRUE)
			// .addExample(new StringDataPoint[]{(new
			// StringDataPoint("Outlook",1,DataPointType.STRING,"Sunny")), (new
			// StringDataPoint("Temperature",1,DataPointType.STRING,"Mild")),
			// (new
			// StringDataPoint("Humidity",1,DataPointType.STRING,"Normal")),
			// (new StringDataPoint("Wind",1,DataPointType.STRING,"Strong"))},
			// Categories.TRUE)
			// .addExample(new StringDataPoint[]{(new
			// StringDataPoint("Outlook",1,DataPointType.STRING,"Overcast")),
			// (new
			// StringDataPoint("Temperature",1,DataPointType.STRING,"Mild")),
			// (new StringDataPoint("Humidity",1,DataPointType.STRING,"High")),
			// (new StringDataPoint("Wind",1,DataPointType.STRING,"Strong"))},
			// Categories.TRUE)
			// .addExample(new StringDataPoint[]{(new
			// StringDataPoint("Outlook",1,DataPointType.STRING,"Overcast")),
			// (new
			// StringDataPoint("Temperature",1,DataPointType.STRING,"Hot")),
			// (new
			// StringDataPoint("Humidity",1,DataPointType.STRING,"Normal")),
			// (new StringDataPoint("Wind",1,DataPointType.STRING,"Weak"))},
			// Categories.TRUE)
			// .addExample(new StringDataPoint[]{(new
			// StringDataPoint("Outlook",1,DataPointType.STRING,"Rain")), (new
			// StringDataPoint("Temperature",1,DataPointType.STRING,"Mild")),
			// (new StringDataPoint("Humidity",1,DataPointType.STRING,"High")),
			// (new StringDataPoint("Wind",1,DataPointType.STRING,"Strong"))},
			// Categories.FALSE);

		} catch (UnknownDecisionException e) {
			fail();
			return makeOne(); // this is here to shut up compiler.
		}
	}

	@Test(expected = UnknownDecisionException.class)
	public void testUnknownDecisionThrowsException() throws UnknownDecisionException {
		DecisionTree tree = makeOne().setFeatures(new String[] { "Outlook" }).setDecisions("Outlook",
				new Double[] { 0.1, 0.2 });

		// this causes exception
		tree.addExample(new Double[] { 0.1 }, Categories.TRUE);
	}

	@Test
	public void testOutlookOvercastApplyReturnsTrue() throws BadDecisionException {
		Map<String, String> case1 = new HashMap<String, String>();
		case1.put("Outlook", "Overcast");
		case1.put("Temperature", "Hot");
		case1.put("Humidity", "High");
		case1.put("Wind", "Strong");
		// assertTrue(makeOutlookTree().apply(case1));
	}

	@Test(expected = BadDecisionException.class)
	public void testOutlookRainInsufficientDataThrowsException() throws BadDecisionException {
		Map<String, Double> case1 = new HashMap<String, Double>();
		case1.put("Outlook", 0.1);
		case1.put("Temperature", 0.2);
		makeOutlookTree().apply(case1);
	}

	public void nodeIsUsedOnlyOnceInTree(Feature node, List<String> nodes) {
		for (Feature child : node.getDecisions().values()) {
			if (!child.isLeaf()) {
				assertFalse(nodes.contains(child.getName()));
				nodes.add(child.getName());
				nodeIsUsedOnlyOnceInTree(child, nodes);
			}
		}
	}

	@Test
	public void testFeatureIsUsedOnlyOnceInTree() {
		DecisionTree tree = makeOutlookTree();
		tree.compile();

		List<String> nodeList = new LinkedList<String>();
		nodeList.add(tree.getRoot().getName());
		nodeIsUsedOnlyOnceInTree(tree.getRoot(), nodeList);
	}

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("DecisionTreeTest");
	}
}
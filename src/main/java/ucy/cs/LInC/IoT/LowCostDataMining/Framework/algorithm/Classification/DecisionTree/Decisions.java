/**
 *
 */

package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree;

import java.util.*;
import java.util.Map.Entry;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;

class Decisions<T, E extends DataPoint> {
	private Map<T, Feature> decisions;

	public Decisions() {
		decisions = new HashMap<T, Feature>();
	}

	public Map<T, Feature> getMap() {
		return decisions;
	}

	public void put(T decisionName, Feature feature) {
		decisions.put(decisionName, feature);
	}

	public void clear() {
		decisions.clear();
	}

	/**
	 * Returns the Feature based on the decision matching the provided value.
	 *
	 * Throws BadDecisionException if no decision matches.
	 */
	public Feature apply(T double1) throws BadDecisionException {
		Feature result = decisions.get(double1);

		if (result == null)
			throw new BadDecisionException();

		return result;
	}

	public String toString(Feature f) {
		StringBuffer b = new StringBuffer();

		for (Entry<T, Feature> e : decisions.entrySet()) {
			b.append(f.getName());
			b.append(" -> ");
			if (e.getValue().isLeaf())
				b.append(e.getValue().getClassification());
			else
				b.append(e.getValue().getName());
			b.append(" [label=\"");
			b.append(e.getKey());
			b.append("\"]\n");

			b.append(e.getValue().toString());
		}

		return b.toString();
	}
}

/**
 *
 */

package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree;

import java.util.*;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;


class Decisions {
  private Map<Double, Feature> decisions;

  public Decisions() {
    decisions = new HashMap<Double, Feature>();
  }

  public Map<Double, Feature> getMap() {
    return decisions;
  }

  public void put(Double decisionName, Feature feature) {
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
  public Feature apply(Double double1) throws BadDecisionException {
	  Feature result = decisions.get(double1);

    if ( result == null )
      throw new BadDecisionException();

    return result;
  }
}

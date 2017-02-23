/**
 *
 */

package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree;

import java.util.*;
import java.util.Map.Entry;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;


public class Feature {
  /**
   * Indicates if this Feature yields a classification (true) or has child 
   * decisions that point to further Features (false).
   */
  private boolean leaf;

  private String featureName;
  private Decisions decisions;
  private Categories classification;

  public Feature(Categories classification) {
    leaf = true;
    this.classification = classification;
    decisions = new Decisions();
    featureName = null;
  }

  public Feature(String name) {
    leaf = false;
    featureName = name;
    decisions = new Decisions();
  }

  public String getName() {
    return featureName;
  }

  public boolean isLeaf() {
    return leaf;
  }

  public void setClassification(Categories classification) {
    assert ( leaf );

    this.classification = classification;
  }

  /**
   * Returns the classification of the followed decision.
   *
   * Undefined if isLeaf() returns false.
   */
  public Categories getClassification() {
    assert ( leaf );

    return classification;
  }

  public Categories apply(Map<String, Double> data) throws BadDecisionException {
    if ( isLeaf() )
      return getClassification();

    Feature nextFeature = decisions.apply(data.get(featureName));
    return nextFeature.apply(data);
  }

  public void addDecision(Double decisionName, Feature feature) {
    assert ( !leaf );

    decisions.put(decisionName, feature);
  }

  public String toString() {
    StringBuffer b = new StringBuffer();

    for ( Entry<Double, Feature> e : decisions.getMap().entrySet() ) {
      b.append(getName());
      b.append(" -> ");
      if ( e.getValue().isLeaf() )
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

  public Map<Double, Feature> getDecisions() {
    return decisions.getMap();
  }
}

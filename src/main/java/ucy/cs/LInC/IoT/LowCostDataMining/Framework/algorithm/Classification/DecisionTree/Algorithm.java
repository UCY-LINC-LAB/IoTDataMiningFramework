/**
 *  
 */

package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree;

import java.util.*;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;


public interface Algorithm {
  /**
   * Find the next Feature.
   *
   * For the initial Feature, pass an empty
   * chosenFeatures and use the returned Feature as the rootFeature.
   * Then, walk the decision tree pre-order. At each decision, call this method
   * with the Feature/decision pairs that led to that feature in 
   * chosenFeatures. Attach the returned Feature to the decision.
   *
   */
  abstract public Feature nextFeature(Map<String, Double> chosenFeatures, Set<String> usedFeatures);
}

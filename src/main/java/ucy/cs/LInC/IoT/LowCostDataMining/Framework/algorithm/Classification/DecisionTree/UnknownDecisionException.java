/**
 *
 */

package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;

public class UnknownDecisionException extends Exception {
  public <T> UnknownDecisionException(String attribute, T featureValues) {
    super();
  }
}


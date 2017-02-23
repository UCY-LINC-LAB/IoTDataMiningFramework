/**
 *
 */

package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree;

import java.util.*;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;

public class DecisionTree {
  /**
   * Contains the set of available Features.
   */
  private LinkedHashSet<String> features;

  /**
   * Maps a Feature name to a set of possible decisions for that Feature.
   */
  private Map<String, Set<Double>> decisions;
  private boolean decisionsSpecified;

  /**
   * Contains the examples to be processed into a decision tree.
   *
   * The 'Features' and 'decisions' member variables should be updated
   * prior to adding examples that refer to new Features or decisions.
   */
  private Examples examples;

  /**
   * Indicates if the provided data has been processed into a decision tree.
   *
   * This value is initially false, and is reset any time additional data is
   * provided.
   */
  private boolean compiled;

  /**
   * Contains the top-most Feature of the decision tree.
   *
   * For a tree where the decision requires no Features,
   * the rootFeature yields a boolean classification.
   *
   */
  private Feature rootFeature;

  private Algorithm algorithm;

  public DecisionTree() {
    algorithm = null;
    examples = new Examples();
    features = new LinkedHashSet<String>();
    decisions = new HashMap<String, Set<Double> >();
    decisionsSpecified = false;
  }

  private void setDefaultAlgorithm() {
    if ( algorithm == null )
      setAlgorithm(new ID3Algorithm(examples));
  }

  public void setAlgorithm(Algorithm algorithm) {
    this.algorithm = algorithm;
  }

  /**
   * Saves the array of Feature names in an insertion ordered set.
   *
   * The ordering of Feature names is used when addExamples is called to
   * determine which values correspond with which names.
   *
   */
  public DecisionTree setFeatures(String[] featureNames) {
    compiled = false;

    decisions.clear();
    decisionsSpecified = false;

    features.clear();

    for ( int i = 0 ; i < featureNames.length ; i++ )
      features.add(featureNames[i]);

    return this;
  }

  /**
   * 
   * Set categories
   * 
   */
  public DecisionTree setDecisions(String featureName, Double[] decisions) {
    if ( !features.contains(featureName) ) {
      // TODO some kind of warning or something
      return this;
    }

    compiled = false;
    decisionsSpecified = true;

    Set<Double> decisionsSet = new HashSet<Double>();
    for ( int i = 0 ; i < decisions.length ; i++ )
      decisionsSet.add(decisions[i]);

    this.decisions.put(featureName, decisionsSet);

    return this;
  }

  /**
   */
  public DecisionTree addExample(Double[] featureValues, Categories classification) throws UnknownDecisionException {
    String[] features = this.features.toArray(new String[0]);

    if ( decisionsSpecified )
      for ( int i = 0 ; i < featureValues.length ; i++ )
        if ( !decisions.get(features[i]).contains(featureValues[i]) ) {
          throw new UnknownDecisionException(features[i], featureValues[i]);
        }

    compiled = false;

    examples.add(features, featureValues, classification);
    
    return this;
  }

  public DecisionTree addExample(Map<String, Double> features, Categories classification) throws UnknownDecisionException {
    compiled = false;

    examples.add(features, classification);

    return this;
  }

  public Categories apply(Map<String, Double> case1) throws BadDecisionException {
    compile();

    return rootFeature.apply(case1);
  }

  private Feature compileWalk(Feature current, Map<String, Double> chosenFeatures, Set<String> usedFeatures) {
    // if the current Feature is a leaf, then there are no decisions and thus no
    // further Features to find.
    if ( current.isLeaf() )
      return current;

    // get decisions for the current Feature (from this.decisions)
    String featureName = current.getName();

    // remove this Feature from all further consideration
    usedFeatures.add(featureName);

    for ( Double decisionName : decisions.get(featureName) ) {
      // overwrite the feature decision for each value considered
      chosenFeatures.put(featureName, decisionName);

      // find the next Feature to choose for the considered decision
      // build the subtree from this new Feature, pre-order
      // insert the newly-built subtree into the open decision slot
      current.addDecision(decisionName, compileWalk(algorithm.nextFeature(chosenFeatures, usedFeatures), chosenFeatures, usedFeatures));
    }

    // remove the Feature decision before we walk back up the tree.
    chosenFeatures.remove(featureName);

    // return the subtree so that it can be inserted into the parent tree.
    return current;
  }

  public void compile() {
    // skip compilation if already done.
    if ( compiled )
      return;

    // if no algorithm is set beforehand, select the default one.
    setDefaultAlgorithm();

    Map<String, Double> chosenFeatures = new HashMap<String, Double>();
    Set<String> usedFeatures = new HashSet<String>();

    if ( !decisionsSpecified )
      decisions = examples.extractDecisions();

    // find the root Feature (either leaf or non)
    // walk the tree, adding Features as needed under each decision
    // save the original Feature as the root Feature.
    rootFeature = compileWalk(algorithm.nextFeature(chosenFeatures, usedFeatures), chosenFeatures, usedFeatures);

    compiled = true;
  }

  public String toString() {
    compile();

    if ( rootFeature != null )
      return rootFeature.toString();
    else
      return "";
  }

  public Feature getRoot() {
    return rootFeature;
  }
}

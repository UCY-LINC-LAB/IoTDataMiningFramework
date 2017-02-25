/**
 *
 */

package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree;

import java.util.*;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;

public class DecisionTree <T>{
  /**
   * Contains the set of available Features.
   */
  private LinkedHashSet<String> features;

  /**
   * Maps a Feature name to a set of possible decisions for that Feature.
   */
  private Map<String, Set<T>> decisions;
  private boolean decisionsSpecified;

  /**
   * Contains the examples to be processed into a decision tree.
   *
   * The 'Features' and 'decisions' member variables should be updated
   * prior to adding examples that refer to new Features or decisions.
   */
  private Examples<T> examples;

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
  private Feature<T> rootFeature;

  private ID3Algorithm<T> algorithm;

  public DecisionTree() {
    algorithm = null;
    examples = new Examples<T>();
    features = new LinkedHashSet<String>();
    decisions = new HashMap<String, Set<T> >();
    decisionsSpecified = false;
  }

  private void setDefaultAlgorithm() {
    if ( algorithm == null )
      setAlgorithm(new ID3Algorithm<T>(examples));
  }

  public void setAlgorithm(ID3Algorithm<T> algorithm) {
    this.algorithm = algorithm;
  }

  /**
   * Saves the array of Feature names in an insertion ordered set.
   *
   * The ordering of Feature names is used when addExamples is called to
   * determine which values correspond with which names.
   *
   */
  public DecisionTree<T> setFeatures(String[] featureNames) {
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
  public DecisionTree<T> setDecisions(String featureName, T[] decisions) {
    if ( !features.contains(featureName) ) {
      // TODO some kind of warning or something
      return this;
    }

    compiled = false;
    decisionsSpecified = true;

    Set<T> decisionsSet = new HashSet<T>();
    for ( int i = 0 ; i < decisions.length ; i++ )
      decisionsSet.add((T)decisions[i]);

    this.decisions.put(featureName, decisionsSet);

    return this;
  }

  /**
   */
  public DecisionTree<T> addExample(T[] featureValues, Categories classification) throws UnknownDecisionException {
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

  public DecisionTree<T> addExample(Map<String, T> features, Categories classification) throws UnknownDecisionException {
    compiled = false;

    examples.add(features, classification);

    return this;
  }

  public Categories apply(Map<String, T> case1) throws BadDecisionException {
    compile();

    return rootFeature.apply(case1);
  }

  private Feature<T> compileWalk(Feature<T> current, Map<String, T> chosenFeatures, Set<String> usedFeatures) {
    // if the current Feature is a leaf, then there are no decisions and thus no
    // further Features to find.
    if ( current.isLeaf() )
      return current;

    // get decisions for the current Feature (from this.decisions)
    String featureName = current.getName();

    // remove this Feature from all further consideration
    usedFeatures.add(featureName);

    for ( T decisionName : decisions.get(featureName) ) {
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

    Map<String, T> chosenFeatures = new HashMap<String, T>();
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

  public Feature<T> getRoot() {
    return rootFeature;
  }
}

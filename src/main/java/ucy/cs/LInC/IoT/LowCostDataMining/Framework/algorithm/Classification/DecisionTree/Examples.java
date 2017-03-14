/**
 *
 */

package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree;

import java.util.*;
import java.util.Map.Entry;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;

class Examples <T>{
	class Example <T>{
		private Map<String, T> values;
		private Categories classifier;

		public Example(String[] featureNames, T[] featureValues, Categories classifier) {
			assert (featureNames.length == featureValues.length);			
			values = new HashMap<String, T>();

			for (int i = 0; i < featureNames.length; i++) {
				values.put(featureNames[i], featureValues[i]);
			}

			this.classifier = classifier;
		}

		public Example(Map<String, T> features, Categories classifier) {
			this.classifier = classifier;
			this.values = features;
		}

		public Set<String> getFeatures() {
			return values.keySet();
		}

		public T getFeatureValue(String feature) {
			return values.get(feature);
		}

		public boolean matchesClass(Categories classifier) {
			return classifier == this.classifier;
		}
	}

	private List<Example> examples;

	public Examples() {
		examples = new LinkedList<Example>();
	}

	public void add(String[] featureNames, T[] featureValues, Categories classifier) {
		examples.add(new Example(featureNames, featureValues, classifier));
	}

	public void add(Map<String, T> features, Categories classifier) {
		examples.add(new Example(features, classifier));
	}

	/**
	 * Returns the number of examples where the Feature has the specified
	 * 'decision' value
	 */
	int countDecisions(String feature, T decision) {
		int count = 0;

		for (Example<T> e : examples) {
			if (e.getFeatureValue(feature).equals(decision))
				count++;
		}

		return count;
	}

	/**
	 * Returns a map from each Feature name to a set of all values used in the
	 * examples for that Feature.
	 */
	public Map<String, Set<T>> extractDecisions() {
		Map<String, Set<T>> decisions = new HashMap<String, Set<T>>();

		for (String feature : extractFeatures()) {
			decisions.put(feature, extractDecisions(feature));
		}

		return decisions;
	}

	public int countNegative(String feature, T decision, Map<String, T> features) {
		return countClassifier(Categories.NONE, feature, decision, features);
	}

	public int countPositive(String feature, T decision, Map<String, T> features) {
		return countClassifier(Categories.ZONE1, feature, decision, features);
	}

	public int countNegative(Map<String, T> features) {
		return countClassifier(Categories.NONE, features);
	}

	public int countPositive(Map<String, T> chosenFeatures) {
		return countClassifier(Categories.ZONE1, chosenFeatures);
	}

	public int count(String feature, T decision, Map<String, T> specifiedFeatures) {
		specifiedFeatures = new HashMap(specifiedFeatures);
		specifiedFeatures.put(feature, decision);

		return count(specifiedFeatures);
	}

	public int count(Map<String, T> specifiedFeatures) {
		int count = 0;

		nextExample: for (Example e : examples) {
			for (Entry<String, T> feature : specifiedFeatures.entrySet())
				if (!(e.getFeatureValue(feature.getKey()).equals(feature.getValue())))
					continue nextExample;

			// All of the provided Features match the example.
			count++;
		}

		return count;
	}

	public int countClassifier(Categories classifier, Map<String, T> features) {
		int count = 0;

		nextExample: for (Example e : examples) {
			for (Entry<String, T> feature : features.entrySet())
				if (!(e.getFeatureValue(feature.getKey()).equals(feature.getValue())))
					continue nextExample;

			// All of the provided Features match the example.
			// If the example matches the classifier, then include it in the
			// count.
			if (e.matchesClass(classifier))
				count++;
		}

		return count;
	}

	public int countClassifier(Categories classifier, String feature, T decision, Map<String, T> features) {
		features = new HashMap(features);
		features.put(feature, decision);

		return countClassifier(classifier, features);
	}

	/**
	 * Returns the number of examples.
	 */
	public int count() {
		return examples.size();
	}

	/**
	 * Returns a set of Feature names used in the examples.
	 */
	public Set<String> extractFeatures() {
		Set<String> features = new HashSet<String>();

		for (Example<T> e : examples) {
			features.addAll(e.getFeatures());
		}

		return features;
	}

	private Set<T> extractDecisions(String feature) {
		Set<T> decisions = new HashSet<T>();

		for (Example e : examples) {
			decisions.add((T)e.getFeatureValue(feature));
		}

		return decisions;
	}
}

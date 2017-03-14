/**
 *
 */

package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.Classification.DecisionTree;

import java.util.*;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;

public class ID3Algorithm<T> implements Algorithm<T> {
	// final Logger logger = LoggerFactory.getLogger(ID3Algorithm.class);
	private Examples<T> examples;

	public ID3Algorithm(Examples<T> examples) {
		this.examples = examples;
	}

	/**
	 * Returns the next Feature to be chosen.
	 *
	 * chosenFeatures represents the decision path from the root Feature to the
	 * feature under consideration. usedFeatures is the set of all Features that
	 * have been incorporated into the tree prior to this call to nextFeature(),
	 * even if the Features were not used in the path to the feature under
	 * consideration.
	 *
	 * Results are undefined if examples.count() == 0.
	 */
	public Feature<T> nextFeature(Map<String, T> chosenFeatures, Set<String> usedFeatures) {
		double currentGain = 0.0, bestGain = 0.0;
		String bestFeature = "";

		/*
		 * If there are no positive examples for the already chosen Features,
		 * then return a false classifier leaf. If no negative examples, then
		 * return a true classifier leaf.
		 */
		if (examples.countPositive(chosenFeatures) == 0)
			return new Feature<T>(Categories.NONE);
		else if (examples.countNegative(chosenFeatures) == 0)
			return new Feature<T>(Categories.ZONE1);

		// if (examples.countClassifier(Categories.FALSE, chosenFeatures)==0){
		//
		// }

		// logger.debug("Choosing Feature out of {} remaining Features.",
		// remainingFeatures(usedFeatures).size());
		// logger.debug("Already chosen Features/decisions are {}.",
		// chosenFeatures);

		for (String feature : remainingFeatures(usedFeatures)) {
			// for each remaining Feature, determine the information gain of
			// using
			// it
			// to choose among the examples selected by the chosenFeatures
			// if none give any information gain, return a leaf Feature,
			// otherwise return the found Feature as a non-leaf Feature
			currentGain = informationGain(feature, chosenFeatures);
			// logger.debug("Evaluating Feature {}, information gain is {}",
			// Feature, currentGain);
			if (currentGain > bestGain) {
				bestFeature = feature;
				bestGain = currentGain;
			}
		}

		// If no Feature gives information gain, generate leaf Feature.
		// Leaf is true if there are any true classifiers.
		// If there is at least one negative example, then the information gain
		// would be greater than 0.
		if (bestGain == 0.0) {
			if (examples.countPositive(chosenFeatures) > 0)
				return new Feature<T>(Categories.ZONE1);
			else
				return new Feature<T>(Categories.NONE);
			// logger.debug("Creating new leaf Feature with classifier {}.",
			// classifier);
		} else {
			// logger.debug("Creating new non-leaf Feature {}.", bestFeature);
			return new Feature<T>(bestFeature);
		}
	}

	private Set<String> remainingFeatures(Set<String> usedFeatures) {
		Set<String> result = examples.extractFeatures();
		result.removeAll(usedFeatures);
		return result;
	}

	private double entropy(Map<String, T> chosenFeatures) {
		double totalExamples = examples.count();
		double positiveExamples = examples.countPositive(chosenFeatures);
		double negativeExamples = examples.countNegative(chosenFeatures);

		return -nlog2(positiveExamples / totalExamples) - nlog2(negativeExamples / totalExamples);
	}

	private double entropy(String feature, T decision, Map<String, T> specifiedFeatures) {
		double totalExamples = examples.count(feature, decision, specifiedFeatures);
		double positiveExamples = examples.countPositive(feature, decision, specifiedFeatures);
		double negativeExamples = examples.countNegative(feature, decision, specifiedFeatures);

		return -nlog2(positiveExamples / totalExamples) - nlog2(negativeExamples / totalExamples);
	}

	private double informationGain(String feature, Map<String, T> chosenFeatures) {
		double sum = entropy(chosenFeatures);
		double examplesCount = examples.count(chosenFeatures);

		if (examplesCount == 0)
			return sum;

		Map<String, Set<T>> decisions = examples.extractDecisions();

		for (T decision : decisions.get(feature)) {
			double entropyPart = entropy(feature, decision, chosenFeatures);
			double decisionCount = examples.countDecisions(feature, decision);

			sum += -(decisionCount / examplesCount) * entropyPart;
		}

		return sum;
	}

	private double nlog2(double value) {
		if (value == 0)
			return 0;

		return value * Math.log(value) / Math.log(2);
	}
}

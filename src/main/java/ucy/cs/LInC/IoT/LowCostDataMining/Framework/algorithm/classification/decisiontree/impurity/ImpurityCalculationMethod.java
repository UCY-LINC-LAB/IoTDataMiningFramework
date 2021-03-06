package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.impurity;

import java.util.List;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.label.Label;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;

/**
 * Impurity calculation method of decision tree. It is used during training while trying to find best split. For example
 * one split results in 5 positive and 5 negative labels and another in 9 positive and 1 negative. Impurity calculator
 * should return high value in the first case and low value in the second. That means that during training we will
 * prefer second split as that will provide most information. Most popular methods are entropy and gini index.
 * 
 * Annotated as functional interface to allow lambda usage.
 * 
 *
 */
@FunctionalInterface
public interface ImpurityCalculationMethod {

    /**
     * Calculates impurity value. High impurity implies low information gain and more random labels of data which in
     * turn means that split is not very good.
     * 
     * @param splitData
     *            Data subset on which impurity is calculated.
     * 
     * @return Impurity.
     */
    double calculateImpurity(List<DataPoint> splitData);

    
    /**
     * Calculate and return empirical probability of positive class. p+ = n+ / (n+ + n-).
     * 
     * @param splitData Data on which positive label probability is calculated.
     * @return Empirical probability.
     */
    default double getEmpiricalProbability(List<DataPoint> splitData, Label positive, Label negative) {
        return (double)splitData.parallelStream().filter(d -> d.getLabel().equals(positive)).count() / splitData.size();
    }
}

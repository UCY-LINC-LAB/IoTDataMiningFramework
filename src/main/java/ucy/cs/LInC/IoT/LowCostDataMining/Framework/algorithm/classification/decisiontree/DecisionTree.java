package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import java.util.List;
import java.util.Map;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.feature.Feature;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.impurity.EntropyCalculationMethod;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.impurity.ImpurityCalculationMethod;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.label.Label;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;

/**
 * Decision tree implementation. This class implements a decision Tree.
 * 
 *
 */
public class DecisionTree {

    /** Root node. */
    private Node root;

    /** Impurity calculation method. */
    private ImpurityCalculationMethod impurityCalculationMethod = new EntropyCalculationMethod();

    /**
     * When data is considered homogeneous and node becomes leaf and is labeled. If it is equal 1.0 then absolutely all
     * data must be of the same label that node would be considered a leaf.
     */
    private double homogenityPercentage = 0.90;

    /**
     * Max depth parameter. Growth of the tree is stopped once this depth is reached. Limiting depth of the tree can
     * help with overfitting, however if depth will be set too low tree will not be acurate.
     */
    private int maxDepth = 15;

    /**
     * Get root.
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Trains tree on training data for provided features.
     * 
     * @param trainingData
     *            List of training data samples.
     * @param features
     *            List of possible features.
     */
    public void train(List<DataPoint> trainingData, List<Feature> features) {
        root = growTree(trainingData, features, 1);
    }

    /**
     * Grow tree during training by splitting data recusively on best feature.
     * 
     * @param trainingData
     *            List of training data samples.
     * @param features
     *            List of possible features.
     * 
     * @return Node after split. For a first invocation it returns tree root node.
     */
    protected Node growTree(List<DataPoint> trainingData, List<Feature> features, int currentDepth) {

        Label currentNodeLabel = null;
        // if dataset already homogeneous enough (has label assigned) make this node a leaf
        if ((currentNodeLabel = getLabel(trainingData)) != null) {
            return Node.newLeafNode(currentNodeLabel);
        }
        
        boolean stoppingCriteriaReached = features.isEmpty() || currentDepth >= maxDepth;
        if (stoppingCriteriaReached) {
            Label majorityLabel = getMajorityLabel(trainingData);
            return Node.newLeafNode(majorityLabel);
        }

        Feature bestSplit = findBestSplitFeature(trainingData, features); // get best set of literals
        List<List<DataPoint>> splitData = bestSplit.split(trainingData);

        // remove best split from features (TODO check if it is not slow)
        List<Feature> newFeatures = features.stream().filter(p -> !p.equals(bestSplit)).collect(toList());
        Node node = Node.newNode(bestSplit);
        for (List<DataPoint> subsetTrainingData : splitData) { // add children to current node according to split
            if (subsetTrainingData.isEmpty()) {
                // if subset data is empty add a leaf with label calculated from initial data
                node.addChild(Node.newLeafNode(getMajorityLabel(trainingData)));
            } else {
                // grow tree further recursively
                node.addChild(growTree(subsetTrainingData, newFeatures, currentDepth + 1));
            }
        }

        return node;
    }

    /**
     * Classify dataSample.
     * 
     * @param dataSample
     *            Data sample
     * @return Return label of class.
     */
    public Label classify(DataPoint dataSample) {
        Node node = root;
        while (!node.isLeaf()) { // go through tree until leaf is reached
            // only binary splits for now - has feature first child node(left branch), does not have feature second child node(right branch).
            if (dataSample.has(node.getFeature())) {
                node = node.getChildren().get(0); 
            } else {
                node = node.getChildren().get(1);
            }
        }
        return node.getLabel();
    }

    /**
     * Finds best feature to split on which is the one whose split results in lowest impurity measure.
     */
    protected Feature findBestSplitFeature(List<DataPoint> data, List<Feature> features) {
        double currentImpurity = 1;
        Feature bestSplitFeature = null; // rename split to feature

        for (Feature feature : features) {
            List<List<DataPoint>> splitData = feature.split(data);
            // totalSplitImpurity = sum(singleLeafImpurities) / nbOfLeafs
            // in other words splitImpurity is average of leaf impurities
            double calculatedSplitImpurity = splitData.parallelStream().filter(list -> !list.isEmpty()).mapToDouble(list -> impurityCalculationMethod.calculateImpurity(list)).average().getAsDouble();
            if (calculatedSplitImpurity < currentImpurity) {
                currentImpurity = calculatedSplitImpurity;
                bestSplitFeature = feature;
            }
        }

        return bestSplitFeature;
    }

    /**
     * Returns Label if data is homogeneous.
     */
    protected Label getLabel(List<DataPoint> data) {
        // group by to map <Label, count>
        Map<Label, Long> labelCount = data.parallelStream().collect(groupingBy(DataPoint::getLabel, counting()));
        long totalCount = data.size();
        for (Label label : labelCount.keySet()) {
            long nbOfLabels = labelCount.get(label);
            if (((double) nbOfLabels / (double) totalCount) >= homogenityPercentage) {
                return label;
            }
        }
        return null;
    }

    /**
     * Differs from getLabel() that it always return some label and does not look at homogenityPercentage parameter. It
     * is used when tree growth is stopped and everything what is left must be classified so it returns majority label for the data.
     */
    protected Label getMajorityLabel(List<DataPoint> data) {
        // group by to map <Label, count> like in getLabels() but return Label with most counts
        return data.parallelStream().collect(groupingBy(DataPoint::getLabel, counting())).entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    // -------------------------------- TREE PRINTING ------------------------------------

    public void printTree() {
        printSubtree(root);
    }

    public void printSubtree(Node node) {
        if (!node.getChildren().isEmpty() && node.getChildren().get(0) != null) {
            printTree(node.getChildren().get(0), true, "");
        }
        printNodeValue(node);
        if (node.getChildren().size() > 1 && node.getChildren().get(1) != null) {
            printTree(node.getChildren().get(1), false, "");
        }
    }

    private void printNodeValue(Node node) {
        if (node.isLeaf()) {
            System.out.print(node.getLabel());
        } else {
            System.out.print(node.getName());
        }
        System.out.println();
    }

    private void printTree(Node node, boolean isRight, String indent) {
        if (!node.getChildren().isEmpty() && node.getChildren().get(0) != null) {
            printTree(node.getChildren().get(0), true, indent + (isRight ? "        " : " |      "));
        }
        System.out.print(indent);
        if (isRight) {
            System.out.print(" /");
        } else {
            System.out.print(" \\");
        }
        System.out.print("----- ");
        printNodeValue(node);
        if (node.getChildren().size() > 1 && node.getChildren().get(1) != null) {
            printTree(node.getChildren().get(1), false, indent + (isRight ? " |      " : "        "));
        }
    }
}

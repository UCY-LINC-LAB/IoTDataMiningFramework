package ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.feature;

import static java.util.stream.Collectors.partitioningBy;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;

/**
 * Feature interface. Each data sample either have or does not have a feature and it can be split based on that.
 *
 */
public interface Feature {
    
    /**
     * Calculates and checks if data contains feature.
     * 
     * @param dataSample Data sample.
     * @return true if data has this feature and false otherwise.
     */
    boolean belongsTo(DataPoint dataSample);

    /**
     * Split data according to if it has this feature.
     * 
     * @param data Data to by split by this feature.
     * @return Sublists of split data samples.
     */
    default List<List<DataPoint>> split(List<DataPoint> data) {
        List<List<DataPoint>> result = Lists.newArrayList();
        // http://stackoverflow.com/questions/22917270/how-to-get-a-range-of-items-from-stream-using-java-8-lambda
        Map<Boolean, List<DataPoint>> split = data.parallelStream().collect(partitioningBy(dataSample -> belongsTo(dataSample)));
        
        if (split.get(true).size() > 0) {
            result.add(split.get(true));
        } else {
            result.add(Lists.newArrayList());
        }
        if (split.get(false).size() > 0) {
            result.add(split.get(false));
        } else {
            result.add(Lists.newArrayList());
        }
        return result;
    }

}

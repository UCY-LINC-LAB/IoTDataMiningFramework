package client;

import static ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.feature.P.betweenD;
import static ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.feature.P.lessThanD;
import static ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.feature.P.moreThan;
import static ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.feature.P.moreThanD;
import static ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.feature.P.startsWith;
import static ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.feature.PredicateFeature.newFeature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.DecisionTree;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.feature.Feature;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.algorithm.classification.decisiontree.label.BooleanLabel;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DataPoint;
import ucy.cs.LInC.IoT.LowCostDataMining.Framework.data.DecisionTreeDataPoint;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

import com.google.common.collect.Lists;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        List<DataPoint> trainingData = readData(true);
        DecisionTree tree = new DecisionTree();
        
        List<Feature> features = getFeatures();
        
        tree.train(trainingData, features);
        
        // print tree after training
        tree.printTree();
        
        // read test data
        List<DataPoint> testingData = readData(false);
        List<String> predictions = Lists.newArrayList();
        // classify all test data
        for (DataPoint dataSample : testingData) {
            predictions.add(dataSample.getValue("time").get() + "," + tree.classify(dataSample).getPrintValue());
        }
        
        // write predictions to file
        FileWriter fileWriter = new FileWriter(new File("predictions.csv"));
        fileWriter.append("time,zone").append("\n");
        for (String prediction : predictions) {
            fileWriter.append(prediction).append("\n");
        }
        fileWriter.flush();
        fileWriter.close();
        
    }
    
    private static List<Feature> getFeatures() {

        Feature out_of_zone = newFeature("heart_rate", betweenD(0, 89.0), "out of zone");
        Feature fat_burn = newFeature("heart_rate", betweenD(89.0,110.0), "out of zone");
        Feature cardio_zone = newFeature("heart_rate", betweenD(110.0, 135.0), "out of zone");
        Feature peak_zone = newFeature("heart_rate", betweenD(135.0, 152.0), "out of zone");

        Feature calories = newFeature("calories", moreThanD(0.0), "more than 0");
        
        return Arrays.asList(out_of_zone,fat_burn,cardio_zone,peak_zone,calories);
    }
    
    private static List<DataPoint> readData(boolean training) throws IOException {
        List<DataPoint> data = Lists.newArrayList();
        String filename = training ? "train.csv" : "test.csv";
        InputStreamReader stream = new InputStreamReader(Main.class.getResourceAsStream(filename));
        try (ICsvListReader listReader = new CsvListReader(stream, CsvPreference.STANDARD_PREFERENCE);) {
            
            // the header elements are used to map the values to the bean (names must match)
            final String[] header = listReader.getHeader(true);
            
            List<Object> values;
            while ((values = listReader.read(getProcessors(training))) != null) {
//                System.out.println(String.format("lineNo=%s, rowNo=%s, data=%s", listReader.getLineNumber(), listReader.getRowNumber(), values));
                data.add(DecisionTreeDataPoint.newSimpleDataSample(new Timestamp(1),"zone", header, values.toArray()));
            }
        }
        return data;
    }
    
    private static CellProcessor[] getProcessors(boolean training) {
        // TODO fix this is ugly
        if (training) {
            final CellProcessor[] processors = new CellProcessor[] { 
                    new Optional(new ParseInt()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseInt()),
                    new Optional(new ParseBooleanLabel())
            };
            return processors;
        } else {
            final CellProcessor[] processors = new CellProcessor[] { 
            		new Optional(new ParseInt()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseInt())
            };
            return processors;
        }
    }
    
    private static class ParseBooleanLabel extends ParseBool {
        
        public Object execute(final Object value, final CsvContext context) {
            Boolean parsed = (Boolean)super.execute(value, context);
            return parsed ? BooleanLabel.TRUE_LABEL : BooleanLabel.FALSE_LABEL;
        }
        
    }
    
}

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.classifiers.Evaluation;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToNominal;

import java.util.Random;

public class WekaClassifier {
    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\abc\\Documents\\NetBeansProjects\\Event-managment-main\\events.arff";

        // Load the dataset
        DataSource source = new DataSource(path);
        Instances data = source.getDataSet();

        // Convert string attributes to nominal
        StringToNominal filter = new StringToNominal();
        filter.setAttributeRange("first-last"); 
        filter.setInputFormat(data);
        data = Filter.useFilter(data, filter);

        // Set class attribute (usually the last one)
        if (data.classIndex() == -1)
            data.setClassIndex(data.numAttributes() - 1);

        // Create and train the classifier
        Classifier cls = new weka.classifiers.trees.RandomForest();
 // You can replace with NaiveBayes, etc.
        cls.buildClassifier(data);

        // Evaluate the model
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(cls, data, 10, new Random(1));


        // Output evaluation
        System.out.println(eval.toSummaryString("\nResults\n======\n", false));
    }
}

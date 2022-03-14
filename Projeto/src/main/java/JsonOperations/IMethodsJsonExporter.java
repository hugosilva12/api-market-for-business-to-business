package JsonOperations;

import org.json.simple.JSONObject;

import java.util.Map;

/**
 * Interface to the class that exports statistical metrics to Json file
 */
public interface IMethodsJsonExporter {
    /**
     * Create a file
     * @param path path and name of file
     * @return true if file was create
     * @throws IllegalArgumentException if path is null or empty
     */
     boolean createFile(String path) ;

    /**
     * Write a json object to file
     *
     * @param path path and name of file
     * @param objWrite object to write in file
     * @return true if the object was written
     */
     boolean writeToFile(String path, JSONObject objWrite);

    /**
     * Export statistics to json file
     *
     * @param path path and name of file
     * @param map map in with values of average by district
     * @param AverageValueTransactions value of average  transactions
     * @param AverageNumberProductsTransaction value of average product by transactions
     * @return true if the statistics were exported
     */
     boolean exportStatistics(String path, Map<String, Map<String, Double>> map, Double AverageValueTransactions, Double AverageNumberProductsTransaction);

}

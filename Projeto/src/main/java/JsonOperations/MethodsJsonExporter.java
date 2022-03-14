package JsonOperations;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class that exports statistical metrics to Json file
 */
public class MethodsJsonExporter {
    private File file = null;
    private JSONParser jsonParser;
    private FileWriter fWriter;

    public MethodsJsonExporter() {
        this.jsonParser = new JSONParser();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean createFile(String path) {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Path should not be null or empty");
        }
        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                return true;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean exportStatistics(String path, Map<String, Map<String, Double>> map, Double averageValueTransactions, Double averageNumberProductsTransaction, Map<String, Map<String, Integer>> numberOrderSentReceivedByDistrict, Double standardDeviationTransactions, Double standardDeviationNumberProductTransactions) {
        if (map == null || path == null || numberOrderSentReceivedByDistrict == null) {
            throw new IllegalArgumentException("Path should not be null or empty");
        }
        if (averageValueTransactions < 0 || averageNumberProductsTransaction < 0 || standardDeviationTransactions < 0 || standardDeviationNumberProductTransactions < 0) {
            throw new IllegalArgumentException("Cannot be negative values");
        }

        JSONArray listAverageSalePurchasesDristrict = new JSONArray();
        for (Map.Entry<String, Map<String, Double>> key : map.entrySet()) {
            JSONObject objDistrict = new JSONObject();
            Map<String, Double> valuesForDistict = new HashMap<String, Double>();
            valuesForDistict = key.getValue();
            objDistrict.put("District", key.getKey());
            objDistrict.put("Compra", valuesForDistict.get("Compra"));
            objDistrict.put("Venda", valuesForDistict.get("Venda"));

            listAverageSalePurchasesDristrict.add(objDistrict);
        }

        JSONArray listNumberOrderSentReceivedByDistrict = new JSONArray();
        for (Map.Entry<String, Map<String, Integer>> key : numberOrderSentReceivedByDistrict.entrySet()) {
            JSONObject objDistrict = new JSONObject();
            Map<String, Integer> valuesForDistict = valuesForDistict = key.getValue();
            objDistrict.put("District", key.getKey());
            objDistrict.put("Recebidas", valuesForDistict.get("Recebidas"));
            objDistrict.put("Enviadas", valuesForDistict.get("Enviadas"));
            listNumberOrderSentReceivedByDistrict.add(objDistrict);
        }


        JSONObject objStandardDeviationNumberProductTransactions = new JSONObject();
        objStandardDeviationNumberProductTransactions.put("StandardDeviationNumberProductTransactions", standardDeviationNumberProductTransactions);

        JSONObject objStandardDeviationTransactions = new JSONObject();
        objStandardDeviationTransactions.put("StandardDeviationTransactions", standardDeviationTransactions);

        JSONObject objValueSalesPurchasesByDistrict = new JSONObject();
        objValueSalesPurchasesByDistrict.put("ValueSalesPurchasesByDistrict", listAverageSalePurchasesDristrict);

        JSONObject objNumberOrderSendReceive = new JSONObject();
        objNumberOrderSendReceive.put("NumberOrderSendReceive", listNumberOrderSentReceivedByDistrict);

        JSONObject objStatisticsValueTransactions = new JSONObject();
        objStatisticsValueTransactions.put("averageValueTransactions", averageValueTransactions);

        JSONObject objStatisticsNumberProductsTransaction = new JSONObject();
        objStatisticsNumberProductsTransaction.put("averageNumberProductsTransaction", averageNumberProductsTransaction);

        JSONObject objWrite = new JSONObject();

        JSONArray arrayStatistics = new JSONArray();
        arrayStatistics.add(objNumberOrderSendReceive);
        arrayStatistics.add(objValueSalesPurchasesByDistrict);
        arrayStatistics.add(objStatisticsNumberProductsTransaction);
        arrayStatistics.add(objStatisticsValueTransactions);
        arrayStatistics.add(objStandardDeviationNumberProductTransactions);
        arrayStatistics.add(objStandardDeviationTransactions);
        objWrite.put("Statistics", arrayStatistics);
        return writeToFile(path, objWrite);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean writeToFile(String path, JSONObject objWrite) {
        if (path == null || path.isEmpty() || objWrite == null) {
            throw new IllegalArgumentException("Path should not be null or empty");
        }

        this.file = new File(path);
        boolean exists = file.exists();
        boolean isCreated = true;
        if (!exists) {
            isCreated = createFile(path);
        }
        if (isCreated) {

            try {
                fWriter = new FileWriter(file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fWriter.write(objWrite.toJSONString());
                fWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

}

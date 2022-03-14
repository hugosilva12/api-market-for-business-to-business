package TestsModuleExpedition;


import ApiUse.IOrder;

import JsonOperations.MethodsJsonExporter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The ExpeditionMethods class implements the methods that group orders by district
 */
public class ExpeditionMethods implements IExpeditionMethods {
    private IGroupAllTrucks groupTrucksAllOrders = new GroupAllTrucks();
    private MethodsJsonExporter methodsJsonExporter = new MethodsJsonExporter();
    private final String pathFile = "filesJson/expedictionResults.json";

    /**
     * {@inheritDoc}
     */
    @Override
    public JSONArray groupOrdersByTrucks(ArrayList<IOrder> listOrders) {
        if (listOrders == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }

        Map<String, Map<String, Double>> trucksDistrict = new HashMap<String, Map<String, Double>>();

        for (IOrder order : listOrders) {

            //So agrupa se nao for do mesmo distrito
            if (!order.getSender().getDistrict().equals(order.getReceiver().getDistrict())) {
                Map<String, Double> values = new HashMap<String, Double>();

                if (trucksDistrict.get(order.getSender().getDistrict()) == null) {
                    values.put("Volume", order.getOrderVolum());
                    trucksDistrict.put(order.getSender().getDistrict(), values);

                } else {
                    values = trucksDistrict.get(order.getSender().getDistrict());
                    Double newValue = values.get("Volume") + order.getOrderVolum();
                    values.put("Volume", newValue);
                    trucksDistrict.put(order.getSender().getDistrict(), values);
                }
            }

        }

        Map<String, Integer> numberContainers = new HashMap<>();

        //Obter número de containers necessários à partida
        for (Map.Entry<String, Map<String, Double>> key : trucksDistrict.entrySet()) {
            Map<String, Double> values = new HashMap<String, Double>();
            values = key.getValue();
            int roundedNumA = (int) Math.ceil(values.get("Volume") / 63);
            numberContainers.put(key.getKey(), roundedNumA);
        }

        for (Map.Entry<String, Integer> key : numberContainers.entrySet()) {
            ArrayList<IOrder> ordersByDistrict = getArrayOrdersByDistrict(key.getKey(), listOrders);
            IDistrictTrucks districtExpedition = new DistrictTrucks(key.getKey(), key.getValue(), ordersByDistrict);
            this.groupTrucksAllOrders.addDistrictTrucks(districtExpedition);
        }

        return this.groupTrucksAllOrders.getDistrictExpeditionJSONFormat();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<IOrder> getArrayOrdersByDistrict(String district, ArrayList<IOrder> listOrders) {
        if (listOrders == null) {
            throw new IllegalArgumentException("DistrictTrucks cannot be null.");
        }
        if (district == null) {
            throw new IllegalArgumentException("district cannot be null or empty.");
        }
        if (district.isEmpty()) {
            throw new IllegalArgumentException("district cannot empty.");
        }

        ArrayList<IOrder> ordersByDistrict = new ArrayList<>();

        for (IOrder order : listOrders) {
            if (order.getSender().getDistrict().equals(district)) {
                ordersByDistrict.add(order);
            }
        }
        return ordersByDistrict;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exportGroupOrdersForFileJson() {
        JSONArray jsonArray = new JSONArray();
        if (groupTrucksAllOrders.getDistrictExpeditionJSONFormat().equals(jsonArray)) {
            return false;
        } else {
            JSONObject objectToWrite = new JSONObject();
            objectToWrite.put("Expedition Results", groupTrucksAllOrders.getDistrictExpeditionJSONFormat().get(0));
            methodsJsonExporter.writeToFile(pathFile, objectToWrite);
            return true;
        }

    }

}



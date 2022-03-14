package TestsModuleExpedition;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * The GroupTrucksAllOrders class provides the possibility to store the result of grouping all orders
 */
public class GroupAllTrucks implements IGroupAllTrucks {

    private ArrayList<IDistrictTrucks> districtExpeditions;

    public GroupAllTrucks() {
        this.districtExpeditions = new ArrayList<IDistrictTrucks>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addDistrictTrucks(IDistrictTrucks iDistrictExpedition) {
        if (iDistrictExpedition == null) {
            throw new IllegalArgumentException("DistrictTrucks cannot be null.");
        }
        iDistrictExpedition.groupOrders();
        this.districtExpeditions.add(iDistrictExpedition);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print() {
        for (IDistrictTrucks iDistrictExpedition : districtExpeditions) {
            System.out.println(iDistrictExpedition.toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public JSONArray getDistrictExpeditionJSONFormat() {

        JSONArray jsonArrayGroupDistrict = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray2 = new JSONArray();


        for (IDistrictTrucks iDistrictExpedition : districtExpeditions) {
            jsonObject = new JSONObject();
            jsonObject.put("District", iDistrictExpedition.getNameDistrict());
            jsonObject.put("NumberTrucks", iDistrictExpedition.getSize());
            jsonArray2 = new JSONArray();

            for (int i = 0; i < iDistrictExpedition.getSize(); i++) {

                JSONObject truck = new JSONObject();
                truck.put("TruckName", iDistrictExpedition.getTruckByIndex(i).getNameContainer());
                truck.put("AvaibleCapacity", iDistrictExpedition.getTruckByIndex(i).availableCapacity());

                JSONArray jsonArrayProductInsideContainer = new JSONArray();

                for (int j = 0; j < iDistrictExpedition.getTruckByIndex(i).numberProductInContainer(); j++) {
                    JSONObject jsonObjectProductInsideContainer = new JSONObject();
                    jsonObjectProductInsideContainer.put("Id_Order", iDistrictExpedition.getTruckByIndex(i).getProductListProductsInsideTruck(j).getIdOrder());
                    jsonObjectProductInsideContainer.put("Id_Product", iDistrictExpedition.getTruckByIndex(i).getProductListProductsInsideTruck(j).getIdProduct());
                    jsonObjectProductInsideContainer.put("Quantity", iDistrictExpedition.getTruckByIndex(i).getProductListProductsInsideTruck(j).getQuantity());
                    jsonObjectProductInsideContainer.put("Unit_Volume", iDistrictExpedition.getTruckByIndex(i).getProductListProductsInsideTruck(j).getUniqueVolume());
                    jsonArrayProductInsideContainer.add(jsonObjectProductInsideContainer);
                }
                truck.put("ProductInContainer", jsonArrayProductInsideContainer);
                jsonArray2.add(truck);
            }

            jsonObject.put("Trucks", jsonArray2);
            jsonObject.put("District", iDistrictExpedition.getNameDistrict());
            jsonArrayGroupDistrict.add(jsonObject);
        }

        return jsonArrayGroupDistrict;
    }

}

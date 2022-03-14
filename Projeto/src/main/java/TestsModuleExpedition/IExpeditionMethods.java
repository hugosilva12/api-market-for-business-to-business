package TestsModuleExpedition;


import ApiUse.IOrder;
import org.json.simple.JSONArray;

import java.util.ArrayList;

/**
 * This interface provides the methods for grouping orders by district in trucks.
 */
public interface IExpeditionMethods {

    /**
     * This method groups, by district, a list of orders.
     *
     * @param listOrders list of orders
     * @return JsonArray with the result of the sort performed
     * @throws IllegalArgumentException if listOrders is null
     */
    JSONArray groupOrdersByTrucks(ArrayList<IOrder> listOrders);

    /**
     * This method returns the {@link IOrder list}  of orders for a district.
     *
     * @param district   name of district
     * @param listOrders list of orders
     * @return arraylist with district orders
     */
    ArrayList<IOrder> getArrayOrdersByDistrict(String district, ArrayList<IOrder> listOrders);

    /**
     * Exports the result of grouping orders by district to JSON file
     *
     * @return true if the array is eligible for export, false if array is empty
     */
    boolean exportGroupOrdersForFileJson();
}

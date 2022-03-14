package TestsModuleExpedition;

import org.json.simple.JSONArray;

/**
 * This interface provides the methods for the structure that stores the cluster results.
 */
public interface IGroupAllTrucks {


    /**
     * Add the trucks needed for a district.
     *
     * @param districtTrucks needed trucks
     * @return true if trucks for a district were added, false if there is already a set of trucks for the district
     */
    boolean addDistrictTrucks(IDistrictTrucks districtTrucks);

    /**
     * List the order grouping
     */
    void print();

    /**
     * This method returns JsonArray with the result of the grouping performed.
     *
     * @return JsonArray with the result of the sort performed
     */
    JSONArray getDistrictExpeditionJSONFormat();
}

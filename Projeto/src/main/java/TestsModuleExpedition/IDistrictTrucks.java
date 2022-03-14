package TestsModuleExpedition;

/**
 * This interface provides the methods for the structure representing the trucks needed for a district.
 */
public interface IDistrictTrucks {
    /**
     * This method creates a truck.
     *
     * @param numberContainer truck number
     */
    void createTruck(int numberContainer);

    /**
     * This method groups a list of orders in trucks.
     */
    void groupOrders();

    /**
     * Returns the name of district.
     *
     * @return he name of district
     */
    String getNameDistrict();

    /**
     * Return the {@link Truck truck} with the given index in the DistrictTrucks.
     *
     * @param index the index of the {@link IProductsInsideTruck iProductInsideTruck} to return.
     * @return the {@link Truck truck} with the given index in the DistrictTrucks.
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    Truck getTruckByIndex(int index);

    /**
     * Return the number of trucks necessary for a district.
     *
     * @return number of trucks necessary for a district
     */
    public int getSize();
}

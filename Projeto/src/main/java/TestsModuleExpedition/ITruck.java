package TestsModuleExpedition;


import ApiUse.IOrder;

/**
 * This interface provides the methods for the structure that represents a truck.
 */
public interface ITruck {

    /**
     * Add a product to a truck
     *
     * @param idOrder   order id
     * @param idProduct product id
     * @param volume    product volume
     * @return true if the product was added, false if there is not enough space in the container
     */
    boolean addProductInTruck(String idOrder, String idProduct, Double volume);

    /**
     * Add a complete order to the truck container.
     *
     * @param order order
     * @return true if order was added, false if there is no space
     */
    boolean addAllOrderInTruck(IOrder order);


    /**
     * Returns a {@link IProductsInsideTruck product} inside the truck container.
     *
     * @param iProductsInsideTruck product that must be the equal
     * @return returns the product, null if it does not exist
     * @throws IllegalArgumentException if the iProductsInsideTruck is invalid
     */
    IProductsInsideTruck getProductContainer(IProductsInsideTruck iProductsInsideTruck);

    /**
     * Return the name of truck
     *
     * @return name of truck
     */
    String getNameContainer();

    /**
     * Return the available capacity of truck
     *
     * @return available capacity of truck
     */
    double availableCapacity();

    /**
     * Return the {@link IProductsInsideTruck iProductInsideTruck} with the given index in the truck.
     *
     * @param index the index of the {@link IProductsInsideTruck iProductInsideTruck} to return.
     * @return the {@link IProductsInsideTruck iProductInsideTruck} with the given index in the truck.
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    IProductsInsideTruck getProductListProductsInsideTruck(int index);

    /**
     * Return the number of product set inside the truck.
     *
     * @return the number of product set inside the truck
     */
    int numberProductInContainer();

    /**
     * Print the set of products inside the truck.
     */
    void print();
}

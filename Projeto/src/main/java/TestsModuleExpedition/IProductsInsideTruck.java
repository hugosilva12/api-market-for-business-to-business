package TestsModuleExpedition;

/**
 * This interface provides the methods for the structure that represents a set of products (which belong to an order) inside a truck.
 */
public interface IProductsInsideTruck {
    /**
     * Return id of order.
     *
     * @return id of order
     */
    String getIdOrder();


    /**
     * Return id of product.
     *
     * @return id of product
     */
    String getIdProduct();


    /**
     * Return quantity of product.
     *
     * @return quantity of product
     */
    int getQuantity();

    /**
     * Assigns a new quantity to the amount of products inside a truck.
     *
     * @param quantity new quantity
     */
    void setQuantity(int quantity);

    /**
     * Returns the volume of a product
     *
     * @return the volume of a product
     */
    double getUniqueVolume();
}

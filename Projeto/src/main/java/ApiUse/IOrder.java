package ApiUse;

import ModuleTransaction.Orgcom.Entity;
import ModuleTransaction.Orgcom.hashing.Hashable;

/**
 * The order interface provides the functionality of an order in the system.
 */
public interface IOrder extends Hashable, Iterable<IProduct> {

    /**
     * Returns the id of the order.
     *
     * @return the id of the order.
     */
    String getId();

    /**
     * Returns the date of the order.
     *
     * @return the date of the order.
     */
    String getDate();

    /**
     * Returns the sender entity of the order.
     *
     * @return the sender entity of the order.
     */
    Entity getSender();

    /**
     * Returns the receiver entity of the order.
     *
     * @return the receiver entity of the order.
     */
    Entity getReceiver();

    /**
     * Return the number of different products in the order.
     *
     * @return the number of different products in the order.
     */
    int getProductCounter();

    /**
     * Return the Shipping Cost of the order.
     *
     * @return the Shipping Cost of the order.
     */
    double getShippingCost();

    /**
     * Returns the price of the order.
     *
     * @return the price of the order.
     */
    Double getPrice();

    /**
     * Returns the total volume of the order.
     *
     * @return the total volume of the order.
     */
    double getOrderVolum();

    /**
     * Returns the total weight of the order.
     *
     * @return the total weight of the order.
     */
    double getOrderWeight();

    /**
     * Return the {@link IProduct product} in the order that have the same hash.
     *
     * @param IProduct the {@link IProduct product} to be checked.
     * @return the {@link IProduct product} in the order that have the same hash, null if not found.
     * @throws IllegalArgumentException if the product parameter is null.
     */
    IProduct getProduct(IProduct IProduct);

    /**
     * Adds a product to the order.
     *
     * @param IProduct the product to add.
     * @return true if the product was added correctly or false is it was not added.
     * @throws IllegalArgumentException if the product parameter is null.
     */
    boolean addProduct(IProduct IProduct);

    /**
     * Return the {@link IProduct product} in the order that have the gaven index.
     *
     * @param index the index of the product list to be found.
     * @return the {@link IProduct product} in the order that have the gaven index.
     * @throws IndexOutOfBoundsException if the order index is invalid.
     */
    IProduct getProductByIndex(int index);

    /**
     * Calculates the cost of shipping an order.
     *
     * @return the cost of shipping of an order.
     */
    double shippingCostCalculation(); //Alterar AINDA

    /**
     * Prints the order to the console.
     * @return the order to the console.
     */
    String print();
    /**
     *  Return the state of Basic Order
     * @return the state of Basic Order
     */
    public StateOrder getStateOrder();

    /**
     * Set the attribute of enum StateOrder
     * @param stateOrder the state of {@link StateOrder basicOrders}
     */
    public void setStateOrder(StateOrder stateOrder);
}

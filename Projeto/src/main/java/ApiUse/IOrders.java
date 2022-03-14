package ApiUse;

import Exceptions.AverageException;
import ModuleTransaction.Orgcom.Block;
import ModuleTransaction.Orgcom.hashing.UnHashableException;

import java.util.ArrayList;
import java.util.Map;

/**
 * The orders interface provides the functionality of an orders list in the system.
 */
public interface IOrders extends Iterable<IOrder> {

    /**
     * Return the {@link IOrder order} in the orders list that have the same hash.
     *
     * @param IOrder the {@link IOrder order} to be checked.
     * @return the {@link IOrder order} in the orders list that have the same hash, null if not found.
     * @throws UnHashableException if the order are not hashable.
     */
    IOrder getOrder(IOrder IOrder);

    /**
     * Adds an order to the orders list.
     *
     * @param IOrder the product to add.
     * @return true if the order was added correctly or false is it was not added.
     * @throws IllegalArgumentException if the product parameter is null.
     */
    boolean addOrder(IOrder IOrder);

    /**
     * Prints the orders list to the console.
     */
    String print();

    /**
     * Return number of orders
     *
     * @return number of orders
     */
    int getNumberOrder();

    /**
     * Return the {@link IOrder order} in the orders that have the gaven index.
     *
     * @param index the index of the order list to be found.
     * @return the {@link IOrder order} in the order that have the gaven index.
     * @throws IndexOutOfBoundsException if the order index is invalid.
     */
    IOrder getOrderIndex(int index);

    /**
     * Returns the shipping cost of the given order.
     *
     * @param IOrder the order that will be returned the shipping cost.
     * @return the shipping cost of the given order.
     * @throws IllegalArgumentException if the IOrder parameter is null.
     */
    double getOrderShippingCost(IOrder IOrder);

    /**
     * Return the  number of transactions registed
     *
     * @return the number of transactions registed
     * @throws IllegalArgumentException if the {@link IOrders basicOrders} is null
     * @throws IllegalArgumentException if the {@link IOrders basicOrders} is empty
     */
     int registerOrdersInLedger();


    /**
     * Returns Returns the average value of transactions in format '##.##' (two decimal places)
     *
     * @return average value of transactions
     * @throws AverageException if the ledger is empty.
     */
    double averageValueTransactions();

    /**
     * Returns average number of products per transaction in format '##.##' (two decimal places)
     *
     * @return average number of products per transaction
     * @throws AverageException if the ledger is empty.
     */
    double averageNumberProductsTransaction();


    /**
     * Returns the average sales/purchases by district on a map
     *
     * @return map in format (String, Map (String,Double) (example: Porto={"Compra":60.00,"Venda":30.00})
     * @throws AverageException if the ledger is empty (Genisis block only).
     */
    Map<String, Map<String, Double>> averageValueSalesPurchasesDistrict();

    /**
     * Return arraylist of organization blocks
     *
     * @return arraylist of organization {@link Block}
     */
    ArrayList<Block> getArrayListBlocks();

    /**
     * Return list of {@link IOrder transaction}
     *
     * @return list of {@link IOrder transaction}
     */
    ArrayList<IOrder> getBasicOrders();

    /**
     * Set list of {@link IOrder transaction}
     *
     * @param basicIOrders list of {@link IOrder transaction}
     */
    void setBasicOrders(ArrayList<IOrder> basicIOrders);

    /**
     * Returns in map format the number of purchases and sales by district
     *
     * @return map in format String, String Double (example: Porto={"Compra":60.00,"Venda":30.00}) with values
     * @throws AverageException if the ledger is empty (Genisis block only).
     */
    Map<String, Map<String, Integer>> numberOrderSentReceivedByDistrict();

    /**
     * Returns the standardDeviation of transactions
     *
     * @return value of standardDeviation
     * @throws AverageException if the ledger is empty (Genisis block only).
     */
    double standardDeviationTransactions();

    /**
     * Returns the standard deviation of number products by transactions
     *
     * @return value of standardDeviation
     * @throws AverageException if the ledger is empty (Genisis block only).
     */
    double standardDeviationNumberProductTransactions();

}

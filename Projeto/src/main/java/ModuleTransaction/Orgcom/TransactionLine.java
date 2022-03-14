package ModuleTransaction.Orgcom;

import ModuleTransaction.Orgcom.hashing.Hashable;


/**
 * The TransactionLine interface provides the functionality of a transaction line.
 * The implementation of this interface is responsible for the creation of the transaction line.
 * A transaction line is a line in a transaction, and is composed of an item description, a quantity, and a price.
 * The product description cannot be null or empty.
 * The quantity must be a positive integer.
 * The price must be a positive double.
 */
public interface TransactionLine extends Hashable {

    /**
     * Getter for item description in the transaction line.
     *
     * @return item description in the transaction line.
     */
    String getItemDescription();

    /**
     * Getter for the item unit price of the transaction line.
     *
     * @return item unit price of the transaction line
     */
    double getUnitPrice();

    /**
     * Getter for quantity of the item in the transaction line.
     *
     * @return quantity of the item in the transaction line.
     */
    int getQuantity();

    /**
     * Getter for total price of the item in the transaction line.
     * The value must be the quantity times the item unit price.
     *
     * @return total price of the item in the transaction line.
     */
    double getTotalPrice();

    String getHash();

    /**
     * Returns a string representation of the transaction line.
     *
     * @return a string representation of the transaction line.
     */
    @Override
    String toString();

}

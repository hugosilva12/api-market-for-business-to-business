package ApiUse;

import ModuleTransaction.Orgcom.hashing.Hashable;

/**
 * The Product interface provides the methods for a product
 *
 */
public interface IProduct extends Hashable {

    /**
     * Returns the id of the product.
     *
     * @return the id of the product.
     */
    int getId();

    /**
     * Returns the name of the product.
     *
     * @return the name of the product.
     */
    String getName();

    /**
     * Returns the description of the product.
     *
     * @return the description of the product.
     */
    String getDescription();

    /**
     * Returns the value of the product.
     *
     * @return the value of the product.
     */
    double getValue();

    /**
     * Returns the quantity of the product.
     *
     * @return the quantity of the product.
     */
    int getQuantity();

    /**
     * Returns the volume of the product.
     *
     * @return the volume of the product.
     */
    double getVolume();

    /**
     * Returns the weight of the product.
     *
     * @return the weight of the product.
     */
    double getWeight();

    /**
     * Prints the product to the console.
     */
    String print();
}

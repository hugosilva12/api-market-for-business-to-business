package ModuleCost;


import ModuleTransaction.Orgcom.hashing.Hashable;

/**
 * The IDistance interface provides the methods for a distance
 */
public interface IDistanceLine extends Hashable {

    /**
     * Returns the id of the distance.
     *
     * @return the id of the distance.
     */
    String getId();

    /**
     * Returns the distance value of the distance.
     *
     * @return the distance value of the distance.
     */
    int getDistanceValue();

    /**
     * Prints the distance to the console.
     */
    String print();
}

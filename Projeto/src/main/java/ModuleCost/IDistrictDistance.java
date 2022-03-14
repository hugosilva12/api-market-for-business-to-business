package ModuleCost;

import ModuleTransaction.Orgcom.hashing.Hashable;
import ModuleTransaction.Orgcom.hashing.UnHashableException;

import java.util.ArrayList;

/**
 * The IDistrictDistance interface provides the methods for the class that will store the distance from one district to all others
 */
public interface IDistrictDistance extends Hashable, Iterable<IDistanceLine> {

    /**
     * Returns the name of the DistrictDistance.
     *
     * @return the name of the DistrictDistance.
     */
    String getName();

    /**
     * Return the {@link IDistanceLine distance} in the DistrictDistance that have the same hash.
     *
     * @param distance the {@link IDistanceLine distance} to be checked.
     * @return the {@link IDistanceLine distance} in the DistrictDistance that have the same hash, null if not found.
     * @throws UnHashableException if the distance are not hashable.
     */
    IDistanceLine getDistance(IDistanceLine distance);

    /**
     * Adds a distance to the DistrictDistance.
     *
     * @param distance the distance to add.
     * @return true if the distance was added correctly or false is it was not added.
     * @throws IllegalArgumentException if the distance parameter is null.
     */
    boolean addDistance(IDistanceLine distance);

    /**
     * Returns the array of distances of the DistrictDistance.
     *
     * @return the array of distances of the DistrictDistance.
     */
    ArrayList<IDistanceLine> getDistances();

    /**
     * Prints the DistrictDistance to the console.
     */
    String print();
}

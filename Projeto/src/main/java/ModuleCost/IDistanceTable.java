package ModuleCost;

import ModuleTransaction.Orgcom.Entity;
import ModuleTransaction.Orgcom.Transaction;
import ModuleTransaction.Orgcom.hashing.UnHashableException;

import java.util.Iterator;

/**
 * The IDistanceTable interface provides the methods for a distance table
 */
public interface IDistanceTable extends Iterable<IDistrictDistance> {

    /**
     * Return the {@link IDistrictDistance districtCost} in the costTable that have the same hash.
     *
     * @param districtCost the {@link IDistrictDistance districtCost} to be checked.
     * @return the {@link IDistrictDistance districtCost} in the costTable that have the same hash, null if not found.
     * @throws UnHashableException if the districtCost are not hashable.
     */
    IDistrictDistance getDistrictDistance(IDistrictDistance districtCost);

    /**
     * Adds an districtCost to the costsTable list.
     *
     * @param districtCost the product to add.
     * @return true if the districtCost was added correctly or false is it was not added.
     * @throws IllegalArgumentException if the districtCost parameter is null.
     */
    boolean addDistrictDistance(IDistrictDistance districtCost);

    /**
     * Returns the distance between two districts
     *
     * @param sender   district sender
     * @param receiver district receiver
     * @return the distance between the two districts, -1 if not found
     * @throws IllegalArgumentException if the sender or receiver is null
     */
    int getDistanceValueFromShipping(Entity sender, Entity receiver);

    /**
     * Prints the costsTable to the console.
     */
    String print();


    /**
     * Returns the iterator of the {@link IDistrictDistance districtDistance} in the table.
     *
     * @return the iterator of the {@link IDistrictDistance districtDistance} in the table.
     */
     Iterator<IDistrictDistance> iterator();
}

package ModuleCost;


import ModuleTransaction.Orgcom.hashing.HashUtils;
import ModuleTransaction.Orgcom.hashing.UnHashableException;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class stores the distance between a district and all others.
 */
public class DistrictDistance implements IDistrictDistance {

    private final String name;

    ArrayList<IDistanceLine> distances = new ArrayList<>();

    /**
     * Creates a new DistrictDistance with the given name.
     *
     * @param name the name of the DistrictDistance.
     * @throws IllegalArgumentException If any of the argument name is null or empty.
     */
    public DistrictDistance(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        this.name = name;
    }

    /**
     * Returns the name of the DistrictDistance.
     *
     * @return the name of the DistrictDistance.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Return the {@link IDistanceLine distance} in the DistrictDistance that have the same hash.
     *
     * @param distance the {@link IDistanceLine distance} to be checked.
     * @return the {@link IDistanceLine distance} in the DistrictDistance that have the same hash, null if not found.
     * @throws UnHashableException if the distance are not hashable.
     */
    @Override
    public IDistanceLine getDistance(IDistanceLine distance) {
        if (distance == null) {
            throw new IllegalArgumentException("Distance cannot be null.");
        }

        for (IDistanceLine distanceTemp : this.distances) {
            if (distance.getHash().equals(distanceTemp.getHash())) {
                return distance;
            }
        }

        return null;
    }

    /**
     * Adds a distance to the DistrictDistance.
     *
     * @param distance the distance to add.
     * @return true if the distance was added correctly or false is it was not added.
     * @throws IllegalArgumentException if the distance parameter is null.
     */
    @Override
    public boolean addDistance(IDistanceLine distance) {
        if (distance == null) {
            throw new IllegalArgumentException("Distance cannot be null.");
        }

        if (this.getDistance(distance) != null) {
            return false;
        }

        this.distances.add(distance);

        return true;
    }

    /**
     * Returns the string representation of the DistrictDistance.
     *
     * @return the string representation of the DistrictDistance.
     */
    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("Districts{");
        sb.append("Name: ").append(this.name);
        sb.append(" ,Distances: ").append(this.name);
        for (int print = 0; print < this.distances.size(); print++) {
            sb.append(", ").append(distances.get(print).print());
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Returns the array of distances of the DistrictDistance.
     *
     * @return the array of distances of the DistrictDistance.
     */
    @Override
    public ArrayList<IDistanceLine> getDistances() {
        return this.distances;
    }

    /**
     * @return the hash value of the DistrictDistance.
     */
    @Override
    public String getHash() {
        return HashUtils.getHash(this.name);
    }

    /**
     * Returns the iterator value of the distances array of the DistrictDistance.
     *
     * @return the iterator value of the distances array of the DistrictDistance.
     */
    @Override
    public Iterator<IDistanceLine> iterator() {
        return this.distances.iterator();
    }
}

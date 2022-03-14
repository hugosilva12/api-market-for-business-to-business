package ModuleCost;

import ModuleTransaction.Orgcom.hashing.HashUtils;

/**
 * This class represents a district and its distance from another
 */
public class DistanceLine implements IDistanceLine {

    private final String nameDistrict;
    private final int distanceValue;

    /**
     * Creates a new Distance with the given id and distanceValue;
     *
     * @param id            the id of the distance
     * @param distanceValue the distanceValue of the distance
     * @throws IllegalArgumentException If any of the arguments are invalid (distance id is null or empty, distanceValue is less than 0).
     */
    public DistanceLine(String id, int distanceValue) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Id cannot be null or empty");
        }
        if (distanceValue < 0) {
            throw new IllegalArgumentException("distance cannot be negative.");
        }

        this.nameDistrict = id;
        this.distanceValue = distanceValue;
    }

    /**
     * Returns the id of the distance.
     *
     * @return the id of the distance.
     */
    @Override
    public String getId() {
        return this.nameDistrict;
    }

    /**
     * Returns the distanceValue of the distance.
     *
     * @return the distanceValue of the distance.
     */
    @Override
    public int getDistanceValue() {
        return distanceValue;
    }

    /**
     * Returns the string representation of the distance.
     *
     * @return the string representation of the distance.
     */
    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("Distance{");
        sb.append("id: ").append(this.nameDistrict);
        sb.append(", distance value (KM): ").append(this.distanceValue);
        return sb.append('}').toString();
    }

    /**
     * @return the hash value of the distance.
     */
    @Override
    public String getHash() {
        return HashUtils.getHash(this.nameDistrict + this.distanceValue);
    }
}

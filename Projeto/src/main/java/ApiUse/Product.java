package ApiUse;

import JsonOperations.ImportConfigsTruck;
import ModuleTransaction.Orgcom.hashing.HashUtils;

/**
 * This class stores the data of a product
 */
public class Product implements IProduct {

    private final int id;
    private final String name;
    private final String description;
    private final double value;
    private final int quantity;
    private final double volume;
    private final double weight;
    private final double maxCapacity;
    private final String pathFile = "filesJson/capacityTruck.json";
    private ImportConfigsTruck importConfigsTruck = new ImportConfigsTruck();

    /**
     * Creates a new Product with the given id, name, description, value, quantity and volume;
     *
     * @param id          the id of the product
     * @param name        the name of the product
     * @param description the description of the product
     * @param value       the value of the product
     * @param quantity    the quantity of the product
     * @param volume      the volume of the product
     * @param weight      the weight of the product
     * @throws IllegalArgumentException If any of the arguments are invalid (product id or name or description are null or empty,
     *                                  value, quantity, volume, or weight is less or equal than 0 and unitPrice is negative).
     */
    public Product(int id, String name, String description, double value, int quantity, double volume, double weight) {
        maxCapacity = importConfigsTruck.importConfigCapacity(pathFile);
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Value cannot be negative.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        if (volume <= 0) {
            throw new IllegalArgumentException("Volume cannot be negative.");
        }
        if (volume > maxCapacity) {
            throw new IllegalArgumentException("Volume greater than 63 m^3.");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight cannot be negative or 0.");
        }

        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
        this.quantity = quantity;
        this.volume = volume;
        this.weight = weight;
    }

    /**
     * Returns the id of the product.
     *
     * @return the id of the product.
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Returns the name of the product.
     *
     * @return the name of the product.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of the product.
     *
     * @return the description of the product.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the value of the product.
     *
     * @return the value of the product.
     */
    @Override
    public double getValue() {
        return this.value;
    }

    /**
     * Returns the quantity of the product.
     *
     * @return the quantity of the product.
     */
    @Override
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Returns the volume of the product.
     *
     * @return the volume of the product.
     */
    @Override
    public double getVolume() {
        return this.volume;
    }

    /**
     * Returns the weight of the product.
     *
     * @return the weight of the product.
     */
    @Override
    public double getWeight() {
        return this.weight;
    }

    /**
     * Returns the string representation of the product.
     *
     * @return the string representation of the product.
     */
    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("Product{");
        sb.append("id: ").append(this.id);
        sb.append(", name: ").append(this.name);
        sb.append(", description: ").append(this.description);
        sb.append(", value: ").append(this.value);
        sb.append(", quantity: ").append(this.quantity);
        sb.append(", volume: ").append(this.volume);
        sb.append(", weight: ").append(this.weight);
        return sb.append('}').toString();
    }

    /**
     * @return the hash value of the product.
     */
    @Override
    public String getHash() {
        return HashUtils.getHash(this.id + this.name + this.description);
    }
}

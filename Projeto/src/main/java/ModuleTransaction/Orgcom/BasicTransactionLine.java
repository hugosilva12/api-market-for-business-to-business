package ModuleTransaction.Orgcom;


import ModuleTransaction.Orgcom.hashing.HashUtils;
import ModuleTransaction.Orgcom.hashing.UnHashableException;


/**
 * The BasicTransactionLine class provides the functionality of a basic transaction line.
 */
public class BasicTransactionLine implements TransactionLine {

    private final String itemDescription;
    private final int quantity;
    private final double unitPrice;

    /**
     * Creates a new transaction line.
     *
     * @param itemDescription Item in the transaction line.
     * @param quantity        Quantity of the item in transaction line.
     * @param unitPrice       Unit Price of the item in the transaction line.
     * @throws IllegalArgumentException If any of the arguments are invalid (item description is null or empty, quantity is less or equal than 0 and unitPrice is negative).
     */
    public BasicTransactionLine(String itemDescription, int quantity, double unitPrice) {
        if (itemDescription == null || itemDescription.isEmpty()) {
            throw new IllegalArgumentException("Item cannot be null or empty.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative.");
        }

        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getItemDescription() {
        return this.itemDescription;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getUnitPrice() {
        return this.unitPrice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getTotalPrice() {
        return this.unitPrice * this.quantity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHash() {
        return HashUtils.getHash(this.itemDescription + this.quantity + this.unitPrice);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TransactionLine{");
        sb.append("item=").append(this.itemDescription);
        sb.append(", quantity=").append(this.quantity);
        sb.append(", Unit price=").append(this.unitPrice);
        try {
            sb.append(", hash=").append(this.getHash());
        } catch (UnHashableException e) {
            sb.append(", hash=").append(e.getMessage());
        }
        return sb.append('}').toString();
    }

}

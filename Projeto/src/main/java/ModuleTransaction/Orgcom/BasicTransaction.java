package ModuleTransaction.Orgcom;

import ModuleTransaction.Orgcom.hashing.HashUtils;
import ModuleTransaction.Orgcom.hashing.UnHashableException;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The transaction class provides the functionality of a transaction.
 */
public class BasicTransaction implements Transaction {

    private final ArrayList<TransactionLine> transactionLines = new ArrayList<>();

    private final LocalDate dateCreated;
    private final Entity sender;
    private final Entity receiver;

    private double totalValue = 0;
    private int transactionCounter = 0;

    /**
     * Constructor for Transaction.
     * The transaction is created with the date of creation, the sender and the receiver.
     *
     * @param sender   the sender of the transaction.
     * @param receiver the receiver of the transaction.
     * @throws IllegalArgumentException if the sender or receiver is null.
     */
    public BasicTransaction(Entity sender, Entity receiver) {
        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("Sender and receiver cannot be null.");
        }

        this.sender = sender;
        this.receiver = receiver;
        this.dateCreated = LocalDate.now();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDate getDateCreated() {
        return this.dateCreated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getSender() {
        return this.sender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getReceiver() {
        return this.receiver;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getTotalValue() {
        return this.totalValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTransactionCounter() {
        return this.transactionCounter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addTransactionLine(TransactionLine transactionLine) {

        if (transactionLine == null) {
            throw new IllegalArgumentException("Transaction line cannot be null.");
        }

        if (this.getTransactionLine(transactionLine) != null) {
            return false;
        }

        this.transactionLines.add(transactionLine);
        this.totalValue += transactionLine.getTotalPrice();
        this.transactionCounter++;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeTransactionLine(TransactionLine transactionLine) {

        if (transactionLine == null) {
            throw new IllegalArgumentException("Transaction line cannot be null.");
        }

        if (this.getTransactionLine(transactionLine) == null) {
            return false;
        }

        this.transactionLines.remove(transactionLine);
        this.totalValue -= transactionLine.getTotalPrice();
        this.transactionCounter--;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print() {
        System.out.println("  hash: " + HashUtils.getSmallHash(this.getHash()) + " value: " + this.getTotalValue() + " sender: " + this.getSender().getName() + " receiver: " + this.getReceiver().getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactionLine getTransactionLine(TransactionLine transactionLine) {
        if (transactionLine == null) {
            throw new UnHashableException ("Transaction line cannot be null.");
        }

        for (TransactionLine transactionLineTemp : this.transactionLines) {
            if (transactionLine.getHash().equals(transactionLineTemp.getHash())) {
                return transactionLine;
            }
        }

        return null;
    }

    /**
     * Returns an iterator over elements of type {@link TransactionLine transaction line}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<TransactionLine> iterator() {
        return this.transactionLines.iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHash() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.sender.getUUID());
        sb.append(this.receiver.getUUID());
        sb.append(this.dateCreated.toString());
        sb.append(this.totalValue);
        for (TransactionLine transactionLine : this.transactionLines) {
            sb.append(transactionLine.getHash());
        }
        return HashUtils.getHash(sb.toString());
    }

    /**
     * Returns the string representation of the transaction
     *
     * @return the string representation of the transaction
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nTransaction{");
        sb.append("dateModified=").append(this.dateCreated);
        sb.append(", totalValue=").append(this.totalValue);
        sb.append(", transactionLines=").append(this.transactionLines);
        try {
            sb.append(", hash=").append(this.getHash()).append('}');
        } catch (UnHashableException e) {
            sb.append(", hash=").append(e.getMessage()).append('}');
        }
        return sb.toString();
    }

}

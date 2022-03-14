package ModuleTransaction.Orgcom;

import ModuleTransaction.Orgcom.hashing.Hashable;
import ModuleTransaction.Orgcom.hashing.UnHashableException;


import java.time.LocalDate;

/**
 * The Transaction interface provides the methods for a transaction.
 */
public interface Transaction extends Hashable, Iterable<TransactionLine> {

    /**
     * Return the {@link TransactionLine transaction line} in the transaction that have the same hash.
     *
     * @param transactionLine the {@link TransactionLine transaction line} to be checked.
     * @return the {@link TransactionLine transaction line} in the transaction that have the same hash, null if not found.
     * @throws UnHashableException if the transactionLine are not hashable.
     */
    TransactionLine getTransactionLine(TransactionLine transactionLine);

    /**
     * Returns the date of creation of the transaction.
     *
     * @return the date of creation of the transaction.
     */
    LocalDate getDateCreated();

    /**
     * Returns the sender of the transaction.
     *
     * @return the sender of the transaction.
     */
    Entity getSender();

    /**
     * Returns the receiver of the transaction.
     *
     * @return the receiver of the transaction.
     */
    Entity getReceiver();

    /**
     * Returns the total value of the transaction.
     *
     * @return the total value of the transaction.
     */
    double getTotalValue();

    /**
     * Return the number of transaction lines in the transaction.
     *
     * @return the number of transaction lines in the transaction.
     */
    int getTransactionCounter();

    /**
     * Adds a {@link TransactionLine transaction line} to the transaction.
     * When the {@link TransactionLine transaction line} is added to the transaction, the total value and the transaction counter are updated.
     *
     * @param transactionLine the {@link TransactionLine transaction line} to be added.
     * @return true if the transaction line was added, false if the transaction line was already added.
     * @throws IllegalArgumentException if the transaction line is null.
     */
    boolean addTransactionLine(TransactionLine transactionLine);

    /**
     * Removes a {@link TransactionLine transaction line} from the transactions.
     * The {@link TransactionLine transaction line} is removed from the transaction, the total value and the transaction counter are updated.
     *
     * @param transactionLine the {@link TransactionLine transaction line} to be removed.
     * @return true if the transaction line was removed, false if the transaction line was not found.
     * @throws IllegalArgumentException if the transaction line is null.
     */
    boolean removeTransactionLine(TransactionLine transactionLine);

    /**
     * Prints the transaction to the console.
     */
    void print();

    String getHash();
}

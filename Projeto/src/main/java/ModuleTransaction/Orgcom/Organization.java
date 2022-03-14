package ModuleTransaction.Orgcom;

import java.util.Iterator;

/**
 * The Organization interface provides the methods for an organization.
 */
public interface Organization {

    /**
     * Adds a new {@link Transaction transaction} to the ledger.
     *
     * @param transaction the {@link Transaction transaction} to add.
     * @return true if the {@link Transaction transaction} was added to the ledger, false if already in the ledger.
     * @throws IllegalArgumentException if the {@link Transaction transaction} is null.
     */
    boolean addTransaction(Transaction transaction);

    /**
     * Removes the {@link Transaction transaction} from the ledger.
     *
     * @param transaction the {@link Transaction transaction} to remove.
     * @return true if the {@link Transaction transaction} was removed from the ledger, false if not in the ledger.
     * @throws IllegalArgumentException if the {@link Transaction transaction} is null.
     */
    boolean removeTransaction(Transaction transaction);

    /**
     * Returns the {@link Transaction transaction} in the transaction list with the same hash as the given {@link Transaction transaction}.
     *
     * @param transaction {@link Transaction transaction} wish hash must be equal.
     * @return the {@link Transaction transaction} with the same given hash,null if not found.
     * @throws IllegalArgumentException if the {@link Transaction transaction} is null.
     */
    Transaction getTransaction(Transaction transaction);

    /**
     * Return the {@link Block nlock} with the given index in the ledger.
     *
     * @param index the index of the {@link Block nlock} to return.
     * @return the {@link Block nlock} with the given index in the ledger.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    Block getBlock(int index);

    /**
     * Returns the number of {@link Block block} in the ledger.
     *
     * @return the number of {@link Block block} in the ledger.
     */
    int getBlockCount();

    /**
     * Return the last {@link Block block} in the ledger.
     *
     * @return the last {@link Block block} in the ledger.
     */
    Block getLastBlock();

    /**
     * Add all possible {@link Transaction transaction} to the ledger.
     * The {@link Entity sender} needs to have a {@link Entity#getTokens() token} token to send a {@link Transaction transaction}.
     * If the {@link Entity sender} does not have a token, the {@link Transaction transaction} is not added to a {@link Block block} to be added the ledger.
     * All payable {@link Transaction transaction} are added to a {@link Block block}.
     * The {@link Entity sender} {@link Entity#spendToken() pay a token} for each {@link Transaction transaction}.
     * If any {@link Transaction transaction} is added to the {@link Block block}, the {@link Block block} is added to the ledger.
     *
     * @return the number of {@link Transaction transaction} added to the new {@link Block block}.
     */
    int registerTransactionsInLedger();

    /**
     * Verifies if the ledger was not tampered.
     * Verifies if the {@link Block blocks} are {@link Block#wasTampered() block} and if the blocks {@link Block#getPreviousHash() previous hash} on the ledger are consistent.
     *
     * @return true if the ledger is valid, false otherwise.
     */
    boolean isValidLedger();

    /**
     * Returns the iterator of the ledger.
     *
     * @return the iterator of the ledger.
     */
    Iterator<Block> ledgerIterator();

    /**
     * Prints the ledger.
     */
    void printLedger();

    /**
     * Returns the iterator of the {@link Transaction transaction} in the ledger.
     *
     * @return the iterator of the {@link Transaction transaction} in the ledger.
     */
    Iterator<Transaction> transactionIterator();

}

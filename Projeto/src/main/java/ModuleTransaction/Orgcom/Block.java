package ModuleTransaction.Orgcom;



import ModuleTransaction.Orgcom.hashing.Hashable;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * The Block interface provides the methods for a block in the {@link Organization Organization} ledger.
 */
public interface Block extends Hashable {

    /**
     * Returns the creation date of the block.
     *
     * @return the creation date of the block.
     */
    LocalDate getDate();

    /**
     * Returns the creation hash of the block.
     * This creation hash is used to verify the integrity of the block.
     *
     * @return the creation hash of the block.
     */
    String getCreationHash();

    /**
     * Returns the hash of the previous block.
     * This hash is used to verify the integrity of the ledger.
     *
     * @return the hash of the previous block.
     */
    String getPreviousHash();

    /**
     * Returns the number of {@link Transaction transactions} in the block.
     *
     * @return the number of {@link Transaction transactions} in the block.
     */
    int getNumberOfTransactions();

    /**
     * Returns an iterator over the {@link Transaction transactions} in the block.
     *
     * @return an Iterator over the {@link Transaction transactions} in the block.
     */
    Iterator<Transaction> getTransactions();

    /**
     * Prints the block to the console.
     */
    void print();

    /**
     * Returns a boolean indicating whether the block is valid, i.e., the
     * {@link Block#getHash() current hash} is equal to the {@link Block#getPreviousHash() creation hash}.
     *
     * @return a boolean indicating whether the block is valid.
     */
    boolean wasTampered();

}

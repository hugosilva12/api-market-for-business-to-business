package ModuleTransaction.Orgcom;

import ModuleTransaction.Orgcom.hashing.HashUtils;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * the BasicBlock class provides the functionality of a basic block in the system.
 */
public class BasicBlock implements Block {

    private final ArrayList<Transaction> transactions;
    private final LocalDate dateCreated = LocalDate.now();
    private final String previousHash;
    private final String creationHash;

    public BasicBlock(ArrayList<Transaction> transactions, String previousHash) {
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.creationHash = this.getHash();
    }

    @Override
    public String getPreviousHash() {
        return this.previousHash;
    }

    @Override
    public int getNumberOfTransactions() {
        return this.transactions.size();
    }

    @Override
    public Iterator<Transaction> getTransactions() {
        return this.transactions.iterator();
    }

    @Override
    public void print() {
        System.out.println("\nBLOCK: " + HashUtils.getSmallHash(this.getCreationHash()) + " {Date:" + this.getDate() + "}");
        System.out.println("Previous:" + HashUtils.getSmallHash(this.getPreviousHash()));
        System.out.println("Valid:" + !this.wasTampered() + " {Current Hash:" + HashUtils.getSmallHash(this.getHash()) + "}");
        System.out.println("Transactions:");
        for (Transaction transaction : this.transactions) {
            transaction.print();
        }
    }

    @Override
    public LocalDate getDate() {
        return this.dateCreated;
    }

    @Override
    public String getCreationHash() {
        return this.creationHash;
    }

    @Override
    public boolean wasTampered() {
        return !this.getHash().equals(this.creationHash);
    }

    @Override
    public String getHash() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.dateCreated);
        sb.append(this.previousHash);
        for (Transaction transaction : this.transactions) {
            sb.append(transaction.getHash());
        }
        return HashUtils.getHash(sb.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        return this.getCreationHash().equals(((BasicBlock) other).getCreationHash());
    }

    /**
     * Returns a string representation of the entity.
     *
     * @return a string representation of the entity.
     */
    @Override
    public String toString() {
        return "\nBlock{" +
                "transactions=" + this.transactions +
                ", dateCreated=" + this.dateCreated +
                ", previousHash='" + this.previousHash + '\'' +
                ", hash='" + this.creationHash + '\'' +
                '}';
    }
}

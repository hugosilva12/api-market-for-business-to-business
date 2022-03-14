package ModuleTransaction.Orgcom.hashing;

public interface Hashable {
    /**
     * Returns a hash code value for the class.
     *
     * @return hash code value for the class.
     * @throws UnHashableException if HashUtils cannot provide a hash for the class.
     */
    String getHash();
}

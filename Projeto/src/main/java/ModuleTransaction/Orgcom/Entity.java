package ModuleTransaction.Orgcom;

/**
 * The entity interface provides the functionality of an entity in the system.
 * The entities have a UUID (universally unique identifier), a name, a district and tokens.
 * The UUID is used to identify the entity in the system.
 * The name is the entity name. The name should not be null or Empty.
 * The district is the entity district. The district is a value from the {@link District District} enumeration.
 * The tokens are the entity tokens. The tokens are a value that allows the entity to process the transactions in the {@link Organization organization} ledger. An entity cannot have negative tokens. The default number of tokens is 0.
 */
public interface Entity {

    /**
     * Returns the UUID of the entity.
     *
     * @return the UUID of the entity.
     */
    String getUUID();

    /**
     * Returns the name of the entity.
     *
     * @return the name of the entity.
     */
    String getName();

    /**
     * Returns the district of the entity.
     *
     * @return the district of the entity.
     */
    String getDistrict();

    /**
     * Returns the amount of tokens the entity has.
     *
     * @return the amount of tokens the entity has.
     */
    int getTokens();

    /**
     * Adds to the tokens of the entity.
     *
     * @param tokens the number of tokens to add.
     * @return the resulting number of tokens.
     * @throws IllegalArgumentException if the number of tokens is negative.
     */
    int addTokens(int tokens);

    /**
     * Decrement by one the tokens of the entity.
     *
     * @throws IllegalStateException if the resulting number of tokens would result in a negative number of tokens.
     */
    void spendToken();
}

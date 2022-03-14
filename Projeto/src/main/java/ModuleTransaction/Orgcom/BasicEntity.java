package ModuleTransaction.Orgcom;

import java.util.UUID;

/**
 * the BasicEntity class provides the functionality of a basic entity in the system.
 */
public class BasicEntity implements Entity {

    private final String uuid;
    private final String name;
    private final District district;
    private int tokens;

    /**
     * Creates a new Entity with the given name and district.
     * The UUID is generated automatically.
     * The tokens are set to 0.
     *
     * @param name     the name of the entity
     * @param district the district of the entity
     * @throws IllegalArgumentException if the name is null or empty or the district is null
     */
    public BasicEntity(String name, District district) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if (district == null) {
            throw new IllegalArgumentException("District cannot be null");
        }

        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.district = district;
        this.tokens = 0;
    }

    @Override
    public String getUUID() {
        return this.uuid;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDistrict() {
        return this.district.toString();
    }

    @Override
    public int getTokens() {
        return this.tokens;
    }

    @Override
    public int addTokens(int tokens) {
        if (tokens < 0) {
            throw new IllegalArgumentException("Cannot add negative tokens");
        }

        this.tokens += tokens;
        return this.tokens;
    }

    @Override
    public void spendToken() {
        if ((this.tokens - 1) < 0) {
            throw new IllegalStateException("Cannot spend tokens when there are none");
        }

        this.tokens--;
    }

    /**
     * Returns a string representation of the entity.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entity{");
        sb.append("uuid=").append(this.uuid);
        sb.append(", name=").append(this.name);
        sb.append(", district=").append(this.district);
        sb.append(", tokens=").append(this.tokens).append('}');
        return sb.toString();
    }

}

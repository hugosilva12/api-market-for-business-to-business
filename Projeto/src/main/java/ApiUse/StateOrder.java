package ApiUse;

/**
 * The StateOrder enum provides the state of Order
 */
public enum StateOrder {
    AGUARDA_REGISTO("AGUARDA_REGISTO"),
    REGISTADA("REGISTADA");


    private final String label;

    private StateOrder(String label) {
        this.label = label;
    }

    /**
     * Returns a string representation of the order status .
     *
     * @return the label for the order status
     */
    @Override
    public String toString() {
        return this.label;
    }


}

package ModuleTransaction.Orgcom;

/**
 * The District enum provides the options for the entity districts..
 */
public enum District {
    VIANA_CASTELO("Viana do Castelo"),
    BRAGA("Braga"),
    VILA_REAL("Vila Real"),
    BRAGANCA("Bragança"),
    PORTO("Porto"),
    VISEU("Viseu"),
    AVEIRO("Aveiro"),
    GUARDA("Guarda"),
    COIMBRA("Coimbra"),
    CASTELO_BRANCO("Castelo Branco"),
    LEIRIA("Leiria"),
    LISBOA("Lisboa"),
    SANTAREM("Santarém"),
    PORTALEGRE("Portalegre"),
    SETUBAL("Setúbal"),
    EVORA("Évora"),
    BEJA("Beja"),
    FARO("Faro");

    private final String label;

    private District(String label) {
        this.label = label;
    }

    /**
     * Returns a string representation of the district.
     *
     * @return the label for the district.
     */
    @Override
    public String toString() {
        return this.label;
    }
}

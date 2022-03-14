package TestsImportTablekms;

import JsonOperations.ImportConfigsTruck;
import JsonOperations.ImportDistanceTableKM;
import ModuleCost.IDistanceTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class performs tests of the ImportDistanceTableKM class
 */
public class TestImportDistanceTableKM {

    private ImportDistanceTableKM importDistanceTableKM;

    @BeforeEach
    void setup() {
        this.importDistanceTableKM = new ImportDistanceTableKM();
    }

    @Test
    public void testImportOrdersBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> this.importDistanceTableKM.importCostsTableKM(""));
    }

    @Test
    public void testImportOrdersBVA02() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> this.importDistanceTableKM.importCostsTableKM(null));
    }

    @Test
    public void testImportOrdersECP01() {
        assertNull(this.importDistanceTableKM.importCostsTableKM("files/DistanceCostsTable.json"), "\"\" Should be null");
    }


    @Test
    public void testImportOrdersECP02() {
        assertTrue(this.importDistanceTableKM.importCostsTableKM("filesJson/DistanceCostsTable.json") instanceof IDistanceTable, "\"\" Should be true");
    }

    @Test
    public void testImportConfigCapacityBVA01() {
        ImportConfigsTruck importConfigsTruck = new ImportConfigsTruck();
        assertThrows(IllegalArgumentException.class, () -> importConfigsTruck.importConfigCapacity(null));
    }

    @Test
    public void testImportConfigCapacityECP01() {
        ImportConfigsTruck importConfigsTruck = new ImportConfigsTruck();
        assertEquals(63.00, importConfigsTruck.importConfigCapacity("file/naoexiste.txt"));
    }

}

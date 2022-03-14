package TestsImportTablekms;

import JsonOperations.ImportDistanceTableKM;
import ModuleCost.DistanceLine;
import ModuleCost.DistanceTable;
import ModuleCost.DistrictDistance;
import ModuleTransaction.Orgcom.BasicEntity;
import ModuleTransaction.Orgcom.District;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class performs tests of the DistanceTable class
 */
public class TestDistanceTable {

    private DistanceTable distanceTable;
    private DistrictDistance basicDistrictDistance;
    private DistanceLine basicDistance;
    private BasicEntity senderEntity;
    private BasicEntity receiverEntity;
    private ImportDistanceTableKM importDistanceTableKM;

    @BeforeEach
    void setup() {
        this.distanceTable = new DistanceTable();
        this.basicDistrictDistance = new DistrictDistance("Porto");
        this.basicDistance = new DistanceLine("Braga", 40);
        this.senderEntity = new BasicEntity("Sender", District.PORTO);
        this.receiverEntity = new BasicEntity("Receiver", District.BRAGA);
        this.importDistanceTableKM = new ImportDistanceTableKM();
    }

    //Test getDistrictDistance
    @Test
    public void testGetDistrictDistanceBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> distanceTable.getDistrictDistance(null));
    }

    @Test
    public void testGetDistrictDistanceECP01() {
        distanceTable.addDistrictDistance(basicDistrictDistance);
        assertTrue(distanceTable.getDistrictDistance(basicDistrictDistance) instanceof DistrictDistance, "\"\" Should be true");
    }

    @Test
    public void testGetDistrictDistanceECP02() {
        assertNull(distanceTable.getDistrictDistance(basicDistrictDistance), "\"\" Should be null");
    }

    //Test addDistrictDistance
    @Test
    public void testAddDistrictDistanceBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> distanceTable.addDistrictDistance(null));
    }

    @Test
    public void testAddDistrictDistanceECP01() {
        assertTrue(distanceTable.addDistrictDistance(basicDistrictDistance), "\"\" Should be true");
    }

    @Test
    public void testAddDistrictDistanceECP02() {
        distanceTable.addDistrictDistance(basicDistrictDistance);
        assertFalse(distanceTable.addDistrictDistance(basicDistrictDistance), "\"\" Should be false");
    }

    //Test getDistanceValueFromShipping
    @Test
    public void testGetDistanceValueFromShippingBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> distanceTable.getDistanceValueFromShipping(null, receiverEntity));
    }

    @Test
    public void testGetDistanceValueFromShippingBVA02() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> distanceTable.getDistanceValueFromShipping(senderEntity, null));
    }

    @Test
    public void testGetDistanceValueFromShippingECP01() {
        distanceTable = (DistanceTable) this.importDistanceTableKM.importCostsTableKM("filesJson/DistanceCostsTable.json");
        int expected = 55;
        assertEquals(expected, distanceTable.getDistanceValueFromShipping(senderEntity, receiverEntity), "\"\" Should be 55.");
    }

    @Test
    public void testGetDistanceValueFromShippingECP02() {
        distanceTable = (DistanceTable) importDistanceTableKM.importCostsTableKM("filesJson/Tabela_Custos_Kms_Test.json");
        int expected = -1;
        assertEquals(expected, distanceTable.getDistanceValueFromShipping(senderEntity, receiverEntity), "\"\" Should be -1.");
    }

}

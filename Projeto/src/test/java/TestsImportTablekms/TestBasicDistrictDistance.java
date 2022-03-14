package TestsImportTablekms;

import ModuleCost.DistanceLine;
import ModuleCost.DistrictDistance;
import ModuleCost.IDistrictDistance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class performs tests of the DistrictDistance class
 */
public class TestBasicDistrictDistance {
    private DistanceLine basicDistance;
    private IDistrictDistance basicIDistrictDistance;

    @BeforeEach
    void setup() {
        this.basicDistance = new DistanceLine("Porto", 40);
        this.basicIDistrictDistance = new DistrictDistance("Porto");
    }

    //Test Constuctor BasicDistrictDistance
    @Test
    public void testDistrictDistanceBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new DistrictDistance(""));
    }


    @Test
    public void testDistrictDistanceECP01() {
        assertTrue(basicIDistrictDistance instanceof DistrictDistance, "\"\" Should be true");
    }

    //Test getDistance
    @Test
    public void testGetDistanceBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> basicIDistrictDistance.getDistance(null));
    }

    @Test
    public void testGetDistanceECP01() {
        basicIDistrictDistance.addDistance(basicDistance);
        assertTrue(basicIDistrictDistance.getDistance(basicDistance) instanceof DistanceLine, "\"\" Should be true");
    }

    @Test
    public void testGetDistanceECP02() {
        assertNull(basicIDistrictDistance.getDistance(basicDistance), "\"\" Should be null");
    }

    //Test addDistance
    @Test
    public void testAddDistanceBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> basicIDistrictDistance.addDistance(null));
    }

    @Test
    public void testAddDistanceECP01() {
        assertTrue(basicIDistrictDistance.addDistance(basicDistance), "\"\" Should be true");
    }

    @Test
    public void testAddDistanceECP02() {
        basicIDistrictDistance.addDistance(basicDistance);
        assertFalse(basicIDistrictDistance.addDistance(basicDistance), "\"\" Should be false");
    }
}

package TestsImportTablekms;

import ModuleCost.DistanceLine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class performs tests of the Distance class
 */
public class TestDistance {

    //Costrutor BasicDistance
    @Test
    public void testDistanceLineBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new DistanceLine("Porto", -1));
    }

    @Test
    public void testDistanceLineBVA02() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new DistanceLine("", 30));
    }

    @Test
    public void testDistanceLineECP01() {
        DistanceLine basicDistance = new DistanceLine("Porto", 30);
        assertTrue(basicDistance instanceof DistanceLine,"\"\" should be true");
    }
}

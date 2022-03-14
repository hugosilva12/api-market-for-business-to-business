package TestsModuleExpedition;

import ApiUse.IOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * This class performs tests of the GroupAllTrucks class
 */
public class TestGroupAllTrucks {

    private IDistrictTrucks iDistrictExpedition;
    private GroupAllTrucks groupAllTrucks;
    private ArrayList<IOrder> ordersByDistrictBeja;

    @BeforeEach
    void setup() {
        this.ordersByDistrictBeja = new ArrayList<>();
        this.iDistrictExpedition = new DistrictTrucks("Porto", 4, ordersByDistrictBeja);
        this.groupAllTrucks = new GroupAllTrucks();
    }

    @Test
    public void testAddDistrictTrucksBVA01() {
        assertThrows(IllegalArgumentException.class, () -> this.groupAllTrucks.addDistrictTrucks(null));
    }

    @Test
    public void testAddDistrictTrucksECP01() {
        assertTrue(this.groupAllTrucks.addDistrictTrucks(iDistrictExpedition), "should be true");
    }
}

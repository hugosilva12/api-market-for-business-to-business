package TestsModuleExpedition;

import ApiUse.IOrder;
import ApiUse.Order;
import ApiUse.Product;
import ModuleTransaction.Orgcom.BasicEntity;
import ModuleTransaction.Orgcom.District;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class performs tests of the DistrictTrucks class
 */
public class TestDistrictTrucks {

    private DistrictTrucks districtTrucks;
    private BasicEntity basicEntity1, basicEntity2;
    private Order basicOrder1, basicOrder2;
    private ArrayList<IOrder> ordersByDistrictBeja;
    private Product product;

    @BeforeEach
    void setUp() {

        this.basicEntity1 = new BasicEntity("Entidade1", District.BEJA);
        this.basicEntity2 = new BasicEntity("Entidade2", District.AVEIRO);

        this.basicOrder1 = new Order("20210101_01", "2021-2-2", basicEntity1, basicEntity2);
        this.basicOrder2 = new Order("20210101_02", "2021-2-2", basicEntity2, basicEntity1);
        this.product = new Product(1, "nome", "produto fixe", 25, 2, 31, 7);

        this.basicOrder1.addProduct(product);

        this.ordersByDistrictBeja = new ArrayList<IOrder>();
        this.ordersByDistrictBeja.add(basicOrder1);
        this.districtTrucks = new DistrictTrucks("Porto", 1, ordersByDistrictBeja);

    }

    @Test
    public void testGetTruckByIndexBVA01() {
        this.districtTrucks.groupOrders();
        assertThrows(IndexOutOfBoundsException.class, () -> districtTrucks.getTruckByIndex(ordersByDistrictBeja.size()));
    }

    @Test
    public void testGetTruckByIndexBVA02() {
        assertThrows(IndexOutOfBoundsException.class, () -> districtTrucks.getTruckByIndex(-1));
    }

    @Test
    public void testGetTruckByIndexECP01() {
        this.districtTrucks.groupOrders();
        assertTrue(districtTrucks.getTruckByIndex(0) instanceof ITruck, "should be true");
    }

    @Test
    public void testCreateTruckBVA01() {
        assertThrows(IllegalArgumentException.class, () -> districtTrucks.createTruck(0));
    }

    @Test
    public void testCreateTruckECP01() {
        districtTrucks.createTruck(2);
        int expected = 3;
        assertEquals(expected, districtTrucks.getSize(), "should be 3");
    }

    @Test
    public void testCreateTruckECP02() {
        assertThrows(IllegalArgumentException.class, () -> districtTrucks.createTruck(-1));
    }


    @Test
    public void testDistrictTrucksBVA01() {
        assertThrows(IllegalArgumentException.class, () -> new DistrictTrucks("Porto", 3, null));
    }

    @Test
    public void testDistrictTrucksBVA02() {
        assertThrows(IllegalArgumentException.class, () -> new DistrictTrucks("", 5, ordersByDistrictBeja));
    }

    @Test
    public void testDistrictTrucksBVA03() {
        assertThrows(IllegalArgumentException.class, () -> new DistrictTrucks(null, 5, ordersByDistrictBeja));
    }

    @Test
    public void testDistrictTrucksECP01() {
        DistrictTrucks districtTrucks  = new  DistrictTrucks("Porto", 4, ordersByDistrictBeja);
        assertTrue(districtTrucks instanceof DistrictTrucks,"should true");
    }
}


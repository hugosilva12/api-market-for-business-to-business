import ApiUse.IOrder;
import ApiUse.Order;
import ApiUse.Product;
import ModuleCost.DistanceTable;
import TestsModuleExpedition.DistrictTrucks;
import TestsModuleExpedition.IDistrictTrucks;
import ModuleTransaction.Orgcom.BasicEntity;
import ModuleTransaction.Orgcom.District;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class performs white box tests
 */
public class TestWhiteBox {
    private BasicEntity basicEntity1, basicEntity2, basicEntity4;
    private DistanceTable distanceTable;
    private Order basicOrder1, basicOrder2;
    private Product product, product2, product3, product4, product5, product6;
    private IDistrictTrucks districtTrucksBeja;
    private ArrayList<IOrder> ordersByDistrictPorto, ordersByDistrictBeja;

    @BeforeEach
    void setUp() {
        this.distanceTable = new DistanceTable();

        this.basicEntity1 = new BasicEntity("Entidade1", District.BEJA);
        this.basicEntity2 = new BasicEntity("Entidade2", District.AVEIRO);
        this.basicEntity4 = new BasicEntity("Entidade4", District.PORTO);


        this.basicOrder1 = new Order("20210101_01", "2021-2-2", basicEntity1, basicEntity2);
        this.basicOrder2 = new Order("20210101_02", "2021-2-2", basicEntity1, basicEntity4);


        this.product = new Product(1, "nome", "produto fixe", 25, 2, 31, 7);
        this.product2 = new Product(2, "nome", "produto top", 5.0, 1, 62.9, 4);
        this.product3 = new Product(3, "nome", "produto reacondiciona", 5.0, 6, 1, 4);
        this.product4 = new Product(4, "nome", "produto frágil", 2.0, 1, 2, 5);
        this.product5 = new Product(5, "nome", "produto informático", 2.0, 1, 0.12, 5);
        this.product6 = new Product(6, "nome", "produto unico", 2.0, 1, 62.999999, 5);

        this.ordersByDistrictPorto = new ArrayList<>();
        this.ordersByDistrictBeja = new ArrayList<>();
    }

    @Test
    public void testGetDistanceValueFromShippingWhiteBox01() {
        int expected = 0;
        assertEquals(expected, distanceTable.getDistanceValueFromShipping(basicEntity1, basicEntity1), "\"\" Should be 0.");
    }

    @Test
    public void testGroupOrdersByTrucksWhiteBox02() {
        basicOrder1.addProduct(product);
        basicOrder2.addProduct(product);
        basicOrder2.addProduct(product4);

        this.ordersByDistrictBeja.add(basicOrder2);
        this.ordersByDistrictBeja.add(basicOrder1);

        this.districtTrucksBeja = new DistrictTrucks("Beja", 2, ordersByDistrictBeja);
        this.districtTrucksBeja.groupOrders();

        int expectedTrucks = 3;
        assertEquals(expectedTrucks, districtTrucksBeja.getSize(), "should be 3");
    }
}

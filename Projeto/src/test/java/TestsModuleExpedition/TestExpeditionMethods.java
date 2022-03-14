package TestsModuleExpedition;

import ApiUse.IOrder;
import ApiUse.Order;
import ApiUse.Orders;
import ApiUse.Product;
import ModuleTransaction.Orgcom.BasicEntity;
import ModuleTransaction.Orgcom.District;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This class performs tests of the ExpeditionMethods class
 */
public class TestExpeditionMethods {
    private Orders basicOrders;
    private Order basicOrder1, basicOrder2, basicOrder3, basicOrder4;
    private BasicEntity basicEntity1, basicEntity2, basicEntity3, basicEntity4;
    private Product product, product2, product3, product4, product5, product6;
    private ExpeditionMethods expedition;
    private IGroupAllTrucks groupAllTrucksExpected;
    private IDistrictTrucks districtTrucksPorto, districtTrucksBeja;
    private JSONArray jsonArrayExpected;
    private ArrayList<IOrder> ordersByDistrictPorto, ordersByDistrictBeja;

    @BeforeEach
    void setUp() {

        this.basicEntity1 = new BasicEntity("Entidade1", District.BEJA);
        this.basicEntity2 = new BasicEntity("Entidade2", District.AVEIRO);
        this.basicEntity3 = new BasicEntity("Entidade3", District.BRAGA);
        this.basicEntity4 = new BasicEntity("Entidade4", District.PORTO);

        this.basicOrders = new Orders();
        this.basicOrder1 = new Order("20210101_01", "2021-2-2", basicEntity1, basicEntity2);
        this.basicOrder2 = new Order("20210101_02", "2021-2-2", basicEntity1, basicEntity4);
        this.basicOrder3 = new Order("3", "2021-2-2", basicEntity3, basicEntity3);
        this.basicOrder4 = new Order("3", "2021-2-2", basicEntity4, basicEntity1);

        this.product = new Product(1, "nome", "produto fixe", 25, 2, 31, 7);
        this.product2 = new Product(2, "nome", "produto top", 5.0, 1, 62.9, 4);
        this.product3 = new Product(3, "nome", "produto reacondiciona", 5.0, 6, 1, 4);
        this.product4 = new Product(4, "nome", "produto frágil", 2.0, 1, 2, 5);
        this.product5 = new Product(5, "nome", "produto informático", 2.0, 1, 0.12, 5);
        this.product6 = new Product(6, "nome", "produto unico", 2.0, 1, 62.999999, 5);

        this.expedition = new ExpeditionMethods();
        this.groupAllTrucksExpected = new GroupAllTrucks();

        this.ordersByDistrictPorto = new ArrayList<>();
        this.ordersByDistrictBeja = new ArrayList<>();
        this.jsonArrayExpected = new JSONArray();


        this.groupAllTrucksExpected = new GroupAllTrucks();
    }


    @Test
    void testGetArrayOrdersByDistrictBV01() {
        assertThrows(IllegalArgumentException.class, () -> expedition.getArrayOrdersByDistrict("Porto", null));
    }

    @Test
    void testGetArrayOrdersByDistrictBV02() {
        assertThrows(IllegalArgumentException.class, () -> expedition.getArrayOrdersByDistrict("", basicOrders.getBasicOrders()));
    }

    @Test
    void testGetArrayOrdersByDistrictBV03() {
        assertThrows(IllegalArgumentException.class, () -> expedition.getArrayOrdersByDistrict(null, basicOrders.getBasicOrders()));
    }
    //Falta ECP01 DE CIMA

    @Test
    public void testGetArrayOrdersByDistrictECP01() {
        basicOrder4.addProduct(product5);
        basicOrder4.addProduct(product6);

        this.ordersByDistrictPorto.add(basicOrder4);

        this.districtTrucksPorto = new DistrictTrucks("Porto", 1, ordersByDistrictPorto);

        assertEquals(ordersByDistrictPorto, expedition.getArrayOrdersByDistrict("Porto", ordersByDistrictPorto), "should be 2");
    }

    @Test
    public void testGroupOrdersByTrucksBVA01() {
        assertThrows(IllegalArgumentException.class, () -> expedition.groupOrdersByTrucks(null));
    }

    @Test
    public void testGroupOrdersByTrucksECP01() {

        basicOrder1.addProduct(product);
        basicOrder1.addProduct(product2);

        basicOrder2.addProduct(product3);
        basicOrder2.addProduct(product4);
        basicOrder2.addProduct(product5);

        basicOrder4.addProduct(product5);
        basicOrder4.addProduct(product6);

        basicOrders.addOrder(basicOrder1);
        basicOrders.addOrder(basicOrder2);
        basicOrders.addOrder(basicOrder4);

        this.ordersByDistrictPorto.add(basicOrder4);

        this.ordersByDistrictBeja.add(basicOrder1);
        this.ordersByDistrictBeja.add(basicOrder2);

        //Instanciar metodo compare
        this.districtTrucksPorto = new DistrictTrucks("Porto", 2, ordersByDistrictPorto);
        this.districtTrucksBeja = new DistrictTrucks("Beja", 3, ordersByDistrictBeja);

        this.groupAllTrucksExpected.addDistrictTrucks(districtTrucksBeja);
        this.groupAllTrucksExpected.addDistrictTrucks(districtTrucksPorto);

        assertEquals(groupAllTrucksExpected.getDistrictExpeditionJSONFormat(), expedition.groupOrdersByTrucks(basicOrders.getBasicOrders()), "should be true");

    }


    @Test
    public void testGroupOrdersByTrucksECP02() {
        basicOrder3.addProduct(product);
        basicOrder3.addProduct(product);
        basicOrder3.addProduct(product3);
        basicOrders.addOrder(basicOrder3);
        JSONArray jsonArrayExpected = new JSONArray();
        assertEquals(jsonArrayExpected, expedition.groupOrdersByTrucks(basicOrders.getBasicOrders()), "should be true");
    }



    @Test
    public void testExportGroupOrdersForFileJsonECP01() {
        basicOrder1.addProduct(product);
        basicOrder1.addProduct(product2);

        basicOrder2.addProduct(product3);
        basicOrder2.addProduct(product4);
        basicOrder2.addProduct(product5);

        basicOrder4.addProduct(product5);
        basicOrder4.addProduct(product6);

        basicOrders.addOrder(basicOrder1);
        basicOrders.addOrder(basicOrder2);
        basicOrders.addOrder(basicOrder4);

        expedition.groupOrdersByTrucks(basicOrders.getBasicOrders());

        boolean expected = true;
        assertEquals(expected, expedition.exportGroupOrdersForFileJson(), "should be true");
    }

    @Test
    public void testExportGroupOrdersForFileJsonECP02() {
        basicOrder3.addProduct(product);
        basicOrders.addOrder(basicOrder3);

        expedition.groupOrdersByTrucks(basicOrders.getBasicOrders());

        boolean expected = false;
        assertEquals(expected, expedition.exportGroupOrdersForFileJson(), "should be false");
    }

}

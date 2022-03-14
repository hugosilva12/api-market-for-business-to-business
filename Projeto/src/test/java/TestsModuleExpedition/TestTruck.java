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
 * This class performs tests of the truck class
 */
public class TestTruck {

    private DistrictTrucks districtTrucks;
    private BasicEntity basicEntity1, basicEntity2;
    private Order basicOrder1;
    private ArrayList<IOrder> ordersByDistrictBeja;
    private Product product;
    private Truck truck;

    @BeforeEach
    void setUp() {

        this.basicEntity1 = new BasicEntity("Entidade1", District.BEJA);
        this.basicEntity2 = new BasicEntity("Entidade2", District.AVEIRO);

        this.basicOrder1 = new Order("20210101_01", "2021-2-2", basicEntity1, basicEntity2);

        this.product = new Product(1, "nome", "produto fixe", 25, 2, 31.55, 7);


        this.ordersByDistrictBeja = new ArrayList<IOrder>();
        this.ordersByDistrictBeja.add(basicOrder1);
        this.districtTrucks = new DistrictTrucks("Porto", 1, ordersByDistrictBeja);
        this.truck = new Truck("Cmiao");

    }

    @Test
    public void testTruckBVA01() {
        assertThrows(IllegalArgumentException.class, () -> new Truck(null));
    }

    @Test
    public void testTruckBVA02() {
        assertThrows(IllegalArgumentException.class, () -> new Truck(""));
    }

    @Test
    public void testTruckECP01() {
        Truck truck = new Truck("hUGO");
        assertTrue(truck instanceof Truck, "should be true");
    }

    @Test
    public void TestAddAllOrderInTruckBVA01() {
        assertThrows(IllegalArgumentException.class, () -> truck.addAllOrderInTruck(null));
    }

    @Test
    public void testAddAllOrderInTruckBVA02() {
        this.basicOrder1.addProduct(product);
        assertFalse(truck.addAllOrderInTruck(basicOrder1), "should be false");
    }

    @Test
    public void testAddAllOrderInTruckBVA03() {
        this.basicOrder1 = new Order("20210101_01", "2021-2-2", basicEntity1, basicEntity2);
        assertThrows(IllegalArgumentException.class, () -> truck.addAllOrderInTruck(basicOrder1));
    }

    @Test
    public void testAddAllOrderInTruckProductInTruckECP01() {
        this.product = new Product(1, "nome", "produto fixe", 25, 1, 45, 7);
        this.basicOrder1.addProduct(product);
        assertTrue(truck.addAllOrderInTruck(basicOrder1), "should be true");
    }

    @Test
    public void testAddProductInTruckBVA01() {
        assertFalse(truck.addProductInTruck(product.getName(), "23", product.getVolume() * 2), "should be false");
    }

    @Test
    public void testAddProductInTruckBVA02() {
        this.basicOrder1.addProduct(product);
        assertThrows(IllegalArgumentException.class, () -> truck.addProductInTruck(product.getName(), "23", 0.0));

    }

    @Test
    public void testAddProductInTruckBVA03() {
        this.basicOrder1.addProduct(product);
        assertThrows(IllegalArgumentException.class, () -> truck.addProductInTruck(null, "23", 5.0));

    }

    @Test
    public void testAddProductInTruckBVA04() {
        this.basicOrder1.addProduct(product);
        assertThrows(IllegalArgumentException.class, () -> truck.addProductInTruck("wsa", null, 5.0));

    }

    @Test
    public void testAddProductInTruckBVA05() {
        this.basicOrder1.addProduct(product);
        assertThrows(IllegalArgumentException.class, () -> truck.addProductInTruck("", "23", 5.0));

    }

    @Test
    public void testAddProductInTruckECP01() {
        this.basicOrder1.addProduct(product);
        assertTrue(truck.addProductInTruck(product.getName(), "23", 34.2), "should be true");
    }

    @Test
    public void testGetProductContainerTruckBVA01() {
        assertThrows(IllegalArgumentException.class, () -> truck.getProductContainer(null));
    }

    @Test
    public void testGetProductContainerTruckECP01() {
        truck.addProductInTruck(product.getName(), "23", 34.2);
        IProductsInsideTruck productsInsideTruck = new ProductsInsideTruck(product.getName(), "23", 1, 34.2);
        assertTrue(truck.getProductContainer(productsInsideTruck) instanceof ProductsInsideTruck, "should be true");
    }

    @Test
    public void testGetProductContainerTruckECP02() {
        truck.addProductInTruck(product.getName(), "23", 34.2);
        IProductsInsideTruck productsInsideTruck = new ProductsInsideTruck(product.getName(), "12", 1, 34.2);
        assertNull(truck.getProductContainer(productsInsideTruck), "should be Null");
    }
    @Test
    public void testGetProductListProductsInsideTruckBVA01() {
        assertThrows(IndexOutOfBoundsException.class, () ->     truck.getProductListProductsInsideTruck(-1));
    }
    @Test
    public void testGetProductListProductsInsideTruckBVA02() {
        assertThrows(IndexOutOfBoundsException.class, () ->     truck.getProductListProductsInsideTruck(truck.numberProductInContainer()));
    }

    @Test
    public void testGetProductListProductsInsideTruckECP01() {
        truck.addProductInTruck(product.getName(), "23", 34.2);
        assertTrue(truck.getProductListProductsInsideTruck(0) instanceof ProductsInsideTruck, "should be true");
    }



}

package TestApiUse;

import ApiUse.IOrder;
import ApiUse.Order;
import ApiUse.Product;
import ModuleTransaction.Orgcom.BasicEntity;
import ModuleTransaction.Orgcom.District;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class tests order methods
 */
public class TestOrder {
    private Order basicOrder;
    private BasicEntity senderEntity;
    private BasicEntity receiverEntity;
    private Product product1;

    @BeforeEach
    void setup() {
        this.senderEntity = new BasicEntity("Sender", District.VIANA_CASTELO);
        this.receiverEntity = new BasicEntity("Receiver", District.BRAGA);
        this.basicOrder = new Order("1234", "2021-01-01", senderEntity, receiverEntity);
        this.product1 = new Product(1, "Product1", "Description", 2, 1, 4.1, 5.2);
    }

    //Test Constructor BasicOrder
    @Test
    public void testBasicOrderBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new Order("", "2021-01-01", senderEntity, receiverEntity));
    }

    @Test
    public void testBasicOrderBVA02() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new Order("1234", "", senderEntity, receiverEntity));
    }

    @Test
    public void testBasicOrderBVA03() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new Order("1234", "2021-01-01", null, receiverEntity));
    }

    @Test
    public void testBasicOrderBVA04() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new Order("1234", "2021-01-01", senderEntity, null));
    }

    @Test
    public void testBasicOrderECP01() {
        assertTrue(basicOrder instanceof Order,"\"\" should be true");
    }

    //Test getProduct
    @Test
    public void testGetProductBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> basicOrder.getProduct(null));
    }

    @Test
    public void testGetProductECP01() {
        basicOrder.addProduct(product1);
        assertTrue(basicOrder.getProduct(product1) instanceof Product, "\"\" Should be true");
    }

    @Test
    public void testGetProductECP02() {
        assertNull(basicOrder.getProduct(product1), "\"\" Should be null");
    }

    //Test addProduct
    @Test
    public void testAddProductBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> basicOrder.addProduct(null));
    }

    @Test
    public void testAddProductECP01() {
        assertTrue(basicOrder.addProduct(product1), "\"\" Should be true");
    }

    @Test
    public void testAddProductECP02() {
        basicOrder.addProduct(product1);
        assertFalse(basicOrder.addProduct(product1), "\"\" Should be false");
    }

    @Test
    public void testShippingCostCalculationECP01() {
        this.basicOrder.addProduct(product1);
        double expected = 16.91;

        assertEquals(expected, this.basicOrder.getShippingCost(), "\"\" Should be 16.91");
    }

    @Test
    public void testShippingCostCalculationECP02() {
        IOrder order = new Order("Order2", "2021-12-20", senderEntity, senderEntity);
        order.addProduct(product1);
        double expected = 0;

        assertEquals(expected, order.getShippingCost(), "\"\" Should be 0");
    }

}

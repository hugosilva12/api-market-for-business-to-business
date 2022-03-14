package TestApiUse;

import ApiUse.*;
import ModuleTransaction.Orgcom.BasicEntity;
import ModuleTransaction.Orgcom.District;
import ModuleTransaction.Orgcom.hashing.UnHashableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests orders methods
 */
public class TestOrders {

    private Orders orders;
    private Order order;
    private BasicEntity senderEntity;
    private BasicEntity receiverEntity;
    private IProduct product, product2, product3, product4, product5;

    @BeforeEach
    void setup() {
        this.orders = new Orders();
        this.senderEntity = new BasicEntity("Sender", District.VIANA_CASTELO);
        this.receiverEntity = new BasicEntity("Receiver", District.BRAGA);
        this.order = new Order("1234", "2021-01-01", senderEntity, receiverEntity);
        this.product5 = new Product(1, "Product1", "Description", 2, 1, 4.1, 5.2);
        this.order.addProduct(product5);

        this.product = new Product(2, "nome", "produto fixe", 12.00, 2, 5.4, 7);
        this.product2 = new Product(3, "nome", "produto top", 12.00, 2, 6.4, 4);
        this.product3 = new Product(4, "nome", "produto reacondiciona", 12.00, 2, 7.4, 4);
        this.product4 = new Product(5, "nome", "produto frágil", 12.00, 2, 8.4, 5);
    }


    //Test getOrder
    @Test
    public void testGetOrderBVA01() throws UnHashableException {
        assertThrows(UnHashableException.class, () -> orders.getOrder(null));
    }

    @Test
    public void testGetOrderECP01() {
        orders.addOrder(order);
        assertTrue(orders.getOrder(order) instanceof IOrder, "\"\" Should be true");
    }

    @Test
    public void testGetOrderECP02() {
        assertNull(orders.getOrder(order), "\"\" Should be null");
    }

    //Test addOrder
    @Test
    public void testAddOrderBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> orders.addOrder(null));
    }

    @Test
    public void testAddOrderECP01() {
        assertTrue(orders.addOrder(order), "\"\" Should be true");
    }

    @Test
    public void testAddOrderECP02() {
        orders.addOrder(order);
        assertFalse(orders.addOrder(order), "\"\" Should be false");
    }

    @Test
    public void testRegisterOrdersInLedgerBVA01() {
        int expected = 0;
        assertEquals(expected, orders.registerOrdersInLedger(), "\"\"should be 0");
    }


    @Test
    public void testRegisterOrdersInLedgerBVA02() {
        senderEntity.addTokens(1);
        Order basicOrder = new Order("123_222", "2021-2-21", senderEntity, receiverEntity);
        basicOrder.addProduct(product);
        basicOrder.addProduct(product2);
        basicOrder.addProduct(product3);
        basicOrder.addProduct(product4);
        orders.addOrder(basicOrder);
        int expected = 2;
        assertEquals(expected, orders.registerOrdersInLedger(), "\"\"should be 2");
    }

    @Test
    public void testRegisterOrdersInLedgerBVA03() {
        Order basicOrder = new Order("123_222", "2021-2-21", senderEntity, receiverEntity);
        basicOrder.addProduct(product);
        basicOrder.addProduct(product2);
        basicOrder.addProduct(product3);
        basicOrder.addProduct(product4);
        orders.addOrder(basicOrder);
        int expected = 2;
        assertEquals(expected, orders.registerOrdersInLedger(), "\"\"should be 2");
    }

    @Test
    public void testRegisterOrdersInLedgerECP01() {
        senderEntity.addTokens(2);
        Order basicOrder = new Order("123_2", "2022-2-21", senderEntity, receiverEntity);
        basicOrder.addProduct(product);
        basicOrder.addProduct(product2);
        orders.addOrder(basicOrder);
        int expected = 2;
        assertEquals(expected, orders.registerOrdersInLedger(), "\"\"should be 2");
    }

    @Test
    public void testRegisterOrdersInLedgerECP02() { ///Fazer estado == fechado
        senderEntity.addTokens(2);
        Order basicOrder = new Order("123_2", "2022-2-21", senderEntity, receiverEntity);
        basicOrder.addProduct(product);
        basicOrder.addProduct(product2);
        orders.addOrder(basicOrder);

        int expected = 0;
        orders.registerOrdersInLedger();
        assertEquals(expected, orders.registerOrdersInLedger(), "should be 0");
    }

    @Test
    public void testGetOrderShippingCostBVA01() {
        assertThrows(IllegalArgumentException.class, () -> this.orders.getOrderShippingCost(null));
    }

    @Test
    public void testGetOrderShippingCostECP01() {
        double expected = 16.91;
        assertEquals(expected, this.orders.getOrderShippingCost(order), "\"\"should be 16.91");
    }

    @Test
    public void testGetOrderShippingCostECP02() {
        IOrder order2 = new Order("Order2", "2021-12-20", senderEntity, senderEntity);
        order2.addProduct(product5);
        double expected = 0;
        assertEquals(expected, this.orders.getOrderShippingCost(order2), "\"\"should be 0");
    }

    @Test
    public void testGetOrderIndexBVA01() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.orders.getOrderIndex(-1));
    }

    @Test
    public void testGetOrderIndexBVA02() {
        IOrder order2 = new Order("Order2", "2021-12-20", senderEntity, senderEntity);
        this.orders.addOrder(order2);
        assertThrows(IndexOutOfBoundsException.class, () -> this.orders.getOrderIndex(this.orders.getNumberOrder()));
    }

    @Test
    public void testGetOrderIndexECP01() {
        IOrder order2 = new Order("Order2", "2021-12-20", senderEntity, senderEntity);
        this.orders.addOrder(order2);
        assertTrue(this.orders.getOrderIndex(this.orders.getNumberOrder() - 1) instanceof IOrder, "should true");
    }
}


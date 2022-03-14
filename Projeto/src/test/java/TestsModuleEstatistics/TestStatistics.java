package TestsModuleEstatistics;

import ApiUse.IProduct;
import ApiUse.Order;
import ApiUse.Orders;
import ApiUse.Product;
import Exceptions.AverageException;
import ModuleTransaction.*;
import ModuleTransaction.Orgcom.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
/**
 * This class performs tests of the Statistics class
 */
public class TestStatistics {
    private Orders basicOrders;
    private Order basicOrder1, basicOrder2, basicOrder3, basicOrder4;
    private BasicEntity basicEntity1, basicEntity2, basicEntity3, basicEntity4;
    private IProduct product, product2, product3, product4, product5, product6;
    private Map<String, Map<String, Double>> expected;

    @BeforeEach
    void setUp() {

        this.basicEntity1 = new BasicEntity("Entidade1", District.BEJA);
        this.basicEntity2 = new BasicEntity("Entidade2", District.AVEIRO);
        this.basicEntity3 = new BasicEntity("Entidade3", District.BRAGA);
        this.basicEntity4 = new BasicEntity("Entidade4", District.PORTO);

        this.basicOrders = new Orders();
        this.basicOrder1 = new Order("1", "2021-2-2", basicEntity1, basicEntity2);
        this.basicOrder2 = new Order("2", "2021-2-2", basicEntity3, basicEntity4);
        this.basicOrder3 = new Order("3", "2021-2-2", basicEntity3, basicEntity4);
        this.basicOrder4 = new Order("3", "2021-2-2", basicEntity3, basicEntity4);

        this.product = new Product(1, "nome", "produto fixe", 25, 2, 5.4, 7);
        this.product2 = new Product(2, "nome", "produto top", 5.0, 1, 6.4, 4);
        this.product3 = new Product(3, "nome", "produto reacondiciona", 5.0, 2, 7.4, 4);
        this.product4 = new Product(4, "nome", "produto frágil", 2.0, 1, 8.4, 5);
        this.product5 = new Product(5, "nome", "produto informático", 2.0, 1, 8.4, 5);
        this.product6 = new Product(6, "nome", "produto unico", 2.0, 1, 8.4, 5);


        expected = new HashMap<String, Map<String, Double>>();
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("Venda", 6.67); //Braga  20/3
        map.put("Compra", 0.0); //
        expected.put("Braga", map);

        map = new HashMap<String, Double>();
        map.put("Venda", 0.0); //Porto 20/3
        map.put("Compra", 6.67);
        expected.put("Porto", map);

        map = new HashMap<String, Double>();
        map.put("Venda", 55.00);
        map.put("Compra", 0.0);
        expected.put("Beja", map);

        map = new HashMap<String, Double>();
        map.put("Compra", 55.00); //Porto
        map.put("Venda", 0.0);
        expected.put("Aveiro", map);
    }

    @Test
    public void testAverageNumberProductsTransactionBVA01() {
        assertThrows(AverageException.class, () -> basicOrders.averageValueTransactions());
    }

    @Test
    public void testAverageNumberProductsTransactionECP01() {
        this.product = new Product(1, "nome", "produto fixe", 2.0, 15, 5.4, 7);
        this.product2 = new Product(2, "nome", "produto top", 2.0, 15, 6.4, 4);
        this.product4 = new Product(4, "nome", "produto frágil", 2.0, 10, 10, 5);
        this.product5 = new Product(5, "nome", "produto informático", 2.0, 10, 8.4, 5);
        this.product6 = new Product(6, "nome", "produto unico", 2.0, 5, 8.4, 5);

        basicOrder1.addProduct(product);
        basicOrder1.addProduct(product2); //30

        basicOrder2.addProduct(product4);
        basicOrder2.addProduct(product5); //20
        basicOrder3.addProduct(product6); //5

        basicOrders.addOrder(basicOrder1);
        basicOrders.addOrder(basicOrder2);
        basicOrders.addOrder(basicOrder3);
        basicOrders.registerOrdersInLedger();

        double expected = 18.33;
        assertEquals(expected, basicOrders.averageNumberProductsTransaction(), "should be 18.33");

    }

    @Test
    public void testAverageValueTransactionsECP01() {
        this.product = new Product(1, "nome", "produto fixe", 2.0, 2, 5.4, 7);
        this.product2 = new Product(2, "nome", "produto top", 2.0, 2, 6.4, 4);
        this.product3 = new Product(3, "nome", "produto reacondiciona", 2.0, 2, 7.4, 4);

        basicOrder1.addProduct(product);
        basicOrder1.addProduct(product2);
        basicOrder1.addProduct(product3);

        basicOrder2.addProduct(product4);
        basicOrder2.addProduct(product5);
        basicOrder2.addProduct(product6);

        basicOrders.addOrder(basicOrder1);
        basicOrders.addOrder(basicOrder2);
        basicOrders.registerOrdersInLedger();
        basicOrders.addOrder(basicOrder1);
        basicOrders.registerOrdersInLedger();
        basicOrders.addOrder(basicOrder2);
        basicOrders.registerOrdersInLedger();

        int expected = 9;
        assertEquals(expected, basicOrders.averageValueTransactions(), "should be 9");
    }

    @Test
    public void testAverageValueSalesPurchasesDistrictBVA01() {
        assertThrows(AverageException.class, () -> basicOrders.averageValueSalesPurchasesDistrict());
    }

    @Test
    public void testAverageValueSalesPurchasesDistrictBVA02() {
        OrdersMetricStatistics basicOrdersMetricStatistics = new OrdersMetricStatistics();
        assertThrows(IllegalArgumentException.class, () -> basicOrdersMetricStatistics.averageValueSalesPurchasesDistrict(null));
    }

    @Test
    public void testAverageValueSalesPurchasesDistrictECP01() {

        basicOrder1.addProduct(product);
        basicOrder1.addProduct(product2);
        basicOrder2.addProduct(product2);
        basicOrder3.addProduct(product3);
        basicOrder4.addProduct(product2);

        basicOrders.addOrder(basicOrder1);
        basicOrders.addOrder(basicOrder2);
        basicOrders.addOrder(basicOrder3);
        basicOrders.addOrder(basicOrder4);

        basicOrders.registerOrdersInLedger();
        basicOrders.addOrder(basicOrder1);
        basicOrders.registerOrdersInLedger();

        assertEquals(expected, basicOrders.averageValueSalesPurchasesDistrict());
    }

    @Test
    void testAverageValueSalesPurchasesDistrictECP02() {

        this.basicOrder2 = new Order("2", "2021-2-2", basicEntity3, basicEntity4);
        this.basicOrder3 = new Order("3", "2021-2-2", basicEntity3, basicEntity3);

        this.basicOrder1.addProduct(product);
        this.basicOrder1.addProduct(product2);
        this.basicOrder2.addProduct(product2);
        this.basicOrder3.addProduct(product3);
        this.basicOrder4.addProduct(product2);

        this.basicOrders.addOrder(basicOrder1);
        this.basicOrders.addOrder(basicOrder2);
        this.basicOrders.addOrder(basicOrder3);
        this.basicOrders.addOrder(basicOrder4);

        this.basicOrders.registerOrdersInLedger();

        this.basicOrder1.addProduct(product);

        this.basicOrders.registerOrdersInLedger();

        Map<String, Map<String, Double>> expected = new HashMap<String, Map<String, Double>>();
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("Venda", 6.67); //Braga  20/3
        map.put("Compra", 10.0); //
        expected.put("Braga", map);

        map = new HashMap<String, Double>();
        map.put("Venda", 0.0); //Porto 20/3
        map.put("Compra", 5.00);
        expected.put("Porto", map);

        map = new HashMap<String, Double>();
        map.put("Venda", 55.00);
        map.put("Compra", 0.0);
        expected.put("Beja", map);

        map = new HashMap<String, Double>();
        map.put("Compra", 55.00); //Porto
        map.put("Venda", 0.0);
        expected.put("Aveiro", map);
        assertEquals(expected, basicOrders.averageValueSalesPurchasesDistrict());
    }

    @Test
    public void testNumberOrdersByDistrictBVA01() {
        OrdersMetricStatistics basicOrdersMetricStatistics = new OrdersMetricStatistics();
        assertThrows(IllegalArgumentException.class, () -> basicOrdersMetricStatistics.numberOrderSentReceivedByDistrict(null));
    }

    //DOCUMENTAÇÃO
    @Test
    public void testNumberOrdersByDistrictECP01() {
        this.basicOrder2 = new Order("2", "2021-2-2", basicEntity4, basicEntity4);
        this.basicOrder3 = new Order("3", "2021-2-2", basicEntity2, basicEntity4);

        this.basicOrder1.addProduct(product);
        this.basicOrder1.addProduct(product2);
        this.basicOrder2.addProduct(product2);
        this.basicOrder3.addProduct(product3);
        this.basicOrder4.addProduct(product2);

        this.basicOrders.addOrder(basicOrder1);
        this.basicOrders.addOrder(basicOrder2);
        this.basicOrders.addOrder(basicOrder3);
        this.basicOrders.addOrder(basicOrder4);

        this.basicOrders.registerOrdersInLedger();

        this.basicOrder1.addProduct(product);

        this.basicOrders.registerOrdersInLedger();

        Map<String, Map<String, Integer>> expected = new HashMap<String, Map<String, Integer>>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("Enviadas", 1);
        map.put("Recebidas", 0); //
        expected.put("Braga", map);

        map = new HashMap<String, Integer>();
        map.put("Enviadas", 1);
        map.put("Recebidas", 3);
        expected.put("Porto", map);

        map = new HashMap<String, Integer>();
        map.put("Enviadas", 1);
        map.put("Recebidas", 0);
        expected.put("Beja", map);

        map = new HashMap<String, Integer>();
        map.put("Enviadas", 1);
        map.put("Recebidas", 1);
        expected.put("Aveiro", map);

        assertEquals(expected, basicOrders.numberOrderSentReceivedByDistrict());
    }

    @Test
    public void testStandardDeviationTransactionsBVA01() {
        OrdersMetricStatistics basicOrdersMetricStatistics = new OrdersMetricStatistics();
        assertThrows(IllegalArgumentException.class, () -> basicOrdersMetricStatistics.standardDeviationTransactions(null));
    }

    @Test
    public void testStandardDeviationTransactionsECP01() {
        basicOrder1.addProduct(product);
        basicOrder1.addProduct(product2); //30

        basicOrder2.addProduct(product4);
        basicOrder2.addProduct(product5); //20
        basicOrder3.addProduct(product6); //5

        basicOrders.addOrder(basicOrder1);
        basicOrders.addOrder(basicOrder2);
        basicOrders.addOrder(basicOrder3);
        basicOrders.registerOrdersInLedger();

        Double expectedValue = 24.53;

        assertEquals(expectedValue, basicOrders.standardDeviationTransactions());

    }

    @Test
    public void testStandardDeviationNumberProductTransactionsBVA01() {
        OrdersMetricStatistics basicOrdersMetricStatistics = new OrdersMetricStatistics();
        assertThrows(IllegalArgumentException.class, () -> basicOrdersMetricStatistics.standardDeviationNumberProductTransactions(null));
    }

    @Test
    public void testStandardDeviationNumberProductTransactionsECP01() {
        basicOrder1.addProduct(product);
        basicOrder1.addProduct(product2); //30

        basicOrder2.addProduct(product4);
        basicOrder2.addProduct(product5); //20
        basicOrder3.addProduct(product6); //5

        basicOrders.addOrder(basicOrder1);
        basicOrders.addOrder(basicOrder2);
        basicOrders.addOrder(basicOrder3);
        basicOrders.registerOrdersInLedger();

        Double expectedValue = 0.82;
        assertEquals(expectedValue, basicOrders.standardDeviationNumberProductTransactions());
    }
}


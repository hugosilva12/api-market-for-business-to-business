package TestsModuleEstatistics;

import JsonOperations.MethodsJsonExporter;
import ApiUse.IProduct;
import ApiUse.Order;
import ApiUse.Orders;
import ModuleTransaction.Orgcom.BasicEntity;
import ModuleTransaction.Orgcom.District;
import ApiUse.Product;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class performs tests of the StatisticsExporter class
 */
public class TestStatisticsExporter {

    private MethodsJsonExporter jsonExporter;
    private JSONObject jsonObject;

    private Orders orders;
    private Order order;
    private BasicEntity senderEntity;
    private BasicEntity receiverEntity;
    private IProduct product, product2, product3, product4, product5;

    @BeforeEach
    void setup() {
        this.jsonExporter = new MethodsJsonExporter();
        this.jsonObject = new JSONObject();
    }

    @Test
    public void testCreateFileBVA01() {
        assertThrows(IllegalArgumentException.class, () -> this.jsonExporter.createFile(null));
    }

    @Test
    public void testCreateFileBVA02() {
        assertThrows(IllegalArgumentException.class, () -> this.jsonExporter.createFile(""));
    }

    @Test
    public void testCreateFileECP01() {
        assertTrue(this.jsonExporter.createFile("filesJson/TestCreateFile/test.json"), "\"\" should be true");
    }

    @Test
    public void testWriteToFileBVA01() {
        assertThrows(IllegalArgumentException.class, () -> this.jsonExporter.writeToFile("", jsonObject));
    }

    @Test
    public void testWriteToFileBVA02() {
        assertThrows(IllegalArgumentException.class, () -> this.jsonExporter.writeToFile(null, jsonObject));
    }

    @Test
    public void testWriteToFileBVA03() {
        assertThrows(IllegalArgumentException.class, () -> this.jsonExporter.writeToFile("filesJson/TestCreateFile/test.json", null));
    }

    @Test
    public void testWriteToFileECP01() {
        assertTrue(this.jsonExporter.writeToFile("filesJson/TestCreateFile/test.json", jsonObject));
    }

    @Test
    public void testExportStatisticsBVA01() {
        Map<String, Map<String, Double>> map = new HashMap<>();
        Map<String, Map<String, Integer>> mapNumberOrder = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> this.jsonExporter.exportStatistics("", map, -1.0, 2.0, mapNumberOrder, 23.2, 34.7));
    }

    @Test
    public void testExportStatisticsBVA02() {
        Map<String, Map<String, Integer>> mapNumberOrder = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> this.jsonExporter.exportStatistics("", null, -1.0, 2.0, mapNumberOrder, 23.2, 34.7));
    }

    @Test
    public void testExportStatisticsBVA03() {
        Map<String, Map<String, Double>> map = new HashMap<>();
        Map<String, Map<String, Integer>> mapNumberOrder = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> this.jsonExporter.exportStatistics(null, map, 1.0, 2.0, mapNumberOrder, 23.2, 34.7));
    }

    @Test
    public void testExportStatisticsBVA04() {
        Map<String, Map<String, Double>> map = new HashMap<>();
        Map<String, Map<String, Integer>> mapNumberOrder = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> this.jsonExporter.exportStatistics(null, map, 4.0, -2.0, mapNumberOrder, 23.2, 34.7));
    }

    @Test
    public void testExportStatisticsBVA05() {
        Map<String, Map<String, Double>> map = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> this.jsonExporter.exportStatistics(null, map, 4.0, -2.0, null, 23.2, 34.7));
    }

    @Test
    public void testExportStatisticsECP01A() {
        this.orders = new Orders();

        this.senderEntity = new BasicEntity("Sender", District.VIANA_CASTELO);
        this.receiverEntity = new BasicEntity("Receiver", District.VIANA_CASTELO);


        this.order = new Order("1234", "2021-01-01", senderEntity, receiverEntity);
        this.product5 = new Product(1, "Product1", "Description", 2, 3, 4.1, 5.2);
        this.order.addProduct(product5);

        this.product = new Product(2, "nome", "produto fixe", 12.00, 1, 5.4, 7);
        this.product2 = new Product(3, "nome", "produto top", 12.00, 1, 6.4, 4);
        this.product3 = new Product(4, "nome", "produto reacondiciona", 12.00, 1, 7.4, 4);
        this.product4 = new Product(5, "nome", "produto frágil", 12.00, 1, 8.4, 5);


        Order basicOrder = new Order("123_222", "2021-2-21", senderEntity, receiverEntity);

        basicOrder.addProduct(product);
        basicOrder.addProduct(product2);
        basicOrder.addProduct(product3);
        basicOrder.addProduct(product4);

        orders.addOrder(basicOrder);
        orders.registerOrdersInLedger();

        this.jsonExporter.exportStatistics("filesJson/TestCreateFile/testEstatistic.json", orders.averageValueSalesPurchasesDistrict(), orders.averageValueTransactions(), orders.averageNumberProductsTransaction(), orders.numberOrderSentReceivedByDistrict(), orders.standardDeviationTransactions(), orders.standardDeviationNumberProductTransactions());
    }
}

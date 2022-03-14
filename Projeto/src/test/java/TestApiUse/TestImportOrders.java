package TestApiUse;

import ApiUse.IOrders;
import JsonOperations.ImportOrders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class performs tests of the ImportOrders class
 */
public class TestImportOrders {

    private ImportOrders importOrders;

    @BeforeEach
    void setup() {
        this.importOrders = new ImportOrders();
    }

    @Test
    public void testImportOrdersBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> this.importOrders.importOrders(""));
    }

    @Test
    public void testImportOrdersBVA02() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> this.importOrders.importOrders(null));
    }

    @Test
    public void testImportOrdersECP01() {
        assertNull(this.importOrders.importOrders("files/OrdersFiles.json"), "\"\" Should be null");
    }


    @Test
    public void testImportOrdersECP02() {
        assertTrue(this.importOrders.importOrders("filesJson/OrdersFile.json") instanceof IOrders, "\"\" Should be true");
    }
}

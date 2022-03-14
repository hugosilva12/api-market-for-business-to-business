package TestsModuleExpedition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * This class performs tests of the ProductsInsideTruck class
 */
public class TestProductsInsideTruck {


    @Test
    public void testProductsInsideTruckBVA01() {
        assertThrows(IllegalArgumentException.class, () -> new ProductsInsideTruck("", "Hugo", 4, 2.2));
    }

    @Test
    public void testProductsInsideTruckBVA02() {
        assertThrows(IllegalArgumentException.class, () -> new ProductsInsideTruck("Hugo", "", 4, 2.2));
    }

    @Test
    public void testProductsInsideTruckBVA03() {
        assertThrows(IllegalArgumentException.class, () -> new ProductsInsideTruck("Hugo", "ID_PRODUCT", 0, 2.2));
    }

    @Test
    public void testProductsInsideTruckBVA04() {
        assertThrows(IllegalArgumentException.class, () -> new ProductsInsideTruck("Hugo", "", 1, 0));
    }

    @Test
    public void testProductsInsideTruckBVA05() {
        assertThrows(IllegalArgumentException.class, () -> new ProductsInsideTruck(null, "hry", 1, 34));
    }

    @Test
    public void testProductsInsideTruckBVA06() {
        assertThrows(IllegalArgumentException.class, () -> new ProductsInsideTruck("hello", null, 1, 34));
    }

    @Test
    public void testProductsInsideTruckECP01() {
        ProductsInsideTruck productsInsideTruck = new ProductsInsideTruck("Hugo", "idProduto", 2, 34);
        assertTrue(productsInsideTruck instanceof ProductsInsideTruck, "should be true");
    }
}

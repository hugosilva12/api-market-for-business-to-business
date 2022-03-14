package TestApiUse;

import ApiUse.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * This class performs tests of the Product class
 */
public class TestProduct {

    //Construtor BasicProduct
    @Test
    public void testBasicProductBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new Product(-1, "Product1", "Description", 2, 3, 4.1, 5.2));
    }

    @Test
    public void testBasicProductBVA02() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new Product(1, "", "Description", 2, 3, 4.1, 5.2));
    }

    @Test
    public void testBasicProductBVA03() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new Product(1, "Product1", "", 2, 3, 4.1, 5.2));
    }

    @Test
    public void testBasicProductBVA04() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new Product(1, "Product1", "Description", -0.1, 3, 4.1, 5.2));
    }

    @Test
    public void testBasicProductBVA05() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new Product(1, "Product1", "Description", 1, 0, 4.1, 5.2));
    }

    @Test
    public void testBasicProductBVA06() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new Product(1, "Product1", "Description", 1, 3, 0, 5.2));
    }

    @Test
    public void testBasicProductBVA07() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new Product(1, "Product1", "Description", 1, 3, 4.1, 0));
    }
    @Test
    public void testBasicProductBVA08() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new Product(1, "Product1", "Description", 1, 3, 63.1, 5.2));
    }
    @Test
    public void testBasicProductBVA09() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new Product(1, "Product1", null, 1, 3, 63.1, 5.2));
    }

    @Test
    public void testBasicProductBVA010() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new Product(1, null, "Description", 1, 3, 63.1, 5.2));
    }


    @Test
    public void testBasicProductECP01() {
        Product product = new Product(1, "Product1", "Description", 2, 3, 4.1, 5.2);
        assertTrue(product instanceof Product,"\"\" should be true");
    }
}

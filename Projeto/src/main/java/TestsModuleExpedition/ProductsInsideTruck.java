package TestsModuleExpedition;

import java.util.Objects;

/**
 * The ProductsInsideTruck class provides the functionality of a container line in the API.
 */
public class ProductsInsideTruck implements IProductsInsideTruck {
    private String idOrder;
    private String idProduct;


    private double uniqueVolum;
    private int quantity;

    @Override
    public String toString() {
        return "ContainerLine{" +
                "idOrder='" + idOrder + '\'' +
                ", idProduct='" + idProduct + '\'' +
                ", TamanhoProduto='" + uniqueVolum + '\'' +
                ", Número de Produtos=" + quantity +
                '}';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIdOrder() {
        return idOrder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIdProduct() {
        return idProduct;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getUniqueVolume() {
        return uniqueVolum;
    }


    public ProductsInsideTruck(String idOrder, String idProduct, int quantity, double uniqueSize) {
        if (idOrder == null || idProduct == null) {
            throw new IllegalArgumentException("Strings cannot be null.");
        }
        if (idOrder.isEmpty() || idProduct.isEmpty()) {
            throw new IllegalArgumentException("Strings cannot be Empty.");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity be negative or 0");
        }

        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.uniqueVolum = uniqueSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductsInsideTruck)) return false;
        ProductsInsideTruck that = (ProductsInsideTruck) o;
        return Objects.equals(getIdOrder(), that.getIdOrder()) && Objects.equals(getIdProduct(), that.getIdProduct());
    }

}

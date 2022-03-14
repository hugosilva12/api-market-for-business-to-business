package TestsModuleExpedition;


import ApiUse.IOrder;
import JsonOperations.ImportConfigsTruck;

import java.util.ArrayList;

/**
 * The Container class provides the functionality of a container in the API.
 */
public class Truck implements ITruck {

    private String nameContainer;
    private final String pathFile = "filesJson/capacityTruck.json";
    private Double capacity;
    private ArrayList<IProductsInsideTruck> listProductsInsideTruck = new ArrayList<IProductsInsideTruck>();
    ImportConfigsTruck importConfigsTruck = new ImportConfigsTruck();


    public Truck(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be empty.");
        }
        this.nameContainer = name;
        this.capacity = importConfigsTruck.importConfigCapacity(pathFile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addProductInTruck(String idOrder, String idProduct, Double volume) {
        if (idOrder == null || idProduct == null) {
            throw new IllegalArgumentException("String cannot be null.");
        }
        if (idOrder.isEmpty() || idProduct.isEmpty()) {
            throw new IllegalArgumentException("Strings cannot be null.");
        }
        if (capacity < volume) {
            return false;
        }
        if (volume <= 0) {
            throw new IllegalArgumentException("Strings cannot be null.");
        }
        ProductsInsideTruck productsInsideTruck = new ProductsInsideTruck(idOrder, idProduct, 1, volume);
        IProductsInsideTruck getProductsInsideTruck = getProductContainer(productsInsideTruck);
        capacity = capacity - volume;
        if (getProductsInsideTruck == null) {
            listProductsInsideTruck.add(productsInsideTruck);
        } else {
            getProductsInsideTruck.setQuantity(getProductsInsideTruck.getQuantity() + 1);
            listProductsInsideTruck.add(getProductsInsideTruck);
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAllOrderInTruck(IOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        if (capacity < order.getOrderVolum()) {
            return false;
        }
        if (order.getOrderVolum() > 63 || order.getOrderVolum() <= 0) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        this.capacity = capacity - order.getOrderVolum();

        for (int i = 0; i < order.getProductCounter(); i++) {
            ProductsInsideTruck productsInsideTruck = new ProductsInsideTruck(order.getId(), String.valueOf(order.getProductByIndex(i).getId()), order.getProductByIndex(i).getQuantity(), order.getProductByIndex(i).getVolume());
            listProductsInsideTruck.add(productsInsideTruck);
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IProductsInsideTruck getProductContainer(IProductsInsideTruck iProductsInsideTruck) {
        if (iProductsInsideTruck == null) {
            throw new IllegalArgumentException("iProductsInsideTruck cannot be null.");
        }
        IProductsInsideTruck containerLine = null;
        for (IProductsInsideTruck containerLineOnly : listProductsInsideTruck) {
            if (containerLineOnly.equals(iProductsInsideTruck)) {
                containerLine = containerLineOnly;
                listProductsInsideTruck.remove(containerLineOnly);
                return containerLine;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print() {
        for (IProductsInsideTruck productsInsideTruckOnly : listProductsInsideTruck) {
            System.out.println(productsInsideTruckOnly.toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNameContainer() {
        return nameContainer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Truck{" +
                "name='" + nameContainer + '\'' +
                ", ocupation=' " + (63 - capacity) + "/63.00" + '\'' +
                ", orderExpedition=" + listProductsInsideTruck +
                '}';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double availableCapacity() {
        return this.capacity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IProductsInsideTruck getProductListProductsInsideTruck(int index) {
        if (index < 0 || index >= listProductsInsideTruck.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return this.listProductsInsideTruck.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int numberProductInContainer() {
        return this.listProductsInsideTruck.size();
    }
}

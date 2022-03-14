package ApiUse;

import JsonOperations.ImportDistanceTableKM;
import ModuleCost.IDistanceTable;
import ModuleTransaction.Orgcom.Entity;
import ModuleTransaction.Orgcom.hashing.HashUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Order class provides the functionality of a basic order in the system.
 */
public class Order implements IOrder {
    private final String PathFile = "filesJson/DistanceCostsTable.json";
    private final String id;
    private final String date;
    private final Entity sender;
    private final Entity receiver;
    private double shippingCost = 0;
    private double price = 0;
    private double orderVolum = 0;
    private double orderWeight = 0;
    private StateOrder stateOrder;
    private ArrayList<IProduct> IProducts = new ArrayList<>();

    /**
     * Creates a new Order with the given id, date, sender entity, receiver entity;
     *
     * @param id       the id of the order
     * @param date     the name of the order
     * @param sender   the description of the order
     * @param receiver the value of the order
     * @throws IllegalArgumentException If any of the arguments are invalid (product id or date are null or empty,
     *                                  or the sender or receiver are null).
     */
    public Order(String id, String date, Entity sender, Entity receiver) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Id cannot be null or empty");
        }
        if (date == null || date.isEmpty()) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }
        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("Sender and receiver cannot be null.");
        }
        this.id = id;
        this.date = date;
        this.sender = sender;
        this.receiver = receiver;
        this.stateOrder = StateOrder.AGUARDA_REGISTO;
    }

    /**
     * Returns the id of the order.
     *
     * @return the id of the order.
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Returns the date of the order.
     *
     * @return the date of the order.
     */
    @Override
    public String getDate() {
        return this.date;
    }

    /**
     * Returns the sender entity of the order.
     *
     * @return the sender entity of the order.
     */
    @Override
    public Entity getSender() {
        return this.sender;
    }

    /**
     * Returns the receiver entity of the order.
     *
     * @return the receiver entity of the order.
     */
    @Override
    public Entity getReceiver() {
        return this.receiver;
    }

    /**
     * Returns the counter of the different products of the order.
     *
     * @return the counter of the different products of the order.
     */
    @Override
    public int getProductCounter() {
        return this.IProducts.size();
    }

    /**
     * Return the Shipping Cost of the order.
     *
     * @return the Shipping Cost of the order.
     */
    @Override
    public double getShippingCost() {
        return shippingCost;
    }

    /**
     * Returns the price of the order.
     *
     * @return the price of the order.
     */
    @Override
    public Double getPrice() {
        return this.price;
    }

    /**
     * Returns the total volume of the order.
     *
     * @return the total volume of the order.
     */
    @Override
    public double getOrderVolum() {
        return this.orderVolum;
    }

    /**
     * Returns the weight volume of the order.
     *
     * @return the weight volume of the order.
     */
    @Override
    public double getOrderWeight() {
        return this.orderWeight;
    }

    /**
     * Return the {@link IProduct product} in the order that have the same hash.
     *
     * @param IProduct the {@link IProduct product} to be checked.
     * @return the {@link IProduct product} in the order that have the same hash, null if not found.
     * @throws IllegalArgumentException if the product are not hashable.
     */
    @Override
    public IProduct getProduct(IProduct IProduct) {
        if (IProduct == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }

        for (IProduct IProductTemp : this.IProducts) {
            if (IProduct.getHash().equals(IProductTemp.getHash())) {
                return IProduct;
            }
        }

        return null;
    }

    /**
     * Adds a product to the order.
     *
     * @param IProduct the product to add.
     * @return true if the product was added correctly or false is it was not added.
     * @throws IllegalArgumentException if the product parameter is null.
     */
    @Override
    public boolean addProduct(IProduct IProduct) {
        if (IProduct == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }

        if (this.getProduct(IProduct) != null) {
            return false;
        }

        this.IProducts.add(IProduct);
        this.orderVolum += IProduct.getVolume() * IProduct.getQuantity();
        this.orderWeight += IProduct.getWeight() * IProduct.getQuantity();
        this.shippingCostCalculation();
        this.price += IProduct.getValue() * IProduct.getQuantity();

        return true;
    }

    /**
     * Return the {@link IProduct product} in the order that have the gaven index.
     *
     * @param index the index of the product list to be found.
     * @return the {@link IProduct product} in the order that have the gaven index.
     * @throws IndexOutOfBoundsException if the order index is invalid.
     */
    @Override
    public IProduct getProductByIndex(int index) {
        if (index < 0 || index >= this.IProducts.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return this.IProducts.get(index);
    }

    @Override
    public double shippingCostCalculation() {
        ImportDistanceTableKM importDistanceTableKM = new ImportDistanceTableKM();
        IDistanceTable distanceTable;
        int distance = 0;
        double cost = 0;

        if (this.sender.getDistrict().equals(this.receiver.getDistrict())) {
            return this.shippingCost = 0;
        }

        distanceTable = importDistanceTableKM.importCostsTableKM(PathFile);

        distance = distanceTable.getDistanceValueFromShipping(this.sender, this.receiver);


        cost += (this.orderWeight + distance) * 0.25;

        cost += 0.15 * this.orderVolum;

        BigDecimal shippingCostTemp = new BigDecimal(cost);
        shippingCostTemp = shippingCostTemp.setScale(2, RoundingMode.HALF_UP);

        this.shippingCost = shippingCostTemp.doubleValue();

        return this.shippingCost;
    }

    /**
     * Returns the string representation of the order.
     *
     * @return the string representation of the order.
     */
    @Override
    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order{");
        sb.append("id: ").append(this.id);
        sb.append(", date: ").append(this.date);
        sb.append(", price: ").append(this.price);
        sb.append(", productCounter: ").append(this.IProducts.size());
        sb.append(", sender: ").append(this.sender);
        sb.append(", receiver: ").append(this.receiver);
        sb.append(", volume: ").append(this.orderVolum);
        sb.append(", weight: ").append(this.orderWeight);
        sb.append(", shippingCost:").append(this.shippingCost);
        sb.append(", state: ").append(this.stateOrder.toString());
        for (int print = 0; print < this.IProducts.size(); print++) {
            sb.append(", products: ").append(IProducts.get(print).print());
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * @return the hash value of the order.
     */
    @Override
    public String getHash() {
        return HashUtils.getHash(this.id + this.date + this.price + this.IProducts.size() + this.sender + this.receiver + this.orderVolum + this.orderWeight);
    }

    /**
     * Returns the iterator value of the products array of the order.
     *
     * @return the iterator value of the products array of the order.
     */
    @Override
    public Iterator<IProduct> iterator() {
        return this.IProducts.iterator();
    }

    /**
     * Return the state of Basic Order
     *
     * @return the state of Basic Order
     */
    public StateOrder getStateOrder() {
        return stateOrder;
    }

    /**
     * Set the attribute of enum StateOrder
     *
     * @param stateOrder the state of {@link StateOrder basicOrders}
     */
    public void setStateOrder(StateOrder stateOrder) {
        this.stateOrder = stateOrder;
    }
}

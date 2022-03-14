package ApiUse;

import ModuleTransaction.OrdersMetricStatistics;
import ModuleTransaction.Orgcom.*;
import ModuleTransaction.Orgcom.hashing.UnHashableException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * The Orders class provides the functionality of a basic orders in the API.
 */
public class Orders implements IOrders {

    private ArrayList<IOrder> listOrders;
    private BasicOrganization basicOrganization;
    private ModuleTransaction.OrdersMetricStatistics OrdersMetricStatistics;
    private ArrayList<Block> listBlocks;

    public Orders() {
        this.basicOrganization = new BasicOrganization();
        this.listOrders = new ArrayList<>();
        this.OrdersMetricStatistics = new OrdersMetricStatistics();

    }

    /**
     * Return the {@link IOrder order} in the basicOrders list that have the same hash.
     *
     * @param IOrder the {@link IOrder order} to be checked.
     * @return the {@link IProduct product} in the basicOrders list that have the same hash, null if not found.
     * @throws UnHashableException if the order are not hashable.
     */
    @Override
    public IOrder getOrder(IOrder IOrder) {
        if (IOrder == null) {
            throw new UnHashableException("Order cannot be nul.");
        }

        for (IOrder IOrderTemp : this.listOrders) {
            if (IOrder.getHash().equals(IOrderTemp.getHash())) {
                return IOrder;
            }
        }

        return null;
    }

    /**
     * Adds a order to the basicOrders list.
     *
     * @param IOrder the product to add.
     * @return true if the order was added correctly or false is it was not added.
     * @throws IllegalArgumentException if the order parameter is null.
     */
    @Override
    public boolean addOrder(IOrder IOrder) {
        if (IOrder == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        if (this.getOrder(IOrder) != null) {
            return false;
        }

        this.listOrders.add(IOrder);

        return true;
    }

    /**
     * Returns the string representation of the orders list.
     *
     * @return the string representation of the orders list.
     */
    @Override
    public String print() {
        StringBuilder sb = new StringBuilder();
        for (int print = 0; print < this.listOrders.size(); print++) {
            sb.append("basicOrders{").append(this.listOrders.get(print).print());
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOrder() {
        return this.listOrders.size();
    }

    @Override //Test
    public IOrder getOrderIndex(int index) {
        if (index < 0 || index >= this.listOrders.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return this.listOrders.get(index);
    }

    @Override
    public double getOrderShippingCost(IOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        BigDecimal shippingCost = new BigDecimal(order.getShippingCost());
        shippingCost = shippingCost.setScale(2, RoundingMode.HALF_UP);

        return shippingCost.doubleValue();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int registerOrdersInLedger() {
        if (listOrders.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < listOrders.size(); i++) {
            if (listOrders.get(i).getStateOrder() != StateOrder.REGISTADA) {
                listOrders.get(i).setStateOrder(StateOrder.REGISTADA);
                Entity entitySender = listOrders.get(i).getSender();
                entitySender.addTokens(2);
                BasicTransaction basicTransactionSend = new BasicTransaction(entitySender, listOrders.get(i).getReceiver());
                BasicTransaction basicTransactionPayment = new BasicTransaction(entitySender, listOrders.get(i).getReceiver());
                for (int j = 0; j < listOrders.get(i).getProductCounter(); j++) {
                    BasicTransactionLine basicTransactionLineSend = new BasicTransactionLine("Send-Product-ID: " + String.valueOf(listOrders.get(i).getProductByIndex(j).getId()), listOrders.get(i).getProductByIndex(j).getQuantity(), listOrders.get(i).getProductByIndex(j).getValue());
                    BasicTransactionLine basicTransactionLinePayment = new BasicTransactionLine("Payment-Product-ID:" + String.valueOf(listOrders.get(i).getProductByIndex(j).getId()), listOrders.get(i).getProductByIndex(j).getQuantity(), listOrders.get(i).getProductByIndex(j).getValue());
                    basicTransactionSend.addTransactionLine(basicTransactionLineSend);
                    basicTransactionPayment.addTransactionLine(basicTransactionLinePayment);
                }
                this.basicOrganization.addTransaction(basicTransactionSend);
                this.basicOrganization.addTransaction(basicTransactionPayment);
            }

        }

        return this.basicOrganization.registerTransactionsInLedger();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double averageValueTransactions() {
        listBlocks = getArrayListBlocks();
        return OrdersMetricStatistics.averageValueTransactions(listBlocks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double averageNumberProductsTransaction() {
        listBlocks = getArrayListBlocks();
        return OrdersMetricStatistics.averageNumberProductsTransaction(listBlocks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Map<String, Double>> averageValueSalesPurchasesDistrict() {
        listBlocks = getArrayListBlocks();
        return OrdersMetricStatistics.averageValueSalesPurchasesDistrict(listBlocks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Map<String, Integer>> numberOrderSentReceivedByDistrict() {
        listBlocks = getArrayListBlocks();
        return OrdersMetricStatistics.numberOrderSentReceivedByDistrict(listBlocks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double standardDeviationTransactions() {
        listBlocks = getArrayListBlocks();
        return OrdersMetricStatistics.standardDeviationTransactions(listBlocks);
    }

    @Override
    public double standardDeviationNumberProductTransactions() {
        listBlocks = getArrayListBlocks();
        return OrdersMetricStatistics.standardDeviationNumberProductTransactions(listBlocks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Block> getArrayListBlocks() {
        Iterator<Block> iteratorBlock = this.basicOrganization.ledgerIterator();
        ArrayList<Block> listBlocks = new ArrayList<Block>();
        while (iteratorBlock.hasNext())
            listBlocks.add(iteratorBlock.next());
        return listBlocks;
    }

    /**
     * @return the hash value of the basicOrders list.
     */
    @Override
    public Iterator<IOrder> iterator() {
        return this.listOrders.iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<IOrder> getBasicOrders() {
        return listOrders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBasicOrders(ArrayList<IOrder> basicIOrders) {
        this.listOrders = basicIOrders;
    }


}

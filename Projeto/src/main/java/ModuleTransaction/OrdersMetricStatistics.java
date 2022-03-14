package ModuleTransaction;

import Exceptions.AverageException;
import ModuleTransaction.Orgcom.Block;
import ModuleTransaction.Orgcom.Transaction;
import ModuleTransaction.Orgcom.TransactionLine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The OrdersMetricStatistics class provides the functionality that gets the statistics in the API.
 */
public class OrdersMetricStatistics implements IStatistics {

    public OrdersMetricStatistics() {

    }

    /**
     * Returns Returns the average value of transactions in format '##.##' (two decimal places)
     *
     * @param ledger average value of transactions
     * @return average value of transactions
     * @throws AverageException if the ledger is empty.
     */
    @Override
    public double averageValueTransactions(ArrayList<Block> ledger) {

        isAvailableToMetrics(ledger);

        double valueTotalTransactions = 0;

        int numberTransactions = 0;

        for (Block block : ledger) {
            for (Iterator<Transaction> itTransaction = block.getTransactions(); itTransaction.hasNext(); ) {
                Transaction transaction = itTransaction.next();
                numberTransactions++;
                valueTotalTransactions += transaction.getTotalValue();
            }
        }

        BigDecimal average = new BigDecimal(valueTotalTransactions / numberTransactions);
        average = average.setScale(2, RoundingMode.HALF_UP);
        return average.doubleValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double averageNumberProductsTransaction(ArrayList<Block> ledger) {

        isAvailableToMetrics(ledger);

        int numberProductsTransactions = 0;
        int numberTransactions = 0;
        for (Block block : ledger) {
            numberTransactions += block.getNumberOfTransactions();
            for (Iterator<Transaction> itTransaction = block.getTransactions(); itTransaction.hasNext(); ) {
                Transaction transaction = itTransaction.next();
                for (Iterator<TransactionLine> itTransactionLines = transaction.iterator(); itTransactionLines.hasNext(); ) {
                    TransactionLine transactionline = itTransactionLines.next();
                    numberProductsTransactions += transactionline.getQuantity();
                }
            }
        }
        BigDecimal average = new BigDecimal(numberProductsTransactions / (double) numberTransactions);
        average = average.setScale(2, RoundingMode.HALF_UP);
        return average.doubleValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Map<String, Double>> averageValueSalesPurchasesDistrict(ArrayList<Block> ledger) {

        isAvailableToMetrics(ledger);

        double valueTransaction = 0;

        Map<String, Map<String, Double>> valuesDistrict = new HashMap<String, Map<String, Double>>();
        Map<String, Integer> numberTransationsDistrictSender = new HashMap<String, Integer>();
        Map<String, Integer> numberTransationsDistrictReceiver = new HashMap<String, Integer>();

        for (Block block : ledger) {

            for (Iterator<Transaction> itTransaction = block.getTransactions(); itTransaction.hasNext(); ) {
                Transaction transaction = itTransaction.next();
                valueTransaction = 0;
                Map<String, Double> values = new HashMap<String, Double>();
                for (Iterator<TransactionLine> itTransactionLines = transaction.iterator(); itTransactionLines.hasNext(); ) {
                    TransactionLine transactionline = itTransactionLines.next();
                    valueTransaction = valueTransaction + (transactionline.getUnitPrice() * transactionline.getQuantity());
                }
                if (valuesDistrict.get(transaction.getSender().getDistrict()) == null) {
                    values.put("Venda", valueTransaction);
                    values.put("Compra", 0.0);
                    valuesDistrict.put(transaction.getSender().getDistrict(), values);
                    numberTransationsDistrictSender.put(transaction.getSender().getDistrict(), 1);

                } else {
                    values = valuesDistrict.get(transaction.getSender().getDistrict());
                    double newValue = valueTransaction + values.get("Venda");
                    values.put("Venda", newValue);
                    valuesDistrict.put(transaction.getSender().getDistrict(), values);
                    int numero = numberTransationsDistrictSender.get(transaction.getSender().getDistrict());
                    numberTransationsDistrictSender.put(transaction.getSender().getDistrict(), numero + 1);
                }
                values = new HashMap<String, Double>();
                if (valuesDistrict.get(transaction.getReceiver().getDistrict()) == null) {
                    values.put("Venda", 0.0);
                    values.put("Compra", valueTransaction);
                    valuesDistrict.put(transaction.getReceiver().getDistrict(), values);
                    numberTransationsDistrictReceiver.put(transaction.getReceiver().getDistrict(), 1);
                } else if (transaction.getReceiver().getDistrict() == transaction.getSender().getDistrict() && numberTransationsDistrictReceiver.get(transaction.getReceiver().getDistrict()) == null) {
                    values = valuesDistrict.get(transaction.getReceiver().getDistrict());
                    values.put("Compra", valueTransaction);
                    valuesDistrict.put(transaction.getReceiver().getDistrict(), values);
                    numberTransationsDistrictReceiver.put(transaction.getReceiver().getDistrict(), 1);
                } else {
                    values = valuesDistrict.get(transaction.getReceiver().getDistrict());
                    values.put("Compra", valueTransaction + values.get("Compra"));
                    valuesDistrict.put(transaction.getReceiver().getDistrict(), values);
                    int numero = numberTransationsDistrictReceiver.get(transaction.getReceiver().getDistrict());
                    numberTransationsDistrictReceiver.put(transaction.getReceiver().getDistrict(), numero + 1);
                }
            }
        }
        return calculateAverage(valuesDistrict, numberTransationsDistrictSender, numberTransationsDistrictReceiver);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Map<String, Integer>> numberOrderSentReceivedByDistrict(ArrayList<Block> ledger) {

        isAvailableToMetrics(ledger);

        Map<String, Map<String, Integer>> valuesDistrict = new HashMap<String, Map<String, Integer>>();

        for (Block block : ledger) {

            for (Iterator<Transaction> itTransaction = block.getTransactions(); itTransaction.hasNext(); ) {
                Transaction transaction = itTransaction.next();
                Map<String, Integer> values = new HashMap<String, Integer>();

                if (valuesDistrict.get(transaction.getSender().getDistrict()) == null) {
                    values.put("Enviadas", 1);
                    values.put("Recebidas", 0);
                    valuesDistrict.put(transaction.getSender().getDistrict(), values);

                } else {
                    values = valuesDistrict.get(transaction.getSender().getDistrict());
                    int newValue = 1 + values.get("Enviadas");
                    values.put("Enviadas", newValue);
                    valuesDistrict.put(transaction.getSender().getDistrict(), values);

                }
                values = new HashMap<String, Integer>();
                if (valuesDistrict.get(transaction.getReceiver().getDistrict()) == null) {
                    values.put("Enviadas", 0);
                    values.put("Recebidas", 1);
                    valuesDistrict.put(transaction.getReceiver().getDistrict(), values);

                } else {
                    values = valuesDistrict.get(transaction.getReceiver().getDistrict());
                    values.put("Recebidas", 1 + values.get("Recebidas"));
                    valuesDistrict.put(transaction.getReceiver().getDistrict(), values);
                }
            }
        }
        for (Map.Entry<String, Map<String, Integer>> key : valuesDistrict.entrySet()) {
            Map<String, Integer> values = new HashMap<String, Integer>();
            values = key.getValue();
            values.put("Enviadas", values.get("Enviadas") / 2);
            values.put("Recebidas", values.get("Recebidas") / 2);
        }

        return valuesDistrict;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Map<String, Double>> calculateAverage(Map<String, Map<String, Double>> valuesDistrict, Map<String, Integer> numberTransationsDistrictSender, Map<String, Integer> numberTransationsDistrictReceiver) {

        for (Map.Entry<String, Map<String, Double>> key : valuesDistrict.entrySet()) {
            Map<String, Double> values = new HashMap<String, Double>();
            values = key.getValue();

            if (numberTransationsDistrictReceiver.get(key.getKey()) != null) {
                BigDecimal average = new BigDecimal(values.get("Compra") / numberTransationsDistrictReceiver.get(key.getKey()));
                average = average.setScale(2, RoundingMode.HALF_UP);
                values.put("Compra", average.doubleValue());
            }

            if (numberTransationsDistrictSender.get(key.getKey()) != null) {
                BigDecimal average = new BigDecimal(values.get("Venda") / numberTransationsDistrictSender.get(key.getKey()));
                average = average.setScale(2, RoundingMode.HALF_UP);
                values.put("Venda", average.doubleValue());
            }
        }
        return valuesDistrict;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double standardDeviationTransactions(ArrayList<Block> ledger) {
        isAvailableToMetrics(ledger);

        Double mean = this.averageValueTransactions(ledger);
        Double deviationSome = 0.0;
        int numberTransactions = 0;
        for (Block block : ledger) {
            for (Iterator<Transaction> itTransaction = block.getTransactions(); itTransaction.hasNext(); ) {
                Transaction transaction = itTransaction.next();
                numberTransactions++;
                deviationSome += Math.pow((transaction.getTotalValue() - mean), 2);
            }
        }
        BigDecimal deviation = new BigDecimal(Math.sqrt(deviationSome / numberTransactions));
        deviation = deviation.setScale(2, RoundingMode.HALF_UP);
        return deviation.doubleValue();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public double standardDeviationNumberProductTransactions(ArrayList<Block> ledger) {
        isAvailableToMetrics(ledger);

        Double mean = this.averageNumberProductsTransaction(ledger);
        double someProduct = 0.0;
        int productByTransaction = 0;
        int numberTransactions = 0;

        for (Block block : ledger) {
            numberTransactions += block.getNumberOfTransactions();
            for (Iterator<Transaction> itTransaction = block.getTransactions(); itTransaction.hasNext(); ) {
                Transaction transaction = itTransaction.next();
                productByTransaction = 0;
                for (Iterator<TransactionLine> itTransactionLines = transaction.iterator(); itTransactionLines.hasNext(); ) {
                    TransactionLine transactionline = itTransactionLines.next();
                    productByTransaction += transactionline.getQuantity();
                }
                someProduct += Math.pow((productByTransaction - mean), 2);
            }
        }

        BigDecimal deviation = new BigDecimal(Math.sqrt(someProduct / numberTransactions));
        deviation = deviation.setScale(2, RoundingMode.HALF_UP);
        return deviation.doubleValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isAvailableToMetrics(ArrayList<Block> ledger) {
        if (ledger == null) {
            throw new IllegalArgumentException("Ledger is null");
        }

        if (ledger.size() == 1) {
            throw new AverageException("Ledger without any transaction registed");
        }
        return true;
    }
}

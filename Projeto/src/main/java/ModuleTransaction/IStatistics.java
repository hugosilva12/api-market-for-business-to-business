package ModuleTransaction;

import Exceptions.AverageException;

import ModuleTransaction.Orgcom.Block;

import java.util.ArrayList;
import java.util.Map;

/**
 * The Statistics interface provides the methods for statistics.
 */
public interface IStatistics {


    /**
     * Returns the average value of transactions in format '##.##' (two decimal places)
     *
     * @param ledger book to calculate averages
     * @return average value of transactions
     * @throws AverageException if the ledger is empty.
     */
    double averageValueTransactions(ArrayList<Block> ledger);

    /**
     * Returns average number of products per transaction in format '.##' (two decimal places)
     *
     * @param ledger book to calculate averages
     * @return average number of products per transaction
     * @throws AverageException if the ledger is empty (Genisis block only).
     */
    double averageNumberProductsTransaction(ArrayList<Block> ledger);

    /**
     * Returns the average sales/purchases by district on a map
     *
     * @param ledger book to calculate averages
     * @return map in format Map {@literal <}String, Map {@literal <} String, Double{@literal >>} (example: Porto={"Compra":60.00,"Venda":30.00}) with values
     * @throws AverageException         if the ledger is empty (Genisis block only).
     * @throws IllegalArgumentException if block is null
     */
    Map<String, Map<String, Double>> averageValueSalesPurchasesDistrict(ArrayList<Block> ledger);

    /**
     * Returns  the number of orders sent/received by district
     *
     * @param ledger book to calculate averages
     * @return map in format Map {@literal <}String, Map {@literal <} String, Integer{@literal >>}  (example: Porto={"Enviadas":3,"Recebidas":3}).
     * @throws AverageException         if the ledger is empty (Genisis block only).
     * @throws IllegalArgumentException if block is null
     */
    Map<String, Map<String, Integer>> numberOrderSentReceivedByDistrict(ArrayList<Block> ledger);

    /**
     * Returns in map format the number of purchases and sales by district
     *
     * @param valuesDistrict                    total purchases and sales
     * @param numberTransationsDistrictSender   number of transactions sent
     * @param numberTransationsDistrictReceiver number of transactions received
     * @return map in format Map {@literal <}String, Map {@literal <} String, Double{@literal >>} (example: Porto={"Compra":60.00,"Venda":30.00}) with values
     */
    Map<String, Map<String, Double>> calculateAverage(Map<String, Map<String, Double>> valuesDistrict, Map<String, Integer> numberTransationsDistrictSender, Map<String, Integer> numberTransationsDistrictReceiver);

    /**
     * Returns the standard deviation of transactions in format '.##' (two decimal places)
     *
     * @param ledger book to calculate mean and standardDeviation
     * @return value of standard deviation
     * @throws AverageException         if the ledger is empty (Genisis block only).
     * @throws IllegalArgumentException if the ledger is null.
     */
    double standardDeviationTransactions(ArrayList<Block> ledger);

    /**
     * Checks if the book is valid for statistical metrics
     *
     * @param ledger book for check
     * @return true if book is valid
     * @throws AverageException         if the ledger is empty (only genesys block).
     * @throws IllegalArgumentException if the ledger is null.
     */
    public Boolean isAvailableToMetrics(ArrayList<Block> ledger);

    /**
     * Returns the standard deviation of number products by transactions in format '.##' (two decimal places)
     *
     * @param ledger book to calculate mean and standardDeviation
     * @return value of standard deviation
     * @throws AverageException         if the ledger is empty (Genisis block only).
     * @throws IllegalArgumentException if the ledger is null.
     */
    double standardDeviationNumberProductTransactions(ArrayList<Block> ledger);
}


import ApiUse.Orders;
import JsonOperations.ImportOrders;
import JsonOperations.MethodsJsonExporter;
import TestsModuleExpedition.ExpeditionMethods;
import org.json.simple.JSONObject;

import java.util.Scanner;

public class Main {
    private static void menu() { // menu principal
        final String pathFile = "filesJson/capacityTruck.json";
        MethodsJsonExporter methodsJsonExporter = new MethodsJsonExporter();
        Orders basicOrder = null;
        ImportOrders importOrders = new ImportOrders();
        Scanner menu = new Scanner(System.in);
        int opcao = 0, index = 0;
        do {
            System.out.println("\n\n### API para otimizar atividade da  Market for Business to Business");

            System.out.println("    1 - Importar Encomendas (filesJson/OrdersFile.json) |");
            System.out.println("    2 - Visualizar Encomendas");
            System.out.println("    3 - Registar Encomendas");
            System.out.println("    4 - Obter Custo de envio de uma encomenda ");
            System.out.println("    5 - Agrupar encomendas em camiões para exportação");
            System.out.println("    6 - Métricas Estatisticas");
            System.out.println("    7 - Exportar Relatório Json!");
            System.out.println("    8 - Alterar capacidade dos camiões");
            System.out.println("    0 - Sair");
            System.out.println("    ##############");
            if (basicOrder != null) {
                System.out.println("    - Número de encomendas em memória: " + basicOrder.getNumberOrder() + " ||Número de encomendas Registadas:");
            }
            System.out.println("     Escolha opcão: ");
            opcao = menu.nextInt();
            switch (opcao) {
                case 1:
                    basicOrder = (Orders) importOrders.importOrders("filesJson/OrdersFile.json");
                    break;
                case 2:
                    if (basicOrder != null) {
                        System.out.println(basicOrder.print());
                    }
                    break;
                case 3:
                    if (basicOrder != null) {
                        System.out.println(basicOrder.registerOrdersInLedger() + " transações  registadas!");
                    }
                    break;
                case 4:
                    if (basicOrder != null) {
                        System.out.println("Selecione o index da encomendas [0-" + (basicOrder.getNumberOrder() - 1) + "]");
                        index = menu.nextInt();
                        System.out.println("Custo de envio da encomenda" + basicOrder.getOrderIndex(index).shippingCostCalculation());
                    }

                case 5:
                    ExpeditionMethods expeditionMethods = new ExpeditionMethods();
                    System.out.println(expeditionMethods.groupOrdersByTrucks(basicOrder.getBasicOrders()));
                    break;
                case 6:
                    System.out.println("Número médio de produtos por transação: " + basicOrder.averageNumberProductsTransaction());
                    System.out.println("Valor médio das transações: " + basicOrder.averageValueTransactions());
                    System.out.println("Média de compras e vendas por distrito " + basicOrder.averageValueSalesPurchasesDistrict());
                    System.out.println("Desvio Padrão média produtos: " + basicOrder.standardDeviationNumberProductTransactions());
                    System.out.println("Desvio Padrão média  transações: " + basicOrder.standardDeviationTransactions());
                    System.out.println("Número de encomendas recebidas / enviadas:  " + basicOrder.numberOrderSentReceivedByDistrict());
                    break;
                case 7:
                    methodsJsonExporter.exportStatistics("filesJson/Statistics.json", basicOrder.averageValueSalesPurchasesDistrict(), basicOrder.averageValueTransactions(), basicOrder.averageNumberProductsTransaction(), basicOrder.numberOrderSentReceivedByDistrict(), basicOrder.standardDeviationTransactions(), basicOrder.standardDeviationNumberProductTransactions());
                    ExpeditionMethods expeditionMethods1 = new ExpeditionMethods();
                    expeditionMethods1.groupOrdersByTrucks(basicOrder.getBasicOrders());
                    expeditionMethods1.exportGroupOrdersForFileJson();
                    break;
                case 8:
                    System.out.println(" Escolha nova capacidade: ");
                    Double capacity = menu.nextDouble();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("capacity", capacity);
                    methodsJsonExporter.writeToFile(pathFile, jsonObject);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }

        } while (opcao != 0);
    }

    public static void main(String[] args) {
        menu();
    }

}



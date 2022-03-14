package JsonOperations;

import ApiUse.*;
import ModuleTransaction.Orgcom.BasicEntity;
import ModuleTransaction.Orgcom.District;
import ModuleTransaction.Orgcom.Entity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

/**
 * Class that imports a json file with orders
 */
public class ImportOrders {

    private IOrders basicIOrders = new Orders();

    public ImportOrders(){ }

    /**
     * Returns a ArrayList of Orders imported by a JSON file.
     *
     * @param path   the path of the JSON file to import the orders' data.
     * @throws IllegalArgumentException If the argument path is null or empty.
     *
     * @return  a ArrayList of Orders imported by a JSON file.
     */
    public IOrders importOrders(String path) {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Path should not be null or empty");
        }

        this.basicIOrders = new Orders();

        File file = new File(path);

        JSONParser jsonParser = new JSONParser();
        boolean exists = file.exists();

        if (exists) {

            JSONObject obj = null;
            try {
                obj = (JSONObject) jsonParser.parse(new FileReader(file.getPath()));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            JSONArray listOrders = (JSONArray) obj.get("orders");
            for (int i = 0; i < listOrders.size(); i++) {
                JSONObject objOrder = (JSONObject) listOrders.get(i);

                JSONObject objSender = (JSONObject) objOrder.get("sender");
                String district = (String) objSender.get("district");
                Entity sender = new BasicEntity((String) objSender.get("name"), District.valueOf(district.toUpperCase(Locale.ROOT)));

                JSONObject objReceiver = (JSONObject) objOrder.get("receiver");
                district = (String) objReceiver.get("district");
                Entity receiver = new BasicEntity((String) objReceiver.get("name"), District.valueOf(district.toUpperCase(Locale.ROOT)));

                Order basicOrder = new Order((String) objOrder.get("id"), (String) objOrder.get("date"), sender , receiver);

                JSONArray listProdutos = (JSONArray) objOrder.get("products");
                for (int j = 0; j < listProdutos.size(); j++) {
                    JSONObject objProduct = (JSONObject) listProdutos.get(j);

                    int id = Integer.parseInt(objProduct.get("id").toString());

                    double price = 0;
                    String priceTemp = objProduct.get("price").toString();
                    if(!priceTemp.contains(".")){
                        Long readValue = Long.valueOf(priceTemp);
                        price = readValue.doubleValue();
                    }else{
                        price = Double.valueOf(priceTemp);
                    }

                    int quantity = Integer.parseInt(objProduct.get("quantity").toString());

                    double volume = 0;
                    String volumeTemp = objProduct.get("volume-m3").toString();
                    if(!volumeTemp.contains(".")){
                        Long readValue = Long.valueOf(volumeTemp);
                        volume = readValue.doubleValue();
                    }else{
                        volume = Double.valueOf(volumeTemp);
                    }

                    double weight = 0;
                    String weightTemp = objProduct.get("volume-m3").toString();
                    if(!weightTemp.contains(".")){
                        Long readValue = Long.valueOf(weightTemp);
                        weight = readValue.doubleValue();
                    }else{
                        weight = Double.valueOf(weightTemp);
                    }

                    IProduct basicIProduct = new Product(id, (String) objProduct.get("name"), (String) objProduct.get("description"),
                                                            price, quantity, volume, weight);

                    basicOrder.addProduct(basicIProduct);
                }
                basicIOrders.addOrder(basicOrder);
            }

            return this.basicIOrders;

        } else {
            return null;
        }
    }

}

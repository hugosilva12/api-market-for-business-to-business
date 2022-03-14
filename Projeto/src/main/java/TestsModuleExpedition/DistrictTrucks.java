package TestsModuleExpedition;

import ApiUse.IOrder;


import java.util.ArrayList;

/**
 * The DistrictExpedition class provides the functionality of a district expedition in the API.
 */
public class DistrictTrucks implements IDistrictTrucks {
    private String nameDistrict;
    private ArrayList<Truck> listTrucks = new ArrayList<Truck>();
    private ArrayList<IOrder> ordersByDistrict;

    /**
     * {@inheritDoc}
     */
    public DistrictTrucks(String nameDistrict, int numberContainer, ArrayList<IOrder> ordersByDistrict) {
        if (nameDistrict == null) {
            throw new IllegalArgumentException("Name District cannot be Empty.");
        }
        if (nameDistrict.isEmpty()) {
            throw new IllegalArgumentException("Name District cannot be Empty.");
        }
        if (ordersByDistrict == null) {
            throw new IllegalArgumentException("ordersByDistrict cannot be null.");
        }
        this.nameDistrict = nameDistrict;
        this.createTruck(numberContainer);
        this.ordersByDistrict = ordersByDistrict;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("nameDistrict:" + nameDistrict);
        System.out.println("Escalonamento Truck:");
        for (int i = 0; i < this.listTrucks.size(); i++) {
            System.out.println(listTrucks.get(i).toString());
        }
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createTruck(int numberTruck) {
        if (numberTruck < 1) {
            throw new IllegalArgumentException("number contanier cannot be negative or 0.");
        }
        for (int i = 0; i < numberTruck; i++) {
            Truck container = new Truck("Truck" + String.valueOf(i + 1));
            listTrucks.add(container);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void groupOrders() {
        int containerIndex = 0;

        for (IOrder order : ordersByDistrict) {

            if (order.getOrderVolum() <= listTrucks.get(containerIndex).availableCapacity()) {
                listTrucks.get(containerIndex).addAllOrderInTruck(order);
            } else {

                containerIndex++;
                if (containerIndex == listTrucks.size()) {
                    containerIndex--;
                }
                for (int j = 0; j < order.getProductCounter(); j++) {
                    for (int k = 0; k < order.getProductByIndex(j).getQuantity(); k++) {
                        Boolean isInsert = listTrucks.get(containerIndex).addProductInTruck(order.getId(), String.valueOf(order.getProductByIndex(j).getId()), order.getProductByIndex(j).getVolume());

                        //NAO INSERIDO
                        if (!isInsert) {

                            boolean allocationPossible = false;
                            for (int i = 0; i < listTrucks.size(); i++) {
                                if (listTrucks.get(i).availableCapacity() >= order.getProductByIndex(j).getVolume()) {

                                    allocationPossible = true;
                                    listTrucks.get(i).addProductInTruck(order.getId(), String.valueOf(order.getProductByIndex(j).getId()), order.getProductByIndex(j).getVolume());
                                    break;
                                }
                            }

                            if (!allocationPossible) { //Peciso novo camiao

                                Truck container = new Truck("Truck" + String.valueOf(listTrucks.size() - 1));
                                listTrucks.add(container);

                                isInsert = listTrucks.get(listTrucks.size() - 1).addProductInTruck(order.getId(), String.valueOf(order.getProductByIndex(j).getId()), order.getProductByIndex(j).getVolume());
                            }
                        }
                    }
                }
            }

        }
    }

    /**
     * {@inheritDoc}
     */
    public String getNameDistrict() {
        return nameDistrict;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Truck getTruckByIndex(int index) {
        if (index < 0 || index >= listTrucks.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return this.listTrucks.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return this.listTrucks.size();
    }
}

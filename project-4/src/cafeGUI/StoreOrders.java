package cafeGUI;

import java.util.ArrayList;

public class StoreOrders {
    private int orderNumber = 0;
    private ArrayList<Order> orders = new ArrayList<Order>();

    private void grow() {
        int curr_catalogSize = orders.size();
        int new_catalogSize = curr_catalogSize + 4;

        ArrayList<Order> newItems = new ArrayList<Order>(new_catalogSize); //array with new catalog capacity
        newItems.addAll(orders);
        orders = newItems;
    }

    public boolean add(Order orderItems){
        if(orderItems == null) return false;
        orders.add(orderItems);
        return true;
    }

    public boolean remove(Order orderItems){
        if(orderItems == null) return false;
        if(!orders.contains(orderItems)) return false;
        orders.remove(orderItems);
        return true;
    }

    public ArrayList<Order> returnOrder(){
        return orders;
    }
}

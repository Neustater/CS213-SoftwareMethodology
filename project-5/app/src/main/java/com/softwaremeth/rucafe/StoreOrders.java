package com.softwaremeth.rucafe;

import java.io.Serializable;
import java.util.ArrayList;

/**
 The StoreOrders Class allows the user to create a StoreOrders object.
 Also allows access to add Orders to the StoreOrders.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public class StoreOrders implements Customizable, Serializable {
    private ArrayList<Order> orders = new ArrayList<Order>();

    /**
     * Constructor to create a StoreOrders Object
     */
    public StoreOrders(){

    }

    /**
     * Method to add a Order to the orders Arraylist in the StoreOrders Object.
     * @param object takes in to check if it is a Orders object, and adds it into the orders ArrayList.
     * @return a boolean being true if the object successfully been added, false otherwise.
     */
    public boolean add(Object object){
        if(object instanceof Order){
            Order orderItems = (Order)object;
            if(orderItems == null) return false;
            orders.add(orderItems);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Method to remove a Order from the orders Arraylist in the StoreOrders Object.
     * @param object takes in to check if it is a Orders object, and removes it into the orders ArrayList.
     * @return a boolean being true if the object successfully been removed, false otherwise.
     */
    public boolean remove(Object object){
        if(object instanceof Order){
            Order orderItems = (Order)object;
            if(orderItems == null) return false;
            if(!orders.contains(orderItems)) return false;
            orders.remove(orderItems);
            return true;
        }else {
            return false;
        }
    }


    /**
     * Method to return the orders in the StoreOrders object.
     * @return an ArrayList containing the orders.
     */
    public ArrayList<Order> returnOrder() { return orders;
    }
}

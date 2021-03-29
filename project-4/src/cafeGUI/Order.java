package cafeGUI;

import java.util.ArrayList;

public class Order {
    private ArrayList<MenuItem> itemsArray = new ArrayList<MenuItem>(); //Initial Capacity of 10
    private double subTotal;

    public Order(ArrayList<MenuItem> menuItems){
        itemsArray = menuItems;
        calculateSubTotal();
    }

    private void grow() {
        int curr_catalogSize = itemsArray.size();
        int new_catalogSize = curr_catalogSize + 4;

        ArrayList<MenuItem> newItems = new ArrayList<MenuItem>(new_catalogSize); //array with new catalog capacity
        newItems.addAll(itemsArray);
        itemsArray = newItems;
    }

    public boolean add(MenuItem menuItem){
        if(menuItem == null) return false;

        itemsArray.add(menuItem);
        calculateSubTotal();
        return true;
    }

    public boolean remove(MenuItem menuItem){
        if(menuItem == null) return false;
        if(!itemsArray.contains(menuItem)) return false;

        itemsArray.remove(menuItem);
        calculateSubTotal();
        return true;
    }

    private void calculateSubTotal(){
        double tempTotal = 0;

        for(int i=0; i<itemsArray.size(); i++){
            tempTotal += itemsArray.get(i).returnPrice();
        }

        subTotal = tempTotal;
    }

    public double returnSubTotal(){
        return subTotal;
    }
}

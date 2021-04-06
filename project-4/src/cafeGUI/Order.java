package cafeGUI;

import java.util.ArrayList;

/**
 The Order Class allows the user to create a Order object.
 Also allows access to add Donuts and Coffee to the Order.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public class Order implements Customizable{
    private ArrayList<MenuItem> itemsArray = new ArrayList<MenuItem>(); //Initial Capacity of 10
    private double subTotal;
    private double salesTax;
    private double total;
    private static double SALES_TAX_RATE = 0.06625;

    /**
     * Constructor to create a Order Object
     */
    public Order(){
        subTotal = 0;
        salesTax = 0;
        total = 0;
    }

    /**
     * Method to add a MenuItem to the itemsArray Arraylist in the Order Object.
     * @param object takes in to check if it is a MenuItem object, and adds it into the itemsArray ArrayList.
     * @return a boolean being true if the object successfully been added, false otherwise.
     */
    public boolean add(Object object){
        if(object instanceof MenuItem) {
            MenuItem menuItem = (MenuItem) object;
            if(menuItem == null) return false;

            itemsArray.add(menuItem);
            calculateSubTotal();
            return true;
        }else
        {
            return false;
        }

    }

    /**
     * Method to remove a MenuItem to the itemsArray Arraylist in the Order Object.
     * @param object takes in to check if it is a MenuItem object, and removes it from the itemsArray ArrayList.
     * @return a boolean being true if the object successfully been removed, false otherwise.
     */
    public boolean remove(Object object){
        if(object instanceof MenuItem){
            MenuItem menuItem = (MenuItem) object;
            if(!itemsArray.contains(menuItem)) return false;
            itemsArray.remove(menuItem);
            calculateSubTotal();
            return true;
        } else {
            return false;
        }

    }

    /**
     * Method to calculate the subTotal of the Order based on the itemsArray.
     */
    private void calculateSubTotal(){
        double tempTotal = 0;

        for(int i=0; i<itemsArray.size(); i++){
            tempTotal += itemsArray.get(i).returnPrice();
        }

        subTotal = tempTotal;
    }

    /**
     * Method to update the total price of the Order.
     */
    public void updateTotals(){
        salesTax  = subTotal * SALES_TAX_RATE;
        total = subTotal + salesTax;
    }

    /**
     * Method to return the subTotal of the Order.
     * @return a double price of the subTotal.
     */
    public double getSubTotal(){
        return subTotal;
    }

    /**
     * Method to return the total of the Order.
     * @return a double price of the total.
     */
    public double getTotal(){
        return total;
    }

    /**
     * Method to return the salesTax of the Order.
     * @return a double price of the salesTax.
     */
    public double getTax() {return salesTax;}

    /**
     * Method to return the MenuItems of the Order contained in a ArrayList.
     * @return the itemsArray ArrayList of the Order.
     */
    public ArrayList<MenuItem> getCurrentOrder() {
        return itemsArray;
    }

}

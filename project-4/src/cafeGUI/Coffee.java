package cafeGUI;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 The Coffee Class allows the user to create a Coffee object.
 Also allows access to add toppings to the coffee.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public class Coffee extends MenuItem implements Customizable{

    private String size;
    private double price;
    private int numberOfAddIns = 0;
    private ArrayList<String> addIns = new ArrayList();
    private int quantity;

    private static final double SHORT_PRICE = 1.99;
    private static final double TALL_PRICE = 2.49;
    private static final double GRANDE_PRICE = 2.99;
    private static final double VENTI_PRICE = 3.49;
    private static final double ADD_IN = 0.20;

    /**
     * Constructor to create a Coffee Object
     */
    public Coffee(){
        size = null;
        price = 0;
        quantity = 0;
    }


    /**
     * Method to set the price of the Coffee based on size and quantity of Coffees.
     */
    private void itemPrice(){
        price = (numberOfAddIns * ADD_IN);
        switch (size){
            case "Short" : price += SHORT_PRICE;
                break;
            case "Tall" : price += TALL_PRICE;
                break;
            case "Grande" : price += GRANDE_PRICE;
                break;
            case "Venti" : price += VENTI_PRICE;
                break;
        }

        price *= quantity;

    }

    /**
     * Method to add a topping to the Coffee Object's add-ins ArrayList.
     * @param object takes in to check if it is a String object, and adds it into the add-ins ArrayList.
     * @return a boolean being true if the object successfully been added, false otherwise.
     */
    public boolean add(Object object){
        if(object instanceof String) {
            String newAddIn = (String) object;
            addIns.add(newAddIn);
            Collections.sort(addIns);
            numberOfAddIns += 1;
            return true;
        }
        return false;
    }

    /**
     * Method to remove a topping from the Coffee Object's add-ins ArrayList.
     * @param object takes in to check if it is a String object, and finds it in the add-ins and removes it.
     * @return a boolean being true if the object was found and removed, false otherwise.
     */
    public boolean remove(Object object){
        if(object instanceof String) {
            String newAddIn = (String) object;
            if(!addIns.contains(newAddIn)) return false;
            addIns.remove(newAddIn);
            Collections.sort(addIns);
            numberOfAddIns -= 1;
            return true;
        }
        return false;
    }

    /**
     * Method to return the price of the Coffee object.
     * @return a double called price holding the price of the Coffee object.
     */
    public double returnPrice(){
        itemPrice();
        return price;
    }

    /**
     * Method to set the size of the Coffee.
     * @param size takes in a String to set the Coffee's size.
     */
    public void setSize(String size){
        this.size = size;
    }

    /**
     * Method to set the quantity of the Coffee.
     * @param quantity takes in a int to set the Coffee's size.
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    /**
     * Method to return a String of the Coffee object.
     * @return the Coffee Object in a specified string format.
     */
    public String toString(){
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = ("$" + df.format(price));
        return quantity + "x " + size + " Coffee " + addIns.toString() + " " + curPriceStr;
    }

}

package cafeGUI;

import java.util.ArrayList;
import java.util.Collections;

/**
 The Donut Class allows the user to create a Donut object.
 Also allows access to add flavors to the donut.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public abstract class Donuts extends MenuItem implements Customizable{

    private int quantity;
    private ArrayList<String> flavors = new ArrayList<String>();

    /**
     * Method to add a flavor to the Donut Object's flavors ArrayList.
     * @param object takes in to check if it is a String object, and adds it into the flavors ArrayList.
     * @return a boolean being true if the object successfully been added, false otherwise.
     */
    public boolean add(Object object){
        if(object instanceof String){
            String newFlavor = (String) object;
            flavors.add(newFlavor);
            Collections.sort(flavors);
            return true;
        }
        return false;
    }

    /**
     * Method to remove a flavor from the Donut Object's flavors ArrayList.
     * @param object takes in to check if it is a String object, and finds it in the flavors and removes it.
     * @return a boolean being true if the object was found and removed, false otherwise.
     */
    public boolean remove(Object object){
        if(object instanceof String){
            String newFlavor = (String) object;
            if(!flavors.contains(newFlavor)) return false;
            flavors.remove(newFlavor);
            Collections.sort(flavors);
            return true;
        }
        return false;
    }

    /**
     * Method to return a String of the Donut object.
     * @return the Donut Object in a specified string format.
     */
    public String toString(){
        return flavors.toString();
    }

    /**
     * Method to return a double for returning Donut price.
     * @return the Donut Object price in double format.
     */
    public double returnPrice(){
        return quantity;
    }

    /**
     * Method to return the flavors of the Donut.
     * @return the Donut Object flavors.
     */
    public ArrayList<String> returnFlavors() {return flavors;}

    /**
     * Method to update the price of the Donut based on quantity of Donuts.
     */
    abstract void itemPrice();

    /**
     * Method to set the quantity of the Donut object.
     * @param quantity takes in a int and set the quantity of the Donut Object.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

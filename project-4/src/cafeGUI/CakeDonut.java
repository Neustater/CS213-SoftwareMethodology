package cafeGUI;

import java.text.DecimalFormat;

/**
 The Donut Class allows the user to create a CakeDonut object.
 Also allows access to add flavors to the donut.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public class CakeDonut extends Donuts{


    private static final double CAKE_DONUT_PRICE = 1.59;
    private static final String CAKE_DONUT = "Cake Donut";
    public static final String[] CAKE_FLAVORS = {"Strawberry", "Blueberry", "Cinnamon", "Chocolate"};
    private double price;

    /**
     * Constructor to create a CakeDonut Object
     */
    public CakeDonut(){
        price = 0;
    }

    /**
     * Method to return a String of the CakeDonut object.
     * @return the CakeDonut Object in a specified string format.
     */
    public String toString(){
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = ("$" + df.format(returnPrice()));
        return  (int)super.returnPrice() + "x "+ CAKE_DONUT + " " + super.toString() + " " + curPriceStr;

    }

    /**
     * Method to update the price of the Donut based on quantity of Donuts.
     */
    public void itemPrice(){
        price = super.returnPrice() * CAKE_DONUT_PRICE;
    }

    /**
     * Method to return the price of the CakeDonut object.
     * @return a double called price holding the price of the Donut object.
     */
    public double returnPrice(){
        itemPrice();
        return price;
    }


}

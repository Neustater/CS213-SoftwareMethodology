package cafeGUI;

import java.text.DecimalFormat;

/**
 The Donut Class allows the user to create a YeastDonut object.
 Also allows access to add flavors to the donut.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public class YeastDonut extends Donuts{

    private static final double YEAST_DONUT_PRICE = 1.39;
    private static final String YEAST_DONUT = "Yeast Donut";
    public static final String[] YEAST_FLAVORS = {"Old Fashion","Chocolate Frosted","Cinnamon", "Glazed"};
    private double price = 0;

    /**
     * Constructor to create a YeastDonut Object
     */
    public YeastDonut(){
        price = 0;
    }

    /**
     * Method to return a String of the YeastDonut object.
     * @return the YeastDonut Object in a specified string format.
     */
    public String toString(){
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = ("$" + df.format(returnPrice()));
        return  (int)super.returnPrice() + "x "+ YEAST_DONUT + " " + super.toString() + " " + curPriceStr;
    }

    /**
     * Method to update the price of the Donut based on quantity of Donuts.
     */
    public void itemPrice(){
        price = super.returnPrice() * YEAST_DONUT_PRICE;
    }

    /**
     * Method to return the price of the YeastDonut object.
     * @return a double called price holding the price of the YeastDonut object.
     */
    public double returnPrice(){
        itemPrice();
        return price;
    }


}

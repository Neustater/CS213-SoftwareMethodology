package cafeGUI;

import java.text.DecimalFormat;

/**
 The Donut Class allows the user to create a DonutHoleDonut object.
 Also allows access to add flavors to the donut.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public class DonutHoleDonut extends Donuts{

    private static final double DONUT_HOLE_PRICE = 0.33;
    private static final String DONUT_HOLE = "Donut Hole";
    public static final String[] DONUT_HOLE_FLAVORS ={"Peanut", "Chocolate", "Egg", "Glazed"};

    /**
     * Constructor to create a DonutHoleDonut Object
     */
    public DonutHoleDonut(){
    }

    /**
     * Method to return a String of the DonutHoleDonut object.
     * @return the Donut Object in a specified string format.
     */
    public String toString(){
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = ("$" + df.format(returnPrice()));
        return  (int)super.returnPrice() + "x "+ DONUT_HOLE + " " + super.toString() + " " + curPriceStr;
    }

    /**
     * Method to return the price of the DonutHoleDonut object.
     * @return a double called price holding the price of the Donut object.
     */
    public double returnPrice(){
        return super.returnPrice() * DONUT_HOLE_PRICE;
    }


}

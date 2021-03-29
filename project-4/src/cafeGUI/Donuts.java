package cafeGUI;

import java.util.ArrayList;

public class Donuts extends MenuItem{

    private String type;
    private double price;
    private ArrayList<String> flavors = new ArrayList<String>();

    private static final double YEAST_DONUT_PRICE = 1.39;
    private static final double CAKE_DONUT_PRICE = 1.59;
    private static final double DONUT_HOLE_PRICE = 0.33;

    public Donuts(String donutType, ArrayList<String> newFlavors){
        //type = do
        flavors = newFlavors;
    }

    private void grow() {
        int curr_catalogSize = flavors.size();
        int new_catalogSize = curr_catalogSize + 4;

        ArrayList<String> newItems = new ArrayList<String>(new_catalogSize); //array with new catalog capacity
        newItems.addAll(flavors);
        flavors = newItems;
    }

    private void itemPrice(){
        if(type.equals("Yeast")) price = YEAST_DONUT_PRICE;
        if(type.equals("Cake")) price = CAKE_DONUT_PRICE;
        if(type.equals("Donut Hole")) price = DONUT_HOLE_PRICE;
    }

    public boolean add(Object object){
        if(object instanceof String){
            String newFlavor = (String) object;
            flavors.add(newFlavor);
            return true;
        }
        return false;
    }

    public boolean remove(Object object){
        if(object instanceof String){
            String newFlavor = (String) object;
            if(!flavors.contains(newFlavor)) return false;
            flavors.remove(newFlavor);
            return true;
        }
        return false;
    }

    public double returnPrice(){
        return price;
    }

}

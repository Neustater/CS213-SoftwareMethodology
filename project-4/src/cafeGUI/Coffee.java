package cafeGUI;

import java.util.ArrayList;

public class Coffee extends MenuItem{

    private String size;
    private double price;
    private int numberOfAddIns = 0;
    private ArrayList<String> addIns = new ArrayList<String>();

    private static final double SHORT_PRICE = 1.99;
    private static final double TALL_PRICE = 2.49;
    private static final double GRANDE_PRICE = 2.99;
    private static final double VENTI_PRICE = 3.49;
    private static final double ADD_IN = 0.20;

    public Coffee(String inputSize, ArrayList<String> inputAddIns){
        size = inputSize;

        for(int i=0; i<inputAddIns.size(); i++){
            if(inputAddIns.get(i) == null) continue;
            numberOfAddIns += 1;
        }
        addIns = inputAddIns;
    }

    private void grow() {
        int curr_catalogSize = addIns.size();
        int new_catalogSize = curr_catalogSize + 4;

        ArrayList<String> newItems = new ArrayList<String>(new_catalogSize); //array with new catalog capacity
        newItems.addAll(addIns);
        addIns = newItems;
    }

    private void itemPrice(){

        if(size.equals("Short")) price = SHORT_PRICE;
        if(size.equals("Tall")) price = TALL_PRICE;
        if(size.equals("Grande")) price = GRANDE_PRICE;
        if(size.equals("Venti")) price = VENTI_PRICE;

        price += numberOfAddIns * ADD_IN;
    }

    public boolean add(Object object){
        if(object instanceof String) {
            String newAddIn = (String) object;
            addIns.add(newAddIn);
            numberOfAddIns += 1;
            return true;
        }
        return false;
    }

    public boolean remove(Object object){
        if(object instanceof String) {
            String newAddIn = (String) object;
            if(!addIns.contains(newAddIn)) return false;
            addIns.remove(newAddIn);
            numberOfAddIns -= 1;
            return true;
        }
        return false;
    }

    public double returnPrice(){
        return price;
    }

    public ArrayList<String> returnAddIns(){
        return addIns;
    }

}

package cafeGUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderDonutsController {

    private String[] cakes = {"Yeast","Cake","Donut Hole"};
    private ArrayList<String> usedFlavors = new ArrayList<String>();
    private Donuts donut;
    private Integer[] quantities = {1,2,3,4,5,6};
    private double curPrice;
    private int amount;
    private Order currentOrder;

    @FXML
    private ComboBox<String> cakesList;

    @FXML
    private ComboBox<Integer> quantityList;

    @FXML
    private ListView<String> flavorsList;

    @FXML
    private Button orderButton;

    @FXML
    private ListView<Donuts> addedFlavorsList;

    @FXML
    private TextField subTotalBox;

    /**
     * Initializes the GUI for the Donut.
     */
    @FXML
    public void initialize() {

        cakesList.getItems().removeAll(cakesList.getItems());
        cakesList.getItems().addAll(cakes[0], cakes[1], cakes[2]);
        cakesList.getSelectionModel().select(cakes[0]);

        quantityList.getItems().removeAll(quantityList.getItems());
        quantityList.getItems().addAll(quantities[0], quantities[1],
                quantities[2], quantities[3], quantities[4], quantities[5]);
        quantityList.getSelectionModel().select(quantities[0]);

        flavorsList.getItems().removeAll(quantityList.getItems());
        flavorsList.getItems().addAll(YeastDonut.YEAST_FLAVORS[0],YeastDonut.YEAST_FLAVORS[1],
                YeastDonut.YEAST_FLAVORS[2],YeastDonut.YEAST_FLAVORS[3]);

        updateDonutType(cakesList.getSelectionModel().getSelectedItem());
        amount = (quantityList.getSelectionModel().getSelectedItem());
        donut.setQuantity(amount);
        updatePrice();
    }

    /**
     * Initializes the current order for the Donut GUI.
     * @param currentOrder passes in order object of current
     *      *                     order
     */
    void initOrder(Order currentOrder){
        this.currentOrder = currentOrder;
    }

    /**
     * Adds selected flavor to the Donut Object.
     * @param event waits for the add button to be pressed.
     */
    @FXML
    void addButton(ActionEvent event) {
        String flavor = flavorsList.getSelectionModel().getSelectedItem();
        if(flavor != null) {
            usedFlavors.add(flavor);
            donut.add(flavor);
            addedFlavorsList.getItems().add(donut);
            refreshFlavors();
        }
        updateDonutType(cakesList.getSelectionModel().getSelectedItem());
        updatePrice();
    }

    /**
     * Adds the Donut to the Current Order.
     * @param event waits for the order button to be pressed.
     */
    @FXML
    void addToOrderButton(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like " +
                "to this item(s) to the current order?", ButtonType.YES, ButtonType.NO);
        if(addedFlavorsList.getItems().size() != 0) {
            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
            if (ButtonType.YES.equals(result)) {
                for (int i = 0; i < addedFlavorsList.getItems().size(); i++) {
                    currentOrder.add(addedFlavorsList.getItems().get(i));
                }
                Stage stage = (Stage) orderButton.getScene().getWindow();
                stage.close();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Cannot Place Order");
            a.setContentText("\tNo Donuts Have Been Added!");
            a.show();
        }
    }

    /**
     * Removes selected flavor to the Donut Object.
     * @param event waits for the remove button to be pressed.
     */
    @FXML
    void removeButton(ActionEvent event) {
        int firstIndex = 0;
        Donuts checkForDonut = addedFlavorsList.getSelectionModel().getSelectedItem();
        String flavor;

        if(checkForDonut != null) {
            flavor = addedFlavorsList.getSelectionModel().getSelectedItem().returnFlavors().get(firstIndex);
            addedFlavorsList.getItems().remove(addedFlavorsList.getSelectionModel().getSelectedItem());
            if(usedFlavors.contains(flavor)){
                usedFlavors.remove(flavor);
                refreshFlavors();
            }

        }
        updatePrice();
    }

    /**
     * Sets the quantity of the Donut Object.
     * @param event waits for the number to be pressed.
     */
    @FXML
    void setQuantity(Event event) {
        amount = quantityList.getSelectionModel().getSelectedItem();
        donut.setQuantity(amount);
        updatePrice();
    }

    /**
     * Sets the type of Donut for the Donut Object.
     * @param event waits for the type of donut to be pressed.
     */
    @FXML
    void setCake(Event event) {
        updateDonutType(cakesList.getSelectionModel().getSelectedItem());
        updatePrice();

        refreshFlavors();
    }

    /**
     * Refreshes the Flavors list in the GUI.
     */
    private void refreshFlavors(){
        String[] flavors = YeastDonut.YEAST_FLAVORS;

        if(donut instanceof YeastDonut){
            flavors = YeastDonut.YEAST_FLAVORS;
        } else if(donut instanceof CakeDonut){
            flavors = CakeDonut.CAKE_FLAVORS;

        } else if(donut instanceof DonutHoleDonut){
            flavors = DonutHoleDonut.DONUT_HOLE_FLAVORS;
        }

        flavorsList.getItems().removeAll(flavorsList.getItems());
        for(int i=0; i<flavors.length; i++){
            if(usedFlavors.contains(flavors[i])) {
                boolean used = false;
                for(int j=0; j<addedFlavorsList.getItems().size(); j++){
                    if(cakesList.getSelectionModel().getSelectedItem().equals("Yeast")){
                        if(addedFlavorsList.getItems().get(j) instanceof YeastDonut){
                            used = true;
                        }
                    }
                    if(cakesList.getSelectionModel().getSelectedItem().equals("Cake")){
                        if(addedFlavorsList.getItems().get(j) instanceof CakeDonut){
                            used = true;
                        }
                    }
                    if(cakesList.getSelectionModel().getSelectedItem().equals("Donut Hole")){
                        if(addedFlavorsList.getItems().get(j) instanceof DonutHoleDonut){
                            used = true;
                        }
                    }
                }
                if(!used) flavorsList.getItems().add(flavors[i]);
                continue;
            }
            flavorsList.getItems().add(flavors[i]);
        }
    }

    /**
     * Updates the price of the Donut.
     */
    private void updatePrice() {
        curPrice = 0;
        for(int i=0; i<addedFlavorsList.getItems().size(); i++){
            curPrice += addedFlavorsList.getItems().get(i).returnPrice();
        }
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = df.format(curPrice);
        subTotalBox.setText("$" + curPriceStr);
    }

    /**
     * Updates the type of Donut for the Donut.
     * @param type takes in a String to see which type of Donut it is.
     */
    private void updateDonutType(String type){

        switch (type){
            case "Yeast" :
                donut = new YeastDonut();
                break;
            case "Cake" :
                donut = new CakeDonut();
                break;
            case "Donut Hole" :
                donut = new DonutHoleDonut();
                break;
        }
        amount = quantityList.getSelectionModel().getSelectedItem();
        donut.setQuantity(amount);
    }


}
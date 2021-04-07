package cafeGUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class OrderCoffeeController {

    private String[] sizes = {"Short","Tall","Grande","Venti"};
    private String[] addIns = {"Cream","Milk","Syrup","Caramel","Whipped Cream"};
    private Integer[] quantities = {1,2,3,4,5,6};
    private Coffee coffee = new Coffee();
    private double curPrice = 0;
    private Order currentOrder;

    @FXML
    private ComboBox<String> sizeList;

    @FXML
    private ComboBox<Integer> quantityList;

    @FXML
    private TextField subTotalBox;

    @FXML
    private Button orderButton;

    @FXML
    private CheckBox creamBox;

    @FXML
    private CheckBox milkBox;

    @FXML
    private CheckBox syrupBox;

    @FXML
    private CheckBox caramelBox;

    @FXML
    private CheckBox whippedBox;

    /**
     * Initializes the GUI for the Coffee.
     */
    @FXML
    public void initialize() {
        sizeList.getItems().removeAll(sizeList.getItems());
        sizeList.getItems().addAll(sizes[0], sizes[1], sizes[2], sizes[3]);
        sizeList.getSelectionModel().select(sizes[0]);

        quantityList.getItems().removeAll(quantityList.getItems());
        quantityList.getItems().addAll(quantities[0], quantities[1],
                quantities[2], quantities[3], quantities[4], quantities[5]);
        quantityList.getSelectionModel().select(quantities[0]);

        coffee.setSize(sizeList.getValue());
        coffee.setQuantity(quantityList.getValue());

        updateSubtotal();
    }

    /**
     * Adds the current Coffee order to the Current Order GUI of the User.
     * @param event waits for the order button to be pressed.
     */
    @FXML
    void addToOrderButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like " +
                "to add this item(s) to the current order?", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
            if (ButtonType.YES.equals(result)) {
                currentOrder.add(coffee);
                Stage stage = (Stage) orderButton.getScene().getWindow();
                stage.close();
            }
        }

    /**
     * Initializes the current order for the Coffee GUI.
     * @param currentOrder passes in order object of current
     *                     order
     */
    void initOrder(Order currentOrder){
        this.currentOrder = currentOrder;
    }

    /**
     * Adds Cream add-in to the Coffee after Cream has been checked.
     * @param event waits for the cream button to be pressed.
     */
    @FXML
    void clickCream(ActionEvent event) {
        if(creamBox.isSelected()){
            //currAddIns.add(addIns[0]);
            coffee.add(addIns[0]);
        }else{
            //currAddIns.remove(addIns[0]);
            coffee.remove(addIns[0]);
        }
        updateSubtotal();
    }

    /**
     * Adds Milk add-in to the Coffee after Cream has been checked.
     * @param event waits for the milk button to be pressed.
     */
    @FXML
    void clickMilk(ActionEvent event) {
        if(milkBox.isSelected()){
            //currAddIns.add(addIns[1]);
            coffee.add(addIns[1]);
        }else{
           //currAddIns.remove(addIns[1]);
            coffee.remove(addIns[1]);
        }
        updateSubtotal();
    }

    /**
     * Adds Syrup add-in to the Coffee after Cream has been checked.
     * @param event waits for the syrup button to be pressed.
     */
    @FXML
    void clickSyrup(ActionEvent event) {
        if(syrupBox.isSelected()){
            //currAddIns.add(addIns[2]);
            coffee.add(addIns[2]);
        }else{
            //currAddIns.remove(addIns[2]);
            coffee.remove(addIns[2]);
        }
        updateSubtotal();
    }

    /**
     * Adds Caramel add-in to the Coffee after Cream has been checked.
     * @param event waits for the caramel button to be pressed.
     */
    @FXML
    void clickCaramel(ActionEvent event) {
        if(caramelBox.isSelected()){
            //currAddIns.add(addIns[3]);
            coffee.add(addIns[3]);
        }else{
            //currAddIns.remove(addIns[3]);
            coffee.remove(addIns[3]);
        }
        updateSubtotal();
    }

    /**
     * Adds Whipped add-in to the Coffee after Cream has been checked.
     * @param event waits for the whipped button to be pressed.
     */
    @FXML
    void clickWhipped(ActionEvent event) {
        if(whippedBox.isSelected()){
            //currAddIns.add(addIns[4]);
            coffee.add(addIns[4]);
        }else{
            //currAddIns.remove(addIns[4]);
            coffee.remove(addIns[4]);
        }
        updateSubtotal();
    }

    /**
     * Sets the Quantity of the Coffee
     * @param event waits for the number to be pressed.
     */
    @FXML
    void setQuantity(Event event) {
        coffee.setQuantity(quantityList.getValue());

        updateSubtotal();
    }

    /**
     * Sets the Size of the Coffee
     * @param event waits for the size to be pressed.
     */
    @FXML
    void setSize(Event event) {
        coffee.setSize(sizeList.getValue());

        updateSubtotal();
    }

    /**
     * Updates the subTotal of the Coffee
     */
    private void updateSubtotal(){
        curPrice = coffee.returnPrice();
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = df.format(curPrice);
        subTotalBox.setText("$" + curPriceStr);
    }

}

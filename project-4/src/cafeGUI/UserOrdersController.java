package cafeGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class UserOrdersController {

    private Order currentOrder;
    private StoreOrders storeOrders;
    private ArrayList<MenuItem> orderList;
    private int emptyOrder = 0;


    @FXML
    private TextField totalBox;

    @FXML
    private ListView<MenuItem> itemsList;

    @FXML
    private TextField salesTaxBox;

    @FXML
    private Button orderButton;

    @FXML
    private TextField subTotalBox;

    /**
     * Initializes the Store Orders and the Current Order.
     * @param currentOrder takes in the Current Order.
     * @param storeOrders takes in the Store Orders.
     */
    void initOrder(Order currentOrder, StoreOrders storeOrders){
        this.storeOrders = storeOrders;
        this.currentOrder = currentOrder;
        orderList = currentOrder.getCurrentOrder();
        updateTotals();

        itemsList.getItems().removeAll(itemsList.getItems());
        for(int i = 0; i < orderList.size(); i++) {
            itemsList.getItems().addAll(orderList.get(i));
        }
    }

    /**
     * Places the Order to the Store Orders in the GUI.
     * @param event waits for the order to be pressed.
     */
    @FXML
    void placeOrder(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like " +
                "to place this order?", ButtonType.YES, ButtonType.NO);

            if(orderList.size() != emptyOrder) {
                ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
                if (ButtonType.YES.equals(result)) {
                    Order newOrder = new Order();
                    storeOrders.add(newOrder);


                    Stage stage = (Stage) orderButton.getScene().getWindow();
                    stage.close();
                }
            }else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Cannot Place Order");
                a.setContentText("\tNo Items Have Been Ordered!");
                a.show();
            }

    }

    /**
     * Places the Order to the Store Orders in the GUI.
     * @param event waits for the order to be pressed.
     */
    @FXML
    void removeSelectedItem(ActionEvent event) {
        MenuItem selectedItem = itemsList.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like "
                + "to remove this item from the order?", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        if (ButtonType.YES.equals(result)) {
            if (currentOrder.remove(selectedItem)) {
                itemsList.getItems().remove(selectedItem);
                updateTotals();

            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Cannot Remove Item");
                a.setContentText("\tNo Items Have Been Selected\n \tOr Item Does Not Exist!");
                a.show();
            }
        }
    }

    /**
     * Updates the total of the Current Order.
     */
    private void updateTotals(){
        currentOrder.updateTotals();
        double subTotal = currentOrder.getSubTotal();
        double salesTax = currentOrder.getTax();
        double total = currentOrder.getTotal();

        DecimalFormat df = new DecimalFormat("0.00");
        String curSubtotalStr = df.format(subTotal);
        subTotalBox.setText("$ " + curSubtotalStr);
        String curSalesTaxStr = df.format(salesTax);
        salesTaxBox.setText("$ " + curSalesTaxStr);
        String curTotalStr = df.format(total);
        totalBox.setText("$ " + curTotalStr);
    }

}

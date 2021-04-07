package cafeGUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class StoreOrdersController {

    private StoreOrders storeOrders;
    private ArrayList<Order> storeOrdersList;
    private int numStoreOrders;
    private double total = 0;
    private final int NO_ORDERS_PLACED = 0;
    private final int FIRST_ORDER = 1;
    private final int OFF_SET = 1;
    private Order currentOrder;
    private ArrayList<MenuItem> orderList;

    @FXML
    private TextField totalBox;

    @FXML
    private ComboBox<Integer> orderListBox;

    @FXML
    private ListView<MenuItem> ordersList;

    /**
     * Cancels the order that is selected.
     * @param event waits for the selection and cancel to be pressed.
     */
    @FXML
    void cancelOrder(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like " +
                "to cancel this order?", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        if (ButtonType.YES.equals(result)) {

                if (storeOrders.remove(currentOrder)) {
                    storeOrdersList = storeOrders.returnOrder();
                    numStoreOrders = storeOrdersList.size() - OFF_SET;

                    ordersList.getItems().removeAll(ordersList.getItems());
                    orderListBox.getItems().removeAll(orderListBox.getItems());


                reloadOrders();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Cannot Cancel Order");
                a.setContentText("\tNo Orders Exist!");
                a.show();
            }
        }
    }

    /**
     * Initializes the Store Orders GUI.
     *
     */
    @FXML
    public void initialize() {
        ordersList.getItems().removeAll(ordersList.getItems());
        orderListBox.getItems().removeAll(orderListBox.getItems());
        DecimalFormat df = new DecimalFormat("0.00");
        String curTotalStr = df.format(total);
        totalBox.setText("$ " + curTotalStr);
    }

    /**
     * Initializes the Store Orders
     * @param storeOrders takes in the Store Orders.
     */
    void initOrder(StoreOrders storeOrders){
        this.storeOrders = storeOrders;
        storeOrdersList = storeOrders.returnOrder();
        numStoreOrders= storeOrdersList.size()-OFF_SET;
        reloadOrders();

    }

    /**
     * Exports the Orders to a file.
     * @param event waits for the export to be pressed.
     */
    @FXML
    void exportOrders(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile;

        Alert a = new Alert(AlertType.ERROR);

        if(numStoreOrders == 0){
            a.setTitle("Orders Could Not Be Exported");
            a.setContentText("\tThere are no orders to be exported!");
            a.show();
            return;
        }

        try{
            targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
            if(targetFile == null){
                a.setTitle("No File Selected");
                a.setContentText("\tPlease Select or Create a new File.");
                a.show();
                return;
            }

            FileWriter fileWriter = new FileWriter(targetFile, false);
            DecimalFormat df = new DecimalFormat("0.00");

            for(int index = 0; index < numStoreOrders; index++) {

                Order curOrder = storeOrdersList.get(index);
                String curSubTotalStr = df.format(curOrder.getSubTotal());
                String curTaxStr = df.format(curOrder.getTax());
                String curTotalStr = df.format(curOrder.getTotal());

                fileWriter.write("User Order Number: " + curOrder.getOrderNumber() +
                        " | Sub Total: $" + curSubTotalStr +
                        " | Sales Tax: $" + curTaxStr +
                        " | Total: $" + curTotalStr + "\n");

                ArrayList<MenuItem> tempOrder = curOrder.getCurrentOrder();
                int tempOrderSize = tempOrder.size();
                for(int j = 0; j < tempOrderSize; j++) {
                    fileWriter.write(tempOrder.get(j)+"\n");
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        }catch(Exception e){
            a.setTitle("Orders Could Not Be Exported");
            a.setContentText("\tAn error has occurred selecting the file.\n");
            a.show();
            return;
        }
    }

    /**
     * Sets the Order List in the Store Orders GUI.
     * @param event waits for the order to be pressed.
     */
    @FXML
    void setOrder(Event event) {
        try {
            int curOrder = orderListBox.getValue();
            int curOrderIndex = NO_ORDERS_PLACED;
            for(int i = 0; i < numStoreOrders; i++){
                if(curOrder == storeOrdersList.get(i).getOrderNumber()){
                    curOrderIndex = i;
                    break;
                }
            }
            setOrderList(curOrderIndex);
        }catch(Exception e){
            return;
        }
    }

    /**
     * Reloads the Orders in the Store Orders.
     */
    private void reloadOrders(){
        if(numStoreOrders > NO_ORDERS_PLACED ){
            for(int i = 0; i < numStoreOrders; i++) {
                orderListBox.getItems().addAll(storeOrdersList.get(i).getOrderNumber());
            }
            orderListBox.getSelectionModel().select(NO_ORDERS_PLACED);
            setOrderList(NO_ORDERS_PLACED);
        }else{
            currentOrder = null;
            updateTotals();
        }
    }

    /**
     * Sets the Order List in the Store Orders GUI.
     * @param orderNumber takes in the orderNumber.
     */
    private void setOrderList(int orderNumber){
        int orderIndex = (orderNumber);
        currentOrder = storeOrdersList.get(orderIndex);
        orderList = currentOrder.getCurrentOrder();
        updateTotals();

        ordersList.getItems().removeAll(ordersList.getItems());
        for(int i = 0; i < orderList.size(); i++) {
            ordersList.getItems().addAll(orderList.get(i));
        }
    }

    /**
     * Updates the Total based on the Store Orders.
     */
    private void updateTotals(){
        if(currentOrder != null) {
            currentOrder.updateTotals();
            total = currentOrder.getTotal();
        }else{
            total = NO_ORDERS_PLACED;
        }

        DecimalFormat df = new DecimalFormat("0.00");
        String curTotalStr = df.format(total);
        totalBox.setText("$ " + curTotalStr);
    }

}

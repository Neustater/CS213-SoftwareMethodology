package cafeGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

public class StoreOrdersController {
    @FXML
    private TextField total;

    @FXML
    private ComboBox<?> quantityList;

    @FXML
    private ListView<?> ordersList;

    @FXML
    void cancelOrder(ActionEvent event) {

    }

    @FXML
    void exportOrders(ActionEvent event) {

    }
}

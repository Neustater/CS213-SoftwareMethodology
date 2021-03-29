package cafeGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class UserOrdersController {
    @FXML
    private TextField total;

    @FXML
    private ListView<?> itemsList;

    @FXML
    private TextField salesTax;

    @FXML
    private TextField subTotal;

    @FXML
    void placeOrder(ActionEvent event) {

    }

    @FXML
    void removeSelectedItem(ActionEvent event) {

    }
}

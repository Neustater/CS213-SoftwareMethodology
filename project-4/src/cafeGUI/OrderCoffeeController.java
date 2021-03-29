package cafeGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class OrderCoffeeController {

    String[] sizes = {"Short","Tall","Grande","Venti"};
    Integer[] quantities = {1,2,3,4,5,6};

    @FXML
    private ComboBox<String> sizeList;

    @FXML
    private ComboBox<Integer> quantityList;

    @FXML
    private TextField subTotalBox;

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

    @FXML
    public void initialize() {
        sizeList.getItems().removeAll(sizeList.getItems());
        sizeList.getItems().addAll(sizes[0], sizes[1], sizes[2], sizes[3]);
        sizeList.getSelectionModel().select(sizes[0]);

        quantityList.getItems().removeAll(quantityList.getItems());
        quantityList.getItems().addAll(quantities[0], quantities[1],
                quantities[2], quantities[3], quantities[4], quantities[5]);
        quantityList.getSelectionModel().select(quantities[0]);
    }

    @FXML
    void addToOrderButton(ActionEvent event) {

    }

    @FXML
    void clickCaramel(ActionEvent event) {

    }

    @FXML
    void clickCream(ActionEvent event) {

    }

    @FXML
    void clickMilk(ActionEvent event) {

    }

    @FXML
    void clickSyrup(ActionEvent event) {

    }

    @FXML
    void clickWhipped(ActionEvent event) {

    }

}

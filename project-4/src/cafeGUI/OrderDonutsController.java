package cafeGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class OrderDonutsController {

    String[] cakes = {"Yeast","Cake","Donut Hole"};
    String[] flavors = {"Old Fashion","Chocolate Frosted","Cinnamon", "Glazed"};
    Integer[] quantities = {1,2,3,4,5,6};

    @FXML
    private ComboBox<String> cakesList;

    @FXML
    private ComboBox<Integer> quantityList;

    @FXML
    private ListView<String> flavorsList;

    @FXML
    private ListView<String> addedFlavorsList;

    @FXML
    private TextField subTotalBox;

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
        flavorsList.getItems().addAll(flavors[0],flavors[1],flavors[2],flavors[3]);
    }

    @FXML
    void addButton(ActionEvent event) {

    }

    @FXML
    void addToOrderButton(ActionEvent event) {

    }

    @FXML
    void removeButton(ActionEvent event) {

    }

}
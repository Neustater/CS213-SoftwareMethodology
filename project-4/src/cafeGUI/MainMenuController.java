package cafeGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    private Button donutButton;

    @FXML
    private Button coffeeButton;

    @FXML
    private ImageView userOrderButton;

    @FXML
    private Button storeOrderButton;

    @FXML
    void checkStoreOrder(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("StoreOrders.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 318, 401);
            Stage stage = new Stage();
            stage.setTitle("Store Orders");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void checkUserOrder(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("UserOrders.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 373, 404);
            Stage stage = new Stage();
            stage.setTitle("User Orders");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void orderCoffee(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("OrderCoffee.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 342, 337);
            Stage stage = new Stage();
            stage.setTitle("Order Coffee");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void orderDonuts(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("OrderDonuts.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 456, 363);
            Stage stage = new Stage();
            stage.setTitle("Order Donuts");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

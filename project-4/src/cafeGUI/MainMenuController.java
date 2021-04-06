package cafeGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuController {

    private Order currentOrder;
    private StoreOrders storeOrders;

    @FXML
    private Button donutButton;

    @FXML
    private Button coffeeButton;

    @FXML
    private ImageView userOrderButton;

    @FXML
    private Button storeOrderButton;

    /**
     * Initializes the GUI for the Main Menu.
     */
    @FXML
    public void initialize() {
        currentOrder = new Order();
        storeOrders = new StoreOrders();
        storeOrders.add(currentOrder);
    }

    /**
     * Loads the Store Orders GUI.
     */
    @FXML
    void checkStoreOrder(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("StoreOrders.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 318, 401);
            Stage stage = new Stage();
            stage.setTitle("Store Orders");
            stage.setScene(scene);
            stage.setResizable(false);

            StoreOrdersController controller = fxmlLoader.getController();
            controller.initOrder(storeOrders);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Loads the User's Current Order GUI.
     */
    @FXML
    void checkUserOrder(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("UserOrders.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 420, 404);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("User Orders");

            UserOrdersController controller = fxmlLoader.getController();
            controller.initOrder(currentOrder, storeOrders);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            int numStoreOrders = storeOrders.returnOrder().size()-1;
            currentOrder = storeOrders.returnOrder().get(numStoreOrders);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the Order Coffee GUI.
     */
    @FXML
    void orderCoffee(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("OrderCoffee.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 342, 337);
            Stage stage = new Stage();
            stage.setTitle("Order Coffee");
            stage.setScene(scene);
            stage.setResizable(false);

            OrderCoffeeController controller = fxmlLoader.getController();
            controller.initOrder(currentOrder);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the Order Donuts GUI.
     */
    @FXML
    void orderDonuts(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("OrderDonuts.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 549, 390);
            Stage stage = new Stage();
            stage.setTitle("Order Donuts");
            stage.setScene(scene);
            stage.setResizable(false);

            OrderDonutsController controller = fxmlLoader.getController();
            controller.initOrder(currentOrder);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

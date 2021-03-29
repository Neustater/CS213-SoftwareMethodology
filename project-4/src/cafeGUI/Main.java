package cafeGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 The Main class used to order form RU Cafe with the use of a GUI.
 Runs the Controller Class GUI.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public class Main extends Application {

    /**
     * Method to start the GUI in the Controller Class with the basic settings for display.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = (Parent)FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Scene scene = new Scene(root, 400, 475);
            primaryStage.setScene(scene);
            primaryStage.setTitle("RU Cafe");
            primaryStage.show();
            primaryStage.setMinWidth(400);
            primaryStage.setMinHeight(475);
            primaryStage.setMaxWidth(400);
            primaryStage.setMaxHeight(475);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Method to launch the GUI to run payroll simulation in a company.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

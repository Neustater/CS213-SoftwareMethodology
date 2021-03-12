package payrollGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 The Main class used interact with a company with the use of a GUI.
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
            BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("PayrollGUI.fxml"));
            Scene scene = new Scene(root, 625, 500);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Payroll Processor");
            primaryStage.show();
            primaryStage.setMinWidth(525);
            primaryStage.setMinHeight(500);
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

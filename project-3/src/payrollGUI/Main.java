package payrollGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("PayrollGUI.fxml"));
            Scene scene = new Scene(root, 600, 550);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Payroll Processor");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}

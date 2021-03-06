/**
 Module-info file which states the required packages
 from JavaFX for payrollGUI
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
module project {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;
    requires javafx.swt;

    opens payrollGUI;
}
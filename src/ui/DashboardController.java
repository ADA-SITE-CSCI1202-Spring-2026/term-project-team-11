package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label testLabel;

    @FXML
    public void initialize() {
        testLabel.setText("JavaFX is working");
    }
}
package ui;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.util.Random;

public class DashboardController {

    @FXML
    private Label teamLabel;

    @FXML
    private AnchorPane welcomePage;

    @FXML
    private Circle planet1, planet2, planet3, planet4;

    @FXML
    public void initialize() {
        teamLabel.setText("Team 11");
        welcomePage.setVisible(false);

        scatterPlanets();  // Scatter the planets when the app starts
        animatePlanets();  // Animate planets to create more dynamics

        // Add fade-in effect for the welcome page
        welcomePage.setOpacity(0);  // Start with 0 opacity (invisible)
    }

    @FXML
    public void handleWelcomeButton() {
        // Toggle the visibility of the welcome page with fade effect
        FadeTransition fade = new FadeTransition(Duration.seconds(1), welcomePage);
        fade.setToValue(welcomePage.isVisible() ? 0 : 1);
        fade.setOnFinished(e -> welcomePage.setVisible(!welcomePage.isVisible()));
        fade.play();
    }

    private void scatterPlanets() {
        Random random = new Random();
        planet1.setLayoutX(random.nextInt(500));  
        planet1.setLayoutY(random.nextInt(300));  

        planet2.setLayoutX(random.nextInt(500));  
        planet2.setLayoutY(random.nextInt(300));  

        planet3.setLayoutX(random.nextInt(500));  
        planet3.setLayoutY(random.nextInt(300));  

        planet4.setLayoutX(random.nextInt(500));  
        planet4.setLayoutY(random.nextInt(300));  
    }

    private void animatePlanets() {
        // Animate each planet to move across the screen
        TranslateTransition translate1 = new TranslateTransition(Duration.seconds(5), planet1);
        translate1.setByX(150);
        translate1.setByY(100);
        translate1.setCycleCount(TranslateTransition.INDEFINITE);
        translate1.setAutoReverse(true);
        translate1.play();

        TranslateTransition translate2 = new TranslateTransition(Duration.seconds(7), planet2);
        translate2.setByX(-200);
        translate2.setByY(120);
        translate2.setCycleCount(TranslateTransition.INDEFINITE);
        translate2.setAutoReverse(true);
        translate2.play();

        TranslateTransition translate3 = new TranslateTransition(Duration.seconds(6), planet3);
        translate3.setByX(100);
        translate3.setByY(-150);
        translate3.setCycleCount(TranslateTransition.INDEFINITE);
        translate3.setAutoReverse(true);
        translate3.play();

        TranslateTransition translate4 = new TranslateTransition(Duration.seconds(8), planet4);
        translate4.setByX(-120);
        translate4.setByY(-100);
        translate4.setCycleCount(TranslateTransition.INDEFINITE);
        translate4.setAutoReverse(true);
        translate4.play();
    }
}
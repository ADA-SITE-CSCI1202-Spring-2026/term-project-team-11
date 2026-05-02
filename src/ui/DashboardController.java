package ui;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.BaseState;
import model.ColonyTask;
import service.TaskGenerator;
import javafx.scene.text.Text;
import java.util.Random;  // <-- Add this import

public class DashboardController {

    @FXML
    private Label teamLabel, oxygenLabel, sparePartsLabel, rationsLabel, powerLabel, creditsLabel;

    @FXML
    private AnchorPane welcomePage, tasksPage;

    @FXML
    private Circle planet1, planet2, planet3, planet4;

    @FXML
    private Button executeNextTaskButton, synthesizeButton;

    @FXML
    private VBox taskListVBox;  // The VBox to hold the task list

    private BaseState baseState;
    private TaskGenerator taskGenerator;

    @FXML
    public void initialize() {
        teamLabel.setText("Team 11");
        welcomePage.setVisible(true);
        tasksPage.setVisible(false);  // Ensure tasksPage is hidden initially

        baseState = new BaseState();
        taskGenerator = new TaskGenerator();
        taskGenerator.startSimulation(); // Start task generation

        scatterPlanets();  // Scatter the planets when the app starts
        animatePlanets();  // Animate planets to create more dynamics

        // Add fade-in effect for the welcome page
        welcomePage.setOpacity(0);  // Start with 0 opacity (invisible)

        updateVitals(); // Initial update of the resource labels
    }

    @FXML
    public void handleWelcomeButton() {
        // Toggle the visibility of the welcome page with fade effect
        FadeTransition fade = new FadeTransition(Duration.seconds(1), welcomePage);
        fade.setToValue(welcomePage.isVisible() ? 0 : 1);
        fade.setOnFinished(e -> welcomePage.setVisible(!welcomePage.isVisible()));
        fade.play();
    }

    // New method to handle transition to the tasks page
    @FXML
    public void handleStartTasksButton() {
        // Transition from the welcomePage to tasksPage
        welcomePage.setVisible(false);
        tasksPage.setVisible(true);

        // Show the tasks when transitioning to the task page
        showTasks();
    }

    @FXML
    public void handleExecuteNextTaskButton() {
        // Handle execution of the next task
        if (!taskGenerator.getTaskQueue().isEmpty()) {
            ColonyTask task = taskGenerator.getTaskQueue().poll();
            boolean success = baseState.executeTask(task);

            if (success) {
                updateVitals(); // Update the resource labels after task execution
                showTasks();    // Update the task list UI
                System.out.println("Task completed: " + task.getTaskName());
            } else {
                System.out.println("Not enough resources to complete task: " + task.getTaskName());
            }
        }
    }

    @FXML
    public void handleSynthesizeButton() {
        // Logic to synthesize resources
        // Example: synthesize spare parts
        boolean success = baseState.buyResource(model.ResourceType.SPARE_PARTS, 10, 100);
        if (success) {
            updateVitals(); // Update after resource synthesis
            System.out.println("Synthesize Spare Parts: Success");
        } else {
            System.out.println("Not enough credits to synthesize Spare Parts");
        }
    }

    private void updateVitals() {
        // Update the vital statistics labels
        oxygenLabel.setText("Oxygen: " + baseState.getResource(model.ResourceType.OXYGEN));
        sparePartsLabel.setText("Spare Parts: " + baseState.getResource(model.ResourceType.SPARE_PARTS));
        rationsLabel.setText("Rations: " + baseState.getResource(model.ResourceType.RATIONS));
        powerLabel.setText("Power: " + baseState.getResource(model.ResourceType.POWER));
        creditsLabel.setText("Credits: " + baseState.getCredits());
    }

    private void showTasks() {
        // Clear existing tasks in the VBox
        taskListVBox.getChildren().clear();

        // Add tasks from the queue
        for (ColonyTask task : taskGenerator.getTaskQueue()) {
            Text taskText = new Text(task.getTaskName());
            taskText.setStyle("-fx-font-size: 18px; -fx-text-fill: #61A6D9;");
            taskListVBox.getChildren().add(taskText);
        }
    }

    private void scatterPlanets() {
        Random random = new Random();  // Initialize Random here
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
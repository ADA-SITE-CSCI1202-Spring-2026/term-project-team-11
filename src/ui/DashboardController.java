package ui;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.io.File;
import java.util.Queue;
import java.util.List;
import model.*;
import service.*;

public class DashboardController {
    
    // UI LAYERS
    @FXML private VBox introLayer;
    @FXML private VBox dashboardLayer;
    
    // ANIMATION ELEMENTS
    @FXML private Pane planetContainer;
    @FXML private Circle planet1; // Mars
    @FXML private Circle planet2; // Earth
    private RotateTransition orbitAnimation;
    private Timeline uiPoller; 
    
    // DASHBOARD ELEMENTS
    @FXML private ListView<ColonyTask> taskListView;
    @FXML private Label lblOxygen, lblCredits, lblParts, lblRations, lblPower;
    @FXML private ProgressBar pbPower; 
    @FXML private TextArea logTextArea;
    @FXML private ComboBox<ResourceType> comboRestock;

    // SYSTEM STATE
    private Queue<ColonyTask> queue; 
    private BaseState state = new BaseState();
    private TaskGenerator taskGenerator = new TaskGenerator(); 
    private SaveLoadManager saveManager = new SaveLoadManager();
    private List<IProcessor> modules = List.of(new EngineeringBay(), new MedicalWard());
    
    // TRACKING FLAGS
    private boolean isGameOver = false;
    private int lastQueueSize = 0; // Tracks background queue changes

    public void initialize() {
        try {
            // 1. SETUP DATA & GENERATOR FIRST
            comboRestock.getItems().setAll(ResourceType.values());
            taskGenerator.startSimulation(); 
            queue = taskGenerator.getTaskQueue(); 
            
            // 2. ATTEMPT TO RESTORE SAVED STATE & QUEUE
            File saveFile = new File("init.txt"); 
            if (saveFile.exists()) {
                saveManager.loadProgress(state, queue); 
                lastQueueSize = queue.size(); // Sync the tracker with loaded data
            }

            // 3. SAFE IMAGE LOADING
            try {
                planet1.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/ui/mars.png"))));
            } catch (Exception e) {
                planet1.setFill(Color.ORANGERED); 
            }
            
            try {
                planet2.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/ui/earth.png"))));
            } catch (Exception e) {
                planet2.setFill(Color.DEEPSKYBLUE); 
            }

            // 4. UI POLLER
            uiPoller = new Timeline(new KeyFrame(Duration.seconds(1), e -> refreshUI()));
            uiPoller.setCycleCount(Animation.INDEFINITE);
            uiPoller.play();
            
            refreshUI();
            
            // 5. SETUP ORBITAL ANIMATION
            orbitAnimation = new RotateTransition(Duration.seconds(8), planetContainer);
            orbitAnimation.setByAngle(360);
            orbitAnimation.setCycleCount(Animation.INDEFINITE);
            orbitAnimation.setInterpolator(Interpolator.LINEAR);
            orbitAnimation.play();
            
        } catch (Exception e) {
            System.err.println("Controller Init Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleStart() {
        orbitAnimation.stop();
        
        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(1.2), planet1);
        tt1.setToX(-800); 
        
        TranslateTransition tt2 = new TranslateTransition(Duration.seconds(1.2), planet2);
        tt2.setToX(800);  
        
        tt1.play();
        tt2.play();

        FadeTransition fadeOutIntro = new FadeTransition(Duration.seconds(1), introLayer);
        fadeOutIntro.setToValue(0.0);
        fadeOutIntro.setOnFinished(e -> {
            introLayer.setVisible(false);
            dashboardLayer.setVisible(true);
            
            FadeTransition fadeInDash = new FadeTransition(Duration.seconds(1.5), dashboardLayer);
            fadeInDash.setFromValue(0.0);
            fadeInDash.setToValue(1.0);
            fadeInDash.play();
            
            logTextArea.appendText("SYSTEM REBOOT: Ares Base Online...\n");
            if (new File("init.txt").exists()) {
                logTextArea.appendText("SYSTEM: Previous state and queue restored.\n");
            }
        });
        
        fadeOutIntro.play();
    }

    @FXML
    private void handleExecute() {
        if (isGameOver) return; 

        ColonyTask task = queue.peek(); 
        
        if (task == null) {
            logTextArea.appendText("COMMAND: No pending crises.\n");
            return;
        }

        if (state.executeTask(task)) {
            queue.poll(); 
            logTextArea.appendText("SUCCESS: " + task.getTaskName() + " resolved.\n");
            saveManager.saveProgress(state, queue); 
        } else {
            logTextArea.appendText("FAILURE: Missing resources for " + task.getTaskName() + "\n");
        }
        refreshUI();
    }

    @FXML
    private void handleSynthesize() {
        if (isGameOver) return; 

        ResourceType selected = comboRestock.getValue();
        if (selected != null) {
            if (state.buyResource(selected, 5, 50)) {
                logTextArea.appendText("REPLICATOR: Produced 5 " + selected + "\n");
                saveManager.saveProgress(state, queue); 
            } else {
                logTextArea.appendText("ERROR: Insufficient Credits!\n");
            }
            refreshUI();
        }
    }

    private void refreshUI() {
        if (isGameOver) return; 

        if (queue != null) {
            taskListView.getItems().setAll(queue); 
            
            // Auto-Save if TaskGenerator added new crises in the background
            if (queue.size() > lastQueueSize) {
                saveManager.saveProgress(state, queue);
            }
            lastQueueSize = queue.size(); 
        }
        
        lblOxygen.setText("Oxygen: " + state.getResource(ResourceType.OXYGEN) + "%");
        lblCredits.setText("Credits: " + state.getCredits() + " CR");
        lblParts.setText("Parts: " + state.getResource(ResourceType.SPARE_PARTS));
        lblRations.setText("Rations: " + state.getResource(ResourceType.RATIONS));
        
        int currentPower = state.getResource(ResourceType.POWER);
        lblPower.setText("Power: " + currentPower + " Units");
        pbPower.setProgress(currentPower / 100.0); 

        checkGameOverState();
    }

    private void checkGameOverState() {
        ColonyTask topTask = queue.peek();
        
        if (topTask != null) {
            boolean cannotAffordTask = !state.hasEnough(topTask.getResourceCosts());
            
            if (cannotAffordTask && state.getCredits() < 50) {
                isGameOver = true;
                if (uiPoller != null) uiPoller.stop();
                
                logTextArea.appendText("\n====================================\n");
                logTextArea.appendText("!!! FATAL ERROR !!!\n");
                logTextArea.appendText("INSUFFICIENT RESOURCES TO RESOLVE CRISIS.\n");
                logTextArea.appendText("REPLICATOR FUNDS DEPLETED.\n");
                logTextArea.appendText("ARES BASE STATION LOST.\n");
                logTextArea.appendText("--- GAME OVER ---\n");
                logTextArea.appendText("====================================\n");
                
                File saveFile = new File("init.txt");
                if (saveFile.exists()) saveFile.delete();
            }
        }
    }
}
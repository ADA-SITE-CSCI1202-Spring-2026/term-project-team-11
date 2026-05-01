package service;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Responsible for the Station Clock and random task generation.
 * Part of the feature/simulation-engine branch.
 */
public class TaskGenerator {
    private Queue<ColonyTask> taskQueue;
    private Timeline timeline;
    private Random random;
    private Consumer<String> logCallback;

    public TaskGenerator(Consumer<String> logCallback) {
        this.taskQueue = new LinkedList<>(); // Collections Requirement: LinkedList as a Queue
        this.random = new Random();
        this.logCallback = logCallback;
        setupTimeline();
    }

    private void setupTimeline() {
        // Triggers every 5 seconds to simulate an active loop
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
            generateTask();
        }));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void startSimulation() {
        if (timeline != null) {
            timeline.play();
            logCallback.accept("SYSTEM: Simulation Engine Online. Heartbeat detected.");
        }
    }

    private void generateTask() {
        int choice = random.nextInt(3);
        ColonyTask newTask;

        // Implementation of Polymorphism and Inheritance
        switch (choice) {
            case 0 -> newTask = new LifeSupportTask("Oxygen Leak", 5, 10);
            case 1 -> newTask = new EngineeringTask("Hull Breach", 8, 15);
            default -> newTask = new ResearchTask("Data Corruption", 2, 5);
        }

        taskQueue.add(newTask); // Push to Queue
        logCallback.accept("WARNING: New Task - " + newTask.getName());
    }

    public Queue<ColonyTask> getTaskQueue() {
        return taskQueue;
    }
}
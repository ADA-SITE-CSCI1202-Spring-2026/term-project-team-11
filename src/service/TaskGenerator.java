package service;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class TaskGenerator {
  
    private final Queue<ColonyTask> taskQueue = new LinkedList<>();
    private final Random random = new Random();

    public void startSimulation() {
        Timeline heartbeat = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            generateRandomTask();
        }));
        heartbeat.setCycleCount(Timeline.INDEFINITE);
        heartbeat.play();
    }

    public void generateRandomTask() {
        ColonyTask newTask; 
        int choice = random.nextInt(3);

        newTask = switch (choice) {
            case 0 -> new EngineeringTask();
            case 1 -> new LifeSupportTask();
            default -> new ResearchTask();
        };

        taskQueue.add(newTask);
        System.out.println("PROJECT LOG: New Task Added - " + newTask.getTaskName());
    }

    public Queue<ColonyTask> getTaskQueue() {
        return taskQueue;
    }
}
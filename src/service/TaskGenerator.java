package service;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class TaskGenerator {
  
    private Queue<Task> taskQueue = new LinkedList<>();
    private Random random = new Random();

    public void startSimulation() {
        Timeline heartbeat = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            generateMovlanTask();
        }));
        heartbeat.setCycleCount(Timeline.INDEFINITE);
        heartbeat.play();
    }

    private void generateMovlanTask() {
        Task newTask;
        int choice = random.nextInt(3);

        if (choice == 0) {
            newTask = new FixTask("Air Duct Clog", 10);
        } else if (choice == 1) {
            newTask = new EmergencyTask("Solar Flare", 20);
        } else {
            newTask = new ResearchTask("Soil Sample", 5);
        }

        taskQueue.add(newTask);
        System.out.println("NEW EMERGENCY: " + newTask.getName() + " added to Queue.");
    }

    public Queue<Task> getTaskQueue() {
        return taskQueue;
    }
}
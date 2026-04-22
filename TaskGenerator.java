package service;

import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import model.*;

public class TaskGenerator {
    private Queue<Task> queue;
    private Timer timer;
    private Random rand = new Random();
    private Consumer<String> logOutput; // This will send text to the GUI

    public TaskGenerator(Queue<Task> queue, Consumer<String> logOutput) {
        this.queue = queue;
        this.logOutput = logOutput;
    }

    public void start() {
        timer = new Timer();
        // Run every 4 seconds
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                createNewTask();
            }
        }, 2000, 4000); 
    }
    private void createNewTask() {
        
    }}
package service;

import java.io.*;
import java.util.Queue;
import java.util.Scanner;
import model.*;

public class SaveLoadManager {
    private static final String FILE_NAME = "init.txt";

    public void saveProgress(BaseState state, Queue<ColonyTask> taskQueue) {
        if (state == null || taskQueue == null) {
            System.err.println("SAVE ERROR: BaseState or Queue is null.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println("Credits:" + state.getCredits());
            writer.println("Oxygen:" + state.getResource(ResourceType.OXYGEN));
            writer.println("Spare Parts:" + state.getResource(ResourceType.SPARE_PARTS));
            writer.println("Rations:" + state.getResource(ResourceType.RATIONS));
            writer.println("Power:" + state.getResource(ResourceType.POWER));
            
            for (ColonyTask task : taskQueue) {
                writer.println("Task:" + task.getClass().getSimpleName());
            }

            System.out.println("LOG: Persistence successful. Data written to init.txt");
        } catch (IOException e) {
            System.err.println("CRITICAL ERROR: Could not write to save file.");
        }
    }

    public void loadProgress(BaseState state, Queue<ColonyTask> taskQueue) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("LOG: No init.txt found. Using default values.");
            return;
        }

        taskQueue.clear(); 

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(":")) {
                    String[] parts = line.split(":");
                    String label = parts[0].trim();
                    String value = parts[1].trim();

                    if (label.equals("Task")) {
                        switch (value) {
                            case "EngineeringTask":
                                taskQueue.add(new EngineeringTask());
                                break;
                            case "LifeSupportTask":
                                taskQueue.add(new LifeSupportTask());
                                break;
                            case "ResearchTask":
                                taskQueue.add(new ResearchTask());
                                break;
                        }
                    } else {
                        int numericValue = Integer.parseInt(value);
                        
                        switch (label) {
                            case "Credits": state.setCredits(numericValue); break;
                            case "Oxygen": state.setResource(ResourceType.OXYGEN, numericValue); break;
                            case "Spare Parts": state.setResource(ResourceType.SPARE_PARTS, numericValue); break;
                            case "Rations": state.setResource(ResourceType.RATIONS, numericValue); break;
                            case "Power": state.setResource(ResourceType.POWER, numericValue); break;
                        }
                    }
                }
            }
            System.out.println("LOG: State and Queue synchronized with init.txt");
        } catch (Exception e) {
            System.err.println("LOAD ERROR: Check init.txt formatting. " + e.getMessage());
        }
    }
}
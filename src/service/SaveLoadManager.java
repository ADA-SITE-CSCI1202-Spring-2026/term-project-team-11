package service;

import java.io.*;
import java.util.Scanner;
import model.BaseState;
import model.ResourceType;

public class SaveLoadManager {
    private static final String FILE_NAME = "init.txt";

    public void saveProgress(BaseState state) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println("Credits:" + state.getCredits());
            writer.println("Oxygen:" + state.getResource(ResourceType.OXYGEN));
            writer.println("Spare Parts:" + state.getResource(ResourceType.SPARE_PARTS));
            writer.println("Rations:" + state.getResource(ResourceType.RATIONS));
            writer.println("Power:" + state.getResource(ResourceType.POWER));
            
            System.out.println("LOG: Persistence successful. Data written to init.txt");
        } catch (IOException e) {
            System.err.println("CRITICAL ERROR: Could not write to save file.");
        }
    }

    public void loadProgress() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("LOG: No init.txt found. Using default values.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(":")) {
                    String[] parts = line.split(":");
                    String label = parts[0];
                    String value = parts[1];
                    System.out.println("Restoring " + label + " to " + value);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
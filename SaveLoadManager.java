package service;

import java.io.*;
import java.util.HashMap;
import java.util.Queue;

public class SaveLoadManager {
    private static final String FILE_NAME = "colony_data.dat";

    public static void saveState(HashMap<String, Integer> resources, int credits, Queue<ColonyTask> tasks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(resources);
            oos.writeInt(credits);
            oos.writeObject(tasks);
            System.out.println("Base state archived.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static Object[] loadState() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            HashMap<String, Integer> resources = (HashMap<String, Integer>) ois.readObject();
            int credits = ois.readInt();
            Queue<ColonyTask> tasks = (Queue<ColonyTask>) ois.readObject();
            return new Object[]{resources, credits, tasks};
        } catch (Exception e) {
            return null;
        }
    }
}
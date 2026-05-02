package model;

import java.util.HashMap;
import java.util.Map;


public class BaseState {

    private HashMap<ResourceType, Integer> resources;
    private int credits;

    public BaseState() {
        resources = new HashMap<>();
        resources.put(ResourceType.OXYGEN, 20);
        resources.put(ResourceType.SPARE_PARTS, 15);
        resources.put(ResourceType.RATIONS, 20);
        resources.put(ResourceType.POWER, 10);
        credits = 1000;
    }

    public int getResource(ResourceType type) {
        return resources.getOrDefault(type, 0);
    }

    public int getCredits() {
        return credits;
    }

    // checks if we have enough resources for the task
    public boolean hasEnough(Map<ResourceType, Integer> required) {
        for (Map.Entry<ResourceType, Integer> entry : required.entrySet()) {
            if (getResource(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public void consumeResources(Map<ResourceType, Integer> required) {
        for (Map.Entry<ResourceType, Integer> entry : required.entrySet()) {
            ResourceType type = entry.getKey();
            int newAmount = resources.get(type) - entry.getValue();
            // just making sure it doesn't go below 0
            resources.put(type, Math.max(0, newAmount));
        }
    }

    public void addCredits(int amount) {
        credits += amount;
    }

    public boolean deductCredits(int amount) {
        if (credits < amount) {
            return false;
        }
        credits -= amount;
        return true;
    }

    // called when user buys a resource from the replicator
    public boolean buyResource(ResourceType type, int amount, int cost) {
        if (!deductCredits(cost)) {
            return false;
        }
        resources.put(type, getResource(type) + amount);
        return true;
    }

    // main method for executing a task - checks resources first
    public boolean executeTask(ColonyTask task) {
        Map<ResourceType, Integer> cost = task.getResourceCosts();
        if (!hasEnough(cost)) {
            return false;
        }
        consumeResources(cost);
        addCredits(task.getCreditReward());
        return true;
    }

    public void printState() {
        System.out.println("Credits: " + credits);
        for (Map.Entry<ResourceType, Integer> entry : resources.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
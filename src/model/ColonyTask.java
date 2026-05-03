package model;

import java.util.Map;

public abstract class ColonyTask {
    private String taskName;
    private String desc;
    private Map<ResourceType, Integer> resCost;
    private int creditRew;

    public ColonyTask(String taskName, String desc, Map<ResourceType, Integer> resCost, int creditRew) {
        this.taskName = taskName;
        this.desc = desc;
        this.resCost = resCost;
        this.creditRew = creditRew;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return desc;
    }

    public Map<ResourceType, Integer> getResourceCosts() {
        return resCost;
    }

    public int getCreditReward() {
        return creditRew;
    }

    public abstract void execute();
    public abstract String getTaskCategory();
    @Override
    public String toString() {
        return String.format("[%s] %s | Costs: %s | Reward: %d Credits", 
                             taskName, desc, resCost, creditRew);
    }
}
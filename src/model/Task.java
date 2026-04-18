package model;


public abstract class Task {
    private String taskName;
    private String description;
    private int resourceCost;

    public Task(String taskName, String description, int resourceCost) {
        this.taskName = taskName;
        this.description = description;
        this.resourceCost = resourceCost;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public int getResourceCost() {
        return resourceCost;
    }

       public abstract void execute();

    @Override
    public String toString() {
        return String.format("[%s] %s (Cost: %d)", taskName, description, resourceCost);
    }
}
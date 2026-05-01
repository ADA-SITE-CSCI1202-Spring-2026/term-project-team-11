package model;

import java.util.Map;

public class ResearchTask extends Task {
    public ResearchTask() {
        super(
            "Solar Tech", 
            "Optimize energy output", 
            Map.of(ResourceType.RATIONS, 10, ResourceType.POWER, 15), 
            1000
        );
    }

    @Override
    public void execute() {
        System.out.println("ResearchTask: " + getTaskName() + " complete.");
    }

    @Override
    public String toString() {
        return "[RES] " + super.toString();
    }
}
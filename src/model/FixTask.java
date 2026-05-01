package model;

import java.util.Map;

public class FixTask extends Task {
    public FixTask() {
        super(
            "Air Filter", 
            "Replace clogged filters", 
            Map.of(ResourceType.SPARE_PARTS, 5, ResourceType.OXYGEN, 2), 
            100
        );
    }

    @Override
    public void execute() {
        System.out.println("FixTask: " + getTaskName() + " done.");
    }

    @Override
    public String toString() {
        return "[FIX] " + super.toString();
    }
}
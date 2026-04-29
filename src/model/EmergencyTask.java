package model;

import java.util.Map;

public class EmergencyTask extends Task {

    public EmergencyTask() {
        super(
            "Hull Breach", 
            "Major leak in Sector 7", 
            Map.of(ResourceType.OXYGEN, 20,ResourceType.SPARE_PARTS, 10,ResourceType.POWER, 5),  
            500
        );
    }

    @Override
    public void execute() {
        System.out.println("Emergency resolved: " + getTaskName());
    }

    @Override
    public String toString() {
        return "!!! " + super.toString();
    }
}
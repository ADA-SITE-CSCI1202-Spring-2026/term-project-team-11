package model;

import java.util.Map;

public class EngineeringTask extends ColonyTask {
    public EngineeringTask() {
        super("Solar Array Repair", "Realigning panels", 
              Map.of(ResourceType.SPARE_PARTS, 15, ResourceType.POWER, 2), 150);
    }

    @Override
    public void execute() {
        System.out.println("Engineering repair complete.");
    }
}
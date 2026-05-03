package model;

import java.util.Map;

public class LifeSupportTask extends ColonyTask {
    public LifeSupportTask() {
        super("Oxygen Leak", "Patching airlock", 
              Map.of(ResourceType.SPARE_PARTS, 10, ResourceType.OXYGEN, 5), 200);
    }
    @Override
    public String getTaskCategory() { return "MEDICAL"; }
    @Override
    public void execute() {
        System.out.println("Life Support stabilized.");
    }
}
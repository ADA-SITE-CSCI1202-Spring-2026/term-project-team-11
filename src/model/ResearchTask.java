package model;

import java.util.Map;

public class ResearchTask extends ColonyTask {
    public ResearchTask() {
        super("Soil Analysis", "Testing Martian regolith", 
              Map.of(ResourceType.POWER, 5, ResourceType.RATIONS, 2), 500);
    }
    @Override
    public String getTaskCategory() { return "RESEARCH"; }
    @Override
    public void execute() {
        System.out.println("Research data uploaded to Earth.");
    }
}
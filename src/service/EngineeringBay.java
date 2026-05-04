package service;

import model.BaseState;
import model.ColonyTask;

public class EngineeringBay implements IProcessor {
    
    @Override
    public boolean canProcess(ColonyTask task) {
        return "ENGINEERING".equals(task.getTaskCategory());
    }
   @Override
    public boolean process(ColonyTask task, BaseState state) {
        boolean success = state.executeTask(task);
        if (success) {
            System.out.println("EngineeringBay: completed - " + task.getTaskName()); 
        } else {
            System.out.println("EngineeringBay: not enough resources for - " + task.getTaskName());
        }
        return success;
    }
}
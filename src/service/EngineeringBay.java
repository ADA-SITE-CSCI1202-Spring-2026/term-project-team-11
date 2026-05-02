package service;

import model.BaseState;
import model.ColonyTask;
import model.EngineeringTask;

public class EngineeringBay implements IProcessor {

    @Override
    public boolean canProcess(ColonyTask task) {
        return task instanceof EngineeringTask;
    }

    @Override
    public void handleTask(ColonyTask task, BaseState state) {
        boolean success = state.executeTask(task);
        if (success) {
            System.out.println("EngineeringBay: completed - " + task.getTaskName());
        } else {
            System.out.println("EngineeringBay: not enough resources for - " + task.getTaskName());
        }
    }
}
package service;

import model.BaseState;
import model.ColonyTask;
import model.LifeSupportTask;
import model.ResearchTask;

public class MedicalWard implements IProcessor {

    @Override
    public boolean canProcess(ColonyTask task) {
        return task instanceof LifeSupportTask || task instanceof ResearchTask;
    }

    @Override
    public void handleTask(ColonyTask task, BaseState state) {
        boolean success = state.executeTask(task);
        if (success) {
            System.out.println("MedicalWard: completed - " + task.getTaskName());
        } else {
            System.out.println("MedicalWard: not enough resources for - " + task.getTaskName());
        }
    }
}
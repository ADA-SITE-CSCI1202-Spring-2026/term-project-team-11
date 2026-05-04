package service;

import model.BaseState;
import model.ColonyTask;

public class MedicalWard implements IProcessor {

   @Override
    public boolean canProcess(ColonyTask task) {
        return "MEDICAL".equals(task.getTaskCategory()) || "RESEARCH".equals(task.getTaskCategory());
    }

    @Override
    public boolean process(ColonyTask task, BaseState state) {
        boolean success = state.executeTask(task);
        if (success) {
            System.out.println("MedicalWard: completed - " + task.getTaskName());
        } else {
            System.out.println("MedicalWard: not enough resources for - " + task.getTaskName());
        }
        return success;
    }
}
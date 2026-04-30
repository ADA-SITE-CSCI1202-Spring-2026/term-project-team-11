package service;

import model.BaseState;
import model.ResearchTask;
import model.Task;

public class MedicalWard implements IBaseModule {

    // handles research tasks
    @Override
    public boolean canProcess(Task task) {
        return task instanceof ResearchTask;
    }

    @Override
    public void processTask(Task task, BaseState state) {
        boolean success = state.executeTask(task);
        if (success) {
            System.out.println("MedicalWard: completed task - " + task.getTaskName());
        } else {
            System.out.println("MedicalWard: not enough resources for - " + task.getTaskName());
        }
    }
}
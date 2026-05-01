package service;

import model.BaseState;
import model.EmergencyTask;
import model.FixTask;
import model.Task;

public class EngineeringBay implements IBaseModule {

    // handles emergency and fix tasks
    @Override
    public boolean canProcess(Task task) {
        return task instanceof EmergencyTask || task instanceof FixTask;
    }

    @Override
    public void processTask(Task task, BaseState state) {
        boolean success = state.executeTask(task);
        if (success) {
            System.out.println("EngineeringBay: completed task - " + task.getTaskName());
        } else {
            System.out.println("EngineeringBay: not enough resources for - " + task.getTaskName());
        }
    }
}
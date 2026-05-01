package service;

import model.BaseState;
import model.Task;

public interface IBaseModule {
    boolean canProcess(Task task);
    void processTask(Task task, BaseState state);
}
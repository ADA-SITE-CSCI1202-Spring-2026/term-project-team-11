package service;

import model.ColonyTask;
import model.BaseState;

public interface IProcessor {
    
    boolean canProcess(ColonyTask task);

    void handleTask(ColonyTask task, BaseState state);
}
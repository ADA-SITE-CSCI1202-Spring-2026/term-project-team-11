package service;

import model.ColonyTask;
import model.BaseState;

public interface IProcessor {
    
    boolean canProcess(ColonyTask task);

    boolean process(ColonyTask task, BaseState state);
}
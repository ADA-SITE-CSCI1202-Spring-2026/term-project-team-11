package service;

import java.io.Serializable;

public abstract class ColonyTask implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int partsRequired;
    private int reward;

    public ColonyTask(String name, int partsRequired, int reward) {
        this.name = name;
        this.partsRequired = partsRequired;
        this.reward = reward;
    }

    public String getName() { return name; }
    public int getPartsRequired() { return partsRequired; }
    public int getReward() { return reward; }
    
    @Override
    public String toString() {
        return String.format("[%s] Needs: %d parts", name, partsRequired);
    }
}
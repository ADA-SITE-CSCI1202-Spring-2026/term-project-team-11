package src.model;
public enum ResourceType {
    OXYGEN("Oxygen"),
    SPARE_PARTS("Spare Parts"),
    RATIONS("Rations"),
    POWER("Power");

    private final String displayName;

    ResourceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
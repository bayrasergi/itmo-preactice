package weather.model;

public enum SpecialMode {
    NONE("NONE"),
    COLD("COLD"),
    STRONG_WIND("STRONG_WIND"),
    SNOWFALL("SNOWFALL"),
    ICE("ICE"),
    DOWNPOUR("DOWNPOUR"),
    FLOOD("FLOOD"),
    FIRE("FIRE");

    private String name;

    SpecialMode(String name) {
        this.name = name;
    }

    public static SpecialMode getByName(String modeName) {
        switch (modeName.toUpperCase()) {
            case "COLD":
                return COLD;
            case "STRONG_WIND":
                return STRONG_WIND;
            case "SNOWFALL":
                return SNOWFALL;
            case "ICE":
                return ICE;
            case "DOWNPOUR":
                return DOWNPOUR;
            case "FLOOD":
                return FLOOD;
            case "FIRE":
                return FIRE;
            default:
                return NONE;
        }
    }
}

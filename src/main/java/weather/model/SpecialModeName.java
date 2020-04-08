package weather.model;

public enum SpecialModeName {
    NONE("NONE"),
    COLD("COLD"),
    STRONG_WIND("STRONG_WIND"),
    SNOWFALL("SNOWFALL"),
    ICE("ICE"),
    DOWNPOUR("DOWNPOUR"),
    FLOOD("FLOOD"),
    FIRE("FIRE");

    private String name;

    SpecialModeName(String name) {
        this.name = name;
    }

    public static SpecialModeName getByName(String modeName) {
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

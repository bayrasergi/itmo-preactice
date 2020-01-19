package weather.util;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonUtil {

    public static JsonNode get(JsonNode json, String node) {
        if (json == null) {
            return null;
        }
        return json.get(node);
    }

    public static JsonNode get(JsonNode json, String... nodes) {
        for (String node : nodes) {
            json = get(json, node);
        }
        return json;
    }

    public static String getString(JsonNode json, String... nodes) {
        json = get(json, nodes);
        if (json == null) {
            return null;
        }
        return json.asText();
    }

    public static double getDouble(JsonNode json, String... nodes) {
        json = get(json, nodes);
        if (json == null || json.isNull()) {
            return Double.MIN_VALUE;
        }
        return json.asDouble();
    }

    public static long getLong(JsonNode json, String... nodes) {
        json = get(json, nodes);
        if (json == null || json.isNull()) {
            return Long.MIN_VALUE;
        }
        return json.asLong();
    }

    public static int getInt(JsonNode json, String... nodes) {
        json = get(json, nodes);
        if (json == null || json.isNull()) {
            return Integer.MIN_VALUE;
        }
        return json.asInt();
    }
}

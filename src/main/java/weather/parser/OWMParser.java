package weather.parser;

import com.fasterxml.jackson.databind.JsonNode;
import weather.model.*;

import java.sql.Timestamp;
import java.time.Instant;

import static weather.util.JsonUtil.*;

public class OWMParser implements WeatherParser {

    @Override
    public Weather parseWeather(JsonNode json) {
        Weather weather = new Weather();
        weather.setLat(getDouble(json, "coord", "lat"));
        weather.setLon(getDouble(json, "coord", "lon"));
        JsonNode windNode = get(json, "wind");
        if (windNode != null) {
            double speed = getDouble(windNode, "speed");
            int deg = getInt(windNode, "deg");
            weather.setWindSpeed(speed >= 0 ? speed : 0);
            weather.setWindDirection(Math.max(deg, 0));
        }
        JsonNode rainNode = get(json, "rain");
        if (rainNode != null) {
            double rain3h = getDouble(rainNode, "3h");
            weather.setRainVolume(rain3h >= 0? rain3h : 0);
        }
        JsonNode snowNode = get(json, "snow");
        if (snowNode != null) {
            double snow3h = getDouble(snowNode, "3h");
            weather.setSnowVolume(snow3h >= 0? snow3h : 0);
        }
        weather.setTemperature(getDouble(json, "main", "temp"));
        JsonNode weatherInfo = get(json, "weather");
        if (weatherInfo != null && weatherInfo.isArray()) {
            JsonNode weatherType = weatherInfo.get(0);
            weather.setWeatherType(getString(weatherType, "main"));
        }
        weather.setDate(Timestamp.from(Instant.ofEpochMilli(getLong(json, "dt"))));
        return weather;
    }

}

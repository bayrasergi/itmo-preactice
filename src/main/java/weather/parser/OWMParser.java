package weather.parser;

import com.fasterxml.jackson.databind.JsonNode;
import weather.model.*;

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
            weather.setWindDegree(Math.max(deg, 0));
        }
        JsonNode rainNode = get(json, "rain");
        if (rainNode != null) {
            double rain1h = getDouble(rainNode, "1h");
            double rain3h = getDouble(rainNode, "3h");
            weather.setRainVol_1h(rain1h >= 0? rain1h : 0);
            weather.setRainVol_3h(rain3h >= 0? rain3h : 0);
        }
        JsonNode snowNode = get(json, "snow");
        if (snowNode != null) {
            double snow1h = getDouble(snowNode, "1h");
            double snow3h = getDouble(snowNode, "3h");
            weather.setSnowVol_1h(snow1h >= 0? snow1h : 0);
            weather.setSnowVol_3h(snow3h >= 0? snow3h : 0);
        }
        weather.setHumidity(getDouble(json, "main", "humidity"));
        weather.setTemp(getDouble(json, "main", "temp"));
        JsonNode weatherInfo = get(json, "weather");
        if (weatherInfo != null && weatherInfo.isArray()) {
            JsonNode weatherType = weatherInfo.get(0);
            weather.setType(getString(weatherType, "main"));
        }
        weather.setTimestamp(getLong(json, "dt"));
        return weather;
    }

}

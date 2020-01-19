package weather.parser;

import com.fasterxml.jackson.databind.JsonNode;
import weather.model.*;

import static weather.util.JsonUtil.*;

public class OWMParser implements WeatherParser {

    @Override
    public Weather parseWeather(JsonNode json) {
        Weather weather = new Weather();
        JsonNode coord = json.get("coord");
        Coord coordEntity = new Coord(coord.get("lat").asDouble(), coord.get("lon").asDouble());
        JsonNode wind = json.get("wind");
        Wind windEntity = null;
        if (wind != null && !wind.isNull()) {
            windEntity = new Wind(wind.get("speed").asDouble(), wind.get("deg").asDouble());
        }
        JsonNode rain = json.get("rain");
        Rain rainEntity = null;
        if (rain != null && !rain.isNull()) {
            rainEntity = new Rain(rain.get("1h").asDouble(), rain.get("3h").asDouble());
        }
        JsonNode snow = json.get("snow");
        Snow snowEntity = null;
        if (snow != null && !snow.isNull()) {
            snowEntity = new Snow(rain.get("1h").asDouble(), rain.get("3h").asDouble());
        }
        JsonNode main = json.get("main");
        weather.setHumidity(main.get("humidity").asDouble());
        weather.setTemp(main.get("temp").asDouble());
        JsonNode weatherInfo = json.get("weather");
        if (weatherInfo != null && !weatherInfo.isNull()) {
            JsonNode weatherType = weatherInfo.get(0).get("main");
            if (weatherType != null && !weatherType.isNull()) {
                weather.setType(weatherType.asText());
            }
        }
        weather.setTimestamp(json.get("dt").asLong());
        weather.setCoord(coordEntity);
        weather.setWind(windEntity);
        weather.setRain(rainEntity);
        weather.setSnow(snowEntity);
        return weather;
    }

}

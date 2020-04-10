package weather.parser;

import com.fasterxml.jackson.databind.JsonNode;
import weather.model.Weather;

public interface WeatherParser {

    Weather parseWeather(JsonNode json);
}

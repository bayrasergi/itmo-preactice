package weather;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import weather.model.Coord;
import weather.model.Weather;
import weather.parser.OWMParser;
import weather.parser.WeatherParser;

@Service
public class WeatherService {

    private static final String URL_WEATHER_BASE = "http://api.openweathermap.org/data/2.5/weather?units=metric&lang=ru&lat=%.3f&lon=%.3f&appid=%s";
    private String APP_KEY = "";

    private RestTemplate restTemplate;
    private WeatherParser weatherParser;

    public WeatherService() {
        restTemplate = new RestTemplate();
        weatherParser = new OWMParser();
    }

    public Weather getWeatherForCoord(Coord coord) {
        try {
            String url = String.format(URL_WEATHER_BASE, coord.getLat(), coord.getLon(), APP_KEY);
            JsonNode json = restTemplate.getForObject(url, JsonNode.class);
            return weatherParser.parseWeather(json);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}

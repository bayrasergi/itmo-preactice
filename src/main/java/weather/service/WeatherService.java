package weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import weather.model.Weather;
import weather.parser.OWMParser;
import weather.parser.WeatherParser;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private static final String URL_WEATHER = "http://api.openweathermap.org/data/2.5/%s?units=metric&lang=ru&lat=%.3f&lon=%.3f&appid=%s";
    private String APP_KEY = "";

    private RestTemplate restTemplate;
    private WeatherParser weatherParser;

    public WeatherService() {
        restTemplate = new RestTemplate();
        weatherParser = new OWMParser();
    }

    public Weather getCurrentWeatherForCoord(double lat, double lon) {
        try {
            String url = String.format(URL_WEATHER, "weather", lat, lon, APP_KEY);
            JsonNode json = restTemplate.getForObject(url, JsonNode.class);
            return weatherParser.parseWeather(json);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Weather> getForecastWeatherForCoord(double lat, double lon) {
        List<Weather> weathers = new ArrayList<>();
        try {
            String url = String.format(URL_WEATHER, "forecast", lat, lon, APP_KEY);
            JsonNode json = restTemplate.getForObject(url, JsonNode.class);
            System.out.println(json.toString());
            JsonNode list = json.get("list");
            if (list.isArray()) {
                for (JsonNode jsonWeather : list) {
                    weathers.add(weatherParser.parseWeather(jsonWeather));
                }
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return weathers;
    }
}

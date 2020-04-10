package weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import weather.model.Weather;
import weather.parser.OWMParser;
import weather.parser.WeatherParser;
import weather.respository.WeatherRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("file:weather/values.properties")
public class WeatherService {

    private static final String OWM_URL = "http://api.openweathermap.org/data/2.5/%s?units=metric&lang=ru&lat=%.3f&lon=%.3f&appid=%s";
    @Value("${owm.key}")
    private String OWM_KEY = "";

    private RestTemplate restTemplate;
    private WeatherParser weatherParser;

    private WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        restTemplate = new RestTemplate();
        weatherParser = new OWMParser();
        this.weatherRepository = weatherRepository;
    }

    public Weather save(Weather weather) {
        return weatherRepository.save(weather);
    }

    public Weather getCurrentWeatherForCoord(double lat, double lon) {
        try {
            String url = String.format(OWM_URL, "weather", lat, lon, OWM_KEY);
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
            String url = String.format(OWM_URL, "forecast", lat, lon, OWM_KEY);
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

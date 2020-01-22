package weather.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weather.model.SpecialMode;
import weather.model.Tower;
import weather.model.Weather;

import java.util.*;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SpecialModeService {

    private WeatherService weatherService;

    public Map<SpecialMode, List<Weather>> getPrediction(Tower tower) {
        List<Weather> forecastWeather = weatherService.getForecastWeatherForCoord(tower.getLat(), tower.getLon());
        Map<SpecialMode, List<Weather>> prediction = new HashMap<>();
        List<Weather> cold = checkCold(forecastWeather);
        List<Weather> strongWind = checkStrongWind(forecastWeather);
        List<Weather> downpour = checkDownpour(forecastWeather);
        List<Weather> snowfall = checkSnowFall(forecastWeather);
        List<Weather> fire = checkFire(forecastWeather);
        if (cold != null && !cold.isEmpty()) {
            prediction.put(SpecialMode.COLD, cold);
        }
        if (strongWind != null && !strongWind.isEmpty()) {
            prediction.put(SpecialMode.STRONG_WIND, strongWind);
        }
        if (snowfall != null && !snowfall.isEmpty()) {
            prediction.put(SpecialMode.SNOWFALL, snowfall);
        }
        if (downpour != null && !downpour.isEmpty()) {
            prediction.put(SpecialMode.DOWNPOUR, downpour);
        }
        if (fire != null && !fire.isEmpty()) {
            prediction.put(SpecialMode.FIRE, fire);
        }
        if (prediction.isEmpty()) {
            prediction.put(SpecialMode.NONE, Collections.emptyList());
        }
        return prediction;
    }

    private List<Weather> checkCold(List<Weather> forecastWeather) {
        if (forecastWeather.isEmpty()) {
            return null;
        }
        List<Weather> cold = new ArrayList<>();
        for (Weather weather : forecastWeather) {
            if (weather.getTemp() <= -25) {
                cold.add(weather);
            }
        }
        return cold;
    }

    private List<Weather> checkStrongWind(List<Weather> forecastWeather) {
        if (forecastWeather.isEmpty()) {
            return null;
        }
        List<Weather> strongWind = new ArrayList<>();
        for (Weather weather : forecastWeather) {
            if (weather.getWindSpeed() >= 25) {
                strongWind.add(weather);
            }
        }
        return strongWind;
    }

    private List<Weather> checkSnowFall(List<Weather> forecastWeather) {
        if (forecastWeather.isEmpty()) {
            return null;
        }
        List<Weather> snowWeather = new ArrayList<>();
        for (int i = 0; i < forecastWeather.size(); i++) {
            Weather weather = forecastWeather.get(i);
            int snowCount = 0;
            if (weather.getWindSpeed() >= 15 && weather.getType().equals("Snow")) {
                snowWeather.add(weather);
                ++snowCount;
                for (++i; i < forecastWeather.size(); i++) {
                    weather = forecastWeather.get(i);
                    if (!(weather.getWindSpeed() >= 15) || !weather.getType().equals("Snow")) {
                        if (snowCount < 3) {
                            snowWeather.clear();
                        }
                        break;
                    }
                    ++snowCount;
                    snowWeather.add(weather);
                }
            }
        }
        return snowWeather;
    }

    private List<Weather> checkDownpour(List<Weather> forecastWeather) {
        if (forecastWeather.isEmpty()) {
            return null;
        }
        List<Weather> downpour = new ArrayList<>();
        for (Weather weather : forecastWeather) {
            if (weather.getType().equalsIgnoreCase("Rain") && weather.getRainVol_3h() >= 3) {
                downpour.add(weather);
            }
        }
        return downpour;
    }

    private List<Weather> checkFire(List<Weather> forecastWeather) {
        if (forecastWeather.isEmpty()) {
            return null;
        }
        List<Weather> fire = new ArrayList<>();
        for (Weather weather : forecastWeather) {
            if (weather.getTemp() >= 40) {
                fire.add(weather);
            }
        }
        return fire;
    }
}

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import weather.Application;
import weather.model.SpecialMode;
import weather.model.Tower;
import weather.model.Weather;
import weather.service.SpecialModeService;
import weather.service.WeatherService;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class SpecialModeServiceTest {
    SpecialModeService modeService;

    @Mock
    WeatherService weatherService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        modeService = new SpecialModeService(weatherService);
    }

    @Test
    void testNormalTemp() {
        List<Weather> weathers = new ArrayList<>();
        Random random = new Random();
        getTimeStamps().forEach(t -> {
            weathers.add(getWeather("Cloud", t, 5 + random.nextInt(5), 1 + random.nextInt(3), 0, 0));
        });
        Mockito.when(weatherService.getForecastWeatherForCoord(1.0, 1.0)).thenReturn(weathers);
        Map<SpecialMode, List<Weather>> test = modeService.getPrediction(new Tower("test", 1.0, 1.0));
        Application.printResult(test);
        assertEquals(test.size(), 1);
        assertNotNull(test.get(SpecialMode.NONE));
    }

    @Test
    void testStrongWind() {
        List<Weather> weathers = new ArrayList<>();
        Random random = new Random();
        getTimeStamps().forEach(t -> {
            weathers.add(getWeather("Cloud", t, 5 + random.nextInt(5), 25 + random.nextInt(3), 0, 0));
        });
        Mockito.when(weatherService.getForecastWeatherForCoord(1.0, 1.0)).thenReturn(weathers);
        Map<SpecialMode, List<Weather>> test = modeService.getPrediction(new Tower("test", 1.0, 1.0));
        Application.printResult(test);
        assertEquals(test.size(), 1);
        List<Weather> wind = test.get(SpecialMode.STRONG_WIND);
        assertNotNull(wind);
        wind.forEach(w -> {
            assertTrue(w.getWindSpeed() >= 25);
        });
    }

    @Test
    void testSnowfall() {
        List<Weather> weathers = new ArrayList<>();
        Random random = new Random();
        getTimeStamps().forEach(t -> {
            weathers.add(getWeather("Snow", t, -10 + random.nextInt(5), 15 + random.nextInt(3), 0, 2 + random.nextDouble()));
        });
        Mockito.when(weatherService.getForecastWeatherForCoord(1.0, 1.0)).thenReturn(weathers);
        Map<SpecialMode, List<Weather>> test = modeService.getPrediction(new Tower("test", 1.0, 1.0));
        Application.printResult(test);
        assertEquals(test.size(), 1);
        List<Weather> snowfall = test.get(SpecialMode.SNOWFALL);
        assertNotNull(snowfall);
        snowfall.forEach(w -> {
            assertTrue(w.getWindSpeed() >= 15);
        });
    }

    @Test
    void testDownpour() {
        List<Weather> weathers = new ArrayList<>();
        Random random = new Random();
        getTimeStamps().forEach(t -> {
            weathers.add(getWeather("Rain", t, 10 + random.nextInt(5), 8 + random.nextInt(3), 3 + random.nextDouble(), 0));
        });
        Mockito.when(weatherService.getForecastWeatherForCoord(1.0, 1.0)).thenReturn(weathers);
        Map<SpecialMode, List<Weather>> test = modeService.getPrediction(new Tower("test", 1.0, 1.0));
        Application.printResult(test);
        assertEquals(test.size(), 1);
        List<Weather> downpour = test.get(SpecialMode.DOWNPOUR);
        assertNotNull(downpour);
        downpour.forEach(w -> {
            assertTrue(w.getRainVol_3h() >= 3);
        });
    }

    @Test
    void testFire() {
        List<Weather> weathers = new ArrayList<>();
        Random random = new Random();
        getTimeStamps().forEach(t -> {
            weathers.add(getWeather("Cloud", t, 40 + random.nextInt(5), 8 + random.nextInt(3), 0, 0));
        });
        Mockito.when(weatherService.getForecastWeatherForCoord(1.0, 1.0)).thenReturn(weathers);
        Map<SpecialMode, List<Weather>> test = modeService.getPrediction(new Tower("test", 1.0, 1.0));
        Application.printResult(test);
        assertEquals(test.size(), 1);
        List<Weather> fire = test.get(SpecialMode.FIRE);
        assertNotNull(fire);
        fire.forEach(w -> {
            assertTrue(w.getTemp() >= 40);
        });
    }

    private List<Timestamp> getTimeStamps() {
        List<Timestamp> timestamps = new ArrayList<>();
        Timestamp timestamp = Timestamp.from(Instant.now());
        timestamps.add(timestamp);
        for (int i = 0; i < 10; i++) {
            timestamp = Timestamp.from(Instant.ofEpochSecond(timestamp.toInstant().getEpochSecond() + 60 * 60 * 3));
            timestamps.add(timestamp);
        }
        return timestamps;
    }

    private Weather getWeather(String type, Timestamp timestamp, double temp, double windSpeed, double rainVol, double snowVol) {
        Weather weather = new Weather();
        weather.setLon(1.0);
        weather.setLat(1.0);
        weather.setType(type);
        weather.setTimestamp(timestamp.toInstant().getEpochSecond());
        weather.setTemp(temp);
        weather.setWindSpeed(windSpeed);
        weather.setWindDegree(0);
        weather.setRainVol_3h(rainVol);
        weather.setSnowVol_3h(snowVol);
        return weather;
    }
}

package weather;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import weather.model.SpecialMode;
import weather.model.Tower;
import weather.model.Weather;
import weather.service.SpecialModeService;
import weather.service.WeatherService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@ComponentScan("weather.service")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Application implements CommandLineRunner {

    private WeatherService weatherService;
    private SpecialModeService modeService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
//        List<Weather> forecastWeatherForCoord = weatherService.getForecastWeatherForCoord(51.94, 14.46);
//        forecastWeatherForCoord.forEach(System.out::println);
        Map<SpecialMode, List<Weather>> test = modeService.getPrediction(new Tower("Test", 51.94, 14.46));
        printResult(test);
    }

    public static void printResult(Map<SpecialMode, List<Weather>> test) {
        test.keySet().forEach(key -> {
            switch (key) {
                case COLD:
                    List<Weather> cold = test.get(key);
                    System.out.println("Ожидается падение температуры ниже -25 С!");
                    cold.forEach(w -> {
                        LocalDateTime time = w.getTime();
                        System.out.printf("\t%2d.%d %2d:00 температура %2.1f%n",
                                time.getDayOfMonth(), time.getMonthValue(), time.getHour(), w.getTemp());
                    });
                    break;
                case STRONG_WIND:
                    List<Weather> strongWind = test.get(key);
                    System.out.println("Ожидается усиление скорости ветра выше 25 м/с!");
                    strongWind.forEach(w -> {
                        LocalDateTime time = w.getTime();
                        System.out.printf("\t%2d.%02d %2d:00 скорость ветра %2.1f направление %d%n",
                                time.getDayOfMonth(), time.getMonthValue(), time.getHour(), w.getWindSpeed(), w.getWindDegree());
                    });
                    break;
                case SNOWFALL:
                    List<Weather> snowfall = test.get(key);
                    System.out.println("Ожидается сильный снегопад длительностью более 6 часов с силой ветра более 15 м/с!");
                    snowfall.forEach(w -> {
                        LocalDateTime time = w.getTime();
                        System.out.printf("\t%2d.%d %2d:00 количество осадков за 3 часа %2.2f скорость ветра %2.1f направление %d%n",
                                time.getDayOfMonth(), time.getMonthValue(), time.getHour(), w.getSnowVol_3h(), w.getWindSpeed(), w.getWindDegree());
                    });
                    break;
                case DOWNPOUR:
                    List<Weather> downpour = test.get(key);
                    System.out.println("Ожидается сильный дождь!");
                    downpour.forEach(w -> {
                        LocalDateTime time = w.getTime();
                        System.out.printf("\t%2d.%d %2d:00 количество осадков %2.1f%n",
                                time.getDayOfMonth(), time.getMonthValue(), time.getHour(), w.getRainVol_3h());
                    });
                    break;
                case FIRE:
                    List<Weather> fire = test.get(key);
                    System.out.println("Ожидается пожароопасная температура!");
                    fire.forEach(w -> {
                        LocalDateTime time = w.getTime();
                        System.out.printf("\t%2d.%d %2d:00 температура %2.1f%n",
                                time.getDayOfMonth(), time.getMonthValue(), time.getHour(), w.getTemp());
                    });
                    break;
                default:
                    System.out.println("Ввод особого режима не планируется");
                    break;
            }
        });
    }
}
package weather;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import weather.model.Coord;
import weather.model.Weather;

@SpringBootApplication
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Application implements CommandLineRunner {

    private WeatherService weatherService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        Coord coord = new Coord(51.94, 14.46);
        Weather weather = weatherService.getWeatherForCoord(coord);
        System.out.println(weather);
    }
}
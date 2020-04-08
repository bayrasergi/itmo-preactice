package weather;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import weather.model.SpecialModeName;
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
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
package weather.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import weather.model.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
}

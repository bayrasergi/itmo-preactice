package weather.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Weather")
public class Weather {

    @Id
    @GeneratedValue
    private long weatherId;

    @Column(name = "Lat")
    private double lat;

    @Column(name = "Lon")
    private double lon;

    @Column(name = "Temperature")
    private double temperature;

    @Column(name = "WeatherType")
    private String weatherType;

    @Column(name = "Date")
    private Timestamp date;

    @Column(name = "WindDirection")
    private int windDirection;

    @Column(name = "WindSpeed")
    private double windSpeed;

    @Column(name = "RainVolume")
    private double rainVolume;

    @Column(name = "SnowVolume")
    private double snowVolume;
}

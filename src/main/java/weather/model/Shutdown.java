package weather.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Shutdown")
public class Shutdown {

    @Id
    @GeneratedValue
    private long shutdownId;

    @ManyToOne
    @JoinColumn(name = "WeatherID")
    private Weather weather;

    @ManyToOne
    @JoinColumn(name = "PowerLineID")
    private PowerLine powerLine;
}

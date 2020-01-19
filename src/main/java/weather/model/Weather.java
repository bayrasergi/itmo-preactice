package weather.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    private Coord coord;
    private double temp;
    private double humidity;
    private String type;
    private long timestamp;
    private Wind wind;
    private Rain rain;
    private Snow snow;

    @Override
    public String toString() {
        return "Weather{" +
                "coord=" + coord +
                ", temp=" + temp +
                ", humidity=" + humidity +
                ", type='" + type + '\'' +
                ", timestamp=" + timestamp +
                ", wind=" + wind +
                ", rain=" + rain +
                ", snow=" + snow +
                '}';
    }
}

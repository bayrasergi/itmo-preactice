package weather.model;

import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    private double lat;
    private double lon;
    private double temp;
    private double humidity;
    private String type;
    private long timestamp;

    private int windDegree;
    private double windSpeed;

    private double rainVol_1h;
    private double rainVol_3h;

    private double snowVol_1h;
    private double snowVol_3h;

    public LocalDateTime getTime() {
        if (timestamp <= 0){
            return null;
        }
        return Timestamp.from(Instant.ofEpochSecond(this.timestamp)).toLocalDateTime();
    }

    @Override
    public String toString() {
        return "Weather{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", temp=" + temp +
                ", humidity=" + humidity +
                ", type='" + type + '\'' +
                ", timestamp=" + timestamp +
                ", time=" + getTime() +
                ", windDegree=" + windDegree +
                ", windSpeed=" + windSpeed +
                ", rainVol_1h=" + rainVol_1h +
                ", rainVol_3h=" + rainVol_3h +
                ", snowVol_1h=" + snowVol_1h +
                ", snowVol_3h=" + snowVol_3h +
                '}';
    }
}

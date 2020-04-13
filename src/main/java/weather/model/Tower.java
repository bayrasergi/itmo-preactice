package weather.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tower")
public class Tower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long towerId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Lat")
    private double lat;

    @Column(name = "Lon")
    private double lon;

    @ManyToMany(mappedBy = "towers")
    private List<PowerLine> powerLines;
}

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
@Table(name = "PowerLine")
public class PowerLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long powerLineId;

    @Column(name = "Name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "PowerLineTower",
            joinColumns = {@JoinColumn(name = "PowerLineID")},
            inverseJoinColumns = {@JoinColumn(name = "TowerID")}
    )
    private List<Tower> towers;
}

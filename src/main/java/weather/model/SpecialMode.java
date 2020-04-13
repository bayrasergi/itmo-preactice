package weather.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SpecialMode")
public class SpecialMode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long specialModeId;

    @Column(name = "Name")
    @Enumerated(EnumType.STRING)
    private SpecialModeName name;

    @Column(name = "DateEnable")
    private Timestamp dateEnable;

    @Column(name = "DateDisable")
    private Timestamp dateDisable;
}

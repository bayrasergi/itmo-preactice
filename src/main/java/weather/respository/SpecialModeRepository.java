package weather.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import weather.model.SpecialMode;

@Repository
public interface SpecialModeRepository extends JpaRepository<SpecialMode, Long> {
}

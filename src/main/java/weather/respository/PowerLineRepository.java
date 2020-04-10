package weather.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import weather.model.PowerLine;

@Repository
public interface PowerLineRepository extends JpaRepository<PowerLine, Long> {
}

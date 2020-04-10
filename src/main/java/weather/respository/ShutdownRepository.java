package weather.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import weather.model.Shutdown;

@Repository
public interface ShutdownRepository extends JpaRepository<Shutdown, Long> {
}

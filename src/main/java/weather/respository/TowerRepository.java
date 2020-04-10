package weather.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import weather.model.Tower;

@Repository
public interface TowerRepository extends JpaRepository<Tower, Long> {
}

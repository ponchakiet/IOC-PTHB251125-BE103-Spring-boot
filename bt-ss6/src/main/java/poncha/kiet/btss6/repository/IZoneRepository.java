package poncha.kiet.btss6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poncha.kiet.btss6.entity.Zone;

@Repository
public interface IZoneRepository extends JpaRepository<Zone, Long> {
}

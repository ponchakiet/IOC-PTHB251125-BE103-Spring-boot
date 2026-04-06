package poncha.kiet.btss6.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import poncha.kiet.btss6.dto.VehicleResponse;
import poncha.kiet.btss6.entity.Vehicle;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle,Long> {
    @Query(value = """
            SELECT new poncha.kiet.btss6.dto.VehicleResponse(v.id, v.licensePlate, v.color, v.type) 
            FROM Vehicle v 
            where :keyword IS NULL OR LOWER(v.licensePlate) LIKE LOWER(concat('%', :keyword, '%')) 
            """)
    public Page<VehicleResponse> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);
}

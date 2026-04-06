package poncha.kiet.btss6.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import poncha.kiet.btss6.entity.VehicleEnum;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehicleCreateRequest {
    private String licensePlate;
    private String color;
    private VehicleEnum type;
}

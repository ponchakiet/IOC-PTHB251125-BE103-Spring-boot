package poncha.kiet.btss6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import poncha.kiet.btss6.entity.VehicleEnum;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehicleResponse {
    private Long id;
    private String licensePlate;
    private String color;
    private VehicleEnum vehicleType;
}

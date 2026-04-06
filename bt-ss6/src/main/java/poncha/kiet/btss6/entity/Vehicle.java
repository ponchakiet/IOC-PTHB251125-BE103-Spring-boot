package poncha.kiet.btss6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "license_plate", nullable = false)
    private String licensePlate;
    private String color;
    @Column(name = "vehicle_type")
    private VehicleEnum type;

    @OneToMany(mappedBy = "vehicle")
    private List<ParkingTicket> parkingTickets;
}

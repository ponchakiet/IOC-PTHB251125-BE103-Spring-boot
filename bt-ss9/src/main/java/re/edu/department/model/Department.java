package re.edu.department.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "departments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(length = 100)
    private String description;
}

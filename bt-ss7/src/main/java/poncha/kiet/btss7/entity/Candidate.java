package poncha.kiet.btss7.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "candidates")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String email;
    private Integer age;
    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;
    private String address;
    @Column(length = 200)
    private String bio;
    @Column(length = 10)
    private String phone;
}

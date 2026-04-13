package re.edu.department.model;

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
   @Column(nullable = false)
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(name = "cv_url")
    private String cvUrl;
}

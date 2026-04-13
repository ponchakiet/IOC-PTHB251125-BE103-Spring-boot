package re.edu.department.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(nullable = false, unique = true)
    private String email;
    private String phone;
    private Long salary;
    @Column(name = "avatar_url")
    private String avatarUrl;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}

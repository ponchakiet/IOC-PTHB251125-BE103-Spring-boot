package re.edu.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "readers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String fullName;

    private String phoneNumber;

    private String address;

    private String avatar; // URL từ Cloudinary
}

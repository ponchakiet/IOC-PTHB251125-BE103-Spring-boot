package poncha.kiet.btss2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private int id;
    private String username;
    private String email;
    private String role;
}
